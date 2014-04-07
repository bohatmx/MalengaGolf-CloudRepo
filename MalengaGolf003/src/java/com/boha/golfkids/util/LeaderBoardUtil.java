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

/**
 *
 * @author Aubrey Malabie
 */
public class LeaderBoardUtil {

    public static List<LeaderBoardDTO> getLeaderBoard(int tournamentID) throws DataException {

        List<LeaderBoardDTO> list = new ArrayList<LeaderBoardDTO>();
        try {
            Tournament t = DataUtil.getTournamentByID(tournamentID);
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
            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
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

    public static List<LeaderBoardDTO> getLeaderBoardBoys(int tournamentID) throws DataException {

        List<LeaderBoardDTO> list = new ArrayList<LeaderBoardDTO>();
        try {
            List<TourneyPlayerScoreDTO> listx = DataUtil.getScoresByTournamentBoys(tournamentID, false);

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
            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
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

    public static List<LeaderBoardDTO> getLeaderBoardGirls(int tournamentID) throws DataException {

        List<LeaderBoardDTO> list = new ArrayList<LeaderBoardDTO>();
        try {
            List<TourneyPlayerScoreDTO> listx = DataUtil.getScoresByTournamentGirls(tournamentID, false);

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
            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
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
