/*
 * To change this template, choose Tools | Templates
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

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "golfGroupVolunteer")
@NamedQueries({
    @NamedQuery(name = "GolfGroupVolunteer.findAll", query = "SELECT g FROM GolfGroupVolunteer g"),
    @NamedQuery(name = "GolfGroupVolunteer.findByGolfGroupVolunteerID", query = "SELECT g FROM GolfGroupVolunteer g WHERE g.golfGroupVolunteerID = :golfGroupVolunteerID"),
    @NamedQuery(name = "GolfGroupVolunteer.findByDateRegistered", query = "SELECT g FROM GolfGroupVolunteer g WHERE g.dateRegistered = :dateRegistered")})
public class GolfGroupVolunteer implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "golfGroupID")
    private int golfGroupID;
    @Column(name = "volunteerID")
    private Integer volunteerID;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "golfGroupVolunteerID")
    private int golfGroupVolunteerID;
    @Column(name = "dateRegistered")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistered;
    @JoinColumn(name = "volunteerID", referencedColumnName = "volunteerID")
    @ManyToOne
    private Volunteer volunteer;
    @JoinColumn(name = "golfGroupID", referencedColumnName = "golfGroupID")
    @ManyToOne(optional = false)
    private GolfGroup golfGroup;

    public GolfGroupVolunteer() {
    }

    public GolfGroupVolunteer(int golfGroupVolunteerID) {
        this.golfGroupVolunteerID = golfGroupVolunteerID;
    }

    public int getGolfGroupVolunteerID() {
        return golfGroupVolunteerID;
    }

    public void setGolfGroupVolunteerID(int golfGroupVolunteerID) {
        this.golfGroupVolunteerID = golfGroupVolunteerID;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public GolfGroup getGolfGroup() {
        return golfGroup;
    }

    public void setGolfGroup(GolfGroup golfGroup) {
        this.golfGroup = golfGroup;
    }

   

    @Override
    public String toString() {
        return "com.boha.golfkids.data.GolfGroupVolunteer[ golfGroupVolunteerID=" + golfGroupVolunteerID + " ]";
    }

    public int getGolfGroupID() {
        return golfGroupID;
    }

    public void setGolfGroupID(int golfGroupID) {
        this.golfGroupID = golfGroupID;
    }

    public Integer getVolunteerID() {
        return volunteerID;
    }

    public void setVolunteerID(Integer volunteerID) {
        this.volunteerID = volunteerID;
    }
    
}
