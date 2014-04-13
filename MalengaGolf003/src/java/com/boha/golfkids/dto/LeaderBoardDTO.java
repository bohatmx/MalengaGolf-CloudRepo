/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.golfkids.dto;

/**
 *
 * @author Aubrey Malabie
 */
public class LeaderBoardDTO implements Comparable<LeaderBoardDTO> {

    private int position;
    private PlayerDTO player;
    
    private int 
            scoreRound1, 
            scoreRound2, 
            scoreRound3, 
            scoreRound4, 
            scoreRound5, 
            scoreRound6,
            totalScore;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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
        totalScore = scoreRound1 + scoreRound2 + scoreRound4 +
        scoreRound5 + scoreRound3 + scoreRound6;
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    @Override
    public int compareTo(LeaderBoardDTO t) {
        
        if (getTotalScore() < t.getTotalScore()) {
            return -1;
        }
         if (getTotalScore() > t.getTotalScore()) {
            return 1;
        }
        
        return 0;
    }
    
}
