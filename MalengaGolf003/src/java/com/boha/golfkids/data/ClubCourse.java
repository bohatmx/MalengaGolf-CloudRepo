/*
 * To change this template, choose Tools | Templates
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
import javax.validation.constraints.Size;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "clubCourse")
@NamedQueries({
    @NamedQuery(name = "ClubCourse.findAll", query = "SELECT c FROM ClubCourse c"),
    @NamedQuery(name = "ClubCourse.findByClubCourseID", query = "SELECT c FROM ClubCourse c WHERE c.clubCourseID = :clubCourseID"),
    @NamedQuery(name = "ClubCourse.findByPar", query = "SELECT c FROM ClubCourse c WHERE c.par = :par"),
    @NamedQuery(name = "ClubCourse.findByPar1", query = "SELECT c FROM ClubCourse c WHERE c.par1 = :par1"),
    @NamedQuery(name = "ClubCourse.findByPar2", query = "SELECT c FROM ClubCourse c WHERE c.par2 = :par2"),
    @NamedQuery(name = "ClubCourse.findByPar3", query = "SELECT c FROM ClubCourse c WHERE c.par3 = :par3"),
    @NamedQuery(name = "ClubCourse.findByPar4", query = "SELECT c FROM ClubCourse c WHERE c.par4 = :par4"),
    @NamedQuery(name = "ClubCourse.findByPar5", query = "SELECT c FROM ClubCourse c WHERE c.par5 = :par5"),
    @NamedQuery(name = "ClubCourse.findByPar6", query = "SELECT c FROM ClubCourse c WHERE c.par6 = :par6"),
    @NamedQuery(name = "ClubCourse.findByPar7", query = "SELECT c FROM ClubCourse c WHERE c.par7 = :par7"),
    @NamedQuery(name = "ClubCourse.findByPar8", query = "SELECT c FROM ClubCourse c WHERE c.par8 = :par8"),
    @NamedQuery(name = "ClubCourse.findByPar9", query = "SELECT c FROM ClubCourse c WHERE c.par9 = :par9"),
    @NamedQuery(name = "ClubCourse.findByPar10", query = "SELECT c FROM ClubCourse c WHERE c.par10 = :par10"),
    @NamedQuery(name = "ClubCourse.findByPar11", query = "SELECT c FROM ClubCourse c WHERE c.par11 = :par11"),
    @NamedQuery(name = "ClubCourse.findByPar12", query = "SELECT c FROM ClubCourse c WHERE c.par12 = :par12"),
    @NamedQuery(name = "ClubCourse.findByPar13", query = "SELECT c FROM ClubCourse c WHERE c.par13 = :par13"),
    @NamedQuery(name = "ClubCourse.findByPar14", query = "SELECT c FROM ClubCourse c WHERE c.par14 = :par14"),
    @NamedQuery(name = "ClubCourse.findByPar15", query = "SELECT c FROM ClubCourse c WHERE c.par15 = :par15"),
    @NamedQuery(name = "ClubCourse.findByPar16", query = "SELECT c FROM ClubCourse c WHERE c.par16 = :par16"),
    @NamedQuery(name = "ClubCourse.findByPar17", query = "SELECT c FROM ClubCourse c WHERE c.par17 = :par17"),
    @NamedQuery(name = "ClubCourse.findByPar18", query = "SELECT c FROM ClubCourse c WHERE c.par18 = :par18"),
    @NamedQuery(name = "ClubCourse.findByLatitude", query = "SELECT c FROM ClubCourse c WHERE c.latitude = :latitude"),
    @NamedQuery(name = "ClubCourse.findByLongitude", query = "SELECT c FROM ClubCourse c WHERE c.longitude = :longitude"),
    @NamedQuery(name = "ClubCourse.findByCourseName", query = "SELECT c FROM ClubCourse c WHERE c.courseName = :courseName")})
public class ClubCourse implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "clubCourseID")
    private int clubCourseID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "par")
    private int par;
    @Column(name = "par1")
    private int par1;
    @Column(name = "par2")
    private int par2;
    @Column(name = "par3")
    private int par3;
    @Column(name = "par4")
    private int par4;
    @Column(name = "par5")
    private int par5;
    @Column(name = "par6")
    private int par6;
    @Column(name = "par7")
    private int par7;
    @Column(name = "par8")
    private int par8;
    @Column(name = "par9")
    private int par9;
    @Column(name = "par10")
    private int par10;
    @Column(name = "par11")
    private int par11;
    @Column(name = "par12")
    private int par12;
    @Column(name = "par13")
    private int par13;
    @Column(name = "par14")
    private int par14;
    @Column(name = "par15")
    private int par15;
    @Column(name = "par16")
    private int par16;
    @Column(name = "par17")
    private int par17;
    @Column(name = "par18")
    private int par18;
    //
    @Column(name = "length1")
    private int length1;
    @Column(name = "length2")
    private int length2;
    @Column(name = "length3")
    private int length3;
    @Column(name = "length4")
    private int length4;
    @Column(name = "length5")
    private int length5;
    @Column(name = "length6")
    private int length6;
    @Column(name = "length7")
    private int length7;
    @Column(name = "length8")
    private int length8;
    @Column(name = "length9")
    private int length9;
    @Column(name = "length10")
    private int length10;
    @Column(name = "length11")
    private int length11;
    @Column(name = "length12")
    private int length12;
    @Column(name = "length13")
    private int length13;
    @Column(name = "length14")
    private int length14;
    @Column(name = "length15")
    private int length15;
    @Column(name = "length16")
    private int length16;
    @Column(name = "length17")
    private int length17;
    @Column(name = "length18")
    private int length18;
    //
    @Column(name = "latitude")
    private int latitude;
    @Column(name = "longitude")
    private int longitude;
    @Size(max = 100)
    @Column(name = "courseName")
    private String courseName;
    @JoinColumn(name = "clubID", referencedColumnName = "clubID")
    @ManyToOne(optional = false)
    private Club club;
    
     @OneToMany(mappedBy = "clubCourse")
    private List<Tournament> tournaments;


    public ClubCourse() {
    }

    public ClubCourse(int clubCourseID) {
        this.clubCourseID = clubCourseID;
    }

    public ClubCourse(int clubCourseID, int par) {
        this.clubCourseID = clubCourseID;
        this.par = par;
    }

    public int getClubCourseID() {
        return clubCourseID;
    }

    public void setClubCourseID(int clubCourseID) {
        this.clubCourseID = clubCourseID;
    }

    public int getPar() {
        return par;
    }

    public void setPar(int par) {
        this.par = par;
    }

    public int getPar1() {
        return par1;
    }

    public void setPar1(int par1) {
        this.par1 = par1;
    }

    public List<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }

    public int getPar2() {
        return par2;
    }

    public void setPar2(int par2) {
        this.par2 = par2;
    }

    public int getPar3() {
        return par3;
    }

    public void setPar3(int par3) {
        this.par3 = par3;
    }

    public int getPar4() {
        return par4;
    }

    public void setPar4(int par4) {
        this.par4 = par4;
    }

    public int getPar5() {
        return par5;
    }

    public void setPar5(int par5) {
        this.par5 = par5;
    }

    public int getPar6() {
        return par6;
    }

    public void setPar6(int par6) {
        this.par6 = par6;
    }

    public int getPar7() {
        return par7;
    }

    public void setPar7(int par7) {
        this.par7 = par7;
    }

    public int getPar8() {
        return par8;
    }

    public void setPar8(int par8) {
        this.par8 = par8;
    }

    public int getPar9() {
        return par9;
    }

    public void setPar9(int par9) {
        this.par9 = par9;
    }

    public int getPar10() {
        return par10;
    }

    public void setPar10(int par10) {
        this.par10 = par10;
    }

    public int getPar11() {
        return par11;
    }

    public void setPar11(int par11) {
        this.par11 = par11;
    }

    public int getPar12() {
        return par12;
    }

    public void setPar12(int par12) {
        this.par12 = par12;
    }

    public int getPar13() {
        return par13;
    }

    public void setPar13(int par13) {
        this.par13 = par13;
    }

    public int getPar14() {
        return par14;
    }

    public void setPar14(int par14) {
        this.par14 = par14;
    }

    public int getPar15() {
        return par15;
    }

    public void setPar15(int par15) {
        this.par15 = par15;
    }

    public int getPar16() {
        return par16;
    }

    public void setPar16(int par16) {
        this.par16 = par16;
    }

    public int getPar17() {
        return par17;
    }

    public void setPar17(int par17) {
        this.par17 = par17;
    }

    public int getPar18() {
        return par18;
    }

    public void setPar18(int par18) {
        this.par18 = par18;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public int getLength1() {
        return length1;
    }

    public void setLength1(int length1) {
        this.length1 = length1;
    }

    public int getLength2() {
        return length2;
    }

    public void setLength2(int length2) {
        this.length2 = length2;
    }

    public int getLength3() {
        return length3;
    }

    public void setLength3(int length3) {
        this.length3 = length3;
    }

    public int getLength4() {
        return length4;
    }

    public void setLength4(int length4) {
        this.length4 = length4;
    }

    public int getLength5() {
        return length5;
    }

    public void setLength5(int length5) {
        this.length5 = length5;
    }

    public int getLength6() {
        return length6;
    }

    public void setLength6(int length6) {
        this.length6 = length6;
    }

    public int getLength7() {
        return length7;
    }

    public void setLength7(int length7) {
        this.length7 = length7;
    }

    public int getLength8() {
        return length8;
    }

    public void setLength8(int length8) {
        this.length8 = length8;
    }

    public int getLength9() {
        return length9;
    }

    public void setLength9(int length9) {
        this.length9 = length9;
    }

    public int getLength10() {
        return length10;
    }

    public void setLength10(int length10) {
        this.length10 = length10;
    }

    public int getLength11() {
        return length11;
    }

    public void setLength11(int length11) {
        this.length11 = length11;
    }

    public int getLength12() {
        return length12;
    }

    public void setLength12(int length12) {
        this.length12 = length12;
    }

    public int getLength13() {
        return length13;
    }

    public void setLength13(int length13) {
        this.length13 = length13;
    }

    public int getLength14() {
        return length14;
    }

    public void setLength14(int length14) {
        this.length14 = length14;
    }

    public int getLength15() {
        return length15;
    }

    public void setLength15(int length15) {
        this.length15 = length15;
    }

    public int getLength16() {
        return length16;
    }

    public void setLength16(int length16) {
        this.length16 = length16;
    }

    public int getLength17() {
        return length17;
    }

    public void setLength17(int length17) {
        this.length17 = length17;
    }

    public int getLength18() {
        return length18;
    }

    public void setLength18(int length18) {
        this.length18 = length18;
    }

 
    @Override
    public String toString() {
        return "com.boha.golfkids.data.ClubCourse[ clubCourseID=" + clubCourseID + " ]";
    }
    
}
