/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.dto;

import com.boha.golfkids.data.TeeTime;

/**
 *
 * @author aubreyM
 */
public class TeeTimeDTO {

    private int teeTimeID;
    private int golfRound;
    private long teeTime;
    private int leaderBoardID;
    
    public TeeTimeDTO(TeeTime a) {
        
        teeTimeID = a.getTeeTimeID();
        golfRound = a.getGolfRound();
        teeTime = a.getTeeTime().getTime();
        leaderBoardID = a.getLeaderBoard().getLeaderBoardID();
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

    public long getTeeTime() {
        return teeTime;
    }

    public void setTeeTime(long teeTime) {
        this.teeTime = teeTime;
    }

    public int getLeaderBoardID() {
        return leaderBoardID;
    }

    public void setLeaderBoardID(int leaderBoardID) {
        this.leaderBoardID = leaderBoardID;
    }

   
}
