/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.golfkids.data;

import java.io.Serializable;
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
import javax.persistence.Table;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "tourneyScoreByRound")
@NamedQueries({
    @NamedQuery(name = "TourneyScoreByRound.getScoreTotals", 
            query = "SELECT t FROM TourneyScoreByRound t where t.tourneyPlayerScore = :t")})
public class TourneyScoreByRound implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tourneyScoreByRoundID")
    private Integer tourneyScoreByRoundID;
    @Column(name = "score1")
    private Integer score1;
    @Column(name = "score2")
    private Integer score2;
    @Column(name = "score3")
    private Integer score3;
    @Column(name = "score4")
    private Integer score4;
    @Column(name = "score5")
    private Integer score5;
    @Column(name = "score6")
    private Integer score6;
    @Column(name = "score7")
    private Integer score7;
    @Column(name = "score8")
    private Integer score8;
    @Column(name = "score9")
    private Integer score9;
    @Column(name = "score10")
    private Integer score10;
    @Column(name = "score11")
    private Integer score11;
    @Column(name = "score12")
    private Integer score12;
    @Column(name = "score13")
    private Integer score13;
    @Column(name = "score14")
    private Integer score14;
    @Column(name = "score15")
    private Integer score15;
    @Column(name = "score16")
    private Integer score16;
    @Column(name = "score17")
    private Integer score17;
    @Column(name = "score18")
    private Integer score18;
    @Column(name = "golfRound")
    private Integer golfRound;
     @Column(name = "totalScore")
    private Integer totalScore;
    @JoinColumn(name = "tourneyPlayerScoreID", referencedColumnName = "tourneyPlayerScoreID")
    @ManyToOne(fetch = FetchType.EAGER)
    private TourneyPlayerScore tourneyPlayerScore;

    public TourneyScoreByRound() {
    }

    public TourneyScoreByRound(Integer tourneyScoreByRoundID) {
        this.tourneyScoreByRoundID = tourneyScoreByRoundID;
    }

    public Integer getTourneyScoreByRoundID() {
        return tourneyScoreByRoundID;
    }

    public void setTourneyScoreByRoundID(Integer tourneyScoreByRoundID) {
        this.tourneyScoreByRoundID = tourneyScoreByRoundID;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getScore1() {
        return score1;
    }

    public void setScore1(Integer score1) {
        this.score1 = score1;
    }

    public Integer getScore2() {
        return score2;
    }

    public void setScore2(Integer score2) {
        this.score2 = score2;
    }

    public Integer getScore3() {
        return score3;
    }

    public void setScore3(Integer score3) {
        this.score3 = score3;
    }

    public Integer getScore4() {
        return score4;
    }

    public void setScore4(Integer score4) {
        this.score4 = score4;
    }

    public Integer getScore5() {
        return score5;
    }

    public void setScore5(Integer score5) {
        this.score5 = score5;
    }

    public Integer getScore6() {
        return score6;
    }

    public void setScore6(Integer score6) {
        this.score6 = score6;
    }

    public Integer getScore7() {
        return score7;
    }

    public void setScore7(Integer score7) {
        this.score7 = score7;
    }

    public Integer getScore8() {
        return score8;
    }

    public void setScore8(Integer score8) {
        this.score8 = score8;
    }

    public Integer getScore9() {
        return score9;
    }

    public void setScore9(Integer score9) {
        this.score9 = score9;
    }

    public Integer getScore10() {
        return score10;
    }

    public void setScore10(Integer score10) {
        this.score10 = score10;
    }

    public Integer getScore11() {
        return score11;
    }

    public void setScore11(Integer score11) {
        this.score11 = score11;
    }

    public Integer getScore12() {
        return score12;
    }

    public void setScore12(Integer score12) {
        this.score12 = score12;
    }

    public Integer getScore13() {
        return score13;
    }

    public void setScore13(Integer score13) {
        this.score13 = score13;
    }

    public Integer getScore14() {
        return score14;
    }

    public void setScore14(Integer score14) {
        this.score14 = score14;
    }

    public Integer getScore15() {
        return score15;
    }

    public void setScore15(Integer score15) {
        this.score15 = score15;
    }

    public Integer getScore16() {
        return score16;
    }

    public void setScore16(Integer score16) {
        this.score16 = score16;
    }

    public Integer getScore17() {
        return score17;
    }

    public void setScore17(Integer score17) {
        this.score17 = score17;
    }

    public Integer getScore18() {
        return score18;
    }

    public void setScore18(Integer score18) {
        this.score18 = score18;
    }

    public Integer getGolfRound() {
        return golfRound;
    }

    public void setGolfRound(Integer golfRound) {
        this.golfRound = golfRound;
    }

    public TourneyPlayerScore getTourneyPlayerScore() {
        return tourneyPlayerScore;
    }

    public void setTourneyPlayerScore(TourneyPlayerScore tourneyPlayerScore) {
        this.tourneyPlayerScore = tourneyPlayerScore;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tourneyScoreByRoundID != null ? tourneyScoreByRoundID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TourneyScoreByRound)) {
            return false;
        }
        TourneyScoreByRound other = (TourneyScoreByRound) object;
        if ((this.tourneyScoreByRoundID == null && other.tourneyScoreByRoundID != null) || (this.tourneyScoreByRoundID != null && !this.tourneyScoreByRoundID.equals(other.tourneyScoreByRoundID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.golfkids.data.TourneyScoreByRound[ tourneyScoreByRoundID=" + tourneyScoreByRoundID + " ]";
    }
    
}
