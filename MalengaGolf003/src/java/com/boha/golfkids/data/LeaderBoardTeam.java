/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "leaderBoardTeam")
@NamedQueries({
    @NamedQuery(name = "LeaderBoardTeam.findAll", query = "SELECT l FROM LeaderBoardTeam l")
   })
public class LeaderBoardTeam implements Serializable {
    @Column(name = "winnerFlag")
    private Integer winnerFlag;
    @Column(name = "tied")
    private Integer tied;
    @Column(name = "scoreRound2")
    private Integer scoreRound2;
    @Column(name = "scoreRound3")
    private Integer scoreRound3;
    @Column(name = "scoreRound4")
    private Integer scoreRound4;
    @Column(name = "scoreRound5")
    private Integer scoreRound5;
    @Column(name = "scoreRound6")
    private Integer scoreRound6;
    @Column(name = "pointsRound2")
    private Integer pointsRound2;
    @Column(name = "pointsRound3")
    private Integer pointsRound3;
    @Column(name = "pointsRound4")
    private Integer pointsRound4;
    @Column(name = "pointsRound5")
    private Integer pointsRound5;
    @Column(name = "pointsRound6")
    private Integer pointsRound6;
    @Column(name = "totalPoints")
    private Integer totalPoints;
    @Column(name = "scoringComplete")
    private Integer scoringComplete;
    @Column(name = "withDrawn")
    private Integer withDrawn;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "leaderBoardTeamID")
    private int leaderBoardTeamID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "position")
    private int position;
    @Basic(optional = false)
    @NotNull
    @Column(name = "parStatus")
    private int parStatus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "scoreRound1")
    private int scoreRound1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "totalScore")
    private int totalScore;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pointsRound1")
    private int pointsRound1;
    @Column(name = "dateRegistered")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistered;
    @Basic(optional = false)
    @NotNull
    @Column(name = "orderOfMeritPoints")
    private int orderOfMeritPoints;
    @JoinColumn(name = "teamID", referencedColumnName = "teamID")
    @ManyToOne(optional = false)
    private Team team;
    @JoinColumn(name = "tournamentID", referencedColumnName = "tournamentID")
    @ManyToOne(optional = false)
    private Tournament tournament;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "leaderBoardTeam")
    private List<TourneyScoreByRoundTeam> tourneyScoreByRoundTeamList;

    public LeaderBoardTeam() {
    }

    public LeaderBoardTeam(int leaderBoardTeamID) {
        this.leaderBoardTeamID = leaderBoardTeamID;
    }

    public LeaderBoardTeam(int leaderBoardTeamID, int position, int parStatus, int scoreRound1, int totalScore, int pointsRound1, int orderOfMeritPoints) {
        this.leaderBoardTeamID = leaderBoardTeamID;
        this.position = position;
        this.parStatus = parStatus;
        this.scoreRound1 = scoreRound1;
        this.totalScore = totalScore;
        this.pointsRound1 = pointsRound1;
        this.orderOfMeritPoints = orderOfMeritPoints;
    }

    public int getLeaderBoardTeamID() {
        return leaderBoardTeamID;
    }

    public void setLeaderBoardTeamID(int leaderBoardTeamID) {
        this.leaderBoardTeamID = leaderBoardTeamID;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    public int getParStatus() {
        return parStatus;
    }

    public void setParStatus(int parStatus) {
        this.parStatus = parStatus;
    }

    public int getScoreRound1() {
        return scoreRound1;
    }

    public void setScoreRound1(int scoreRound1) {
        this.scoreRound1 = scoreRound1;
    }


    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getPointsRound1() {
        return pointsRound1;
    }

    public void setPointsRound1(int pointsRound1) {
        this.pointsRound1 = pointsRound1;
    }


    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public int getOrderOfMeritPoints() {
        return orderOfMeritPoints;
    }

    public void setOrderOfMeritPoints(int orderOfMeritPoints) {
        this.orderOfMeritPoints = orderOfMeritPoints;
    }


    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

  

    public List<TourneyScoreByRoundTeam> getTourneyScoreByRoundTeamList() {
        return tourneyScoreByRoundTeamList;
    }

    public void setTourneyScoreByRoundTeamList(List<TourneyScoreByRoundTeam> tourneyScoreByRoundTeamList) {
        this.tourneyScoreByRoundTeamList = tourneyScoreByRoundTeamList;
    }

   

    @Override
    public String toString() {
        return "com.boha.golfkids.data.LeaderBoardTeam[ leaderBoardTeamID=" + leaderBoardTeamID + " ]";
    }

    public Integer getWinnerFlag() {
        return winnerFlag;
    }

    public void setWinnerFlag(Integer winnerFlag) {
        this.winnerFlag = winnerFlag;
    }

    public Integer getTied() {
        return tied;
    }

    public void setTied(Integer tied) {
        this.tied = tied;
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

    public Integer getScoreRound5() {
        return scoreRound5;
    }

    public void setScoreRound5(Integer scoreRound5) {
        this.scoreRound5 = scoreRound5;
    }

    public Integer getScoreRound6() {
        return scoreRound6;
    }

    public void setScoreRound6(Integer scoreRound6) {
        this.scoreRound6 = scoreRound6;
    }

    public Integer getPointsRound2() {
        return pointsRound2;
    }

    public void setPointsRound2(Integer pointsRound2) {
        this.pointsRound2 = pointsRound2;
    }

    public Integer getPointsRound3() {
        return pointsRound3;
    }

    public void setPointsRound3(Integer pointsRound3) {
        this.pointsRound3 = pointsRound3;
    }

    public Integer getPointsRound4() {
        return pointsRound4;
    }

    public void setPointsRound4(Integer pointsRound4) {
        this.pointsRound4 = pointsRound4;
    }

    public Integer getPointsRound5() {
        return pointsRound5;
    }

    public void setPointsRound5(Integer pointsRound5) {
        this.pointsRound5 = pointsRound5;
    }

    public Integer getPointsRound6() {
        return pointsRound6;
    }

    public void setPointsRound6(Integer pointsRound6) {  
        this.pointsRound6 = pointsRound6;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Integer getScoringComplete() {   
        return scoringComplete;
    }

    public void setScoringComplete(Integer scoringComplete) {
        this.scoringComplete = scoringComplete;
    }

    public Integer getWithDrawn() {
        return withDrawn;
    }

    public void setWithDrawn(Integer withDrawn) {
        this.withDrawn = withDrawn;
    }
    
}
