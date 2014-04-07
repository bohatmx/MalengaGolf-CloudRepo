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
@Table(name = "administrator")
@NamedQueries({
    @NamedQuery(name = "Administrator.findAll", query = "SELECT a FROM Administrator a"),
    @NamedQuery(name = "Administrator.findByAdministratorID", query = "SELECT a FROM Administrator a WHERE a.administratorID = :administratorID"),
    @NamedQuery(name = "Administrator.findByCellphone", query = "SELECT a FROM Administrator a WHERE a.cellphone = :cellphone"),
    @NamedQuery(name = "Administrator.findByEmail", query = "SELECT a FROM Administrator a WHERE a.email = :email"),
    @NamedQuery(name = "Administrator.findByPin", query = "SELECT a FROM Administrator a WHERE a.pin = :pin"),
    @NamedQuery(name = "Administrator.findByFirstName", query = "SELECT a FROM Administrator a WHERE a.firstName = :firstName"),
    @NamedQuery(name = "Administrator.findByLastName", query = "SELECT a FROM Administrator a WHERE a.lastName = :lastName")})
public class Administrator implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "administratorID")
    private int administratorID;
    @Size(max = 45)
    @Column(name = "cellphone")
    private String cellphone;
    @Size(max = 100)
    @Column(name = "email")
    private String email;
    @Size(max = 45)
    @Column(name = "pin")
    private String pin;
    @Size(max = 45)
    @Column(name = "firstName")
    private String firstName;
    @Size(max = 45)
    @Column(name = "lastName")
    private String lastName;
    @OneToMany(mappedBy = "administrator")
    private List<TourneyPlayerScore> tourneyPlayerScoreList;
    @JoinColumn(name = "golfGroupID", referencedColumnName = "golfGroupID")
    @ManyToOne
    private GolfGroup golfGroup;

    public Administrator() {
    }

    public Administrator(int administratorID) {
        this.administratorID = administratorID;
    }

    public int getAdministratorID() {
        return administratorID;
    }

    public void setAdministratorID(int administratorID) {
        this.administratorID = administratorID;
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

    public List<TourneyPlayerScore> getTourneyPlayerScoreList() {
        return tourneyPlayerScoreList;
    }

    public void setTourneyPlayerScoreList(List<TourneyPlayerScore> tourneyPlayerScoreList) {
        this.tourneyPlayerScoreList = tourneyPlayerScoreList;
    }

    public GolfGroup getGolfGroup() {
        return golfGroup;
    }

    public void setGolfGroup(GolfGroup golfGroup) {
        this.golfGroup = golfGroup;
    }

   
    @Override
    public String toString() {
        return "com.boha.golfkids.data.Administrator[ administratorID=" + administratorID + " ]";
    }
    
}
