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
@Table(name = "tourneyScoreByRound")
@NamedQueries({
    @NamedQuery(name = "TourneyScoreByRound.getScoreTotals", 
        query = "select a from TourneyScoreByRound a "
                + " where a.tourneyPlayerScore = :t")
    })
public class TourneyScoreByRound implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tourneyScoreByRoundID")
    private int tourneyScoreByRoundID;
    @Column(name = "golfRound")
    private int golfRound;
    @Column(name = "score1")
    private int score1;
    @Column(name = "score2")
    private int score2;
    @Column(name = "score3")
    private int score3;
    @Column(name = "score4")
    private int score4;
    @Column(name = "score5")
    private int score5;
    @Column(name = "score6")
    private int score6;
    @Column(name = "score7")
    private int score7;
    @Column(name = "score8")
    private int score8;
    @Column(name = "score9")
    private int score9;
    @Column(name = "score10")
    private int score10;
    @Column(name = "score11")
    private int score11;
    @Column(name = "score12")
    private int score12;
    @Column(name = "score13")
    private int score13;
    @Column(name = "score14")
    private int score14;
    @Column(name = "score15")
    private int score15;
    @Column(name = "score16")
    private int score16;
    @Column(name = "score17")
    private int score17;
    @Column(name = "score18")
    private int score18;
    @JoinColumn(name = "tourneyPlayerScoreID", referencedColumnName = "tourneyPlayerScoreID")
    @ManyToOne
    private TourneyPlayerScore tourneyPlayerScore;

    public TourneyScoreByRound() {
    }

    public int getTotalScore() {
        int tot = score1 + score2 + score3 + score4 + score5
                + score6 + score7 + score8 + score9 + score10
                + score11 + score12 + score13 + score14 + score15
                + score16 + score17 + score18;
        return tot;
    }

    public int getTourneyScoreByRoundID() {
        return tourneyScoreByRoundID;
    }

    public void setTourneyScoreByRoundID(int tourneyScoreByRoundID) {
        this.tourneyScoreByRoundID = tourneyScoreByRoundID;
    }

    public int getGolfRound() {
        return golfRound;
    }

    public void setGolfRound(int golfRound) {
        this.golfRound = golfRound;
    }

    public int getScore1() {
        return score1;
    }

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }

    public int getScore3() {
        return score3;
    }

    public void setScore3(int score3) {
        this.score3 = score3;
    }

    public int getScore4() {
        return score4;
    }

    public void setScore4(int score4) {
        this.score4 = score4;
    }

    public int getScore5() {
        return score5;
    }

    public void setScore5(int score5) {
        this.score5 = score5;
    }

    public int getScore6() {
        return score6;
    }

    public void setScore6(int score6) {
        this.score6 = score6;
    }

    public int getScore7() {
        return score7;
    }

    public void setScore7(int score7) {
        this.score7 = score7;
    }

    public int getScore8() {
        return score8;
    }

    public void setScore8(int score8) {
        this.score8 = score8;
    }

    public int getScore9() {
        return score9;
    }

    public void setScore9(int score9) {
        this.score9 = score9;
    }

    public int getScore10() {
        return score10;
    }

    public void setScore10(int score10) {
        this.score10 = score10;
    }

    public int getScore11() {
        return score11;
    }

    public void setScore11(int score11) {
        this.score11 = score11;
    }

    public int getScore12() {
        return score12;
    }

    public void setScore12(int score12) {
        this.score12 = score12;
    }

    public int getScore13() {
        return score13;
    }

    public void setScore13(int score13) {
        this.score13 = score13;
    }

    public int getScore14() {
        return score14;
    }

    public void setScore14(int score14) {
        this.score14 = score14;
    }

    public int getScore15() {
        return score15;
    }

    public void setScore15(int score15) {
        this.score15 = score15;
    }

    public int getScore16() {
        return score16;
    }

    public void setScore16(int score16) {
        this.score16 = score16;
    }

    public int getScore17() {
        return score17;
    }

    public void setScore17(int score17) {
        this.score17 = score17;
    }

    public int getScore18() {
        return score18;
    }

    public void setScore18(int score18) {
        this.score18 = score18;
    }

    public TourneyPlayerScore getTourneyPlayerScore() {
        return tourneyPlayerScore;
    }

    public void setTourneyPlayerScore(TourneyPlayerScore tourneyPlayerScore) {
        this.tourneyPlayerScore = tourneyPlayerScore;
    }
}
