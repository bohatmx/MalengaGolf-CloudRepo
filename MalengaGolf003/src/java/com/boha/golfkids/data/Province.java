/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.data;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "province")
@NamedQueries({
    @NamedQuery(name = "Province.findAll", query = "SELECT p FROM Province p"),
    @NamedQuery(name = "Province.findByProvinceID", query = "SELECT p FROM Province p WHERE p.provinceID = :provinceID"),
    @NamedQuery(name = "Province.findByProvinceName", query = "SELECT p FROM Province p WHERE p.provinceName = :provinceName"),
    @NamedQuery(name = "Province.findByLatitude", query = "SELECT p FROM Province p WHERE p.latitude = :latitude"),
    @NamedQuery(name = "Province.findByLongitude", query = "SELECT p FROM Province p WHERE p.longitude = :longitude")})
public class Province implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "provinceID")
    private int provinceID;
    @Size(max = 100)
    @Column(name = "provinceName")
    private String provinceName;
    @Column(name = "latitude")
    private int latitude;
    @Column(name = "longitude")
    private int longitude;
    @OneToMany(mappedBy = "province")
    private List<Club> clubList;
    @JoinColumn(name = "countryID", referencedColumnName = "countryID")
    @ManyToOne
    private Country country;

    public Province() {
    }

    public Province(int provinceID) {
        this.provinceID = provinceID;
    }

    public int getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(int provinceID) {
        this.provinceID = provinceID;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public List<Club> getClubList() {
        return clubList;
    }

    public void setClubList(List<Club> clubList) {
        this.clubList = clubList;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    
    @Override
    public String toString() {
        return "com.boha.golfkids.data.Province[ provinceID=" + provinceID + " ]";
    }
    
}
