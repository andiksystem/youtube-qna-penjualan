/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andik.penjualan.controller;

import com.andik.penjualan.entity.Penjualan;
import com.andik.penjualan.service.PenjualanService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author andik
 */
@Named
@ViewScoped
public class PenjualanList implements Serializable {
    
    @EJB
    private PenjualanService penjualanService;
    
    private List<Penjualan> penjualans;
    
    private Date tanggal1;
    private Date tanggal2;
    private String filterText;
    private int page;
    private int limit;
    
    @PostConstruct
    public void init() {
        tanggal1 = new Date();
        tanggal2 = new Date();
        filterText = "";
        page = 0;
        limit = 25;
        load();
    }
    
    public void load() {
        penjualans = penjualanService.findRange(tanggal1, tanggal2, filterText, page, limit);
    }
    
    public void loadMore() {
        page++;
        penjualans.addAll(penjualanService.findRange(tanggal1, tanggal2, filterText, page, limit));
    }
    
    public void search() {
        page = 0;
        load();
    }

    public Date getTanggal1() {
        return tanggal1;
    }

    public void setTanggal1(Date tanggal1) {
        this.tanggal1 = tanggal1;
    }

    public Date getTanggal2() {
        return tanggal2;
    }

    public void setTanggal2(Date tanggal2) {
        this.tanggal2 = tanggal2;
    }

    public String getFilterText() {
        return filterText;
    }

    public void setFilterText(String filterText) {
        this.filterText = filterText;
    }

    public List<Penjualan> getPenjualans() {
        return penjualans;
    }
    
}
