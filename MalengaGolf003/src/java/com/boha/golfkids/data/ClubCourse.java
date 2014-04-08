/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.golfkids.data;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "clubCourse")
@NamedQueries({
    @NamedQuery(name = "ClubCourse.findAll", query = "SELECT c FROM ClubCourse c")})
public class ClubCourse implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "clubCourseID")
    private Integer clubCourseID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "par")
    private int par;
    @Column(name = "par1")
    private Integer par1;
    @Column(name = "par2")
    private Integer par2;
    @Column(name = "par3")
    private Integer par3;
    @Column(name = "par4")
    private Integer par4;
    @Column(name = "par5")
    private Integer par5;
    @Column(name = "par6")
    private Integer par6;
    @Column(name = "par7")
    private Integer par7;
    @Column(name = "par8")
    private Integer par8;
    @Column(name = "par9")
    private Integer par9;
    @Column(name = "par10")
    private Integer par10;
    @Column(name = "par11")
    private Integer par11;
    @Column(name = "par12")
    private Integer par12;
    @Column(name = "par13")
    private Integer par13;
    @Column(name = "par14")
    private Integer par14;
    @Column(name = "par15")
    private Integer par15;
    @Column(name = "par16")
    private Integer par16;
    @Column(name = "par17")
    private Integer par17;
    @Column(name = "par18")
    private Integer par18;
    @Column(name = "latitude")
    private Integer latitude;
    @Column(name = "longitude")
    private Integer longitude;
    @Size(max = 100)
    @Column(name = "courseName")
    private String courseName;
    @Column(name = "length1")
    private Integer length1;
    @Column(name = "length2")
    private Integer length2;
    @Column(name = "length3")
    private Integer length3;
    @Column(name = "length4")
    private Integer length4;
    @Column(name = "length5")
    private Integer length5;
    @Column(name = "length6")
    private Integer length6;
    @Column(name = "length7")
    private Integer length7;
    @Column(name = "length8")
    private Integer length8;
    @Column(name = "length9")
    private Integer length9;
    @Column(name = "length10")
    private Integer length10;
    @Column(name = "length11")
    private Integer length11;
    @Column(name = "length12")
    private Integer length12;
    @Column(name = "length13")
    private Integer length13;
    @Column(name = "length14")
    private Integer length14;
    @Column(name = "length15")
    private Integer length15;
    @Column(name = "length16")
    private Integer length16;
    @Column(name = "length17")
    private Integer length17;
    @Column(name = "length18")
    private Integer length18;
    @OneToMany(mappedBy = "clubCourse", fetch = FetchType.EAGER)
    private List<Tournament> tournamentList;
    @JoinColumn(name = "clubID", referencedColumnName = "clubID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Club club;

    public ClubCourse() {
    }

    public ClubCourse(Integer clubCourseID) {
        this.clubCourseID = clubCourseID;
    }

    public ClubCourse(Integer clubCourseID, int par) {
        this.clubCourseID = clubCourseID;
        this.par = par;
    }

    public Integer getClubCourseID() {
        return clubCourseID;
    }

    public void setClubCourseID(Integer clubCourseID) {
        this.clubCourseID = clubCourseID;
    }

    public int getPar() {
        return par;
    }

    public void setPar(int par) {
        this.par = par;
    }

    public Integer getPar1() {
        return par1;
    }

    public void setPar1(Integer par1) {
        this.par1 = par1;
    }

    public Integer getPar2() {
        return par2;
    }

    public void setPar2(Integer par2) {
        this.par2 = par2;
    }

    public Integer getPar3() {
        return par3;
    }

    public void setPar3(Integer par3) {
        this.par3 = par3;
    }

    public Integer getPar4() {
        return par4;
    }

    public void setPar4(Integer par4) {
        this.par4 = par4;
    }

    public Integer getPar5() {
        return par5;
    }

    public void setPar5(Integer par5) {
        this.par5 = par5;
    }

    public Integer getPar6() {
        return par6;
    }

    public void setPar6(Integer par6) {
        this.par6 = par6;
    }

    public Integer getPar7() {
        return par7;
    }

    public void setPar7(Integer par7) {
        this.par7 = par7;
    }

    public Integer getPar8() {
        return par8;
    }

    public void setPar8(Integer par8) {
        this.par8 = par8;
    }

    public Integer getPar9() {
        return par9;
    }

    public void setPar9(Integer par9) {
        this.par9 = par9;
    }

    public Integer getPar10() {
        return par10;
    }

    public void setPar10(Integer par10) {
        this.par10 = par10;
    }

    public Integer getPar11() {
        return par11;
    }

    public void setPar11(Integer par11) {
        this.par11 = par11;
    }

    public Integer getPar12() {
        return par12;
    }

    public void setPar12(Integer par12) {
        this.par12 = par12;
    }

    public Integer getPar13() {
        return par13;
    }

    public void setPar13(Integer par13) {
        this.par13 = par13;
    }

    public Integer getPar14() {
        return par14;
    }

    public void setPar14(Integer par14) {
        this.par14 = par14;
    }

    public Integer getPar15() {
        return par15;
    }

    public void setPar15(Integer par15) {
        this.par15 = par15;
    }

    public Integer getPar16() {
        return par16;
    }

    public void setPar16(Integer par16) {
        this.par16 = par16;
    }

    public Integer getPar17() {
        return par17;
    }

    public void setPar17(Integer par17) {
        this.par17 = par17;
    }

    public Integer getPar18() {
        return par18;
    }

    public void setPar18(Integer par18) {
        this.par18 = par18;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getLength1() {
        return length1;
    }

    public void setLength1(Integer length1) {
        this.length1 = length1;
    }

    public Integer getLength2() {
        return length2;
    }

    public void setLength2(Integer length2) {
        this.length2 = length2;
    }

    public Integer getLength3() {
        return length3;
    }

    public void setLength3(Integer length3) {
        this.length3 = length3;
    }

    public Integer getLength4() {
        return length4;
    }

    public void setLength4(Integer length4) {
        this.length4 = length4;
    }

    public Integer getLength5() {
        return length5;
    }

    public void setLength5(Integer length5) {
        this.length5 = length5;
    }

    public Integer getLength6() {
        return length6;
    }

    public void setLength6(Integer length6) {
        this.length6 = length6;
    }

    public Integer getLength7() {
        return length7;
    }

    public void setLength7(Integer length7) {
        this.length7 = length7;
    }

    public Integer getLength8() {
        return length8;
    }

    public void setLength8(Integer length8) {
        this.length8 = length8;
    }

    public Integer getLength9() {
        return length9;
    }

    public void setLength9(Integer length9) {
        this.length9 = length9;
    }

    public Integer getLength10() {
        return length10;
    }

    public void setLength10(Integer length10) {
        this.length10 = length10;
    }

    public Integer getLength11() {
        return length11;
    }

    public void setLength11(Integer length11) {
        this.length11 = length11;
    }

    public Integer getLength12() {
        return length12;
    }

    public void setLength12(Integer length12) {
        this.length12 = length12;
    }

    public Integer getLength13() {
        return length13;
    }

    public void setLength13(Integer length13) {
        this.length13 = length13;
    }

    public Integer getLength14() {
        return length14;
    }

    public void setLength14(Integer length14) {
        this.length14 = length14;
    }

    public Integer getLength15() {
        return length15;
    }

    public void setLength15(Integer length15) {
        this.length15 = length15;
    }

    public Integer getLength16() {
        return length16;
    }

    public void setLength16(Integer length16) {
        this.length16 = length16;
    }

    public Integer getLength17() {
        return length17;
    }

    public void setLength17(Integer length17) {
        this.length17 = length17;
    }

    public Integer getLength18() {
        return length18;
    }

    public void setLength18(Integer length18) {
        this.length18 = length18;
    }

    public List<Tournament> getTournamentList() {
        return tournamentList;
    }

    public void setTournamentList(List<Tournament> tournamentList) {
        this.tournamentList = tournamentList;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clubCourseID != null ? clubCourseID.hashCode() : 0);
        return hash;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClubCourse)) {
            return false;
        }
        ClubCourse other = (ClubCourse) object;
        if ((this.clubCourseID == null && other.clubCourseID != null) || (this.clubCourseID != null && !this.clubCourseID.equals(other.clubCourseID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.golfkids.data.ClubCourse[ clubCourseID=" + clubCourseID + " ]";
    }
    
}
