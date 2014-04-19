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
@Table(name = "teeTime")
@NamedQueries({
    @NamedQuery(name = "TeeTime.findByTournament", 
            query = "SELECT t FROM TeeTime t where t.tournament.tournamentID = :id order by t.golfRound, t.teeTime")})
public class TeeTime implements Serializable {
    @JoinColumn(name = "tournamentID", referencedColumnName = "tournamentID")
    @ManyToOne(optional = false)
    private Tournament tournament;
    @JoinColumn(name = "leaderBoardID", referencedColumnName = "leaderBoardID")
    @ManyToOne(optional = false)
    private LeaderBoard leaderBoard;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "teeTimeID")
    private Integer teeTimeID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "golfRound")
    private int golfRound;
    @Basic(optional = false)
    @NotNull
    @Column(name = "teeTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date teeTime;
    

    public TeeTime() {
    }

    public TeeTime(Integer teeTimeID) {
        this.teeTimeID = teeTimeID;
    }

    public TeeTime(Integer teeTimeID, int golfRound, Date teeTime) {
        this.teeTimeID = teeTimeID;
        this.golfRound = golfRound;
        this.teeTime = teeTime;
    }

    public Integer getTeeTimeID() {
        return teeTimeID;
    }

    public void setTeeTimeID(Integer teeTimeID) {
        this.teeTimeID = teeTimeID;
    }

    public int getGolfRound() {
        return golfRound;
    }

    public void setGolfRound(int golfRound) {
        this.golfRound = golfRound;
    }

    public Date getTeeTime() {
        return teeTime;
    }

    public void setTeeTime(Date teeTime) {
        this.teeTime = teeTime;
    }

 

  

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (teeTimeID != null ? teeTimeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TeeTime)) {
            return false;
        }
        TeeTime other = (TeeTime) object;
        if ((this.teeTimeID == null && other.teeTimeID != null) || (this.teeTimeID != null && !this.teeTimeID.equals(other.teeTimeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.golfkids.data.TeeTime[ teeTimeID=" + teeTimeID + " ]";
    }

    public LeaderBoard getLeaderBoard() {
        return leaderBoard;
    }

    public void setLeaderBoard(LeaderBoard leaderBoard) {
        this.leaderBoard = leaderBoard;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }


    
}
