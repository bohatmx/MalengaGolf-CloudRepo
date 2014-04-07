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
import javax.validation.constraints.Size;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "player")
@NamedQueries({
    @NamedQuery(name = "Player.findAll", query = "SELECT p FROM Player p"),
    @NamedQuery(name = "Player.findByPlayerID", query = "SELECT p FROM Player p WHERE p.playerID = :playerID"),
    @NamedQuery(name = "Player.findByFirstName", query = "SELECT p FROM Player p WHERE p.firstName = :firstName"),
    @NamedQuery(name = "Player.findByMiddleName", query = "SELECT p FROM Player p WHERE p.middleName = :middleName"),
    @NamedQuery(name = "Player.findByLastName", query = "SELECT p FROM Player p WHERE p.lastName = :lastName"),
    @NamedQuery(name = "Player.findByDateOfBirth", query = "SELECT p FROM Player p WHERE p.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "Player.findByGender", query = "SELECT p FROM Player p WHERE p.gender = :gender"),
    @NamedQuery(name = "Player.findByDateRegistered", query = "SELECT p FROM Player p WHERE p.dateRegistered = :dateRegistered"),
    @NamedQuery(name = "Player.findByYearJoined", query = "SELECT p FROM Player p WHERE p.yearJoined = :yearJoined"),
    @NamedQuery(name = "Player.findByEmail", query = "SELECT p FROM Player p WHERE p.email = :email"),
    @NamedQuery(name = "Player.findByCellphone", query = "SELECT p FROM Player p WHERE p.cellphone = :cellphone"),
    @NamedQuery(name = "Player.findByPin", query = "SELECT p FROM Player p WHERE p.pin = :pin")})
public class Player implements Serializable {
    @Column(name = "gender")
    private Integer gender;
    @Column(name = "yearJoined")
    private Integer yearJoined;
    @Column(name = "parentID")
    private Integer parentID;
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
    @ManyToOne
    private Parent parent;
    @OneToMany(mappedBy = "player")
    private List<TourneyPlayerScore> tourneyPlayerScoreList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player")
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

    

    public List<TourneyPlayerScore> getTourneyPlayerScoreList() {
        return tourneyPlayerScoreList;
    }

    public void setTourneyPlayerScoreList(List<TourneyPlayerScore> tourneyPlayerScoreList) {
        this.tourneyPlayerScoreList = tourneyPlayerScoreList;
    }

    public List<GolfGroupPlayer> getGolfGroupPlayerList() {
        return golfGroupPlayerList;
    }

    public void setGolfGroupPlayerList(List<GolfGroupPlayer> golfGroupPlayerList) {
        this.golfGroupPlayerList = golfGroupPlayerList;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

   

    @Override
    public String toString() {
        return "com.boha.golfkids.data.Player[ playerID=" + playerID + " ]";
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

    public Integer getParentID() {
        return parentID;
    }

    public void setParentID(Integer parentID) {
        this.parentID = parentID;
    }
    
}
