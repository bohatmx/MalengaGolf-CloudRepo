/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.util;

import com.boha.golfkids.data.Tournament;
import com.boha.golfkids.data.TourneyPlayerScore;
import com.boha.golfkids.dto.LeaderBoardDTO;
import com.boha.golfkids.dto.PlayerDTO;
import com.boha.golfkids.dto.TourneyPlayerScoreDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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

    public List<LeaderBoardDTO> getLeaderBoard(int tournamentID,
            DataUtil dataUtil) throws DataException {

        List<LeaderBoardDTO> list = new ArrayList<>();
        try {
            Tournament t = dataUtil.getTournamentByID(tournamentID);
            Query q = em.createNamedQuery("TourneyPlayerScore.findByTournament", TourneyPlayerScore.class);
            q.setParameter("id", tournamentID);
            List<TourneyPlayerScore> tpsList = q.getResultList();
            if (t != null) {
                for (TourneyPlayerScore s : tpsList) {
                    LeaderBoardDTO d = new LeaderBoardDTO();
                    d.setPlayer(new PlayerDTO(s.getPlayer()));
                    d.setScoreRound1(s.getScoreRound1());
                    d.setScoreRound2(s.getScoreRound2());
                    d.setScoreRound3(s.getScoreRound3());
                    d.setScoreRound4(s.getScoreRound4());
                    d.setTotalScore(d.getTotalScore());
                    list.add(d);
                }
            }
            Collections.sort(list);

            //set positions
            HashMap<Integer, Integer> map = new HashMap<>();
            int pos = 1;
            for (LeaderBoardDTO board : list) {
                if (map.containsKey(board.getTotalScore())) {
                    continue;
                }
                map.put(board.getTotalScore(), pos);
                pos++;
            }

            for (LeaderBoardDTO b : list) {
                b.setPosition(map.get(b.getTotalScore()));
            }
        } catch (Exception e) {
            throw new DataException(getErrorString(e));
        }

        return list;
    }

    public List<LeaderBoardDTO> getLeaderBoardBoys(int tournamentID,
            DataUtil dataUtil) throws DataException {

        List<LeaderBoardDTO> list = new ArrayList<>();
        try {
            List<TourneyPlayerScoreDTO> listx = dataUtil.getScoresByTournamentBoys(tournamentID, false);

            for (TourneyPlayerScoreDTO s : listx) {
                LeaderBoardDTO d = new LeaderBoardDTO();
                d.setPlayer(s.getPlayer());
                d.setScoreRound1(s.getScoreRound1());
                d.setScoreRound2(s.getScoreRound2());
                d.setScoreRound3(s.getScoreRound3());
                d.setScoreRound4(s.getScoreRound4());
                d.setTotalScore(d.getTotalScore());
                list.add(d);
            }

            Collections.sort(list);

            //set positions
            HashMap<Integer, Integer> map = new HashMap<>();
            int pos = 1;
            for (LeaderBoardDTO board : list) {
                if (map.containsKey(board.getTotalScore())) {
                    continue;
                }
                map.put(board.getTotalScore(), pos);
                pos++;
            }

            for (LeaderBoardDTO b : list) {
                b.setPosition(map.get(b.getTotalScore()));
            }
        } catch (Exception e) {
            throw new DataException(getErrorString(e));
        }

        return list;
    }

    public List<LeaderBoardDTO> getLeaderBoardGirls(int tournamentID,
            DataUtil dataUtil) throws DataException {

        List<LeaderBoardDTO> list = new ArrayList<>();
        try {
            List<TourneyPlayerScoreDTO> listx = dataUtil.getScoresByTournamentGirls(tournamentID, false);

            for (TourneyPlayerScoreDTO s : listx) {
                LeaderBoardDTO d = new LeaderBoardDTO();
                d.setPlayer(s.getPlayer());
                d.setScoreRound1(s.getScoreRound1());
                d.setScoreRound2(s.getScoreRound2());
                d.setScoreRound3(s.getScoreRound3());
                d.setScoreRound4(s.getScoreRound4());
                d.setTotalScore(d.getTotalScore());
                list.add(d);
            }

            Collections.sort(list);

            //set positions
            HashMap<Integer, Integer> map = new HashMap<>();
            int pos = 1;
            for (LeaderBoardDTO board : list) {
                if (map.containsKey(board.getTotalScore())) {
                    continue;
                }
                map.put(board.getTotalScore(), pos);
                pos++;
            }

            for (LeaderBoardDTO b : list) {
                b.setPosition(map.get(b.getTotalScore()));
            }
        } catch (Exception e) {
            throw new DataException(getErrorString(e));
        }

        return list;
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
}
