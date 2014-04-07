/*
 * To change this template, choose Tools | Templates
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
import javax.validation.constraints.Size;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "tournament")
@NamedQueries({
    @NamedQuery(name = "Tournament.findAll", query = "SELECT t FROM Tournament t"),
    @NamedQuery(name = "Tournament.findByTournamentID", query = "SELECT t FROM Tournament t WHERE t.tournamentID = :tournamentID"),
    @NamedQuery(name = "Tournament.findByTourneyName", query = "SELECT t FROM Tournament t WHERE t.tourneyName = :tourneyName"),
    @NamedQuery(name = "Tournament.findByClosingDate", query = "SELECT t FROM Tournament t WHERE t.closingDate = :closingDate"),
    @NamedQuery(name = "Tournament.findByEndDate", query = "SELECT t FROM Tournament t WHERE t.endDate = :endDate"),
    @NamedQuery(name = "Tournament.findByStartDate", query = "SELECT t FROM Tournament t WHERE t.startDate = :startDate"),
    @NamedQuery(name = "Tournament.findByGolfRounds", query = "SELECT t FROM Tournament t WHERE t.golfRounds = :golfRounds")})
public class Tournament implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tournamentID")
    private int tournamentID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tourneyName")
    private String tourneyName;
    @Column(name = "closingDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date closingDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "endDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "startDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "golfRounds")
    private int golfRounds;
    @JoinColumn(name = "golfGroupID", referencedColumnName = "golfGroupID")
    @ManyToOne(optional = false)
    private GolfGroup golfGroup;
    
    @JoinColumn(name = "clubID", referencedColumnName = "clubID")
    @ManyToOne(optional = false)
    private Club club;
    
    
    @JoinColumn(name = "clubCourseID", referencedColumnName = "clubCourseID")
    @ManyToOne(optional = false)
    private ClubCourse clubCourse;
    
    @OneToMany(mappedBy = "tournament")
    private List<TourneyPlayerScore> tourneyPlayerScoreList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tournament")
    private List<TournamentVolunteer> tournamentVolunteerList;

    public Tournament() {
    }

    public Tournament(int tournamentID) {
        this.tournamentID = tournamentID;
    }

    public Tournament(int tournamentID, String tourneyName, Date endDate, Date startDate, int golfRounds) {
        this.tournamentID = tournamentID;
        this.tourneyName = tourneyName;
        this.endDate = endDate;
        this.startDate = startDate;
        this.golfRounds = golfRounds;
    }

    public int getTournamentID() {
        return tournamentID;
    }

    public void setTournamentID(int tournamentID) {
        this.tournamentID = tournamentID;
    }

    public String getTourneyName() {
        return tourneyName;
    }

    public void setTourneyName(String tourneyName) {
        this.tourneyName = tourneyName;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public ClubCourse getClubCourse() {
        return clubCourse;
    }

    public void setClubCourse(ClubCourse clubCourse) {
        this.clubCourse = clubCourse;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getGolfRounds() {
        return golfRounds;
    }

    public void setGolfRounds(int golfRounds) {
        this.golfRounds = golfRounds;
    }

   

    public List<TourneyPlayerScore> getTourneyPlayerScoreList() {
        return tourneyPlayerScoreList;
    }

    public void setTourneyPlayerScoreList(List<TourneyPlayerScore> tourneyPlayerScoreList) {
        this.tourneyPlayerScoreList = tourneyPlayerScoreList;
    }

    public List<TournamentVolunteer> getTournamentVolunteerList() {
        return tournamentVolunteerList;
    }

    public void setTournamentVolunteerList(List<TournamentVolunteer> tournamentVolunteerList) {
        this.tournamentVolunteerList = tournamentVolunteerList;
    }

    public GolfGroup getGolfGroup() {
        return golfGroup;
    }

    public void setGolfGroup(GolfGroup golfGroup) {
        this.golfGroup = golfGroup;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

 

    @Override
    public String toString() {
        return "com.boha.golfkids.data.Tournament[ tournamentID=" + tournamentID + " ]";
    }
    
}
