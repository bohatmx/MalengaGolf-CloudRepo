/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "tournamentVolunteer")
@NamedQueries({
    @NamedQuery(name = "TournamentVolunteer.findAll", query = "SELECT t FROM TournamentVolunteer t"),
    @NamedQuery(name = "TournamentVolunteer.findByTournamentVolunteerID", query = "SELECT t FROM TournamentVolunteer t WHERE t.tournamentVolunteerID = :tournamentVolunteerID")})
public class TournamentVolunteer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tournamentVolunteerID")
    private int tournamentVolunteerID;
    @JoinColumn(name = "volunteerID", referencedColumnName = "volunteerID")
    @ManyToOne
    private Volunteer volunteer;
    @JoinColumn(name = "tournamentID", referencedColumnName = "tournamentID")
    @ManyToOne(optional = false)
    private Tournament tournament;

    public TournamentVolunteer() {
    }

    public TournamentVolunteer(int tournamentVolunteerID) {
        this.tournamentVolunteerID = tournamentVolunteerID;
    }

    public int getTournamentVolunteerID() {
        return tournamentVolunteerID;
    }

    public void setTournamentVolunteerID(int tournamentVolunteerID) {
        this.tournamentVolunteerID = tournamentVolunteerID;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

   
    @Override
    public String toString() {
        return "com.boha.golfkids.data.TournamentVolunteer[ tournamentVolunteerID=" + tournamentVolunteerID + " ]";
    }
    
}
