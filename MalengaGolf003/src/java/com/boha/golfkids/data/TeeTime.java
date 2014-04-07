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

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "teeTime")
@NamedQueries({
    @NamedQuery(name = "TeeTime.findByTournament", 
        query = "select a from TeeTime a   "
                    + " where a.tourneyPlayerScore.tournament.tournamentID = :id "
                    + " order by a.teeTime ") 
    })
public class TeeTime implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "teeTimeID")
    private int teeTimeID;
    
    @Column(name = "golfRound")
    private int golfRound;
    
    @Column(name = "teeTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date teeTime;
        
    @JoinColumn(name = "tourneyPlayerScoreID", referencedColumnName = "tourneyPlayerScoreID")
    @ManyToOne
    private TourneyPlayerScore tourneyPlayerScore;
    public TeeTime() {
    }

    public TeeTime(int teeTimeID) {
        this.teeTimeID = teeTimeID;
    }

    public int getTeeTimeID() {
        return teeTimeID;
    }

    public void setTeeTimeID(int teeTimeID) {
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

    public TourneyPlayerScore getTourneyPlayerScore() {
        return tourneyPlayerScore;
    }

    public void setTourneyPlayerScore(TourneyPlayerScore tourneyPlayerScore) {
        this.tourneyPlayerScore = tourneyPlayerScore;
    }

      
    
}
