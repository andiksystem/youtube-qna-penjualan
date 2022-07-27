/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andik.penjualan.controller;

import com.andik.penjualan.entity.Penjualan;
import com.andik.penjualan.entity.PenjualanProduk;
import com.andik.penjualan.service.PenjualanService;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author andik
 */
@Named
@ViewScoped
public class PenjualanDetail implements Serializable {
    
    private Penjualan penjualan;
    private List<PenjualanProduk> penjualanProduks;
    
    @EJB
    private PenjualanService penjualanService;
    
    @PostConstruct
    public void init() {
        String id = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap().get("id");
        penjualan = penjualanService.findPenjualanById(id);
        penjualanProduks = penjualanService.findPenjualanProdukByPenjualan(penjualan);
    }

    public Penjualan getPenjualan() {
        return penjualan;
    }

    public List<PenjualanProduk> getPenjualanProduks() {
        return penjualanProduks;
    }
    
    public void hapusPenjualan() {
        try {
            penjualanService.deletePenjualan(penjualan);
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.redirect(externalContext.getRequestContextPath() + "/faces/index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(PenjualanDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
