/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.dto;

import com.boha.golfkids.data.Club;
import com.boha.golfkids.data.Province;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aubrey Malabie
 */
public class ProvinceDTO {

    private int provinceID;
    private int latitude;
    private int longitude;
    private String provinceName;
    private List<ClubDTO> clubs;

    public ProvinceDTO(Province a) {
        provinceID = a.getProvinceID();
        latitude = a.getLatitude();
        longitude = a.getLongitude();

        List<Club> cList = a.getClubList();
        if (a.getClubList() != null) {
            clubs = new ArrayList<>();
            for (Club club : cList) {
                clubs.add(new ClubDTO(club));
            }
        }

    }

    public int getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(int provinceID) {
        this.provinceID = provinceID;
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
