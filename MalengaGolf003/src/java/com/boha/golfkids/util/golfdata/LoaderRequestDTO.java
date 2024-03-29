/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.golfkids.util.golfdata;

import com.boha.golfkids.dto.CityDTO;
import com.boha.golfkids.dto.ClubDTO;
import com.boha.golfkids.dto.CountryDTO;
import com.boha.golfkids.dto.ProvinceDTO;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author aubreyM
 */
public class LoaderRequestDTO implements Serializable{
    
    public static final int LOAD_COUNTRIES = 111;
    public static final int LOAD_PROVINCES = 113;
    public static final int LOAD_PROVINCE_CITIES = 115;
    public static final int LOAD_CITY_CLUBS = 117;
    public static final int GET_COUNTRIES = 120;
    public static final int UPDATE_CITY_COORDINATES = 130;
    public static final int GET_STATE_CITIES = 140;
   
    
    public static final int FIND_CLUBS_WITHIN_RADIUS = 200;
    
    private int requestType, countryID, provinceID, cityID;
    List<ProvinceDTO> provinceList;
    List<CityDTO> cityList;
    List<ClubDTO> clubList;
    List<CountryDTO> countryList;
    private int radius, page;
    private double latitude, longitude;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    
    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
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
    

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public int getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(int provinceID) {
        this.provinceID = provinceID;
    }

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }

    public List<ProvinceDTO> getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(List<ProvinceDTO> provinceList) {
        this.provinceList = provinceList;
    }

    public List<CityDTO> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityDTO> cityList) {
        this.cityList = cityList;
    }

    public List<ClubDTO> getClubList() {
        return clubList;
    }

    public void setClubList(List<ClubDTO> clubList) {
        this.clubList = clubList;
    }

    public List<CountryDTO> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<CountryDTO> countryList) {
        this.countryList = countryList;
    }

    
    
}
