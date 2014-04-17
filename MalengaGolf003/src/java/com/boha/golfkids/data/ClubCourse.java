/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.golfkids.data;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "clubCourse")
@NamedQueries({
    @NamedQuery(name = "ClubCourse.findByClub", 
            query = "SELECT c FROM ClubCourse c "
                    + "where c.club.clubID = :id order by c.courseName"),
    @NamedQuery(name = "ClubCourse.findByCountry",
            query = "select a from ClubCourse a "
                    + "where a.club.province.country.countryID = :id "
                    + "order by a.club.clubID"),
    @NamedQuery(name = "ClubCourse.findByProvince",
            query = "select a from ClubCourse a "
                    + "where a.club.province.provinceID = :id "
                    + "order by a.club.clubID")
    })
public class ClubCourse implements Serializable {
    @Column(name = "parHole1")
    private Integer parHole1;
    @Column(name = "parHole2")
    private Integer parHole2;
    @Column(name = "parHole3")
    private Integer parHole3;
    @Column(name = "parHole4")
    private Integer parHole4;
    @Column(name = "parHole5")
    private Integer parHole5;
    @Column(name = "parHole6")
    private Integer parHole6;
    @Column(name = "parHole7")
    private Integer parHole7;
    @Column(name = "parHole8")
    private Integer parHole8;
    @Column(name = "parHole9")
    private Integer parHole9;
    @Column(name = "parHole10")
    private Integer parHole10;
    @Column(name = "parHole11")
    private Integer parHole11;
    @Column(name = "parHole12")
    private Integer parHole12;
    @Column(name = "parHole13")
    private Integer parHole13;
    @Column(name = "parHole14")
    private Integer parHole14;
    @Column(name = "parHole15")
    private Integer parHole15;
    @Column(name = "parHole16")
    private Integer parHole16;
    @Column(name = "parHole17")
    private Integer parHole17;
    @Column(name = "parHole18")
    private Integer parHole18;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clubCourse")
    private List<TournamentCourse> tournamentCourseList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clubCourse")
    private List<TourneyScoreByRound> tourneyScoreByRoundList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "clubCourseID")
    private int clubCourseID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "holes")
    private int holes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "par")
    private int par;
    @Column(name = "courseName")
    private String courseName;
    @JoinColumn(name = "clubID", referencedColumnName = "clubID")
    @ManyToOne(optional = false)
    private Club club;

    public ClubCourse() {
    }

    public ClubCourse(int clubCourseID) {
        this.clubCourseID = clubCourseID;
    }

    public ClubCourse(int clubCourseID, int holes, int par) {
        this.clubCourseID = clubCourseID;
        this.holes = holes;
        this.par = par;
    }

    public int getClubCourseID() {
        return clubCourseID;
    }

    public void setClubCourseID(int clubCourseID) {
        this.clubCourseID = clubCourseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getHoles() {
        return holes;
    }

    public void setHoles(int holes) {
        this.holes = holes;
    }

    public int getPar() {
        return par;
    }

    public void setPar(int par) {
        this.par = par;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
    @Override
    public String toString() {
        return "com.boha.golfkids.data.ClubCourse[ clubCourseID=" + clubCourseID + " ]";
    }
    public List<TourneyScoreByRound> getTourneyScoreByRoundList() {
        return tourneyScoreByRoundList;
    }
    public void setTourneyScoreByRoundList(List<TourneyScoreByRound> tourneyScoreByRoundList) {
        this.tourneyScoreByRoundList = tourneyScoreByRoundList;
    }

    public Integer getParHole1() {
        return parHole1;
    }

    public void setParHole1(Integer parHole1) {
        this.parHole1 = parHole1;
    }

    public Integer getParHole2() {
        return parHole2;
    }

    public void setParHole2(Integer parHole2) {
        this.parHole2 = parHole2;
    }

    public Integer getParHole3() {
        return parHole3;
    }

    public void setParHole3(Integer parHole3) {
        this.parHole3 = parHole3;
    }

    public Integer getParHole4() {
        return parHole4;
    }

    public void setParHole4(Integer parHole4) {
        this.parHole4 = parHole4;
    }

    public Integer getParHole5() {
        return parHole5;
    }

    public void setParHole5(Integer parHole5) {
        this.parHole5 = parHole5;
    }

    public Integer getParHole6() {
        return parHole6;
    }

    public void setParHole6(Integer parHole6) {
        this.parHole6 = parHole6;
    }

    public Integer getParHole7() {
        return parHole7;
    }

    public void setParHole7(Integer parHole7) {
        this.parHole7 = parHole7;
    }

    public Integer getParHole8() {
        return parHole8;
    }

    public void setParHole8(Integer parHole8) {
        this.parHole8 = parHole8;
    }

    public Integer getParHole9() {
        return parHole9;
    }

    public void setParHole9(Integer parHole9) {
        this.parHole9 = parHole9;
    }

    public Integer getParHole10() {
        return parHole10;
    }

    public void setParHole10(Integer parHole10) {
        this.parHole10 = parHole10;
    }

    public Integer getParHole11() {
        return parHole11;
    }

    public void setParHole11(Integer parHole11) {
        this.parHole11 = parHole11;
    }

    public Integer getParHole12() {
        return parHole12;
    }

    public void setParHole12(Integer parHole12) {
        this.parHole12 = parHole12;
    }

    public Integer getParHole13() {
        return parHole13;
    }

    public void setParHole13(Integer parHole13) {
        this.parHole13 = parHole13;
    }

    public Integer getParHole14() {
        return parHole14;
    }

    public void setParHole14(Integer parHole14) {
        this.parHole14 = parHole14;
    }

    public Integer getParHole15() {
        return parHole15;
    }

    public void setParHole15(Integer parHole15) {
        this.parHole15 = parHole15;
    }

    public Integer getParHole16() {
        return parHole16;
    }

    public void setParHole16(Integer parHole16) {
        this.parHole16 = parHole16;
    }

    public Integer getParHole17() {
        return parHole17;
    }


    public void setParHole17(Integer parHole17) {
        this.parHole17 = parHole17;
    }

    public Integer getParHole18() {
        return parHole18;
    }

    public void setParHole18(Integer parHole18) {
        this.parHole18 = parHole18;
    }

    public List<TournamentCourse> getTournamentCourseList() {
        return tournamentCourseList;
    }

    public void setTournamentCourseList(List<TournamentCourse> tournamentCourseList) {
        this.tournamentCourseList = tournamentCourseList;
    }
    
}
