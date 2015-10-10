/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.golfkids.data;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "personalPlayer")
@NamedQueries({
    @NamedQuery(name = "PersonalPlayer.login", 
            query = "SELECT p FROM PersonalPlayer p where p.email = :email and p.pin = :pin"),
    
    @NamedQuery(name = "PersonalPlayer.findByEmail", 
            query = "SELECT p FROM PersonalPlayer p where p.email = :email"),
    
     @NamedQuery(name = "PersonalPlayer.findByCellphone", 
             query = "SELECT p FROM PersonalPlayer p WHERE p.cellphone = :cellphone")

})
public class PersonalPlayer implements Serializable {
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personalPlayer")
    private List<PersonalScoreByHole> personalScoreByHoleList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "personalPlayerID")
    private int personalPlayerID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "firstName")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "lastName")
    private String lastName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "pin")
    private String pin;
    @Size(max = 30)
    @Column(name = "cellphone")
    private String cellphone;
    @JoinColumn(name = "countryID", referencedColumnName = "countryID")
    @ManyToOne
    private Country country;
    @JoinColumn(name = "clubID", referencedColumnName = "clubID")
    @ManyToOne
    private Club club;
    @OneToMany(mappedBy = "personalPlayer")
    private List<PersonalScore> personalScoreList;

    public PersonalPlayer() {
    }

    public PersonalPlayer(int personalPlayerID) {
        this.personalPlayerID = personalPlayerID;
    }

    public PersonalPlayer(int personalPlayerID, String firstName, String lastName, String email, String pin) {
        this.personalPlayerID = personalPlayerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.pin = pin;
    }

    public int getPersonalPlayerID() {
        return personalPlayerID;
    }

    public void setPersonalPlayerID(int personalPlayerID) {
        this.personalPlayerID = personalPlayerID;
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

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

  

    public List<PersonalScore> getPersonalScoreList() {
        return personalScoreList;
    }

    public void setPersonalScoreList(List<PersonalScore> personalScoreList) {
        this.personalScoreList = personalScoreList;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
    
    @Override
    public String toString() {
        return "com.boha.golfkids.data.PersonalPlayer[ personalPlayerID=" + personalPlayerID + " ]";
    }

    public List<PersonalScoreByHole> getPersonalScoreByHoleList() {
        return personalScoreByHoleList;
    }

    public void setPersonalScoreByHoleList(List<PersonalScoreByHole> personalScoreByHoleList) {
        this.personalScoreByHoleList = personalScoreByHoleList;
    }
    
}
