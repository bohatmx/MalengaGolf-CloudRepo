/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.golfkids.dto;

import com.boha.golfkids.data.*;
import java.io.Serializable;

/**
 *
 * @author aubreyM
 */
public class PersonalScoreByHoleDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer personalScoreByHoleID;
    private short hole;
    private boolean fairwayHit;
    private boolean greenInRegulation;
    private boolean fairwayBunker;
    private boolean greensideBunker;
    private short putts;
    private boolean outOfBounds;
    private boolean waterHazard;
    private Short par;
    private short score;
    private Long datePlayed;
   private Integer clubCourseID;
    private Integer personalPlayerID;

    public PersonalScoreByHoleDTO() {
    }

    public PersonalScoreByHoleDTO(PersonalScoreByHole a) {
        this.personalScoreByHoleID = a.getPersonalScoreByHoleID();
        this.hole = a.getHole();
        this.fairwayHit = a.getFairwayHit();
        this.greenInRegulation = a.getGreenInRegulation();
        this.fairwayBunker = a.getFairwayBunker();
        this.greensideBunker = a.getGreensideBunker();
        this.putts = a.getPutts();
        this.outOfBounds = a.getOutOfBounds();
        this.waterHazard = a.getWaterHazard();
        this.score = a.getScore();
        this.datePlayed = a.getDatePlayed().getTime();
        clubCourseID = a.getClubCourse().getClubCourseID();
        personalPlayerID = a.getPersonalPlayer().getPersonalPlayerID();
    }

    public Integer getPersonalScoreByHoleID() {
        return personalScoreByHoleID;
    }

    public void setPersonalScoreByHoleID(Integer personalScoreByHoleID) {
        this.personalScoreByHoleID = personalScoreByHoleID;
    }

    public short getHole() {
        return hole;
    }

    public void setHole(short hole) {
        this.hole = hole;
    }

    public boolean getFairwayHit() {
        return fairwayHit;
    }

    public void setFairwayHit(boolean fairwayHit) {
        this.fairwayHit = fairwayHit;
    }

    public boolean getGreenInRegulation() {
        return greenInRegulation;
    }

    public void setGreenInRegulation(boolean greenInRegulation) {
        this.greenInRegulation = greenInRegulation;
    }

    public boolean getFairwayBunker() {
        return fairwayBunker;
    }

    public void setFairwayBunker(boolean fairwayBunker) {
        this.fairwayBunker = fairwayBunker;
    }

    public boolean getGreensideBunker() {
        return greensideBunker;
    }

    public void setGreensideBunker(boolean greensideBunker) {
        this.greensideBunker = greensideBunker;
    }

    public short getPutts() {
        return putts;
    }

    public void setPutts(short putts) {
        this.putts = putts;
    }

    public boolean getOutOfBounds() {
        return outOfBounds;
    }

    public void setOutOfBounds(boolean outOfBounds) {
        this.outOfBounds = outOfBounds;
    }

    public boolean getWaterHazard() {
        return waterHazard;
    }

    public void setWaterHazard(boolean waterHazard) {
        this.waterHazard = waterHazard;
    }

    public Short getPar() {
        return par;
    }

    public void setPar(Short par) {
        this.par = par;
    }

    public short getScore() {
        return score;
    }

    public void setScore(short score) {
        this.score = score;
    }

    public Long getDatePlayed() {
        return datePlayed;
    }

    public void setDatePlayed(Long datePlayed) {
        this.datePlayed = datePlayed;
    }

    public Integer getClubCourseID() {
        return clubCourseID;
    }

    public void setClubCourseID(Integer clubCourseID) {
        this.clubCourseID = clubCourseID;
    }

    public Integer getPersonalPlayerID() {
        return personalPlayerID;
    }

    public void setPersonalPlayerID(Integer personalPlayerID) {
        this.personalPlayerID = personalPlayerID;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personalScoreByHoleID != null ? personalScoreByHoleID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonalScoreByHoleDTO)) {
            return false;
        }
        PersonalScoreByHoleDTO other = (PersonalScoreByHoleDTO) object;
        if ((this.personalScoreByHoleID == null && other.personalScoreByHoleID != null) || (this.personalScoreByHoleID != null && !this.personalScoreByHoleID.equals(other.personalScoreByHoleID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.golfkids.data.PersonalScoreByHole[ personalScoreByHoleID=" + personalScoreByHoleID + " ]";
    }
    
}
