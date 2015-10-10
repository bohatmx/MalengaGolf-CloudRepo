/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import javax.persistence.FetchType;
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
    @NamedQuery(name = "Tournament.findByGolfGroup",
            query = "SELECT t FROM Tournament t "
            + "where t.golfGroup.golfGroupID = :id "
            + "order by t.startDate DESC"),
    
    @NamedQuery(name = "Tournament.delete",
            query = "DELETE FROM Tournament t where t.tournamentID = :id"),
    
    @NamedQuery(name = "Tournament.deleteSamples",
            query = "DELETE FROM Tournament t where t.exampleFlag > 0 and t.golfGroup.golfGroupID = :id"),
    
    @NamedQuery(name = "Tournament.findByPlayer",
            query = "SELECT distinct t FROM Tournament t, LeaderBoard b "
                    + "where t.tournamentID = b.tournament.tournamentID "
                    + "and b.player.playerID = :id "
                    + "order by t.startDate DESC"),

    @NamedQuery(name = "Tournament.findByClub",
            query = "select a from Tournament a where a.club.clubID = :id")
})
public class Tournament implements Serializable {
    
    
  
    @Column(name = "closedForScoringFlag")
    private Integer closedForScoringFlag;
    @Column(name = "closedForRegistrationFlag")
    private Integer closedForRegistrationFlag;
    @Column(name = "useAgeGroups")
    private Integer useAgeGroups;
    @Column(name = "exampleFlag")
    private Integer exampleFlag;
    @Column(name = "scoringCommencedFlag")
    private Integer scoringCommencedFlag;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tournament")
    private List<LeaderboardViewer> leaderboardViewerList;
   
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tournament")
    private List<LeaderBoardTeam> leaderBoardTeamList;
    @OneToMany(mappedBy = "tournament")
    private List<VideoClip> videoClipList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tournament")
    private List<TournamentCourse> tournamentCourseList;
    @Basic(optional = false)
    @NotNull
    @Column(name = "holesPerRound")
    private int holesPerRound;
    @Basic(optional = false)
    @NotNull
    @Column(name = "par")
    private int par;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tournament", fetch = FetchType.LAZY)
    private List<LeaderBoard> leaderBoardList;
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
    @Column(name = "tournamentType")
    private int tournamentType;

    
    @JoinColumn(name = "golfGroupID", referencedColumnName = "golfGroupID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private GolfGroup golfGroup;
    @JoinColumn(name = "clubID", referencedColumnName = "clubID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Club club;

    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tournament", fetch = FetchType.EAGER)
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


    public int getTournamentType() {
        return tournamentType;
    }

    public void setTournamentType(int tournamentType) {
        this.tournamentType = tournamentType;
    }


    public Date getClosingDate() {
        return closingDate;
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

    public List<TournamentVolunteer> getTournamentVolunteerList() {
        return tournamentVolunteerList;
    }

    public void setTournamentVolunteerList(List<TournamentVolunteer> tournamentVolunteerList) {
        this.tournamentVolunteerList = tournamentVolunteerList;
    }

    @Override
    public String toString() {
        return "com.boha.golfkids.data.Tournament[ tournamentID=" + tournamentID + " ]";
    }

    public List<LeaderBoard> getLeaderBoardList() {
        return leaderBoardList;
    }

    public void setLeaderBoardList(List<LeaderBoard> leaderBoardList) {
        this.leaderBoardList = leaderBoardList;
    }

    public int getHolesPerRound() {
        return holesPerRound;
    }

    public void setHolesPerRound(int holesPerRound) {
        this.holesPerRound = holesPerRound;
    }

    public int getPar() {
        return par;
    }

    public void setPar(int par) {
        this.par = par;
    }

    public List<TournamentCourse> getTournamentCourseList() {
        return tournamentCourseList;
    }

    public void setTournamentCourseList(List<TournamentCourse> tournamentCourseList) {
        this.tournamentCourseList = tournamentCourseList;
    }

    public List<VideoClip> getVideoClipList() {
        return videoClipList;
    }

    public void setVideoClipList(List<VideoClip> videoClipList) {
        this.videoClipList = videoClipList;
    }


 

    public List<LeaderBoardTeam> getLeaderBoardTeamList() {
        return leaderBoardTeamList;
    }

    public void setLeaderBoardTeamList(List<LeaderBoardTeam> leaderBoardTeamList) {
        this.leaderBoardTeamList = leaderBoardTeamList;
    }
    public List<LeaderboardViewer> getLeaderboardViewerList() {
        return leaderboardViewerList;
    }
    public void setLeaderboardViewerList(List<LeaderboardViewer> leaderboardViewerList) {
        this.leaderboardViewerList = leaderboardViewerList;
    }

  
    public Integer getClosedForScoringFlag() { 
        return closedForScoringFlag;
    }

    public void setClosedForScoringFlag(Integer closedForScoringFlag) {
        this.closedForScoringFlag = closedForScoringFlag;
    }

    public Integer getClosedForRegistrationFlag() {
        return closedForRegistrationFlag;
    }

    public void setClosedForRegistrationFlag(Integer closedForRegistrationFlag) {
        this.closedForRegistrationFlag = closedForRegistrationFlag;
    }

    public Integer getUseAgeGroups() {
        return useAgeGroups;
    }

    public void setUseAgeGroups(Integer useAgeGroups) {
        this.useAgeGroups = useAgeGroups;
    }

    public Integer getExampleFlag() { 
        return exampleFlag;
    }

    public void setExampleFlag(Integer exampleFlag) {
        this.exampleFlag = exampleFlag;
    }

    public Integer getScoringCommencedFlag() {
        return scoringCommencedFlag;
    }

    public void setScoringCommencedFlag(Integer scoringCommencedFlag) {
        this.scoringCommencedFlag = scoringCommencedFlag;
    }
    

}
