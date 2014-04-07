/*
 * To change this template, choose Tools | Templates
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
    @NamedQuery(name = "Parent.findAll", query = "SELECT p FROM Parent p"),
    @NamedQuery(name = "Parent.findByParentID", query = "SELECT p FROM Parent p WHERE p.parentID = :parentID"),
    @NamedQuery(name = "Parent.findByFirstName", query = "SELECT p FROM Parent p WHERE p.firstName = :firstName"),
    @NamedQuery(name = "Parent.findByMiddleName", query = "SELECT p FROM Parent p WHERE p.middleName = :middleName"),
    @NamedQuery(name = "Parent.findByLastName", query = "SELECT p FROM Parent p WHERE p.lastName = :lastName"),
    @NamedQuery(name = "Parent.findByEmail", query = "SELECT p FROM Parent p WHERE p.email = :email"),
    @NamedQuery(name = "Parent.findByCellphone", query = "SELECT p FROM Parent p WHERE p.cellphone = :cellphone"),
    @NamedQuery(name = "Parent.findByParentType", query = "SELECT p FROM Parent p WHERE p.parentType = :parentType"),
    @NamedQuery(name = "Parent.findByPin", query = "SELECT p FROM Parent p WHERE p.pin = :pin")})
public class Parent implements Serializable {
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
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 95)
    @Column(name = "email")
    private String email;
    @Size(max = 25)
    @Column(name = "cellphone")
    private String cellphone;
    @Column(name = "parentType")
    private int parentType;
    @Size(max = 20)
    @Column(name = "pin")
    private String pin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    private List<GolfGroupParent> golfGroupParentList;
    @OneToMany(mappedBy = "parent")
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

    public int getParentType() {
        return parentType;
    }

    public void setParentType(int parentType) {
        this.parentType = parentType;
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


    @Override
    public String toString() {
        return "com.boha.golfkids.data.Parent[ parentID=" + parentID + " ]";
    }
    
}
