/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andik.penjualan.controller;

import com.andik.penjualan.entity.Pelanggan;
import com.andik.penjualan.entity.Penjualan;
import com.andik.penjualan.entity.PenjualanProduk;
import com.andik.penjualan.entity.Produk;
import com.andik.penjualan.service.PenjualanService;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author andik
 */
@Named
@ViewScoped
public class PenjualanCreate implements Serializable {

    @EJB
    private PenjualanService penjualanService;

    private Penjualan penjualan;
    private List<PenjualanProduk> penjualanProduks;
    private Produk selectedProduk;

    @PostConstruct
    public void init() {
        penjualan = new Penjualan(UUID.randomUUID().toString());
        penjualanProduks = new ArrayList<>();
        hitungTotal();
    }

    public void onPelangganSelect(SelectEvent<Pelanggan> event) {
        Pelanggan pelanggan = event.getObject();
        penjualan.setPelanggan(pelanggan);
        for (PenjualanProduk p : penjualanProduks) {
            p.setHarga(penjualanService.getHargaProduk(p.getProduk(), pelanggan));
            p.setJumlah(p.getKuantitas().multiply(p.getHarga()));
            penjualanProduks.set(penjualanProduks.indexOf(p), p);
        }
    }

    public void onProdukSelect(SelectEvent<Produk> event) {
        Produk produk = event.getObject();
        PenjualanProduk p = new PenjualanProduk(UUID.randomUUID().toString());
        p.setProduk(produk);
        p.setDeskripsi(produk.getNama());
        p.setKuantitas(BigDecimal.ONE);
        p.setHarga(penjualanService.getHargaProduk(produk, penjualan.getPelanggan()));
        p.setJumlah(p.getKuantitas().multiply(p.getHarga()));
        penjualanProduks.add(p);
        hitungTotal();
        selectedProduk = null;
    }

    public void updatePenjualanProduk(PenjualanProduk p) {
        p.setJumlah(p.getKuantitas().multiply(p.getHarga()));
        penjualanProduks.set(penjualanProduks.indexOf(p), p);
        hitungTotal();
    }

    public void deletePenjualanProduk(PenjualanProduk p) {
        penjualanProduks.remove(p);
        hitungTotal();
    }

    public List<Pelanggan> completePelanggan(String text) {
        return penjualanService.findPelangganByNama(text, 25);
    }

    public List<Produk> completeProduk(String text) {
        return penjualanService.findProdukByNama(text, 25);
    }

    public void hitungTotal() {
        BigDecimal jumlah = BigDecimal.ZERO;
        for (PenjualanProduk penjualanProduk : penjualanProduks) {
            jumlah = jumlah.add(penjualanProduk.getJumlah());
        }
        penjualan.setJumlah(jumlah);
    }

    public Penjualan getPenjualan() {
        return penjualan;
    }

    public List<PenjualanProduk> getPenjualanProduks() {
        return penjualanProduks;
    }

    public Produk getSelectedProduk() {
        return selectedProduk;
    }

    public void setSelectedProduk(Produk selectedProduk) {
        this.selectedProduk = selectedProduk;
    }
    
    public void save() {
        try {
            penjualanService.createPenjualan(penjualan, penjualanProduks);
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.redirect(externalContext.getRequestContextPath() + "/faces/detail.xhtml?id=" + penjualan.getId());
        } catch (IOException ex) {
            Logger.getLogger(PenjualanCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
