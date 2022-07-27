/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andik.penjualan.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author andik
 */
@Entity
@Table(name = "penjualan_produk")
@NamedQueries({
    @NamedQuery(name = "PenjualanProduk.findAll", query = "SELECT p FROM PenjualanProduk p")})
public class PenjualanProduk implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "id")
    private String id;
    @Size(max = 255)
    @Column(name = "deskripsi")
    private String deskripsi;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "kuantitas")
    private BigDecimal kuantitas;
    @Column(name = "harga")
    private BigDecimal harga;
    @Column(name = "jumlah")
    private BigDecimal jumlah;
    @JoinColumn(name = "penjualan_id", referencedColumnName = "id")
    @ManyToOne
    private Penjualan penjualan;
    @JoinColumn(name = "produk_id", referencedColumnName = "id")
    @ManyToOne
    private Produk produk;

    public PenjualanProduk() {
    }

    public PenjualanProduk(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public BigDecimal getKuantitas() {
        return kuantitas;
    }

    public void setKuantitas(BigDecimal kuantitas) {
        this.kuantitas = kuantitas;
    }

    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }

    public Penjualan getPenjualan() {
        return penjualan;
    }

    public void setPenjualan(Penjualan penjualan) {
        this.penjualan = penjualan;
    }

    public Produk getProduk() {
        return produk;
    }

    public void setProduk(Produk produk) {
        this.produk = produk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PenjualanProduk)) {
            return false;
        }
        PenjualanProduk other = (PenjualanProduk) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.andik.penjualan.entity.PenjualanProduk[ id=" + id + " ]";
    }
    
}
