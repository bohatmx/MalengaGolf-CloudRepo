/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.golfkids.data;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "gcmDevice")
@NamedQueries({
    @NamedQuery(name = "GcmDevice.findByGolfGroupModelSerial", 
            query = "SELECT g FROM GcmDevice g "
                    + "where g.golfGroup.golfGroupID = :id "
                    + "and g.model = :model "
                    + "and g.serial = :serial"),
    @NamedQuery(name = "GcmDevice.findByAppUser", 
            query = "SELECT g FROM GcmDevice g WHERE g.appUser.appUserID = :id"),
    @NamedQuery(name = "GcmDevice.findByPlayer", 
            query = "SELECT g FROM GcmDevice g WHERE g.player.playerID = :id"),
    @NamedQuery(name = "GcmDevice.findByManufacturer", 
            query = "SELECT g FROM GcmDevice g WHERE g.manufacturer = :manufacturer"),
    @NamedQuery(name = "GcmDevice.findByModel", 
            query = "SELECT g FROM GcmDevice g WHERE g.model = :model"),
    @NamedQuery(name = "GcmDevice.findByRegistrationID", 
            query = "SELECT g FROM GcmDevice g WHERE g.gcmRegistrationID = :id"),
    
    @NamedQuery(name = "GcmDevice.findByAdministrator", 
            query = "SELECT g FROM GcmDevice g WHERE g.administrator.administratorID = :id"),
    @NamedQuery(name = "GcmDevice.findByScorer", 
            query = "SELECT g FROM GcmDevice g WHERE g.scorer.scorerID = :id")})
public class GcmDevice implements Serializable {
    @JoinColumn(name = "appUserID", referencedColumnName = "appUserID")
    @ManyToOne(optional = false)
    private AppUser appUser;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gcmDeviceID")
    private int gcmDeviceID;
    @Size(max = 255)
    @Column(name = "manufacturer")
    private String manufacturer;
    @Size(max = 255)
    @Column(name = "model")
    private String model;
    @Size(max = 255)
    @Column(name = "serial")
    private String serial;
    @Size(max = 255)
    @Column(name = "product")
    private String product;
    @Size(max = 255)
    @Column(name = "gcmRegistrationID")
    private String gcmRegistrationID;
    @Size(max = 50)
    @Column(name = "androidVersion")
    private String androidVersion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateRegistered")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistered;
    @JoinColumn(name = "parentID", referencedColumnName = "parentID")
    @ManyToOne
    private Parent parent;
    @JoinColumn(name = "playerID", referencedColumnName = "playerID")
    @ManyToOne
    private Player player;
    @JoinColumn(name = "scorerID", referencedColumnName = "scorerID")
    @ManyToOne
    private Scorer scorer;
    @JoinColumn(name = "administratorID", referencedColumnName = "administratorID")
    @ManyToOne
    private Administrator administrator;
    @JoinColumn(name = "golfGroupID", referencedColumnName = "golfGroupID")
    @ManyToOne
    private GolfGroup golfGroup;

    public GcmDevice() {
    }

    public GcmDevice(int gcmDeviceID) {
        this.gcmDeviceID = gcmDeviceID;
    }

    public GcmDevice(int gcmDeviceID, Date dateRegistered) {
        this.gcmDeviceID = gcmDeviceID;
        this.dateRegistered = dateRegistered;
    }

    public int getGcmDeviceID() {
        return gcmDeviceID;
    }

    public void setGcmDeviceID(int gcmDeviceID) {
        this.gcmDeviceID = gcmDeviceID;
    }

    public String getGcmRegistrationID() {
        return gcmRegistrationID;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public void setGcmRegistrationID(String gcmRegistrationID) {
        this.gcmRegistrationID = gcmRegistrationID;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Scorer getScorer() {
        return scorer;
    }

    public void setScorer(Scorer scorer) {
        this.scorer = scorer;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public GolfGroup getGolfGroup() {
        return golfGroup;
    }

    public void setGolfGroup(GolfGroup golfGroup) {
        this.golfGroup = golfGroup;
    }

   

    @Override
    public String toString() {
        return "com.boha.golfkids.data.GcmDevice[ gcmDeviceID=" + gcmDeviceID + " ]";
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

  
    
}
