/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.util;

import com.boha.golfkids.data.ClubCourse;
import com.boha.golfkids.data.LeaderBoard;
import com.boha.golfkids.data.Player;
import com.boha.golfkids.data.Tournament;
import com.boha.golfkids.data.TourneyPlayerScore;
import com.boha.golfkids.data.TourneyScoreByRound;
import com.boha.golfkids.dto.ClubCourseDTO;
import com.boha.golfkids.dto.LeaderBoardDTO;
import com.boha.golfkids.dto.PlayerDTO;
import com.boha.golfkids.dto.ResponseDTO;
import com.boha.golfkids.dto.TourneyPlayerScoreDTO;
import com.boha.golfkids.dto.TourneyScoreByRoundDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Aubrey Malabie
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class LeaderBoardUtil {

    @PersistenceContext
    EntityManager em;

    public ResponseDTO getLeaderBoard(int tournamentID, DataUtil dataUtil) throws DataException {
        ResponseDTO r = new ResponseDTO();
        List<LeaderBoardDTO> lbList = new ArrayList<>();
        try {
            Tournament t = dataUtil.getTournamentByID(tournamentID);
            Query q = em.createNamedQuery("TourneyPlayerScore.findByTournament",
                    TourneyPlayerScore.class);
            q.setParameter("id", tournamentID);
            List<TourneyPlayerScore> tpsList = q.getResultList();
            Query qq = em.createNamedQuery("TourneyScoreByRound.getByTourney",
                    TourneyScoreByRound.class);
            qq.setParameter("id", tournamentID);
            List<TourneyScoreByRound> rList = qq.getResultList();
            for (TourneyPlayerScore s : tpsList) {
                LeaderBoardDTO d = new LeaderBoardDTO();
                d.setPlayer(new PlayerDTO(s.getPlayer()));
                d.setRounds(t.getGolfRounds());
                d.setTournamentID(t.getTournamentID());
                d.setTournamentName(t.getTourneyName());
                
                d.setHolesPerRound(t.getHolesPerRound());
                d.setScoreRound1(s.getScoreRound1());
                d.setScoreRound2(s.getScoreRound2());
                d.setScoreRound3(s.getScoreRound3());
                d.setScoreRound4(s.getScoreRound4());
                d.setScoreRound5(s.getScoreRound5());
                d.setScoreRound6(s.getScoreRound6());
                d.setTotalScore(d.getTotalScore());
                d.setTourneyScoreByRoundList(new ArrayList<TourneyScoreByRoundDTO>());
                for (TourneyScoreByRound tsbr : rList) {
                    if (tsbr.getTourneyPlayerScore().getTourneyPlayerScoreID()
                            == s.getTourneyPlayerScoreID()) {
                        d.getTourneyScoreByRoundList().add(new TourneyScoreByRoundDTO(tsbr));
                    }
                }
                lbList.add(d);
            }
            //calculate current par status and position
            calculateLeaderboard(lbList);
            r.setLeaderBoardList(lbList);

        } catch (Exception e) {
            throw new DataException("Failed to get LeaderBoard\n"
                    + getErrorString(e));
        }

        return r;
    }

    public void createLeaderBoard(int tournamentID, DataUtil dataUtil) throws DataException {
        try {
            ResponseDTO r = getLeaderBoard(tournamentID, dataUtil);
            Tournament tn = dataUtil.getTournamentByID(tournamentID);
            Query q = em.createNamedQuery("Player.findByTourney", Player.class);
            q.setParameter("id", tournamentID);
            List<Player> playerList = q.getResultList();

            for (LeaderBoardDTO lb : r.getLeaderBoardList()) {
                LeaderBoard b = new LeaderBoard();
                b.setTournament(tn);
                b.setParStatus(lb.getParStatus());
                b.setPosition(lb.getPosition());
                b.setScoreRound1(lb.getScoreRound1());
                b.setScoreRound2(lb.getScoreRound2());
                b.setScoreRound3(lb.getScoreRound3());
                b.setScoreRound4(lb.getScoreRound4());
                b.setScoreRound5(lb.getScoreRound5());
                b.setScoreRound6(lb.getScoreRound6());
                if (lb.isTied()) {
                    b.setTied(1);
                } else {
                    b.setTied(0);
                }
                b.setTotalScore(lb.getTotalScore());
                for (Player player : playerList) {
                    if (player.getPlayerID() == lb.getPlayer().getPlayerID()) {
                        b.setPlayer(player);
                        break;
                    }
                }
                em.persist(b);
                Logger.getLogger("LBUtil").log(Level.INFO, "### Leaderboard created");
            }

        } catch (Exception e) {
            Logger.getLogger("LBUtil").log(Level.SEVERE, "Failed to get create Leaderboard");
            throw new DataException("Failed to get create Leaderboard\n"
                    + getErrorString(e));
        }
    }

    public ResponseDTO getPlayerHistory(int playerID) throws DataException {
        ResponseDTO r = new ResponseDTO();
        try {
            Query z = em.createNamedQuery("TourneyScoreByRound.getByPlayer", TourneyScoreByRound.class);
            z.setParameter("id", playerID);
            List<TourneyScoreByRound> tsbList = z.getResultList();

            //
            Query q = em.createNamedQuery("LeaderBoard.findByPlayer", LeaderBoard.class);
            q.setParameter("id", playerID);
            List<LeaderBoard> list = q.getResultList();
            r.setLeaderBoardList(new ArrayList());
            for (LeaderBoard lb : list) {
                LeaderBoardDTO b = new LeaderBoardDTO(lb, true);
                b.setTourneyScoreByRoundList(new ArrayList<TourneyScoreByRoundDTO>());
                for (TourneyScoreByRound tsb : tsbList) {
                    if (tsb.getTournamentIDx()
                            == b.getTournamentID()) {
                        b.getTourneyScoreByRoundList().add(new TourneyScoreByRoundDTO(tsb));
                    }
                }

                r.getLeaderBoardList().add(b);
            }

            Logger.getLogger("me").log(Level.OFF, "LeaderBoard items found: {0}",
                    r.getLeaderBoardList().size());
        } catch (Exception e) {
            throw new DataException("Failed to get player history\n"
                    + getErrorString(e));
        }

        return r;
    }

    public static String getErrorString(Exception e) {
        StringBuilder sb = new StringBuilder();
        if (e.getMessage() != null) {
            sb.append(e.getMessage()).append("\n\n");
        }
        if (e.toString() != null) {
            sb.append(e.toString()).append("\n\n");
        }
        StackTraceElement[] s = e.getStackTrace();
        if (s.length > 0) {
            StackTraceElement ss = s[0];
            String method = ss.getMethodName();
            String cls = ss.getClassName();
            int line = ss.getLineNumber();
            sb.append("Class: ").append(cls).append("\n");
            sb.append("Method: ").append(method).append("\n");
            sb.append("Line Number: ").append(line).append("\n");
        }

        return sb.toString();
    }

    private void setPositions(List<LeaderBoardDTO> list) {

        int mPosition = 1;
        int running = 1, score = 999;
        for (LeaderBoardDTO lb : list) {
            if (lb.isTied()) {
                if (score == 999) {
                    score = lb.getParStatus();
                    mPosition = running;
                    lb.setPosition(mPosition);
                } else {
                    if (score == lb.getParStatus()) {
                        lb.setPosition(mPosition);
                    } else {
                        score = lb.getParStatus();
                        mPosition = running;
                        lb.setPosition(mPosition);
                    }
                }

            } else {
                score = 999;
                lb.setPosition(running);
            }

            running++;
        }
    }
    public static final int NO_PAR_STATUS = 9999;

    private void logm(int par, int score, int parStatus) {
        log.log(Level.INFO, " par status = {0} par: {1} score: {2}", 
                new Object[]{parStatus, par, score});
    }
    private void setCurrentRoundStatus(LeaderBoardDTO lb, TourneyScoreByRoundDTO r) {
        
        int cnt = 0;
        ClubCourseDTO cc = r.getClubCourse();
        int parStatus = 0;
        if (r.getScore1() == 0) cnt++; else parStatus += cc.getParHole1() - r.getScore1();
        if (r.getScore2() == 0) cnt++; else parStatus += cc.getParHole2() - r.getScore2();
        if (r.getScore3() == 0) cnt++; else parStatus += cc.getParHole3() - r.getScore3();
        if (r.getScore4() == 0) cnt++; else parStatus += cc.getParHole4() - r.getScore4();
        if (r.getScore5() == 0) cnt++; else parStatus += cc.getParHole5() - r.getScore5();
        if (r.getScore6() == 0) cnt++; else parStatus += cc.getParHole6() - r.getScore6();
        if (r.getScore7() == 0) cnt++; else parStatus += cc.getParHole7() - r.getScore7();
        if (r.getScore8() == 0) cnt++; else parStatus += cc.getParHole8() - r.getScore8();
        if (r.getScore9() == 0) cnt++; else parStatus += cc.getParHole9() - r.getScore9();
        if (r.getScore10() == 0) cnt++; else parStatus += cc.getParHole10() - r.getScore10();
        if (r.getScore11() == 0) cnt++; else parStatus += cc.getParHole11() - r.getScore11();
        if (r.getScore12() == 0) cnt++; else parStatus += cc.getParHole12() - r.getScore12();
        if (r.getScore13() == 0) cnt++; else parStatus += cc.getParHole13() - r.getScore13();
        if (r.getScore14() == 0) cnt++; else parStatus += cc.getParHole14() - r.getScore14();
        if (r.getScore15() == 0) cnt++; else parStatus += cc.getParHole15() - r.getScore15();
        if (r.getScore16() == 0) cnt++; else parStatus += cc.getParHole16() - r.getScore16();
        if (r.getScore17() == 0) cnt++; else parStatus += cc.getParHole17() - r.getScore17();
        if (r.getScore18() == 0) cnt++; else parStatus += cc.getParHole18() - r.getScore18();
        if (cnt < 18) {
            lb.setCurrentRoundStatus(parStatus);
        }
        
    }
    private void setParStatus(LeaderBoardDTO lb) {
        int parStatus = 0;
        for (TourneyScoreByRoundDTO r : lb.getTourneyScoreByRoundList()) {
            setCurrentRoundStatus(lb, r);
            ClubCourseDTO cc = r.getClubCourse();
            if (r.getScore1() > 0) {
                parStatus += cc.getParHole1() - r.getScore1();
                lb.setLastHole(1);
            }
            if (r.getScore2() > 0) {
                parStatus += cc.getParHole2() - r.getScore2();
                lb.setLastHole(2);
            }
            if (r.getScore3() > 0) {
                parStatus += cc.getParHole3() - r.getScore3();
                lb.setLastHole(3);
            }
            if (r.getScore4() > 0) {
                parStatus += cc.getParHole4() - r.getScore4();
                lb.setLastHole(4);
            }
            if (r.getScore5() > 0) {
                parStatus += cc.getParHole5() - r.getScore5();
                lb.setLastHole(5);
            }
            if (r.getScore6() > 0) {
                parStatus += cc.getParHole6() - r.getScore6();
                lb.setLastHole(6);
            }
            if (r.getScore7() > 0) {
                parStatus += cc.getParHole7() - r.getScore7();
                lb.setLastHole(7);
            }
            if (r.getScore8() > 0) {
                parStatus += cc.getParHole8() - r.getScore8();
                lb.setLastHole(8);
            }
            if (r.getScore9() > 0) {
                parStatus += cc.getParHole9() - r.getScore9();
                lb.setLastHole(9);
            }
            if (r.getScore10() > 0) {
                parStatus += cc.getParHole10() - r.getScore10();
                lb.setLastHole(10);
            }
            if (r.getScore11() > 0) {
                parStatus += cc.getParHole11() - r.getScore11();
                lb.setLastHole(11);
            }
            if (r.getScore12() > 0) {
                parStatus += cc.getParHole12() - r.getScore12();
                lb.setLastHole(12);
            }
            if (r.getScore13() > 0) {
                parStatus += cc.getParHole13() - r.getScore13();
                lb.setLastHole(13);
            }
            if (r.getScore14() > 0) {
                parStatus += cc.getParHole14() - r.getScore14();
                lb.setLastHole(14);
            }
            if (r.getScore15() > 0) {
                parStatus += cc.getParHole15() - r.getScore15();
                lb.setLastHole(15);
            }
            if (r.getScore16() > 0) {
                parStatus += cc.getParHole16() - r.getScore16();
                lb.setLastHole(16);
            }
            if (r.getScore17() > 0) {
                parStatus += cc.getParHole17() - r.getScore17();
                lb.setLastHole(17);
            }
            if (r.getScore18() > 0) {
                parStatus += cc.getParHole18() - r.getScore18();
                lb.setLastHole(18);
            }
            lb.setParStatus(parStatus);
            Logger.getAnonymousLogger().log(Level.OFF,
                    "par status calculated: {0} - {1} {2}",
                    new Object[]{parStatus, lb.getPlayer().getFirstName(), lb.getPlayer().getLastName()});
            
        }
    }

    private void calculateLeaderboard(List<LeaderBoardDTO> list) {

        for (LeaderBoardDTO lb : list) {
            setParStatus(lb);
        }
        Collections.sort(list);
        //set positions
        HashMap<Integer, Integer> map = new HashMap<>();
        int pos = 1;
        for (LeaderBoardDTO board : list) {
            if (map.containsKey(board.getParStatus())) {
                Logger.getLogger("me").log(Level.OFF, "this parStatus {0} is tied",
                        board.getParStatus());
                continue;
            }
            map.put(board.getParStatus(), pos);
            pos++;
        }

        for (LeaderBoardDTO b : list) {
            if (map.containsKey(b.getParStatus())) {
                b.setPosition(map.get(b.getParStatus()));
            }
        }

        //check for tied
        HashMap<Integer, Integer> map2 = new HashMap<>();
        HashMap<Integer, Integer> tied = new HashMap<>();

        for (LeaderBoardDTO d : list) {
            if (d.getTotalScore() == 0) {
                continue;
            }
            if (map2.containsKey(d.getParStatus())) {
                tied.put(d.getParStatus(), d.getParStatus());
            } else {
                map2.put(d.getParStatus(), d.getParStatus());
            }

        }
        for (Map.Entry pairs : tied.entrySet()) {
            for (LeaderBoardDTO d : list) {
                int a = (Integer) pairs.getKey();
                if (d.getParStatus() == a) {
                    d.setTied(true);
                }
            }
        }
        setPositions(list);
    }
    static final Logger log = Logger.getLogger("LeaderBdUtil");
}
