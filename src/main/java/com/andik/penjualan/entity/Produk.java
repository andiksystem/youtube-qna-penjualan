/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andik.penjualan.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author andik
 */
@Entity
@Table(name = "produk")
@NamedQueries({
    @NamedQuery(name = "Produk.findAll", query = "SELECT p FROM Produk p")})
public class Produk implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "id")
    private String id;
    @Size(max = 255)
    @Column(name = "nama")
    private String nama;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "default_harga")
    private BigDecimal defaultHarga;
    @OneToMany(mappedBy = "produk")
    private Collection<ProdukHarga> produkHargaCollection;
    @OneToMany(mappedBy = "produk")
    private Collection<PenjualanProduk> penjualanProdukCollection;

    public Produk() {
    }

    public Produk(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public BigDecimal getDefaultHarga() {
        return defaultHarga;
    }

    public void setDefaultHarga(BigDecimal defaultHarga) {
        this.defaultHarga = defaultHarga;
    }

    public Collection<ProdukHarga> getProdukHargaCollection() {
        return produkHargaCollection;
    }

    public void setProdukHargaCollection(Collection<ProdukHarga> produkHargaCollection) {
        this.produkHargaCollection = produkHargaCollection;
    }

    public Collection<PenjualanProduk> getPenjualanProdukCollection() {
        return penjualanProdukCollection;
    }

    public void setPenjualanProdukCollection(Collection<PenjualanProduk> penjualanProdukCollection) {
        this.penjualanProdukCollection = penjualanProdukCollection;
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
        if (!(object instanceof Produk)) {
            return false;
        }
        Produk other = (Produk) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.andik.penjualan.entity.Produk[ id=" + id + " ]";
    }
    
}
