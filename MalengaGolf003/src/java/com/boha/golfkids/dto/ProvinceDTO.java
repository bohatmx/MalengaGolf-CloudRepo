/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.dto;

import com.boha.golfkids.data.Province;
import java.util.List;

/**
 *
 * @author Aubrey Malabie
 */
public class ProvinceDTO {

    private int provinceID, countryID;
    private double latitude;
    private double longitude;
    private String provinceName;
    private List<ClubDTO> clubs;

    public ProvinceDTO(Province a) {
        provinceID = a.getProvinceID();
        latitude = a.getLatitude();
        longitude = a.getLongitude();
        provinceName = a.getProvinceName();
        countryID = a.getCountry().getCountryID();
      

    }

    public int getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(int provinceID) {
        this.provinceID = provinceID;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public List<ClubDTO> getClubs() {
        return clubs;
    }

    public void setClubs(List<ClubDTO> clubs) {
        this.clubs = clubs;
    }
}
