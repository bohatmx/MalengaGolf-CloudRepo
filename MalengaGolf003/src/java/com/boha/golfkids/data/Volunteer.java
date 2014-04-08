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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "volunteer")
@NamedQueries({
    @NamedQuery(name = "Volunteer.findAll", query = "SELECT v FROM Volunteer v")})
public class Volunteer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "volunteerID")
    private Integer volunteerID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "firstName")
    private String firstName;
    @Size(max = 45)
    @Column(name = "middleName")
    private String middleName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "lastName")
    private String lastName;
    @Size(max = 45)
    @Column(name = "cellphone")
    private String cellphone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    @Size(max = 45)
    @Column(name = "pin")
    private String pin;
    @OneToMany(mappedBy = "volunteer", fetch = FetchType.EAGER)
    private List<GolfGroupVolunteer> golfGroupVolunteerList;
    @OneToMany(mappedBy = "volunteer", fetch = FetchType.EAGER)
    private List<TournamentVolunteer> tournamentVolunteerList;

    public Volunteer() {
    }

    public Volunteer(Integer volunteerID) {
        this.volunteerID = volunteerID;
    }

    public Volunteer(Integer volunteerID, String firstName, String lastName, String email) {
        this.volunteerID = volunteerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Integer getVolunteerID() {
        return volunteerID;
    }

    public void setVolunteerID(Integer volunteerID) {
        this.volunteerID = volunteerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public List<GolfGroupVolunteer> getGolfGroupVolunteerList() {
        return golfGroupVolunteerList;
    }

    public void setGolfGroupVolunteerList(List<GolfGroupVolunteer> golfGroupVolunteerList) {
        this.golfGroupVolunteerList = golfGroupVolunteerList;
    }

    public List<TournamentVolunteer> getTournamentVolunteerList() {
        return tournamentVolunteerList;
    }

    public void setTournamentVolunteerList(List<TournamentVolunteer> tournamentVolunteerList) {
        this.tournamentVolunteerList = tournamentVolunteerList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (volunteerID != null ? volunteerID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Volunteer)) {
            return false;
        }
        Volunteer other = (Volunteer) object;
        if ((this.volunteerID == null && other.volunteerID != null) || (this.volunteerID != null && !this.volunteerID.equals(other.volunteerID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.golfkids.data.Volunteer[ volunteerID=" + volunteerID + " ]";
    }
    
}
