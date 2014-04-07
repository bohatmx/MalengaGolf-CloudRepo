/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.gateway;

/**
 *
 * @author aubreyM
 */
public class GenRequest {
    private int requestType, year, month, day, 
            administratorID, golfGroupID, rounds, clubID;
    private String tournamentName;
    
    public static final int GENERATE_PLAYERS = 1;
    public static final int GENERATE_TOURNAMENT = 2;

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }

    public int getGolfGroupID() {
        return golfGroupID;
    }

    public void setGolfGroupID(int golfGroupID) {
        this.golfGroupID = golfGroupID;
    }

    public int getYear() {
        return year;
    }

    public int getAdministratorID() {
        return administratorID;
    }

    public void setAdministratorID(int administratorID) {
        this.administratorID = administratorID;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getClubID() {
        return clubID;
    }

    public void setClubID(int clubID) {
        this.clubID = clubID;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }
    
}
