/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.dto;

import com.boha.golfkids.data.TeeTime;
import com.boha.golfkids.data.TourneyPlayerScore;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aubrey Malabie
 */
public class TourneyPlayerScoreDTO implements Comparable<TourneyPlayerScoreDTO> {

    public TourneyPlayerScoreDTO() {
        
    }
    private int tourneyPlayerScoreID;
    private long dateRegistered;
    private long dateUpdated, teeTime;
    private int paidFlag;
    private int scoreRound1;
    private int scoreRound2;
    private int scoreRound3;
    private int scoreRound4;
    private int totalScore;
    private int tourneyPosition;
    private int tourneyPositionTied;
    private int winnerFlag;
    private AgeGroupDTO ageGroup;
    private PlayerDTO player;
    private int tournamentID;
    private AdministratorDTO administrator;
    private boolean orderByName;
    private String fullName;
    private List<TeeTimeDTO> teeTimeList;

    public TourneyPlayerScoreDTO(TourneyPlayerScore a, boolean getPlayerData, boolean orderByFullName) {
        tourneyPlayerScoreID = a.getTourneyPlayerScoreID();
        tournamentID = a.getTournament().getTournamentID();
        dateRegistered = a.getDateRegistered().getTime();
        if (a.getDateUpdated() != null) {
            dateUpdated = a.getDateUpdated().getTime();
        }
        paidFlag = a.getPaidFlag();
        scoreRound1 = a.getScoreRound1();
        scoreRound2 = a.getScoreRound2();
        scoreRound3 = a.getScoreRound3();
        scoreRound4 = a.getScoreRound4();
        totalScore = a.getTotalScore();
        tourneyPosition = a.getTourneyPosition();
        tourneyPositionTied = a.getTourneyPositionTied();
        orderByName = orderByFullName;
    
        if (a.getAgeGroup() != null) {
            ageGroup = new AgeGroupDTO(a.getAgeGroup());
        }
        if (getPlayerData) {
            if (a.getPlayer() != null) {
                player = new PlayerDTO(a.getPlayer());
            }
        }
        if (a.getAdministrator() != null) {
            administrator = new AdministratorDTO(a.getAdministrator());
        }
        if (a.getTeeTimeList()!= null) {
            teeTimeList = new ArrayList<>();
            for (TeeTime tt : a.getTeeTimeList()) {
                teeTimeList.add(new TeeTimeDTO(tt));
            }
        }
    }

    public long getTeeTime() {
        return teeTime;
    }

    public void setTeeTime(long teeTime) {
        this.teeTime = teeTime;
    }

    public boolean isOrderByName() {
        return orderByName;
    }

    public void setOrderByName(boolean orderByName) {
        this.orderByName = orderByName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public AdministratorDTO getAdministrator() {
        return administrator;
    }

    public void setAdministrator(AdministratorDTO administrator) {
        this.administrator = administrator;
    }

    public PlayerDTO getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDTO player) {
        this.player = player;
    }

    public int getTournamentID() {
        return tournamentID;
    }

    public void setTournamentID(int tournamentID) {
        this.tournamentID = tournamentID;
    }

    public int getTourneyPlayerScoreID() {
        return tourneyPlayerScoreID;
    }

    public void setTourneyPlayerScoreID(int tourneyPlayerID) {
        this.tourneyPlayerScoreID = tourneyPlayerID;
    }

    public long getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(long dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public long getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(long dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public int getPaidFlag() {
        return paidFlag;
    }

    public void setPaidFlag(int paidFlag) {
        this.paidFlag = paidFlag;
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

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getTourneyPosition() {
        return tourneyPosition;
    }

    public void setTourneyPosition(int tourneyPosition) {
        this.tourneyPosition = tourneyPosition;
    }

    public int getTourneyPositionTied() {
        return tourneyPositionTied;
    }

    public void setTourneyPositionTied(int tourneyPositionTied) {
        this.tourneyPositionTied = tourneyPositionTied;
    }

    public int getWinnerFlag() {
        return winnerFlag;
    }

    public void setWinnerFlag(int winnerFlag) {
        this.winnerFlag = winnerFlag;
    }

    public AgeGroupDTO getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroupDTO ageGroup) {
        this.ageGroup = ageGroup;
    }

    @Override
    public int compareTo(TourneyPlayerScoreDTO t) {
        if (orderByName) {
            String fName = player.getLastName() + ", " + player.getFirstName();
            String s = t.getPlayer().getLastName() + ", " + t.getPlayer().getFirstName();
            return fName.compareToIgnoreCase(s);
        } else {
            if (totalScore < t.totalScore) {
                return 1;
            }
            if (totalScore > t.totalScore) {
                return 1;
            }
        }

        return 0;
    }
}
