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
import javax.persistence.FetchType;
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
@Table(name = "parent")
@NamedQueries({
    @NamedQuery(name = "Parent.login", 
            query = "SELECT a FROM Parent a "
                    + "where a.email = :email and a.pin = :pin"),
    
    @NamedQuery(name = "Parent.findByEmail", 
            query = "SELECT a FROM Parent a "
                    + "where a.email = :email"),
    
    @NamedQuery(name = "Parent.findbyGolfGroup", 
            query = "SELECT p FROM Parent p, GolfGroupParent a "
                    + "where p.parentID = a.parent.parentID "
                    + "and a.golfGroup.golfGroupID = :id "
                    + "order by p.lastName, p.firstName")})
public class Parent implements Serializable {
    @Column(name = "parentType")
    private Integer parentType;
    @OneToMany(mappedBy = "parent")
    private List<GcmDevice> gcmDeviceList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "parentID")
    private int parentID;
    @Size(max = 45)
    @Column(name = "firstName")
    private String firstName;
    @Size(max = 45)
    @Column(name = "middleName")
    private String middleName;
    @Size(max = 45)
    @Column(name = "lastName")
    private String lastName;
    @Size(max = 95)
    @Column(name = "email")
    private String email;
    @Size(max = 25)
    @Column(name = "cellphone")
    private String cellphone;
    @Size(max = 20)
    @Column(name = "pin")
    private String pin;
    @Column(name = "exampleFlag")
    private int exampleFlag;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent", fetch = FetchType.EAGER)
    private List<GolfGroupParent> golfGroupParentList;
    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    private List<Player> playerList;

    public Parent() {
    }

    public Parent(int parentID) {
        this.parentID = parentID;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
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


    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public List<GolfGroupParent> getGolfGroupParentList() {
        return golfGroupParentList;
    }

    public void setGolfGroupParentList(List<GolfGroupParent> golfGroupParentList) {
        this.golfGroupParentList = golfGroupParentList;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public int getExampleFlag() {
        return exampleFlag;
    }

    public void setExampleFlag(int exampleFlag) {
        this.exampleFlag = exampleFlag;
    }

    @Override
    public String toString() {
        return "com.boha.golfkids.data.Parent[ parentID=" + parentID + " ]";
    }


    public List<GcmDevice> getGcmDeviceList() {
        return gcmDeviceList;
    }

    public void setGcmDeviceList(List<GcmDevice> gcmDeviceList) {
        this.gcmDeviceList = gcmDeviceList;
    }

    public Integer getParentType() {
        return parentType;
    }

    public void setParentType(Integer parentType) {
        this.parentType = parentType;
    }
    
}
