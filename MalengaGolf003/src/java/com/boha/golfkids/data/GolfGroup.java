/*
 * To change this template, choose Tools | Templates
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
    @NamedQuery(name = "GolfGroup.findAll", query = "SELECT g FROM GolfGroup g"),
    @NamedQuery(name = "GolfGroup.findByGolfGroupID", query = "SELECT g FROM GolfGroup g WHERE g.golfGroupID = :golfGroupID"),
    @NamedQuery(name = "GolfGroup.findByGolfGroupName", query = "SELECT g FROM GolfGroup g WHERE g.golfGroupName = :golfGroupName"),
    @NamedQuery(name = "GolfGroup.findByEmail", query = "SELECT g FROM GolfGroup g WHERE g.email = :email"),
    @NamedQuery(name = "GolfGroup.findByCellphone", query = "SELECT g FROM GolfGroup g WHERE g.cellphone = :cellphone"),
    @NamedQuery(name = "GolfGroup.findByDateRegistered", query = "SELECT g FROM GolfGroup g WHERE g.dateRegistered = :dateRegistered")})
public class GolfGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "golfGroupID")
    private int golfGroupID;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "golfGroup")
    private List<Tournament> tournamentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "golfGroup")
    private List<GolfGroupParent> golfGroupParentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "golfGroup")
    private List<GolfGroupVolunteer> golfGroupVolunteerList;
    @JoinColumn(name = "countryID", referencedColumnName = "countryID")
    @ManyToOne(optional = false)
    private Country country;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "golfGroup")
    private List<GolfGroupPlayer> golfGroupPlayerList;
    @OneToMany(mappedBy = "golfGroup")
    private List<Administrator> administratorList;

    public GolfGroup() {
    }

    public GolfGroup(int golfGroupID) {
        this.golfGroupID = golfGroupID;
    }

    public GolfGroup(int golfGroupID, String golfGroupName, String email, Date dateRegistered) {
        this.golfGroupID = golfGroupID;
        this.golfGroupName = golfGroupName;
        this.email = email;
        this.dateRegistered = dateRegistered;
    }

    public int getGolfGroupID() {
        return golfGroupID;
    }

    public void setGolfGroupID(int golfGroupID) {
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

    public List<Administrator> getAdministratorList() {
        return administratorList;
    }

    public void setAdministratorList(List<Administrator> administratorList) {
        this.administratorList = administratorList;
    }

 
    @Override
    public String toString() {
        return "com.boha.golfkids.data.GolfGroup[ golfGroupID=" + golfGroupID + " ]";
    }
    
}
