/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.golfkids.dto;

import java.util.List;

/**
 *
 * @author aubreyM
 */
public class LeaderBoardBucket {
   
    private List<LeaderBoardDTO> leaderBoardList;
    private TournamentDTO tournamentDTO;

    public List<LeaderBoardDTO> getLeaderBoardList() {
        return leaderBoardList;
    }

    public void setLeaderBoardList(List<LeaderBoardDTO> leaderBoardList) {
        this.leaderBoardList = leaderBoardList;
    }

    public TournamentDTO getTournamentDTO() {
        return tournamentDTO;
    }

    public void setTournamentDTO(TournamentDTO tournamentDTO) {
        this.tournamentDTO = tournamentDTO;
    }
}
