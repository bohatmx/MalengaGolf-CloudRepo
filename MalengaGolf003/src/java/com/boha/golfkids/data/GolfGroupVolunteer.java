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
import javax.persistence.FetchType;
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

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "golfGroupVolunteer")
@NamedQueries({
    @NamedQuery(name = "GolfGroupVolunteer.findAll", query = "SELECT g FROM GolfGroupVolunteer g")})
public class GolfGroupVolunteer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "golfGroupVolunteerID")
    private Integer golfGroupVolunteerID;
    @Column(name = "dateRegistered")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistered;
    @JoinColumn(name = "golfGroupID", referencedColumnName = "golfGroupID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private GolfGroup golfGroup;
    @JoinColumn(name = "volunteerID", referencedColumnName = "volunteerID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Volunteer volunteer;

    public GolfGroupVolunteer() {
    }

    public GolfGroupVolunteer(Integer golfGroupVolunteerID) {
        this.golfGroupVolunteerID = golfGroupVolunteerID;
    }

    public Integer getGolfGroupVolunteerID() {
        return golfGroupVolunteerID;
    }

    public void setGolfGroupVolunteerID(Integer golfGroupVolunteerID) {
        this.golfGroupVolunteerID = golfGroupVolunteerID;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public GolfGroup getGolfGroup() {
        return golfGroup;
    }

    public void setGolfGroup(GolfGroup golfGroup) {
        this.golfGroup = golfGroup;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (golfGroupVolunteerID != null ? golfGroupVolunteerID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GolfGroupVolunteer)) {
            return false;
        }
        GolfGroupVolunteer other = (GolfGroupVolunteer) object;
        if ((this.golfGroupVolunteerID == null && other.golfGroupVolunteerID != null) || (this.golfGroupVolunteerID != null && !this.golfGroupVolunteerID.equals(other.golfGroupVolunteerID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.golfkids.data.GolfGroupVolunteer[ golfGroupVolunteerID=" + golfGroupVolunteerID + " ]";
    }
    
}
