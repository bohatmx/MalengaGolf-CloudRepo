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
        query = "select a from AgeGroup a  where a.golfGroup.golfGroupID = :id"
                    + " order by a.groupName "),
     @NamedQuery(name = "AgeGroup.findByGender", 
        query = "select a from AgeGroup a "
                    + "where a.golfGroup.golfGroupID = :id "
                    + "and a.gender = :gender "
                    + " order by a.ageGroupID "
          )})
public class AgeGroup implements  Serializable {
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
    private int gender;
    @Column(name = "numberOfHolesPerRound")
    private int numberOfHolesPerRound;
    @JoinColumn(name = "golfGroupID", referencedColumnName = "golfGroupID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private GolfGroup golfGroup;
    
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

    
    public void setAgeGroupID(Integer ageGroupID) {
        this.ageGroupID = ageGroupID;
    }

   
    public void setGender(Integer gender) {
        this.gender = gender;
    }

   

    

    public GolfGroup getGolfGroup() {
        return golfGroup;
    }

    

    public int getGender() {
        return gender;
    }

    public void setGolfGroup(GolfGroup golfGroup) {
        this.golfGroup = golfGroup;
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
        if (!(object instanceof AgeGroup)) {
            return false;
        }
        AgeGroup other = (AgeGroup) object;
        if ((this.ageGroupID == null && other.ageGroupID != null) || (this.ageGroupID != null && !this.ageGroupID.equals(other.ageGroupID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.golfkids.data.AgeGroup[ ageGroupID=" + ageGroupID + " ]";
    }

    
    
}
