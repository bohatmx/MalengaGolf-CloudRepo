/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.golfkids.data;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "personalScoreByHole")
@NamedQueries({
    @NamedQuery(name = "PersonalScoreByHole.findAll", query = "SELECT p FROM PersonalScoreByHole p"),
    @NamedQuery(name = "PersonalScoreByHole.findByPersonalScoreByHoleID", query = "SELECT p FROM PersonalScoreByHole p WHERE p.personalScoreByHoleID = :personalScoreByHoleID"),
    @NamedQuery(name = "PersonalScoreByHole.findByHole", query = "SELECT p FROM PersonalScoreByHole p WHERE p.hole = :hole"),
    @NamedQuery(name = "PersonalScoreByHole.findByFairwayHit", query = "SELECT p FROM PersonalScoreByHole p WHERE p.fairwayHit = :fairwayHit"),
    @NamedQuery(name = "PersonalScoreByHole.findByGreenInRegulation", query = "SELECT p FROM PersonalScoreByHole p WHERE p.greenInRegulation = :greenInRegulation"),
    @NamedQuery(name = "PersonalScoreByHole.findByFairwayBunker", query = "SELECT p FROM PersonalScoreByHole p WHERE p.fairwayBunker = :fairwayBunker"),
    @NamedQuery(name = "PersonalScoreByHole.findByGreensideBunker", query = "SELECT p FROM PersonalScoreByHole p WHERE p.greensideBunker = :greensideBunker"),
    @NamedQuery(name = "PersonalScoreByHole.findByPutts", query = "SELECT p FROM PersonalScoreByHole p WHERE p.putts = :putts"),
    @NamedQuery(name = "PersonalScoreByHole.findByOutOfBounds", query = "SELECT p FROM PersonalScoreByHole p WHERE p.outOfBounds = :outOfBounds"),
    @NamedQuery(name = "PersonalScoreByHole.findByWaterHazard", query = "SELECT p FROM PersonalScoreByHole p WHERE p.waterHazard = :waterHazard"),
    @NamedQuery(name = "PersonalScoreByHole.findByPar", query = "SELECT p FROM PersonalScoreByHole p WHERE p.par = :par"),
    @NamedQuery(name = "PersonalScoreByHole.findByScore", query = "SELECT p FROM PersonalScoreByHole p WHERE p.score = :score"),
    @NamedQuery(name = "PersonalScoreByHole.findByDatePlayed", query = "SELECT p FROM PersonalScoreByHole p WHERE p.datePlayed = :datePlayed")})
public class PersonalScoreByHole implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "personalScoreByHoleID")
    private Integer personalScoreByHoleID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hole")
    private short hole;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fairwayHit")
    private boolean fairwayHit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "greenInRegulation")
    private boolean greenInRegulation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fairwayBunker")
    private boolean fairwayBunker;
    @Basic(optional = false)
    @NotNull
    @Column(name = "greensideBunker")
    private boolean greensideBunker;
    @Basic(optional = false)
    @NotNull
    @Column(name = "putts")
    private short putts;
    @Basic(optional = false)
    @NotNull
    @Column(name = "outOfBounds")
    private boolean outOfBounds;
    @Basic(optional = false)
    @NotNull
    @Column(name = "waterHazard")
    private boolean waterHazard;
    @Column(name = "par")
    private Short par;
    @Basic(optional = false)
    @NotNull
    @Column(name = "score")
    private short score;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datePlayed")
    @Temporal(TemporalType.DATE)
    private Date datePlayed;
    @JoinColumn(name = "clubCourseID", referencedColumnName = "clubCourseID")
    @ManyToOne(optional = false)
    private ClubCourse clubCourse;
    @JoinColumn(name = "personalPlayerID", referencedColumnName = "personalPlayerID")
    @ManyToOne(optional = false)
    private PersonalPlayer personalPlayer;

    public PersonalScoreByHole() {
    }

    public PersonalScoreByHole(Integer personalScoreByHoleID) {
        this.personalScoreByHoleID = personalScoreByHoleID;
    }

    public PersonalScoreByHole(Integer personalScoreByHoleID, short hole, boolean fairwayHit, boolean greenInRegulation, boolean fairwayBunker, boolean greensideBunker, short putts, boolean outOfBounds, boolean waterHazard, short score, Date datePlayed) {
        this.personalScoreByHoleID = personalScoreByHoleID;
        this.hole = hole;
        this.fairwayHit = fairwayHit;
        this.greenInRegulation = greenInRegulation;
        this.fairwayBunker = fairwayBunker;
        this.greensideBunker = greensideBunker;
        this.putts = putts;
        this.outOfBounds = outOfBounds;
        this.waterHazard = waterHazard;
        this.score = score;
        this.datePlayed = datePlayed;
    }

    public ClubCourse getClubCourse() {
        return clubCourse;
    }

    public void setClubCourse(ClubCourse clubCourse) {
        this.clubCourse = clubCourse;
    }

    public PersonalPlayer getPersonalPlayer() {
        return personalPlayer;
    }

    public void setPersonalPlayer(PersonalPlayer personalPlayer) {
        this.personalPlayer = personalPlayer;
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

    public Date getDatePlayed() {
        return datePlayed;
    }

    public void setDatePlayed(Date datePlayed) {
        this.datePlayed = datePlayed;
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
        if (!(object instanceof PersonalScoreByHole)) {
            return false;
        }
        PersonalScoreByHole other = (PersonalScoreByHole) object;
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
