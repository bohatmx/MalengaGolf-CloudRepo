/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.data;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "leaderBoard")
@NamedQueries({
    @NamedQuery(name = "LeaderBoard.findByTournament",
            query = "SELECT l FROM LeaderBoard l "
            + "where l.tournament.tournamentID = :id order by l.tournament.startDate desc"),
    @NamedQuery(name = "LeaderBoard.findByPlayer",
            query = "SELECT l FROM LeaderBoard l "
            + "WHERE l.player.playerID = :id order by l.tournament.startDate desc"),
    @NamedQuery(name = "LeaderBoard.findByGolfGroup",
            query = "SELECT l FROM LeaderBoard l "
            + "WHERE l.tournament.golfGroup.golfGroupID = :id "
            + "order by l.tournament.startDate desc")
})
public class LeaderBoard implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "leaderBoardID")
    private int leaderBoardID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "position")
    private int position;
    @Column(name = "tied")
    private int tied;
    @Basic(optional = false)
    @NotNull
    @Column(name = "parStatus")
    private int parStatus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "scoreRound1")
    private int scoreRound1;
    @Column(name = "scoreRound2")
    private int scoreRound2;
    @Column(name = "scoreRound3")
    private int scoreRound3;
    @Column(name = "scoreRound4")
    private int scoreRound4;
    @Column(name = "scoreRound5")
    private int scoreRound5;
    @Column(name = "scoreRound6")
    private int scoreRound6;
    @Basic(optional = false)
    @NotNull
    @Column(name = "totalScore")
    private int totalScore;
    @JoinColumn(name = "playerID", referencedColumnName = "playerID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Player player;
    @JoinColumn(name = "tournamentID", referencedColumnName = "tournamentID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tournament tournament;

    public LeaderBoard() {
    }

    public LeaderBoard(int leaderBoardID) {
        this.leaderBoardID = leaderBoardID;
    }

    public LeaderBoard(int leaderBoardID, int position, int parStatus, int scoreRound1, int totalScore) {
        this.leaderBoardID = leaderBoardID;
        this.position = position;
        this.parStatus = parStatus;
        this.scoreRound1 = scoreRound1;
        this.totalScore = totalScore;
    }

    public int getLeaderBoardID() {
        return leaderBoardID;
    }

    public void setLeaderBoardID(int leaderBoardID) {
        this.leaderBoardID = leaderBoardID;
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getTied() {
        return tied;
    }

    public void setTied(int tied) {
        this.tied = tied;
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

    public int getScoreRound2() {
        return scoreRound2;
    }

    public void setScoreRound2(int scoreRound2) {
        this.scoreRound2 = scoreRound2;
    }

    public int getScoreRound3() {
        return scoreRound3;
    }

    public void setScoreRound3(int scoreRound3) {
        this.scoreRound3 = scoreRound3;
    }

    public int getScoreRound4() {
        return scoreRound4;
    }

    public void setScoreRound4(int scoreRound4) {
        this.scoreRound4 = scoreRound4;
    }

    public int getScoreRound5() {
        return scoreRound5;
    }

    public void setScoreRound5(int scoreRound5) {
        this.scoreRound5 = scoreRound5;
    }

    public int getScoreRound6() {
        return scoreRound6;
    }

    public void setScoreRound6(int scoreRound6) {
        this.scoreRound6 = scoreRound6;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    @Override
    public String toString() {
        return "com.boha.golfkids.data.LeaderBoard[ leaderBoard=" + leaderBoardID + " ]";
    }

}
