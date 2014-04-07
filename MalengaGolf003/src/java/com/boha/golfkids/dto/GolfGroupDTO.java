/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.dto;

import com.boha.golfkids.data.Administrator;
import com.boha.golfkids.data.GolfGroup;
import com.boha.golfkids.data.GolfGroupPlayer;
import com.boha.golfkids.data.Tournament;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Aubrey Malabie
 */
public class GolfGroupDTO {

    private int golfGroupID, countryID;
    private String cellphone;
    private long dateRegistered;
    private String email;

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

    public List<AdministratorDTO> getAdministrators() {
        return administrators;
    }

    public void setAdministrators(List<AdministratorDTO> administrators) {
        this.administrators = administrators;
    }
    private String countryName;
    private String golfGroupName;
    private List<GolfGroupPlayerDTO> golfGroupPlayers;
    private List<AdministratorDTO> administrators;
    private List<TournamentDTO> tournaments;

    public GolfGroupDTO(GolfGroup a) {
        golfGroupID = a.getGolfGroupID();
        cellphone = a.getCellphone();
        dateRegistered = a.getDateRegistered().getTime();
        email = a.getEmail();
        golfGroupName = a.getGolfGroupName();
        
        if (a.getTournamentList() != null) {
            tournaments = new ArrayList<TournamentDTO>();
            for (Tournament t : a.getTournamentList()) {
                tournaments.add(new TournamentDTO(t));
            }
        }
        if (a.getGolfGroupPlayerList() != null) {
            golfGroupPlayers = new ArrayList<GolfGroupPlayerDTO>();
            for (GolfGroupPlayer player : a.getGolfGroupPlayerList()) {
                golfGroupPlayers.add(new GolfGroupPlayerDTO(player));
            }
        }
        if (a.getCountry() != null) {
            countryID = a.getCountry().getCountryID();
            countryName = a.getCountry().getCountryName();
        }
        
        if (a.getAdministratorList() != null) {
            administrators = new ArrayList<AdministratorDTO>();
            List<Administrator> aList = a.getAdministratorList();
            for (Administrator administrator : aList) {
                administrators.add(new AdministratorDTO(administrator));
            }
        }
    }

    public int getGolfGroupID() {
        return golfGroupID;
    }

    public void setGolfGroupID(int golfGroupID) {
        this.golfGroupID = golfGroupID;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public long getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(long dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGolfGroupName() {
        return golfGroupName;
    }

    public void setGolfGroupName(String golfGroupName) {
        this.golfGroupName = golfGroupName;
    }
}
