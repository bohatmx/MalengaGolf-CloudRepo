/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.golfkids.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "golfGroup")
@NamedQueries({
    @NamedQuery(name = "GolfGroup.findByEmail", 
            query = "SELECT g FROM GolfGroup g "
                    + "where g.email = :email"),

})
public class GolfGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "golfGroupID")
    private Integer golfGroupID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "golfGroupName")
    private String golfGroupName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    @Size(max = 25)
    @Column(name = "cellphone")
    private String cellphone;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateRegistered")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistered;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "golfGroup", fetch = FetchType.EAGER)
    private List<Tournament> tournamentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "golfGroup", fetch = FetchType.EAGER)
    private List<GolfGroupParent> golfGroupParentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "golfGroup", fetch = FetchType.EAGER)
    private List<GolfGroupVolunteer> golfGroupVolunteerList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "golfGroup", fetch = FetchType.EAGER)
    private List<Agegroup> ageGroupList;
    @JoinColumn(name = "countryID", referencedColumnName = "countryID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Country country;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "golfGroup", fetch = FetchType.EAGER)
    private List<GolfGroupPlayer> golfGroupPlayerList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "golfGroup", fetch = FetchType.EAGER)
    private List<Scorer> scorerList;
    @OneToMany(mappedBy = "golfGroup", fetch = FetchType.EAGER)
    private List<Administrator> administratorList;

    public GolfGroup() {
    }

    public GolfGroup(Integer golfGroupID) {
        this.golfGroupID = golfGroupID;
    }

    public GolfGroup(Integer golfGroupID, String golfGroupName, String email, Date dateRegistered) {
        this.golfGroupID = golfGroupID;
        this.golfGroupName = golfGroupName;
        this.email = email;
        this.dateRegistered = dateRegistered;
    }

    public Integer getGolfGroupID() {
        return golfGroupID;
    }

    public void setGolfGroupID(Integer golfGroupID) {
        this.golfGroupID = golfGroupID;
    }

    public String getGolfGroupName() {
        return golfGroupName;
    }

    public void setGolfGroupName(String golfGroupName) {
        this.golfGroupName = golfGroupName;
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

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public List<Tournament> getTournamentList() {
        return tournamentList;
    }

    public void setTournamentList(List<Tournament> tournamentList) {
        this.tournamentList = tournamentList;
    }

    public List<GolfGroupParent> getGolfGroupParentList() {
        return golfGroupParentList;
    }

    public void setGolfGroupParentList(List<GolfGroupParent> golfGroupParentList) {
        this.golfGroupParentList = golfGroupParentList;
    }

    public List<GolfGroupVolunteer> getGolfGroupVolunteerList() {
        return golfGroupVolunteerList;
    }

    public void setGolfGroupVolunteerList(List<GolfGroupVolunteer> golfGroupVolunteerList) {
        this.golfGroupVolunteerList = golfGroupVolunteerList;
    }

    public List<Agegroup> getAgeGroupList() {
        return ageGroupList;
    }

    public void setAgeGroupList(List<Agegroup> ageGroupList) {
        this.ageGroupList = ageGroupList;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

   

    public List<GolfGroupPlayer> getGolfGroupPlayerList() {
        return golfGroupPlayerList;
    }

    public void setGolfGroupPlayerList(List<GolfGroupPlayer> golfGroupPlayerList) {
        this.golfGroupPlayerList = golfGroupPlayerList;
    }

    public List<Scorer> getScorerList() {
        return scorerList;
    }

    public void setScorerList(List<Scorer> scorerList) {
        this.scorerList = scorerList;
    }

    public List<Administrator> getAdministratorList() {
        return administratorList;
    }

    public void setAdministratorList(List<Administrator> administratorList) {
        this.administratorList = administratorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (golfGroupID != null ? golfGroupID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GolfGroup)) {
            return false;
        }
        GolfGroup other = (GolfGroup) object;
        if ((this.golfGroupID == null && other.golfGroupID != null) || (this.golfGroupID != null && !this.golfGroupID.equals(other.golfGroupID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.golfkids.data.GolfGroup[ golfGroupID=" + golfGroupID + " ]";
    }
    
}
