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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "administrator")
@NamedQueries({
    @NamedQuery(name = "Administrator.login", 
            query = "SELECT a FROM Administrator a "
                    + "where a.email = :email and a.pin = :pin"),
    
    @NamedQuery(name = "Administrator.findByEmail", 
            query = "SELECT a FROM Administrator a "
                    + "where a.email = :email"),
    
     @NamedQuery(name = "Administrator.getByGroup", 
            query = "SELECT a FROM Administrator a "
                    + "where a.golfGroup.golfGroupID = :id "
                    + "order by a.lastName, a.firstName")

})
public class Administrator implements Serializable {
    @OneToMany(mappedBy = "administrator")
    private List<GcmDevice> gcmDeviceList;
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "administratorID")
    private int administratorID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "superUserFlag")
    private int superUserFlag;
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
    
    @JoinColumn(name = "golfGroupID", referencedColumnName = "golfGroupID")
    @ManyToOne(fetch = FetchType.EAGER)
    private GolfGroup golfGroup;

    public Administrator() {
    }

    public Administrator(int administratorID) {
        this.administratorID = administratorID;
    }

    public Administrator(int administratorID, int superUserFlag) {
        this.administratorID = administratorID;
        this.superUserFlag = superUserFlag;
    }

    public int getAdministratorID() {
        return administratorID;
    }

    public void setAdministratorID(int administratorID) {
        this.administratorID = administratorID;
    }

    public int getSuperUserFlag() {
        return superUserFlag;
    }

    public void setSuperUserFlag(int superUserFlag) {
        this.superUserFlag = superUserFlag;
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


    @Override
    public String toString() {
        return "com.boha.golfkids.data.Administrator[ administratorID=" + administratorID + " ]";
    }

    public GolfGroup getGolfGroup() {
        return golfGroup;
    }

    public void setGolfGroup(GolfGroup golfGroup) {
        this.golfGroup = golfGroup;
    }

    public List<GcmDevice> getGcmDeviceList() {
        return gcmDeviceList;
    }

    public void setGcmDeviceList(List<GcmDevice> gcmDeviceList) {
        this.gcmDeviceList = gcmDeviceList;
    }
    
}
