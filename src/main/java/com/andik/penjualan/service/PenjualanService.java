/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andik.penjualan.service;

import com.andik.penjualan.entity.Pelanggan;
import com.andik.penjualan.entity.Penjualan;
import com.andik.penjualan.entity.PenjualanProduk;
import com.andik.penjualan.entity.Produk;
import com.andik.penjualan.entity.ProdukHarga;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

/**
 *
 * @author andik
 */
@Stateless
public class PenjualanService {

    @PersistenceContext(unitName = "penjualan-pu")
    private EntityManager em;
    
    public List<Penjualan> findRange(Date tanggal1, Date tanggal2, String filterText, int page, int limit) {
        return em.createQuery("SELECT p FROM Penjualan p WHERE p.tanggal >= :tanggal1 AND p.tanggal <= :tanggal2 AND LOWER(p.nomor) LIKE :nomor ORDER BY p.waktuDibuat DESC")
                .setParameter("tanggal1", tanggal1, TemporalType.DATE)
                .setParameter("tanggal2", tanggal2, TemporalType.DATE)
                .setParameter("nomor", "%" + filterText.toLowerCase() + "%")
                .setMaxResults(page * limit)
                .setFirstResult(page)
                .getResultList();
    }
    
    public Penjualan findPenjualanById(String id) {
        return em.find(Penjualan.class, id);
    }
    
    public List<Pelanggan> findPelangganByNama(String nama, int limit) {
        return em.createQuery("SELECT p FROM Pelanggan p WHERE LOWER(p.nama) LIKE :nama ORDER BY p.nama ASC")
                .setParameter("nama", "%" + nama.toLowerCase() + "%")
                .setMaxResults(limit)
                .getResultList();
    }
    
    public List<Produk> findProdukByNama(String nama, int limit) {
        return em.createQuery("SELECT p FROM Produk p WHERE LOWER(p.nama) LIKE :nama ORDER BY p.nama ASC")
                .setParameter("nama", "%" + nama.toLowerCase() + "%")
                .setMaxResults(limit)
                .getResultList();
    }
    
    public BigDecimal getHargaProduk(Produk produk, Pelanggan pelanggan) {
        if (pelanggan == null) {
            return produk.getDefaultHarga();
        }
        
        if (pelanggan.getJenisPelanggan() == null) {
            return produk.getDefaultHarga();
        }
        
        try {
            ProdukHarga produkHarga = em.createQuery("SELECT p FROM ProdukHarga p WHERE p.produk = :produk AND p.jenisPelanggan = :jenisPelanggan", ProdukHarga.class)
                    .setParameter("produk", produk)
                    .setParameter("jenisPelanggan", pelanggan.getJenisPelanggan())
                    .getSingleResult();
            return produkHarga.getHarga();
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }
    
    public void createPenjualan(Penjualan penjualan, List<PenjualanProduk> penjualanProduks) {
        penjualan.setNomor(generateNomorPenjualan());
        penjualan.setTanggal(new Date());
        penjualan.setWaktuDibuat(new Date());
        em.persist(penjualan);
        
        for (PenjualanProduk penjualanProduk : penjualanProduks) {
            penjualanProduk.setPenjualan(penjualan);
            em.persist(penjualanProduk);
        }
    }
    
    public List<PenjualanProduk> findPenjualanProdukByPenjualan(Penjualan penjualan) {
        return em.createQuery("SELECT p FROM PenjualanProduk p WHERE p.penjualan = :penjualan")
                .setParameter("penjualan", penjualan)
                .getResultList();
    }
    
    public void deletePenjualan(Penjualan penjualan) {
        List<PenjualanProduk> list = findPenjualanProdukByPenjualan(penjualan);
        for (PenjualanProduk penjualanProduk : list) {
            em.remove(em.merge(penjualanProduk));
        }
        em.remove(em.merge(penjualan));
    }

    private String generateNomorPenjualan() {
        return String.format("%016d", System.nanoTime());
    }
    
    
}
