/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.util;

import com.boha.golfkids.data.Agegroup;
import com.boha.golfkids.data.ClubCourse;
import com.boha.golfkids.data.GolfGroup;
import com.boha.golfkids.data.GolfGroupParent;
import com.boha.golfkids.data.GolfGroupPlayer;
import com.boha.golfkids.data.Parent;
import com.boha.golfkids.data.Player;
import com.boha.golfkids.data.Tournament;
import com.boha.golfkids.data.TourneyPlayerScore;
import com.boha.golfkids.data.TourneyScoreByRound;
import com.boha.golfkids.servlet.GenRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author aubreyM
 */
public class Generator {

    public static String generateTournament(GenRequest genRequest,
            DataUtil dataUtil) throws DataException {
        StringBuilder sb = new StringBuilder();
        sb.append("#### GENERATING TOURNAMENT\n\n");
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(Calendar.YEAR, genRequest.getYear());
        cal.set(Calendar.MONTH, genRequest.getMonth() - 1);
        cal.set(Calendar.DAY_OF_MONTH, genRequest.getDay());

        Date startDate = cal.getTime();
        EntityManager em = EMUtil.getEntityManager();

        Tournament tournament = new Tournament();
        tournament.setTourneyName(genRequest.getTournamentName());
        tournament.setStartDate(startDate);
        if (genRequest.getRounds() > 1) {
            for (int i = 0; i < genRequest.getRounds(); i++) {
                cal.roll(Calendar.DATE, true);
            }
            tournament.setEndDate(cal.getTime());
        } else {
            tournament.setEndDate(startDate);
        }
        tournament.setClub(dataUtil.getClubByID(genRequest.getClubID()));
        tournament.setGolfRounds(genRequest.getRounds());
        tournament.setGolfGroup(dataUtil.getGroupByID(genRequest.getGolfGroupID()));

        try {
            EntityTransaction tran = em.getTransaction();
            tran.begin();
            em.persist(tournament);
            tran.commit();
            sb.append("### Tournament generated: ").append(tournament.getTourneyName()).append("\n");
            //add players to tournament 
            GolfGroup gg = dataUtil.getGroupByID(genRequest.getGolfGroupID());
            Query q = em.createQuery("select a from GolfGroupPlayer a "
                    + " where a.golfGroup = :group");
            q.setParameter("group", gg);
            List<GolfGroupPlayer> list = q.getResultList();
            tran.begin();
            for (GolfGroupPlayer ggplayer : list) {
                TourneyPlayerScore score = new TourneyPlayerScore();
                score.setDateRegistered(startDate);
                score.setAgeGroup(getPlayersAgeGroup(ggplayer, dataUtil));
                score.setPaidFlag(1);
                score.setPlayer(ggplayer.getPlayer());
                score.setTournament(tournament);
                score.setAdministrator(dataUtil.getAdministratorByID(genRequest.getAdministratorID()));
                em.persist(score);

            }
            tran.commit();
            sb.append("### Tournament players generated for: ")
                    .append(tournament.getTourneyName()).append("\n");
            sb.append("\n");
            //set up scoring ...
            q = em.createQuery("select a from TourneyPlayerScore a "
                    + " where a.tournament = :t");
            q.setParameter("t", tournament);
            List<TourneyPlayerScore> tpList = q.getResultList();
            ClubCourse cc = tournament.getClub().getClubCourseList().get(0);
            tran.begin();
            for (int i = 0; i < tournament.getGolfRounds(); i++) {

                for (TourneyPlayerScore tpScore : tpList) {
                    if (tpScore.getAgeGroup() != null) {
                        if (tpScore.getAgeGroup().getAgeGroupID() > 3) {
                            TourneyScoreByRound tsbr = scorePlayerFirstNine(tpScore, cc, (i + 1));
                            scorePlayerBackNine(tsbr, cc);
                            em.persist(tsbr);
                        } else {
                            TourneyScoreByRound tsbr =
                                    scorePlayerFirstNine(tpScore, cc, (i + 1));
                            em.persist(tsbr);
                        }
                    } else {
                        Logger.getLogger("Generator").log(Level.WARNING, 
                                "Player has null AgeGroup, tourneyPlayerScoreID: {0}", 
                                tpScore.getTourneyPlayerScoreID());
                    }
                }
            }
            tran.commit();
            sb.append("### Scores entered for all rounds\n");
            //update totals
            //get scores by tourneyPlayerScoreID
            tran.begin();
            for (TourneyPlayerScore tpScore : tpList) {
                q = em.createQuery("select a from TourneyScoreByRound a "
                        + " where a.tourneyPlayerScore = :t");
                q.setParameter("t", tpScore);
                List<TourneyScoreByRound> xlist = q.getResultList();
                for (TourneyScoreByRound tsbr : xlist) {
                    switch (tsbr.getGolfRound()) {
                        case 1:
                            tpScore.setScoreRound1(tsbr.getTotalScore());
                            break;
                        case 2:
                            tpScore.setScoreRound2(tsbr.getTotalScore());
                            break;
                        case 3:
                            tpScore.setScoreRound3(tsbr.getTotalScore());
                            break;
                        case 4:
                            tpScore.setScoreRound4(tsbr.getTotalScore());
                            break;
                    }
                }
                tpScore.setTotalScore(tpScore.getScoreRound1()
                        + tpScore.getScoreRound2() + tpScore.getScoreRound3()
                        + tpScore.getScoreRound4());
                em.merge(tpScore);
            }
            tran.commit();
            sb.append("### Scores totalled up\n");
            sb.append("############### Tournament generated and scored\n");
            Logger.getLogger("Generator").log(Level.OFF, "Tournament generated: {0} \n{1}", 
                    new Object[]{tournament.getTourneyName(), sb.toString()});
        } catch (Exception e) {
            Logger.getLogger("Generator").log(Level.OFF, "Error generating tourney", e);
            throw new DataException();
        }
        return sb.toString();
    }

    private static TourneyScoreByRound scorePlayerFirstNine(
            TourneyPlayerScore tps, ClubCourse cc, int round) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
        }
        Random rand = new Random(System.currentTimeMillis());
        int x = rand.nextInt(1000);
        boolean isLowScore = false;
        if (x > 950) {
            isLowScore = true;
             Logger.getLogger("Generator").log(Level.OFF, 
                     "@@@@ Low scorer on front nine, tourneyPlayerScoreID: {0}", 
                     tps.getTourneyPlayerScoreID());
        }
        TourneyScoreByRound tsbr = new TourneyScoreByRound();
        tsbr.setTourneyPlayerScore(tps);
        tsbr.setGolfRound(round);
        int score1 = 0;
        switch (cc.getPar1()) {
            case 3:
                score1 = rand.nextInt(6);
                if (score1 < 3) {
                    score1 = 3;
                }
                if (isLowScore) {
                    score1 = 2;
                }
                break;
            case 4:
                score1 = rand.nextInt(8);
                if (score1 < 3) {
                    score1 = 5;
                }
                break;
            case 5:
                score1 = rand.nextInt(10);
                if (score1 < 4) {
                    score1 = 5;
                }
                if (isLowScore) {
                    score1 = 4;
                }
                break;
        }
        tsbr.setScore1(score1);
        //
        int score2 = 0;
        switch (cc.getPar2()) {
            case 3:
                score2 = rand.nextInt(6);
                if (score2 < 3) {
                    score2 = 3;
                }
                break;
            case 4:
                score2 = rand.nextInt(8);
                if (score2 < 3) {
                    score2 = 4;
                }
                break;
            case 5:
                score2 = rand.nextInt(10);
                if (score2 < 4) {
                    score2 = 5;
                }
                if (isLowScore) {
                    score2 = 4;
                }
                break;
        }
        tsbr.setScore2(score2);
        //
        int score3 = 0;
        switch (cc.getPar3()) {
            case 3:
                score3 = rand.nextInt(6);
                if (score3 < 3) {
                    score3 = 3;
                }
                break;
            case 4:
                score3 = rand.nextInt(8);
                if (score3 < 3) {
                    score3 = 5;
                }
                break;
            case 5:
                score3 = rand.nextInt(10);
                if (score3 < 4) {
                    score3 = 5;
                }
                if (isLowScore) {
                    score3 = 4;
                }
                break;
        }
        tsbr.setScore3(score3);
        //
        int score4 = 0;
        switch (cc.getPar4()) {
            case 3:
                score4 = rand.nextInt(6);
                if (score4 < 3) {
                    score4 = 3;
                }
                break;
            case 4:
                score4 = rand.nextInt(8);
                if (score4 < 3) {
                    score4 = 6;
                }
                if (isLowScore) {
                    score4 = 3;
                }
                break;
            case 5:
                score4 = rand.nextInt(10);
                if (score4 < 4) {
                    score4 = 5;
                }
                if (isLowScore) {
                    score4 = 4;
                }
                break;
        }
        tsbr.setScore4(score4);
        //
        int score5 = 0;
        switch (cc.getPar5()) {
            case 3:
                score5 = rand.nextInt(6);
                if (score5 < 3) {
                    score5 = 3;
                }
                break;
            case 4:
                score5 = rand.nextInt(8);
                if (score5 < 3) {
                    score5 = 5;
                }
                break;
            case 5:
                score5 = rand.nextInt(10);
                if (score5 < 4) {
                    score5 = 5;
                }
                if (isLowScore) {
                    score5 = 4;
                }
                break;
        }
        tsbr.setScore5(score5);
        //
        int score6 = 0;
        switch (cc.getPar6()) {
            case 3:
                score6 = rand.nextInt(6);
                if (score6 < 3) {
                    score6 = 3;
                }
                break;
            case 4:
                score6 = rand.nextInt(8);
                if (score6 < 3) {
                    score6 = 6;
                }
                if (isLowScore) {
                    score6 = 3;
                }
                break;
            case 5:
                score6 = rand.nextInt(10);
                if (score6 < 4) {
                    score6 = 5;
                }
                if (isLowScore) {
                    score1 = 4;
                }
                break;
        }
        tsbr.setScore6(score6);
        //
        int score7 = 0;
        switch (cc.getPar7()) {
            case 3:
                score7 = rand.nextInt(6);
                if (score7 < 3) {
                    score7 = 3;
                }
                if (isLowScore) {
                    score7 = 2;
                }
                break;
            case 4:
                score7 = rand.nextInt(8);
                if (score7 < 3) {
                    score7 = 4;
                }
                break;
            case 5:
                score7 = rand.nextInt(10);
                if (score7 < 4) {
                    score7 = 5;
                }
                if (isLowScore) {
                    score7 = 3;
                }
                break;
        }
        tsbr.setScore7(score7);
        //
        int score8 = 0;
        switch (cc.getPar8()) {
            case 3:
                score8 = rand.nextInt(6);
                if (score8 < 3) {
                    score8 = 3;
                }
                break;
            case 4:
                score8 = rand.nextInt(8);
                if (score8 < 3) {
                    score8 = 5;
                }
                if (isLowScore) {
                    score8 = 3;
                }
                break;
            case 5:
                score8 = rand.nextInt(10);
                if (score8 < 4) {
                    score8 = 5;
                }
                if (isLowScore) {
                    score8 = 3;
                }
                break;
        }
        tsbr.setScore8(score8);
        //
        int score9 = 0;
        switch (cc.getPar9()) {
            case 3:
                score9 = rand.nextInt(6);
                if (score9 < 3) {
                    score9 = 3;
                }
                if (isLowScore) {
                    score9 = 2;
                }
                break;
            case 4:
                score9 = rand.nextInt(8);
                if (score9 < 3) {
                    score9 = 4;
                }
                if (isLowScore) {
                    score9 = 3;
                }
                break;
            case 5:
                score9 = rand.nextInt(10);
                if (score9 < 4) {
                    score9 = 5;
                }
                if (isLowScore) {
                    score9 = 4;
                }
                break;
        }
        tsbr.setScore9(score9);
        return tsbr;
    }

    private static void scorePlayerBackNine(TourneyScoreByRound tsbr,
            ClubCourse cc) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Random rand = new Random(System.currentTimeMillis());
        int x = rand.nextInt(1000);
        boolean isLowScore = false;
        if (x > 940) {
            isLowScore = true;
            Logger.getLogger("Generator").log(Level.OFF, "@@@@ Low scorer on back nine, tourneyPlayerScoreID: " 
                    + tsbr.getTourneyPlayerScore().getTourneyPlayerScoreID());
        }
        int score10 = 0;
        switch (cc.getPar10()) {
            case 3:
                score10 = rand.nextInt(6);
                if (score10 < 3) {
                    score10 = 3;
                }
                if (isLowScore) {
                    score10 = 2;
                }
                break;
            case 4:
                score10 = rand.nextInt(8);
                if (score10 < 3) {
                    score10 = 4;
                }
                break;
            case 5:
                score10 = rand.nextInt(10);
                if (score10 < 4) {
                    score10 = 5;
                }
                if (isLowScore) {
                    score10 = 4;
                }
                break;
        }
        tsbr.setScore10(score10);
        //
        int score11 = 0;
        switch (cc.getPar11()) {
            case 3:
                score11 = rand.nextInt(6);
                if (score11 < 3) {
                    score11 = 3;
                }
                break;
            case 4:
                score11 = rand.nextInt(8);
                if (score11 < 3) {
                    score11 = 5;
                }
                break;
            case 5:
                score11 = rand.nextInt(10);
                if (score11 < 4) {
                    score11 = 5;
                }
                if (isLowScore) {
                    score10 = 4;
                }
                break;
        }
        tsbr.setScore11(score11);
        //
        int score12 = 0;
        switch (cc.getPar12()) {
            case 3:
                score12 = rand.nextInt(6);
                if (score12 < 3) {
                    score12 = 3;
                }
                if (isLowScore) {
                    score12 = 2;
                }
                break;
            case 4:
                score12 = rand.nextInt(8);
                if (score12 < 3) {
                    score12 = 4;
                }
                break;
            case 5:
                score12 = rand.nextInt(10);
                if (score12 < 4) {
                    score12 = 5;
                }
                if (isLowScore) {
                    score12 = 4;
                }
                break;
        }
        tsbr.setScore12(score12);
        //
        int score13 = 0;
        switch (cc.getPar13()) {
            case 3:
                score13 = rand.nextInt(6);
                if (score13 < 3) {
                    score13 = 3;
                }
                break;
            case 4:
                score13 = rand.nextInt(8);
                if (score13 < 3) {
                    score13 = 4;
                }
                if (isLowScore) {
                    score13 = 3;
                }
                break;
            case 5:
                score13 = rand.nextInt(10);
                if (score13 < 4) {
                    score13 = 5;
                }
                break;
        }
        tsbr.setScore13(score13);
        //
        int score14 = 0;
        switch (cc.getPar14()) {
            case 3:
                score14 = rand.nextInt(6);
                if (score14 < 3) {
                    score14 = 3;
                }
                break;
            case 4:
                score14 = rand.nextInt(8);
                if (score14 < 3) {
                    score14 = 5;
                }
                break;
            case 5:
                score14 = rand.nextInt(10);
                if (score14 < 4) {
                    score14 = 5;
                }
                if (isLowScore) {
                    score14 = 4;
                }
                break;
        }
        tsbr.setScore14(score14);
        //
        int score15 = 0;
        switch (cc.getPar15()) {
            case 3:
                score15 = rand.nextInt(6);
                if (score15 < 3) {
                    score15 = 3;
                }
                break;
            case 4:
                score15 = rand.nextInt(8);
                if (score15 < 3) {
                    score15 = 4;
                }
                break;
            case 5:
                score15 = rand.nextInt(10);
                if (score15 < 4) {
                    score15 = 5;
                }
                break;
        }
        tsbr.setScore15(score15);
        //
        int score16 = 0;
        switch (cc.getPar16()) {
            case 3:
                score16 = rand.nextInt(6);
                if (score16 < 3) {
                    score16 = 3;
                }
                break;
            case 4:
                score16 = rand.nextInt(8);
                if (score16 < 3) {
                    score16 = 5;
                }
                if (isLowScore) {
                    score16 = 3;
                }
                break;
            case 5:
                score16 = rand.nextInt(10);
                if (score16 < 4) {
                    score16 = 5;
                }
                break;
        }
        tsbr.setScore16(score16);
        //
        int score17 = 0;
        switch (cc.getPar17()) {
            case 3:
                score17 = rand.nextInt(6);
                if (score17 < 3) {
                    score17 = 3;
                }
                break;
            case 4:
                score17 = rand.nextInt(8);
                if (score17 < 3) {
                    score17 = 4;
                }
                break;
            case 5:
                score17 = rand.nextInt(10);
                if (score17 < 4) {
                    score17 = 5;
                }
                if (isLowScore) {
                    score17 = 4;
                }
                break;
        }
        tsbr.setScore17(score17);
        //
        int score18 = 0;
        switch (cc.getPar18()) {
            case 3:
                score18 = rand.nextInt(6);
                if (score18 < 3) {
                    score18 = 3;
                }
                break;
            case 4:
                score18 = rand.nextInt(8);
                if (score18 < 3) {
                    score18 = 4;
                }
                if (isLowScore) {
                    score18 = 3;
                }
                break;
            case 5:
                score18 = rand.nextInt(10);
                if (score18 < 4) {
                    score18 = 5;
                }
                if (isLowScore) {
                    score18 = 4;
                }
                break;
        }
        tsbr.setScore18(score18);
    }

    private static Agegroup getPlayersAgeGroup(GolfGroupPlayer ggPlayer,
            DataUtil dataUtil) {
        Date today = new Date();
        Date bDate = ggPlayer.getPlayer().getDateOfBirth();

        int age = getDiffYears(bDate, today);
        if (age == 5 || age == 6) {
            return dataUtil.getAgeGroupByID(1);
        }
        if (age == 7 || age == 8) {
            return dataUtil.getAgeGroupByID(2);
        }
        if (age == 9 || age == 10) {
            return dataUtil.getAgeGroupByID(3);
        }
        if (age == 11 || age == 12) {
            return dataUtil.getAgeGroupByID(4);
        }
        if (age == 13 || age == 14) {
            return dataUtil.getAgeGroupByID(5);
        }
        if (age == 15 || age == 16) {
            return dataUtil.getAgeGroupByID(6);
        }
        if (age == 17 || age == 18) {
            return dataUtil.getAgeGroupByID(7);
        }
        return null;
    }

    public static int getDiffYears(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
        if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH)
                || (a.get(Calendar.MONTH) == b.get(Calendar.MONTH)
                && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
            diff--;
        }
        return diff;
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static String generatePlayers(int golfGroupID,
            DataUtil dataUtil) throws DataException {
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder();
        GolfGroup gg = dataUtil.getGroupByID(golfGroupID);
        sb.append("#### GENERATED PLAYERS AND PARENTS\n\n");
        //generate players in each age group
        EntityManager em = EMUtil.getEntityManager();
        List<Agegroup> list = getAgeGroupsBoys();
        int count = 1;
        for (Agegroup ageGroup : list) {
            for (int i = 0; i < 10; i++) {
                int fNameIndex = rand.nextInt(firstNames.length - 1);
                int lNameIndex = rand.nextInt(lastNames.length - 1);
                Player p = new Player();
                p.setDateRegistered(new Date());
                p.setFirstName(firstNames[fNameIndex]);
                p.setLastName(lastNames[lNameIndex]);
                p.setEmail("malengadev" + count + "@gmail.com");
                count++;
                p.setCellphone("099 999 9999");
                p.setGender(1);
                p.setPin("12345");
                p.setYearJoined(2013);
                p.setDateOfBirth(getDateOfBirth(ageGroup.getAgeGroupID()));

                Parent pr = new Parent();
                fNameIndex = rand.nextInt(firstNames.length - 1);
                pr.setFirstName(firstNames[fNameIndex]);
                pr.setLastName(p.getLastName());
                pr.setEmail("malengadev" + count + "@gmail.com");
                count++;
                pr.setCellphone("099 999 9999");
                pr.setPin("12345");

                EntityTransaction tran = em.getTransaction();
                tran.begin();
                em.persist(p);
                em.persist(pr);
                tran.commit();
                //

                GolfGroupPlayer ggp = new GolfGroupPlayer();
                ggp.setDateRegistered(new Date());
                ggp.setGolfGroup(gg);
                ggp.setPlayer(p);

                GolfGroupParent ggpr = new GolfGroupParent();
                ggpr.setDateRegistered(new Date());
                ggpr.setGolfGroup(gg);
                ggpr.setParent(pr);

                tran.begin();
                em.persist(ggp);
                em.persist(ggpr);
                tran.commit();

                sb.append("### Player Parent, GolfGroupPlayer and GolfGroupParent added. ");
                sb.append(p.getFirstName()).append(" ");
                sb.append(p.getLastName()).append("\t");
                sb.append(pr.getFirstName()).append(" ");
                sb.append(pr.getLastName()).append("\n");
                Logger.getLogger("Generator").log(Level.INFO,
                        "### Player Parent, GolfGroupPlayer and GolfGroupParent added. {0} {1} parent: {2} {3}",
                        new Object[]{p.getFirstName(), p.getLastName(), pr.getFirstName(), pr.getLastName()});
            }
            sb.append("\n\n################\n\n");
        }

        return sb.toString();
    }

    private static Date getDateOfBirth(int ageGroupID) {
        Calendar cal = GregorianCalendar.getInstance();
        switch (ageGroupID) {
            case 1:
                for (int i = 0; i < 6; i++) {
                    cal.roll(Calendar.YEAR, false);
                }
                break;
            case 2:
                for (int i = 0; i < 8; i++) {
                    cal.roll(Calendar.YEAR, false);
                }
                break;
            case 3:
                for (int i = 0; i < 10; i++) {
                    cal.roll(Calendar.YEAR, false);
                }
                break;
            case 4:
                for (int i = 0; i < 12; i++) {
                    cal.roll(Calendar.YEAR, false);
                }
                break;
            case 5:
                for (int i = 0; i < 14; i++) {
                    cal.roll(Calendar.YEAR, false);
                }
                break;
            case 6:
                for (int i = 0; i < 16; i++) {
                    cal.roll(Calendar.YEAR, false);
                }
                break;
            case 7:
                for (int i = 0; i < 18; i++) {
                    cal.roll(Calendar.YEAR, false);
                }
                break;
        }
        Random rand = new Random(System.currentTimeMillis());
        int mth = rand.nextInt(11);
        int day = rand.nextInt(28);
        if (mth == 0) {
            mth = 1;
        }
        if (day == 0) {
            day = 1;
        }
        cal.set(Calendar.MONTH, mth);
        cal.set(Calendar.DAY_OF_MONTH, day);

        return cal.getTime();
    }

    public static List<Agegroup> getAgeGroupsBoys() throws DataException {
        EntityManager em = EMUtil.getEntityManager();
        List<Agegroup> cList = new ArrayList<>();
        try {
            Query q = em.createQuery("select a from AgeGroup a  where a.gender = 1 "
                    + " order by a.ageGroupID ");

            cList = q.getResultList();
        } catch (Exception e) {
            throw new DataException();
        }
        return cList;
    }
    private static String[] firstNames = {
        "Benjamin", "Johnny", "Bakithi", "Sam", "Thomas", "Zeke", "Ivan",
        "Tommy", "Peter", "Paul", "Forrest", "Bennie", "Mark", "MacDonald",
        "McLean", "Obakeng", "Sipho", "Petrus", "Ronald", "Ronnie", "Blake",
        "John", "Vusi", "Jack", "Bobby", "Malenga", "Sean", "Shane",
        "Mack", "Jonty", "Lance", "David",
        "Jean", "Piet", "Francois", "Stephen", "Geoffrey", "Omar", "Andre", "Robert", "Hunter",
        "William", "Harry", "Boyce", "Lee", "Lawrence", "Michael", "Noonan", "Caleb", "Jacob",
        "Samuel", "Andrew", "Bernard", "Msapa", "Zukwa", "Johannes", "Lance", "Jeremiah", "Jerry",
        "Trayvon", "Newton", "Shakes", "Yipper", "Oupa", "Raymond", "Godfrey", "Solomon", "Nkosi"
    };
    private static String[] lastNames = {
        "Armstrong", "Maringa", "Ringane", "Oosthuizen", "Els", "Schwartzel",
        "Botha", "Smythe", "Baloyi", "Zungu", "Thobela", "Player", "Locke",
        "Black", "Venter", "Vermaak", "Jones", "Brown", "Potgieter",
        "van der Merwe", "Peyton", "Williams", "Maluleka", "Sono", "Mathebula", "Johnson", "Gray",
        "van Zyl", "Lombardi", "Smith", "Jackson", "Chauke", "Morris", "Peterson", "Paulson",
        "Remington", "Priest", "Church", "Charles", "Burmingham", "Naidoo", "Bala", "van Wyk", "Zulu",
        "Nxasa", "Sithole", "Nkosi"
    };
}
