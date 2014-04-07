/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.dto;

import com.boha.golfkids.data.ClubCourse;

/**
 *
 * @author aubreyM
 */
public class ClubCourseDTO {
    private int clubCourseID;
	private String courseName;
        private int clubID;
	private int latitude;

	private int longitude;

	private int par;

	private int par1;

	private int par10;

	private int par11;

	private int par12;

	private int par13;

	private int par14;

	private int par15;

	private int par16;

	private int par17;

	private int par18;

	private int par2;

	private int par3;

	private int par4;

	private int par5;

	private int par6;

	private int par7;

	private int par8;

	private int par9;
        //
        private int length1;

	private int length10;

	private int length11;

	private int length12;

	private int length13;

	private int length14;

	private int length15;

	private int length16;

	private int length17;

	private int length18;

	private int length2;

	private int length3;

	private int length4;

	private int length5;

	private int length6;

	private int length7;

	private int length8;

	private int length9;
public ClubCourseDTO(ClubCourse a) {
    clubCourseID = a.getClubCourseID();
    courseName = a.getCourseName();
    clubID = a.getClub().getClubID();
    latitude = a.getLatitude();
    longitude = a.getLongitude();
    par = a.getPar();
    par1 = a.getPar1();
    par2 = a.getPar2();
    par3 = a.getPar3();
    par4 = a.getPar4();
    par5 = a.getPar5();
    par6 = a.getPar6();
    par7 = a.getPar7();
    par8 = a.getPar8();
    par9 = a.getPar9();
    par10 = a.getPar10();
    par11 = a.getPar11();
    par12 = a.getPar12();
    par13 = a.getPar13();
    par14 = a.getPar14();
    par15 = a.getPar15();
    par16 = a.getPar16();
    par17 = a.getPar17();
    par18 = a.getPar18();
    //
    length1 = a.getLength1();
    length2 = a.getLength2();
    length3 = a.getLength3();
    length4 = a.getLength4();
    length5 = a.getLength5();
    length6 = a.getLength6();
    length7 = a.getLength7();
    length8 = a.getLength8();
    length9 = a.getLength9();
    length10 = a.getLength10();
    length11 = a.getLength11();
    length12 = a.getLength12();
    length13 = a.getLength13();
    length14 = a.getLength14();
    length15 = a.getLength15();
    length16 = a.getLength16();
    length17 = a.getLength17();
    length18 = a.getLength18();
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

    public int getClubID() {
        return clubID;
    }

    public void setClubID(int clubID) {
        this.clubID = clubID;
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

    public int getLength1() {
        return length1;
    }

    public void setLength1(int length1) {
        this.length1 = length1;
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

}
