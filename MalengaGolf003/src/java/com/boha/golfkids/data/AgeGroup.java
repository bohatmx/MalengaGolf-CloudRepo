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
@Table(name = "ageGroup")
@NamedQueries({
    @NamedQuery(name = "AgeGroup.findAll", query = "SELECT a FROM AgeGroup a"),
    @NamedQuery(name = "AgeGroup.findByAgeGroupID", query = "SELECT a FROM AgeGroup a WHERE a.ageGroupID = :ageGroupID"),
    @NamedQuery(name = "AgeGroup.findByGroupName", query = "SELECT a FROM AgeGroup a WHERE a.groupName = :groupName"),
    @NamedQuery(name = "AgeGroup.findByGender", query = "SELECT a FROM AgeGroup a WHERE a.gender = :gender"),
    @NamedQuery(name = "AgeGroup.findByNumberOfHolesPerRound", query = "SELECT a FROM AgeGroup a WHERE a.numberOfHolesPerRound = :numberOfHolesPerRound")})
public class AgeGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ageGroupID")
    private int ageGroupID;
    @Size(max = 50)
    @Column(name = "groupName")
    private String groupName;
    @Column(name = "gender")
    private int gender;
    @Column(name = "numberOfHolesPerRound")
    private int numberOfHolesPerRound;
    @OneToMany(mappedBy = "ageGroup")
    private List<TourneyPlayerScore> tourneyPlayerScoreList;

    public AgeGroup() {
    }

    public AgeGroup(int ageGroupID) {
        this.ageGroupID = ageGroupID;
    }

    public int getAgeGroupID() {
        return ageGroupID;
    }

    public void setAgeGroupID(int ageGroupID) {
        this.ageGroupID = ageGroupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getNumberOfHolesPerRound() {
        return numberOfHolesPerRound;
    }

    public void setNumberOfHolesPerRound(int numberOfHolesPerRound) {
        this.numberOfHolesPerRound = numberOfHolesPerRound;
    }

    public List<TourneyPlayerScore> getTourneyPlayerScoreList() {
        return tourneyPlayerScoreList;
    }

    public void setTourneyPlayerScoreList(List<TourneyPlayerScore> tourneyPlayerScoreList) {
        this.tourneyPlayerScoreList = tourneyPlayerScoreList;
    }

    
    
}
