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
import javax.validation.constraints.Size;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "player")
@NamedQueries({
    @NamedQuery(name = "Player.login",
            query = "SELECT a FROM Player a "
            + "where a.email = :email and a.pin = :pin"),

    @NamedQuery(name = "Player.findByEmail",
            query = "SELECT a FROM Player a "
            + "where a.email = :email"),

    @NamedQuery(name = "Player.findByTourney",
            query = "SELECT distinct a FROM Player a, LeaderBoard b "
            + "where a.playerID = b.player.playerID "
            + "and b.tournament.tournamentID = :id"),

    @NamedQuery(name = "Player.findByGolfGroup",
            query = "SELECT p FROM Player p, GolfGroupPlayer b "
            + "where p.playerID = b.player.playerID "
            + "and b.golfGroup.golfGroupID = :id "
            + "order by p.lastName, p.firstName")})
public class Player implements Serializable, Comparable<Player> {
    @Column(name = "gender")
    private Integer gender;
    @Column(name = "yearJoined")
    private Integer yearJoined;
    @OneToMany(mappedBy = "player")
    private List<GcmDevice> gcmDeviceList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player", fetch = FetchType.LAZY)
    private List<LeaderBoard> leaderBoardList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "playerID")
    private int playerID;
    @Size(max = 45)
    @Column(name = "firstName")
    private String firstName;
    @Size(max = 45)
    @Column(name = "middleName")
    private String middleName;
    @Size(max = 45)
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "dateOfBirth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Column(name = "exampleFlag")
    private int exampleFlag;
    @Column(name = "dateRegistered")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistered;
    @Size(max = 95)
    @Column(name = "email")
    private String email;
    @Size(max = 25)
    @Column(name = "cellphone")
    private String cellphone;
    @Size(max = 45)
    @Column(name = "pin")
    private String pin;
    @JoinColumn(name = "parentID", referencedColumnName = "parentID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Parent parent;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player", fetch = FetchType.EAGER)
    private List<GolfGroupPlayer> golfGroupPlayerList;

    public Player() {
    }

    public Player(int playerID) {
        this.playerID = playerID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public List<GolfGroupPlayer> getGolfGroupPlayerList() {
        return golfGroupPlayerList;
    }

    public void setGolfGroupPlayerList(List<GolfGroupPlayer> golfGroupPlayerList) {
        this.golfGroupPlayerList = golfGroupPlayerList;
    }

    public int getExampleFlag() {
        return exampleFlag;
    }

    public void setExampleFlag(int exampleFlag) {
        this.exampleFlag = exampleFlag;
    }

    @Override
    public String toString() {
        return "com.boha.golfkids.data.Player[ playerID=" + playerID + " ]";
    }

    public List<LeaderBoard> getLeaderBoardList() {
        return leaderBoardList;
    }

    public void setLeaderBoardList(List<LeaderBoard> leaderBoardList) {
        this.leaderBoardList = leaderBoardList;
    }

    @Override
    public int compareTo(Player o) {
        String n = lastName + firstName;
        String n1 = o.getLastName() + o.getFirstName();
        
        return n.compareTo(n1);
    }


    public List<GcmDevice> getGcmDeviceList() {
        return gcmDeviceList;
    }

    public void setGcmDeviceList(List<GcmDevice> gcmDeviceList) {
        this.gcmDeviceList = gcmDeviceList;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getYearJoined() {
        return yearJoined;
    }

    public void setYearJoined(Integer yearJoined) {
        this.yearJoined = yearJoined;
    }

}
