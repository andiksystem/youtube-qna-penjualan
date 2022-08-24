/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andik.penjualan.service;

import com.andik.penjualan.entity.JenisPelanggan;
import com.andik.penjualan.entity.Pelanggan;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author andik
 */
@Stateless
public class PelangganService {
    
    @PersistenceContext(unitName = "penjualan-pu")
    private EntityManager em;
    
    public List<Pelanggan> findAll() {
        return em.createQuery("SELECT p FROM Pelanggan p ORDER BY p.nama ASC")
                .getResultList();
    }
    
    @Transactional(rollbackOn = Exception.class)
    public Pelanggan create(Pelanggan pelanggan) throws Exception {
        em.persist(pelanggan);
        return pelanggan;
    }
    
    public JenisPelanggan findJenisPelangganByNama(String nama) {
        try {
            return  em.createQuery("SELECT j FROM JenisPelanggan j WHERE j.nama = :nama", JenisPelanggan.class)
                    .setParameter("nama", nama)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public JenisPelanggan getOrCreateJenisPelanggan(String namaJenisPelanggan) {
        JenisPelanggan jenisPelanggan = findJenisPelangganByNama(namaJenisPelanggan);
        if (jenisPelanggan == null) {
            jenisPelanggan = new JenisPelanggan(String.format("%016d", System.nanoTime()));
            jenisPelanggan.setNama(namaJenisPelanggan);
            em.persist(jenisPelanggan);
        }
        
        return jenisPelanggan;
    }
    
    
}
