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
import javax.validation.constraints.NotNull;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "golfGroupPlayer")
@NamedQueries({
    @NamedQuery(name = "GolfGroupPlayer.findAll", query = "SELECT g FROM GolfGroupPlayer g")})
public class GolfGroupPlayer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "golfGroupPlayerID")
    private Integer golfGroupPlayerID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateRegistered")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistered;
    @JoinColumn(name = "golfGroupID", referencedColumnName = "golfGroupID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private GolfGroup golfGroup;
    @JoinColumn(name = "playerID", referencedColumnName = "playerID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Player player;

    public GolfGroupPlayer() {
    }

    public GolfGroupPlayer(Integer golfGroupPlayerID) {
        this.golfGroupPlayerID = golfGroupPlayerID;
    }

    public GolfGroupPlayer(Integer golfGroupPlayerID, Date dateRegistered) {
        this.golfGroupPlayerID = golfGroupPlayerID;
        this.dateRegistered = dateRegistered;
    }

    public Integer getGolfGroupPlayerID() {
        return golfGroupPlayerID;
    }

    public void setGolfGroupPlayerID(Integer golfGroupPlayerID) {
        this.golfGroupPlayerID = golfGroupPlayerID;
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (golfGroupPlayerID != null ? golfGroupPlayerID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GolfGroupPlayer)) {
            return false;
        }
        GolfGroupPlayer other = (GolfGroupPlayer) object;
        if ((this.golfGroupPlayerID == null && other.golfGroupPlayerID != null) || (this.golfGroupPlayerID != null && !this.golfGroupPlayerID.equals(other.golfGroupPlayerID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.golfkids.data.GolfGroupPlayer[ golfGroupPlayerID=" + golfGroupPlayerID + " ]";
    }
    
}
