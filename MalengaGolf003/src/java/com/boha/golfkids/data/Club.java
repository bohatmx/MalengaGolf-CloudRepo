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
import javax.validation.constraints.Size;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "club")
@NamedQueries({
    @NamedQuery(name = "Club.findByCountry", 
        query = "select a from Club a where a.province.country.countryID = : id "
                    + " order by a.clubName "),
    @NamedQuery(name = "Club.findByProvince", 
        query = "select a from Club a where a.province.provinceID= : id "
                    + " order by a.clubName "),
    })
public class Club implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "clubID")
    private int clubID;
    @Size(max = 100)
    @Column(name = "clubName")
    private String clubName;
    @Size(max = 95)
    @Column(name = "email")
    private String email;
    @Size(max = 25)
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "latitude")
    private int latitude;
    @Column(name = "longitude")
    private int longitude;
    @Size(max = 255)
    @Column(name = "address")
    private String address;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "club")
    private List<Tournament> tournamentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "club")
    private List<ClubCourse> clubCourseList;
    @JoinColumn(name = "provinceID", referencedColumnName = "provinceID")
    @ManyToOne
    private Province province;

    public Club() {
    }

    public Club(int clubID) {
        this.clubID = clubID;
    }

    public int getClubID() {
        return clubID;
    }

    public void setClubID(int clubID) {
        this.clubID = clubID;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Tournament> getTournamentList() {
        return tournamentList;
    }

    public void setTournamentList(List<Tournament> tournamentList) {
        this.tournamentList = tournamentList;
    }

    public List<ClubCourse> getClubCourseList() {
        return clubCourseList;
    }

    public void setClubCourseList(List<ClubCourse> clubCourseList) {
        this.clubCourseList = clubCourseList;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    
    @Override
    public String toString() {
        return "com.boha.golfkids.data.Club[ clubID=" + clubID + " ]";
    }
    
}
