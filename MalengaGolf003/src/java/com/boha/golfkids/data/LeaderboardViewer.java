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
@Table(name = "leaderboardViewer")
@NamedQueries({
    @NamedQuery(name = "LeaderboardViewer.findByAppUser", 
            query = "SELECT l FROM LeaderboardViewer l where l.tournament.tournamentID = :tid "
                    + "and l.appUser.appUserID = :id"),
    @NamedQuery(name = "LeaderboardViewer.findByAdmin", 
            query = "SELECT l FROM LeaderboardViewer l WHERE l.administrator.administratorID = :id "
                    + "and l.tournament.tournamentID = :tid"),
    @NamedQuery(name = "LeaderboardViewer.findByScorer", 
            query = "SELECT l FROM LeaderboardViewer l WHERE l.scorer.scorerID = :id "
                    + "and l.tournament.tournamentID = :tid"),
    @NamedQuery(name = "LeaderboardViewer.findByTournament", 
            query = "SELECT l FROM LeaderboardViewer l WHERE l.tournament.tournamentID = :tid"),
    @NamedQuery(name = "LeaderboardViewer.findByPlayer", 
            query = "SELECT l FROM LeaderboardViewer l WHERE l.player.playerID = :id "
                    + "and l.tournament.tournamentID = :tid")
})
public class LeaderboardViewer implements Serializable {
    @JoinColumn(name = "scorerID", referencedColumnName = "scorerID")
    @ManyToOne
    private Scorer scorer;
    @JoinColumn(name = "administratorID", referencedColumnName = "administratorID")
    @ManyToOne
    private Administrator administrator;
    @JoinColumn(name = "appUserID", referencedColumnName = "appUserID")
    @ManyToOne
    private AppUser appUser;
    @JoinColumn(name = "playerID", referencedColumnName = "playerID")
    @ManyToOne
    private Player player;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "leaderboardViewerID")
    private Integer leaderboardViewerID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "sessionID")
    private String sessionID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sessionDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sessionDate;
    @JoinColumn(name = "tournamentID", referencedColumnName = "tournamentID")
    @ManyToOne(optional = false)
    private Tournament tournament;

    public LeaderboardViewer() {
    }

    public LeaderboardViewer(Integer leaderboardViewerID) {
        this.leaderboardViewerID = leaderboardViewerID;
    }

    public LeaderboardViewer(Integer leaderboardViewerID, String sessionID, Date sessionDate) {
        this.leaderboardViewerID = leaderboardViewerID;
        this.sessionID = sessionID;
        this.sessionDate = sessionDate;
    }

    public Integer getLeaderboardViewerID() {
        return leaderboardViewerID;
    }

    public void setLeaderboardViewerID(Integer leaderboardViewerID) {
        this.leaderboardViewerID = leaderboardViewerID;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (leaderboardViewerID != null ? leaderboardViewerID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LeaderboardViewer)) {
            return false;
        }
        LeaderboardViewer other = (LeaderboardViewer) object;
        if ((this.leaderboardViewerID == null && other.leaderboardViewerID != null) || (this.leaderboardViewerID != null && !this.leaderboardViewerID.equals(other.leaderboardViewerID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.golfkids.data.LeaderboardViewer[ leaderboardViewerID=" + leaderboardViewerID + " ]";
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

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    
    
}
