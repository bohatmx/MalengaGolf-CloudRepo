/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.dto;

import java.util.List;

/**
 *
 * @author Aubrey Malabie
 */
public class RequestDTO {

    private String email, pin;
    private int golfGroupID;
    private int tournamentID, playerID, countryID, provinceID, clubCourseID;
    private GolfGroupDTO golfGroup;
    private TournamentDTO tournament;
    private PlayerDTO player;
    private AdministratorDTO administrator;
    private List<PlayerDTO> playerList;
    private ParentDTO parent;
    private ClubCourseDTO clubCourse;
    private ClubDTO club;
    private List<TourneyPlayerScoreDTO> players;
    private List<TourneyScoreByRoundDTO> tourneyScoreByRoundList;
    private List<TeeTimeDTO> teeTimeList;
    private int requestType;
    private boolean zippedResponse;
    //
    public static final int ADMIN_LOGIN = 1;
    public static final int ADD_GOLF_GROUP = 2;
    public static final int UPDATE_GOLF_GROUP = 3;
    public static final int ADD_TOURNAMENT = 4;
    public static final int UPDATE_TOURNAMENT = 5;
    public static final int ADD_TOURNAMENT_PLAYERS = 6;
    public static final int UPDATE_TOURNAMENT_SCORES = 7;
    public static final int ADD_PARENT = 8;
    public static final int UPDATE_PARENT = 9;
    public static final int GET_LEADERBOARD = 10;
    public static final int ADD_CLUB = 11;
    public static final int UPDATE_CLUB = 12;
    public static final int ADD_ADMINISTRATOR = 13;
    public static final int UPDATE_ADMINISTRATOR = 14;
    public static final int ADD_PLAYER = 15;
    public static final int UPDATE_PLAYER = 16;
    public static final int UPDATE_PLAYER_PARENT = 17;
    
    public static final int GET_CLUBS_IN_PROVINCE = 18;
    public static final int GET_CLUBS_IN_COUNTRY = 19;
    public static final int GET_AGE_GROUPS = 20;
    public static final int GET_AGE_GROUPS_BOYS = 21;
    public static final int GET_AGE_GROUPS_GIRLS = 22;
    public static final int GET_LEADERBOARD_BOYS = 23;
    public static final int GET_LEADERBOARD_GIRLS = 24;
    public static final int GET_COUNTRIES = 25;
    public static final int ADD_CLUB_COURSE = 30;
    public static final int UPDATE_CLUB_COURSE = 31;
    
    public static final int ADD_TEE_TIMES = 32;
    public static final int GET_TOURNAMENT_SCORES = 33;
    public static final int UPDATE_TEE_TIMES = 34;
    
    public static final int REMOVE_TOURNAMENT_PLAYER = 35;
    public static final int UPDATE_TOURNAMENT_SCORE_TOTALS = 36;
    public static final int GET_TEE_TIMES = 37;
    //

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public int getProvinceID() {
        return provinceID;
    }

    public PlayerDTO getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDTO player) {
        this.player = player;
    }

    public void setProvinceID(int provinceID) {
        this.provinceID = provinceID;
    }

    public ClubDTO getClub() {
        return club;
    }

    public void setClub(ClubDTO club) {
        this.club = club;
    }

    public ClubCourseDTO getClubCourse() {
        return clubCourse;
    }

    public void setClubCourse(ClubCourseDTO clubCourse) {
        this.clubCourse = clubCourse;
    }

    public List<TourneyPlayerScoreDTO> getPlayers() {
        return players;
    }

    public List<TourneyScoreByRoundDTO> getTourneyScoreByRoundList() {
        return tourneyScoreByRoundList;
    }

    public int getClubCourseID() {
        return clubCourseID;
    }

    public void setClubCourseID(int clubCourseID) {
        this.clubCourseID = clubCourseID;
    }

    public void setTourneyScoreByRoundList(List<TourneyScoreByRoundDTO> tourneyScoreByRoundList) {
        this.tourneyScoreByRoundList = tourneyScoreByRoundList;
    }

    public List<TeeTimeDTO> getTeeTimeList() {
        return teeTimeList;
    }

    public void setTeeTimeList(List<TeeTimeDTO> teeTimeList) {
        this.teeTimeList = teeTimeList;
    }

    public List<PlayerDTO> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<PlayerDTO> playerList) {
        this.playerList = playerList;
    }

    public void setPlayers(List<TourneyPlayerScoreDTO> players) {
        this.players = players;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public int getGolfGroupID() {
        return golfGroupID;
    }

    public boolean isZippedResponse() {
        return zippedResponse;
    }

    public void setZippedResponse(boolean zippedResponse) {
        this.zippedResponse = zippedResponse;
    }

    public void setGolfGroupID(int golfGroupID) {
        this.golfGroupID = golfGroupID;
    }

    public int getTournamentID() {
        return tournamentID;
    }

    public void setTournamentID(int tournamentID) {
        this.tournamentID = tournamentID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public GolfGroupDTO getGolfGroup() {
        return golfGroup;
    }

    public void setGolfGroup(GolfGroupDTO golfGroup) {
        this.golfGroup = golfGroup;
    }

    public TournamentDTO getTournament() {
        return tournament;
    }

    public void setTournament(TournamentDTO tournament) {
        this.tournament = tournament;
    }

    public AdministratorDTO getAdministrator() {
        return administrator;
    }

    public void setAdministrator(AdministratorDTO administrator) {
        this.administrator = administrator;
    }

    public ParentDTO getParent() {
        return parent;
    }

    public void setParent(ParentDTO parent) {
        this.parent = parent;
    }

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }
}
