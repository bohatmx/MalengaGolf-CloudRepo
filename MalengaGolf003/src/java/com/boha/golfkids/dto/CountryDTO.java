/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.dto;

import com.boha.golfkids.data.Country;
import com.boha.golfkids.data.Province;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aubrey Malabie
 */
public class CountryDTO {

    private int countryID;
    private String countryName;
    private int latitude;
    private int longitude;
    private List<ProvinceDTO> provinces;

    public CountryDTO(Country a) {
        countryID = a.getCountryID();
        countryName = a.getCountryName();
        latitude = a.getLatitude();
        longitude = a.getLongitude();

        List<Province> pList = a.getProvinceList();
        if (a.getProvinceList() != null) {
            provinces = new ArrayList<ProvinceDTO>();
            for (Province province : pList) {
                provinces.add(new ProvinceDTO(province));
            }
        }
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
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

    public List<ProvinceDTO> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<ProvinceDTO> provinces) {
        this.provinces = provinces;
    }
}
