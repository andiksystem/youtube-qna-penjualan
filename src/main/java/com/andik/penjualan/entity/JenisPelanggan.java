/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andik.penjualan.entity;

import java.io.Serializable;
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
@Table(name = "jenis_pelanggan")
@NamedQueries({
    @NamedQuery(name = "JenisPelanggan.findAll", query = "SELECT j FROM JenisPelanggan j")})
public class JenisPelanggan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "id")
    private String id;
    @Size(max = 255)
    @Column(name = "nama")
    private String nama;
    @OneToMany(mappedBy = "jenisPelanggan")
    private Collection<ProdukHarga> produkHargaCollection;
    @OneToMany(mappedBy = "jenisPelanggan")
    private Collection<Pelanggan> pelangganCollection;

    public JenisPelanggan() {
    }

    public JenisPelanggan(String id) {
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

    public Collection<ProdukHarga> getProdukHargaCollection() {
        return produkHargaCollection;
    }

    public void setProdukHargaCollection(Collection<ProdukHarga> produkHargaCollection) {
        this.produkHargaCollection = produkHargaCollection;
    }

    public Collection<Pelanggan> getPelangganCollection() {
        return pelangganCollection;
    }

    public void setPelangganCollection(Collection<Pelanggan> pelangganCollection) {
        this.pelangganCollection = pelangganCollection;
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
        if (!(object instanceof JenisPelanggan)) {
            return false;
        }
        JenisPelanggan other = (JenisPelanggan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.andik.penjualan.entity.JenisPelanggan[ id=" + id + " ]";
    }
    
}
