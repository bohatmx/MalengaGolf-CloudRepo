/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.dto;

import com.boha.golfkids.data.LeaderBoard;
import com.boha.golfkids.data.Tournament;
import java.util.List;

/**
 *
 * @author Aubrey Malabie
 */
public class LeaderBoardDTO implements Comparable<LeaderBoardDTO> {

    private int leaderBoardID, position, parStatus, tournamentID;
    private PlayerDTO player;
    private AgeGroupDTO ageGroup;
    private boolean tied;
    private int rounds, lastHole, holesPerRound, currentRoundStatus;
    private long startDate;
    private String tournamentName, clubName;
    private List<TourneyScoreByRoundDTO> tourneyScoreByRoundList;
    private int winnerFlag;
    private int scoreRound1,
            scoreRound2,
            scoreRound3,
            scoreRound4,
            scoreRound5,
            scoreRound6,
            totalScore;

    public LeaderBoardDTO() {
    }

    public LeaderBoardDTO(LeaderBoard a) {
        setBasics(a);

    }

    public LeaderBoardDTO(LeaderBoard a, boolean getDetail) {
        setBasics(a);
        if (getDetail) {
            tournamentName = a.getTournament().getTourneyName();
        }
    }

    private void setBasics(LeaderBoard a) {
        leaderBoardID = a.getLeaderBoardID();
        winnerFlag = a.getWinnerFlag();
        player = new PlayerDTO(a.getPlayer());
        position = a.getPosition();
        parStatus = a.getParStatus();
        Tournament t = a.getTournament();
        startDate = t.getStartDate().getTime();
        tournamentID = t.getTournamentID();
        holesPerRound = t.getHolesPerRound();
        clubName = t.getClub().getClubName();
        if (a.getAgeGroup() != null) {
            ageGroup = new AgeGroupDTO(a.getAgeGroup());
        }
        if (a.getTied() > 0) {
            tied = true;
        }
        scoreRound1 = a.getScoreRound1();
        scoreRound2 = a.getScoreRound2();
        scoreRound3 = a.getScoreRound3();
        scoreRound4 = a.getScoreRound4();
        scoreRound5 = a.getScoreRound5();
        scoreRound6 = a.getScoreRound6();
        totalScore = a.getTotalScore();
        rounds = a.getTournament().getGolfRounds();
    }

    public int getPosition() {
        return position;
    }

    public AgeGroupDTO getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroupDTO ageGroup) {
        this.ageGroup = ageGroup;
    }

    public int getCurrentRoundStatus() {
        return currentRoundStatus;
    }

    public void setCurrentRoundStatus(int currentRoundStatus) {
        this.currentRoundStatus = currentRoundStatus;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public int getWinnerFlag() {
        return winnerFlag;
    }

    public void setWinnerFlag(int winnerFlag) {
        this.winnerFlag = winnerFlag;
    }

    public int getLastHole() {
        return lastHole;
    }

    public void setLastHole(int lastHole) {
        this.lastHole = lastHole;
    }

    public int getHolesPerRound() {
        return holesPerRound;
    }

    public void setHolesPerRound(int holesPerRound) {
        this.holesPerRound = holesPerRound;
    }

    public int getLeaderBoardID() {
        return leaderBoardID;
    }

    public int getRounds() {
        return rounds;
    }

    public long getStartDate() {
        return startDate;
    }

    public int getTournamentID() {
        return tournamentID;
    }

    public void setTournamentID(int tournamentID) {
        this.tournamentID = tournamentID;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public void setLeaderBoardID(int leaderBoardID) {
        this.leaderBoardID = leaderBoardID;
    }

    public List<TourneyScoreByRoundDTO> getTourneyScoreByRoundList() {
        return tourneyScoreByRoundList;
    }

    public void setTourneyScoreByRoundList(List<TourneyScoreByRoundDTO> tourneyScoreByRoundList) {
        this.tourneyScoreByRoundList = tourneyScoreByRoundList;
    }

    public boolean isTied() {
        return tied;
    }

    public void setTied(boolean tied) {
        this.tied = tied;
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

    public PlayerDTO getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDTO player) {
        this.player = player;
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
        totalScore = scoreRound1 + scoreRound2 + scoreRound4
                + scoreRound5 + scoreRound3 + scoreRound6;
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    @Override
    public int compareTo(LeaderBoardDTO t) {

        if (this.getParStatus() < t.getParStatus()) {
            return 1;
        }
        if (this.getParStatus() > t.getParStatus()) {
            return -1;
        }

        return 0;
    }
    public static final int NO_PAR_STATUS = 9999,
            WINNER_BY_PLAYOFF = 2,
            WINNER_BY_COUNT_OUT = 1;

}
