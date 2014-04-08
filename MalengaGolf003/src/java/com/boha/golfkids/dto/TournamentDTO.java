/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.dto;

import com.boha.golfkids.data.Tournament;
import com.boha.golfkids.data.TournamentVolunteer;
import com.boha.golfkids.data.TourneyPlayerScore;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aubrey Malabie
 */
public class TournamentDTO implements Comparable<TournamentDTO> {

    private int tournamentID;
    private long closingDate;
    private long endDate;
    private int golfRounds;
    private long startDate;
    private String tourneyName;
    private ClubDTO club;
    private ClubCourseDTO clubCourse;
    private int golfGroupID;
    private List<TourneyPlayerScoreDTO> scores;
    private List<VolunteerDTO> volunteers;
    
    public TournamentDTO(Tournament a) {
        tournamentID = a.getTournamentID();
        closingDate = a.getClosingDate().getTime();
        endDate = a.getEndDate().getTime();
        golfRounds = a.getGolfRounds();
        startDate = a.getStartDate().getTime();
        tourneyName = a.getTourneyName();
        if (a.getClubCourse() != null) {
            clubCourse = new ClubCourseDTO(a.getClubCourse());
        }
        if (a.getClub() != null) {
            club = new ClubDTO(a.getClub());
        }
        if (a.getGolfGroup() != null) {
            golfGroupID = a.getGolfGroup().getGolfGroupID();
        }
        if (a.getTourneyPlayerScoreList() != null) {
            scores = new ArrayList<>();
            List<TourneyPlayerScore> tpList = a.getTourneyPlayerScoreList();
            for (TourneyPlayerScore tourneyPlayerScore : tpList) {
                scores.add(new TourneyPlayerScoreDTO(tourneyPlayerScore, true, false));
            }
        }
        if (a.getTournamentVolunteerList() != null) {
            volunteers = new ArrayList<>();
            List<TournamentVolunteer> tvList = a.getTournamentVolunteerList();
            for (TournamentVolunteer vol : tvList) {
                volunteers.add(new VolunteerDTO(vol.getVolunteer()));
            }
        }
      
    }

    public int getTournamentID() {
        return tournamentID;
    }

    public ClubCourseDTO getClubCourse() {
        return clubCourse;
    }

    public void setClubCourse(ClubCourseDTO clubCourse) {
        this.clubCourse = clubCourse;
    }

    public void setTournamentID(int tournamentID) {
        this.tournamentID = tournamentID;
    }

    public long getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(long closingDate) {
        this.closingDate = closingDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public int getGolfRounds() {
        return golfRounds;
    }

    public void setGolfRounds(int golfRounds) {
        this.golfRounds = golfRounds;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public String getTourneyName() {
        return tourneyName;
    }

    public int getGolfGroupID() {
        return golfGroupID;
    }

    public void setGolfGroupID(int golfGroupID) {
        this.golfGroupID = golfGroupID;
    }

    public List<TourneyPlayerScoreDTO> getScores() {
        return scores;
    }

    public void setScores(List<TourneyPlayerScoreDTO> scores) {
        this.scores = scores;
    }

    public List<VolunteerDTO> getVolunteers() {
        return volunteers;
    }

    public void setVolunteers(List<VolunteerDTO> volunteers) {
        this.volunteers = volunteers;
    }

    public void setTourneyName(String tourneyName) {
        this.tourneyName = tourneyName;
    }

    public ClubDTO getClub() {
        return club;
    }

    public void setClub(ClubDTO club) {
        this.club = club;
    }

    
    @Override
    public int compareTo(TournamentDTO t) {
       
        if (startDate > t.startDate) {
            return 1;
        }
        if (startDate < t.startDate) {
            return -1;
        }
        
        return 0;
    }
}
