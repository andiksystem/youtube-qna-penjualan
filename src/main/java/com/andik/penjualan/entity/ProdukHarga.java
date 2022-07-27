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
@Table(name = "produk_harga")
@NamedQueries({
    @NamedQuery(name = "ProdukHarga.findAll", query = "SELECT p FROM ProdukHarga p")})
public class ProdukHarga implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "id")
    private String id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "harga")
    private BigDecimal harga;
    @JoinColumn(name = "jenis_pelanggan_id", referencedColumnName = "id")
    @ManyToOne
    private JenisPelanggan jenisPelanggan;
    @JoinColumn(name = "produk_id", referencedColumnName = "id")
    @ManyToOne
    private Produk produk;

    public ProdukHarga() {
    }

    public ProdukHarga(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }

    public JenisPelanggan getJenisPelanggan() {
        return jenisPelanggan;
    }

    public void setJenisPelanggan(JenisPelanggan jenisPelanggan) {
        this.jenisPelanggan = jenisPelanggan;
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
        if (!(object instanceof ProdukHarga)) {
            return false;
        }
        ProdukHarga other = (ProdukHarga) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.andik.penjualan.entity.ProdukHarga[ id=" + id + " ]";
    }
    
}
