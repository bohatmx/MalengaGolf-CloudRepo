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

/**
 *
 * @author Aubrey Malabie
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class LeaderBoardUtil {

    public  List<LeaderBoardDTO> getLeaderBoard(int tournamentID,
            DataUtil dataUtil) throws DataException {

        List<LeaderBoardDTO> list = new ArrayList<>();
        try {
            Tournament t = dataUtil.getTournamentByID(tournamentID);
            if (t != null) {
                for (TourneyPlayerScore s : t.getTourneyPlayerScoreList()) {
                    LeaderBoardDTO d = new LeaderBoardDTO();
                    d.setPlayer(new PlayerDTO(s.getPlayer(), false));
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
            throw new DataException();
        }

        return list;
    }

    public  List<LeaderBoardDTO> getLeaderBoardBoys(int tournamentID,
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
            throw new DataException();
        }

        return list;
    }

    public  List<LeaderBoardDTO> getLeaderBoardGirls(int tournamentID,
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
            throw new DataException();
        }

        return list;
    }
}
