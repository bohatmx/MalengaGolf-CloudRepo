/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.dto;

import com.boha.golfkids.data.AgeGroup;

/**
 *
 * @author Aubrey Malabie
 */
public class AgeGroupDTO {

    private int ageGroupID, golfGroupID;
    private int gender;
    private String groupName;
    private int numberOfHolesPerRound;

    public AgeGroupDTO(AgeGroup a) {
        gender = a.getGender();
        ageGroupID = a.getAgeGroupID();
        golfGroupID = a.getGolfGroup().getGolfGroupID();
        groupName = a.getGroupName();
        numberOfHolesPerRound = a.getNumberOfHolesPerRound();
    }

    public int getAgeGroupID() {
        return ageGroupID;
    }

    public void setAgeGroupID(int ageGroupID) {
        this.ageGroupID = ageGroupID;
    }

    public int getGolfGroupID() {
        return golfGroupID;
    }

    public void setGolfGroupID(int golfGroupID) {
        this.golfGroupID = golfGroupID;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getNumberOfHolesPerRound() {
        return numberOfHolesPerRound;
    }

    public void setNumberOfHolesPerRound(int numberOfHolesPerRound) {
        this.numberOfHolesPerRound = numberOfHolesPerRound;
    }
}
