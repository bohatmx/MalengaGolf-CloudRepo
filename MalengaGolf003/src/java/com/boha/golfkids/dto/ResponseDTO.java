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
public class ResponseDTO {

    private int statusCode;
    private String message;
    private List<GolfGroupDTO> golfGroups;
    private List<ParentDTO> parents;
    private List<PlayerDTO> players;
    private List<TourneyPlayerScoreDTO> tourneyPlayers;
    private List<TourneyScoreByRoundDTO> tourneyScoreByRoundList;
    private List<TeeTimeDTO> teeTimeList;
    private List<TournamentDTO> tournaments;
    private List<LeaderBoardDTO> leaderBoard;
    private List<CountryDTO> countries;
    private List<ProvinceDTO> province;
    private List<ClubDTO> clubs;
    private List<AgeGroupDTO> ageGroups;
    private List<AdministratorDTO> administrators;
    private ClubCourseDTO clubCourse;
    //
    public static final int LOGIN_EXCEPTION = 101;
    public static final int DATA_EXCEPTION = 102;
    public static final int DUPLICATE_EXCEPTION = 103;
    public static final int GENERIC_EXCEPTION = 109;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<GolfGroupDTO> getGolfGroups() {
        return golfGroups;
    }

    public void setGolfGroups(List<GolfGroupDTO> golfGroups) {
        this.golfGroups = golfGroups;
    }

    public List<TourneyScoreByRoundDTO> getTourneyScoreByRoundList() {
        return tourneyScoreByRoundList;
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

    public ClubCourseDTO getClubCourse() {
        return clubCourse;
    }

    public void setClubCourse(ClubCourseDTO clubCourse) {
        this.clubCourse = clubCourse;
    }

    public List<ParentDTO> getParents() {
        return parents;
    }

    public void setParents(List<ParentDTO> parents) {
        this.parents = parents;
    }

    public List<PlayerDTO> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDTO> players) {
        this.players = players;
    }

    public List<TourneyPlayerScoreDTO> getTourneyPlayers() {
        return tourneyPlayers;
    }

    public void setTourneyPlayers(List<TourneyPlayerScoreDTO> tourneyPlayers) {
        this.tourneyPlayers = tourneyPlayers;
    }

    public List<TournamentDTO> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<TournamentDTO> tournaments) {
        this.tournaments = tournaments;
    }

    public List<LeaderBoardDTO> getLeaderBoard() {
        return leaderBoard;
    }

    public void setLeaderBoard(List<LeaderBoardDTO> leaderBoard) {
        this.leaderBoard = leaderBoard;
    }

    public List<CountryDTO> getCountries() {
        return countries;
    }

    public void setCountries(List<CountryDTO> countries) {
        this.countries = countries;
    }

    public List<ProvinceDTO> getProvince() {
        return province;
    }

    public void setProvince(List<ProvinceDTO> province) {
        this.province = province;
    }

    public List<ClubDTO> getClubs() {
        return clubs;
    }

    public void setClubs(List<ClubDTO> clubs) {
        this.clubs = clubs;
    }

    public List<AgeGroupDTO> getAgeGroups() {
        return ageGroups;
    }

    public void setAgeGroups(List<AgeGroupDTO> ageGroups) {
        this.ageGroups = ageGroups;
    }

    public List<AdministratorDTO> getAdministrators() {
        return administrators;
    }

    public void setAdministrators(List<AdministratorDTO> administrators) {
        this.administrators = administrators;
    }
}
