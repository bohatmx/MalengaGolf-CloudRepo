/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.golfkids.data;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "agegroup")
@NamedQueries({
    @NamedQuery(name = "AgeGroup.findByGolfGroup", 
            query = "SELECT a FROM Agegroup a where a.golfGroup.golfGroupID = :id order by a.groupName"),
    @NamedQuery(name = "AgeGroup.findByGender",
            query = "SELECT a FROM Agegroup a where a.golfGroup.golfGroupID = :id and a.gender = :gender order by a.groupName")

})
public class Agegroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ageGroupID")
    private Integer ageGroupID;
    @Size(max = 50)
    @Column(name = "groupName")
    private String groupName;
    @Column(name = "gender")
    private Integer gender;
    @Column(name = "numberOfHolesPerRound")
    private Integer numberOfHolesPerRound;
    @OneToMany(mappedBy = "ageGroup", fetch = FetchType.EAGER)
    private List<TourneyPlayerScore> tourneyPlayerScoreList;
    @JoinColumn(name = "golfGroupID", referencedColumnName = "golfGroupID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private GolfGroup golfGroup;

    public Agegroup() {
    }

    public Agegroup(Integer ageGroupID) {
        this.ageGroupID = ageGroupID;
    }

    public Integer getAgeGroupID() {
        return ageGroupID;
    }

    public void setAgeGroupID(Integer ageGroupID) {
        this.ageGroupID = ageGroupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getNumberOfHolesPerRound() {
        return numberOfHolesPerRound;
    }

    public void setNumberOfHolesPerRound(Integer numberOfHolesPerRound) {
        this.numberOfHolesPerRound = numberOfHolesPerRound;
    }

    public List<TourneyPlayerScore> getTourneyPlayerScoreList() {
        return tourneyPlayerScoreList;
    }

    public void setTourneyPlayerScoreList(List<TourneyPlayerScore> tourneyPlayerScoreList) {
        this.tourneyPlayerScoreList = tourneyPlayerScoreList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ageGroupID != null ? ageGroupID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agegroup)) {
            return false;
        }
        Agegroup other = (Agegroup) object;
        if ((this.ageGroupID == null && other.ageGroupID != null) || (this.ageGroupID != null && !this.ageGroupID.equals(other.ageGroupID))) {
            return false;
        }
        return true;
    }

    public GolfGroup getGolfGroup() {
        return golfGroup;
    }

    public void setGolfGroup(GolfGroup golfGroup) {
        this.golfGroup = golfGroup;
    }

    @Override
    public String toString() {
        return "com.boha.golfkids.data.Agegroup[ ageGroupID=" + ageGroupID + " ]";
    }
    
}
