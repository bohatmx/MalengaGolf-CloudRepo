/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "tourneyPlayerScore")

public class TourneyPlayerScore implements Serializable {
    @Column(name = "scoreRound1")
    private Integer scoreRound1;
    @Column(name = "scoreRound2")
    private Integer scoreRound2;
    @Column(name = "scoreRound3")
    private Integer scoreRound3;
    @Column(name = "scoreRound4")
    private Integer scoreRound4;
    @Column(name = "tourneyPosition")
    private Integer tourneyPosition;
    @Column(name = "tourneyPositionTied")
    private Integer tourneyPositionTied;
    @Column(name = "winnerFlag")
    private Integer winnerFlag;
    @Column(name = "playerID")
    private Integer playerID;
    @Column(name = "tournamentID")
    private Integer tournamentID;
    @Column(name = "ageGroupID")
    private Integer ageGroupID;
    @Column(name = "administratorID")
    private Integer administratorID;
    @Column(name = "paidFlag")
    private Integer paidFlag;
    @Column(name = "totalScore")
    private Integer totalScore;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tourneyPlayerScoreID")
    private int tourneyPlayerScoreID;
    @Column(name = "dateRegistered")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistered;
    @Column(name = "dateUpdated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;
    
    @JoinColumn(name = "administratorID", referencedColumnName = "administratorID")
    @ManyToOne
    private Administrator administrator;
    @JoinColumn(name = "ageGroupID", referencedColumnName = "ageGroupID")
    @ManyToOne
    private AgeGroup ageGroup;
    @JoinColumn(name = "playerID", referencedColumnName = "playerID")
    @ManyToOne
    private Player player;
    @JoinColumn(name = "tournamentID", referencedColumnName = "tournamentID")
    @ManyToOne
    private Tournament tournament;
//
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tourneyPlayerScore")
    private List<TourneyScoreByRound> tourneyScoreByRounds;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tourneyPlayerScore")
    private List<TeeTime> teeTimes;

    public TourneyPlayerScore() {
    }

    public TourneyPlayerScore(int tourneyPlayerScoreID) {
        this.tourneyPlayerScoreID = tourneyPlayerScoreID;
    }

    public List<TeeTime> getTeeTimes() {
        return teeTimes;
    }

    public void setTeeTimes(List<TeeTime> teeTimes) {
        this.teeTimes = teeTimes;
    }

    public int getTourneyPlayerScoreID() {
        return tourneyPlayerScoreID;
    }

    public void setTourneyPlayerScoreID(int tourneyPlayerScoreID) {
        this.tourneyPlayerScoreID = tourneyPlayerScoreID;
    }

    public List<TourneyScoreByRound> getTourneyScoreByRounds() {
        return tourneyScoreByRounds;
    }

    public void setTourneyScoreByRounds(List<TourneyScoreByRound> tourneyScoreByRounds) {
        this.tourneyScoreByRounds = tourneyScoreByRounds;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

   

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Integer getScoreRound1() {
        return scoreRound1;
    }

    public void setScoreRound1(Integer scoreRound1) {
        this.scoreRound1 = scoreRound1;
    }

    public Integer getScoreRound2() {
        return scoreRound2;
    }

    public void setScoreRound2(Integer scoreRound2) {
        this.scoreRound2 = scoreRound2;
    }

    public Integer getScoreRound3() {
        return scoreRound3;
    }

    public void setScoreRound3(Integer scoreRound3) {
        this.scoreRound3 = scoreRound3;
    }

    public Integer getScoreRound4() {
        return scoreRound4;
    }

    public void setScoreRound4(Integer scoreRound4) {
        this.scoreRound4 = scoreRound4;
    }

    public Integer getTourneyPosition() {
        return tourneyPosition;
    }

    public void setTourneyPosition(Integer tourneyPosition) {
        this.tourneyPosition = tourneyPosition;
    }

    public Integer getTourneyPositionTied() {
        return tourneyPositionTied;
    }

    public void setTourneyPositionTied(Integer tourneyPositionTied) {
        this.tourneyPositionTied = tourneyPositionTied;
    }

    public Integer getWinnerFlag() {
        return winnerFlag;
    }

    public void setWinnerFlag(Integer winnerFlag) {
        this.winnerFlag = winnerFlag;
    }

    public Integer getPlayerID() {
        return playerID;
    }

    public void setPlayerID(Integer playerID) {
        this.playerID = playerID;
    }

    public Integer getTournamentID() {
        return tournamentID;
    }

    public void setTournamentID(Integer tournamentID) {
        this.tournamentID = tournamentID;
    }

    public Integer getAgeGroupID() {
        return ageGroupID;
    }

    public void setAgeGroupID(Integer ageGroupID) {
        this.ageGroupID = ageGroupID;
    }

    public Integer getAdministratorID() {
        return administratorID;
    }

    public void setAdministratorID(Integer administratorID) {
        this.administratorID = administratorID;
    }

    public Integer getPaidFlag() {
        return paidFlag;
    }

    public void setPaidFlag(Integer paidFlag) {
        this.paidFlag = paidFlag;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    
    
}
