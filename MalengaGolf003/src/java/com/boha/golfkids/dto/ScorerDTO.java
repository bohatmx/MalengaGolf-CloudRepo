/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.dto;

import com.boha.golfkids.data.Scorer;

/**
 *
 * @author aubreyM
 */
public class ScorerDTO {

    private Integer scorerID;
    private String firstName;
    private String lastName;
    private String email;
    private String cellphone;
    private String pin;
    private long dateRegistered;
    private int golfGroupID;
    public ScorerDTO(Scorer a) {
        scorerID = a.getScorerID();
        firstName = a.getFirstName();
        lastName = a.getLastName();
        pin = a.getPin();
        email = a.getEmail();
        cellphone = a.getCellphone();
        dateRegistered = a.getDateRegistered().getTime();
        golfGroupID = a.getGolfGroup().getGolfGroupID();
    }

    public Integer getScorerID() {
        return scorerID;
    }

    public void setScorerID(Integer scorerID) {
        this.scorerID = scorerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public long getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(long dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public int getGolfGroupID() {
        return golfGroupID;
    }

    public void setGolfGroupID(int golfGroupID) {
        this.golfGroupID = golfGroupID;
    }
    
}
