/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andik.penjualan.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author andik
 */
@Entity
@Table(name = "penjualan")
@NamedQueries({
    @NamedQuery(name = "Penjualan.findAll", query = "SELECT p FROM Penjualan p")})
public class Penjualan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "id")
    private String id;
    @Size(max = 60)
    @Column(name = "nomor")
    private String nomor;
    @Column(name = "tanggal")
    @Temporal(TemporalType.DATE)
    private Date tanggal;
    @Size(max = 255)
    @Column(name = "keterangan")
    private String keterangan;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "jumlah")
    private BigDecimal jumlah;
    @Column(name = "waktu_dibuat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date waktuDibuat;
    @JoinColumn(name = "pelanggan_id", referencedColumnName = "id")
    @ManyToOne
    private Pelanggan pelanggan;
    @OneToMany(mappedBy = "penjualan")
    private Collection<PenjualanProduk> penjualanProdukCollection;

    public Penjualan() {
    }

    public Penjualan(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }

    public Date getWaktuDibuat() {
        return waktuDibuat;
    }

    public void setWaktuDibuat(Date waktuDibuat) {
        this.waktuDibuat = waktuDibuat;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public void setPelanggan(Pelanggan pelanggan) {
        this.pelanggan = pelanggan;
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
        if (!(object instanceof Penjualan)) {
            return false;
        }
        Penjualan other = (Penjualan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.andik.penjualan.entity.Penjualan[ id=" + id + " ]";
    }
    
}
