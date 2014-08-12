/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.servlet;

import com.boha.golfkids.dto.AgeGroupDTO;
import com.boha.golfkids.dto.CountryDTO;
import com.boha.golfkids.dto.RequestDTO;
import com.boha.golfkids.dto.ResponseDTO;
import com.boha.golfkids.dto.TournamentDTO;
import com.boha.golfkids.util.CloudMessagingRegistrar;
import com.boha.golfkids.util.DataException;
import com.boha.golfkids.util.DataUtil;
import com.boha.golfkids.util.GZipUtility;
import com.boha.golfkids.util.LeaderBoardPointsUtil;
import com.boha.golfkids.util.LeaderBoardUtil;
import com.boha.golfkids.util.MGGolfSession;
import com.boha.golfkids.util.PlatformUtil;
import com.boha.golfkids.util.WorkerBee;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author aubreyM
 */
@ServerEndpoint("/wsadmin")
@Stateful
public class GolfWebSocket {

    @EJB
    DataUtil dataUtil;
    @EJB
    LeaderBoardUtil leaderBoardUtil;
    @EJB
    PlatformUtil platformUtil;
    @EJB
    WorkerBee workerBee;
    @EJB
    LeaderBoardPointsUtil leaderBoardPointsUtil;
//TODO - clean up expired sessions!!!!
    public static final Set<MGGolfSession> peers
            = Collections.synchronizedSet(new HashSet<MGGolfSession>());

    public void sendLeaderBoard(ResponseDTO resp, int tournamentID)
            throws IOException, Exception {
        if (resp == null) throw new Exception("Response data is NULL");
         for (MGGolfSession mg : peers) {
            if (tournamentID == mg.getTournamentID()) {
                ByteBuffer bb = getZippedResponse(resp);
                mg.getSession().getBasicRemote().sendBinary(bb);
                log.log(Level.INFO, "LeaderBoard sent for tournament ID: {0} sessionID: {1}", new Object[]{tournamentID, mg.getSession().getId()});
            }
        }
    }

    private ByteBuffer getZippedResponse(ResponseDTO resp)
            throws Exception {
        File file = GZipUtility.getZipped(gson.toJson(resp));
        byte[] bFile = new byte[(int) file.length()];
        FileInputStream fileInputStream = null;

        //convert file into array of bytes
        fileInputStream = new FileInputStream(file);
        fileInputStream.read(bFile);
        fileInputStream.close();
        ByteBuffer buf = ByteBuffer.wrap(bFile);
        return buf;
    }

    @OnMessage
    public ByteBuffer onMessage(String message) {
        log.log(Level.WARNING, "onMessage: {0}", message);
        ResponseDTO resp = new ResponseDTO();
        try {
            RequestDTO dto = gson.fromJson(message, RequestDTO.class);
            switch (dto.getRequestType()) {
                case RequestDTO.REGISTER_FOR_TOURNAMENT_UPDATES:
                    for (MGGolfSession s : peers) {
                        if (dto.getSessionID() != null) {
                            if (dto.getSessionID().equalsIgnoreCase(s.getSession().getId())) {
                                s.setTournamentID(dto.getTournamentID());
                                resp.setMessage("User registered for tournament updates");
                                log.log(Level.INFO, "User session registered for updates");
                                break;
                            }
                        }
                    }

                    break;
                case RequestDTO.IMPORT_PLAYERS:
                    resp = dataUtil.importPlayers(dto.getImportPlayers(), dto.getGolfGroupID());
                    break;
                case RequestDTO.GET_PLAYER_GROUPS:
                    resp = dataUtil.getPlayerGroups(dto.getPlayerID());
                    break;
                case RequestDTO.GET_APPUSER_GROUPS:
                    resp = dataUtil.getAppUserGroups(dto.getAppUserID());
                    break;
                case RequestDTO.GET_TOURNAMENTS:
                    List<TournamentDTO> list = dataUtil.getTournamentByGroup(dto.getGolfGroupID());
                    resp.setTournaments(list);
                    break;
                case RequestDTO.SIGNIN_APP_USER:
                    resp = dataUtil.signInAppUser(dto.getEmail(), dto.getGcmDevice(), platformUtil);
                    break;
                case RequestDTO.REGISTER_APP_USER:
                    resp = dataUtil.addAppUser(dto.getGolfGroupID(), dto.getEmail(), platformUtil);
                    break;
                case RequestDTO.SIGN_IN_SCORER:
                    resp = dataUtil.signInScorer(dto.getEmail(), dto.getPin(), dto.getGcmDevice(), platformUtil);
                    break;
                case RequestDTO.SIGN_IN_PLAYER:
                    resp = dataUtil.signInPlayer(dto.getEmail(), dto.getPin(), dto.getGcmDevice(), platformUtil);
                    break;
                case RequestDTO.ADMIN_LOGIN:
                    resp = dataUtil.signInAdministrator(dto.getEmail(), dto.getPin(), dto.getGcmDevice(), platformUtil);
                    break;
                case RequestDTO.GET_ERROR_REPORTS:
                    resp = dataUtil.getMalengaGolfEvents(0, 0);
                    break;
                case RequestDTO.SEND_GCM_REGISTRATION:
                    resp = CloudMessagingRegistrar.sendRegistration(dto.getGcmRegistrationID(),
                            platformUtil);
                    break;
                case RequestDTO.UPDATE_TEE_TIMES:
                    resp = dataUtil.updateTeeTime(dto.getTourneyScoreByRound());
                    break;
                case RequestDTO.DELETE_TOURNAMENT:
                    resp = dataUtil.deleteTournament(dto.getTournamentID());
                    break;
                case RequestDTO.CLOSE_TOURNAMENT:
                    resp = dataUtil.closeTournament(dto.getTournamentID());
                    break;
                case RequestDTO.CLOSE_LEADERBORD:
                    resp = leaderBoardUtil.closeLeaderBoard(dto.getTournamentID());
                    break;

                case RequestDTO.UPDATE_WINNER_FLAG:
                    resp = dataUtil.updateWinnerFlag(dto.getLeaderBoardID(), dto.getWinnerFlag());
                    break;
                case RequestDTO.WITHDRAW_PLAYER:
                    resp = dataUtil.withdrawPlayer(dto.getTournamentID(), dto.getLeaderBoardID());
                    break;

                case RequestDTO.GET_GOLF_GROUP_DATA:
                    resp = dataUtil.getGolfGroupData(dto.getGolfGroupID(),
                            dto.getCountryID());
                    break;
                case RequestDTO.GET_CLUBS_IN_PROVINCE:
                    resp = dataUtil.getClubsByProvince(dto.getProvinceID(), dto.getPage(), workerBee);
                    break;
                case RequestDTO.DELETE_SAMPLE_TOURNAMENTS:
                    resp = dataUtil.deleteSample(dto.getGolfGroupID(), dto.getCountryID());
                    break;
                case RequestDTO.GET_CLUBS_NEARBY:
                    resp = workerBee.getClubsWithinRadius(
                            dto.getLatitude(), dto.getLongitude(),
                            dto.getRadius(), dto.getRadiusType(), dto.getPage());

                    break;
                case RequestDTO.GET_LEADERBOARD:
                    log.log(Level.INFO, "getting Leaderboard type is: {0}",
                            dto.getTournamentType());
                    switch (dto.getTournamentType()) {
                        case RequestDTO.STROKE_PLAY_INDIVIDUAL:
                            resp = leaderBoardUtil.getTournamentLeaderBoard(dto.getTournamentID(), dataUtil);
                            break;
                        case RequestDTO.STABLEFORD_INDIVIDUAL:
                            resp = leaderBoardPointsUtil.getTournamentLeaderBoard(dto.getTournamentID(), dataUtil);
                            break;
                    }
                    if (dto.getSessionID() != null) {
                        for (MGGolfSession s : peers) {
                            if (dto.getSessionID().equalsIgnoreCase(s.getSession().getId())) {
                                s.setTournamentID(dto.getTournamentID());
                                resp.setMessage("User registered for tournament updates");
                                log.log(Level.INFO, "## User registered for LeaderBoard, tournamentID: {0}", dto.getTournamentID());
                                break;
                            }
                        }
                    }
                    break;

                case RequestDTO.UPDATE_TOURNAMENT_SCORES:
                    resp = dataUtil.updateTournamentScoreByRound(dto.getLeaderBoard());
                    ResponseDTO xx = null;
                    switch (dto.getTournamentType()) {
                        case RequestDTO.STROKE_PLAY_INDIVIDUAL:
                            xx = leaderBoardUtil.getTournamentLeaderBoard(dto.getLeaderBoard().getTournamentID(), dataUtil);
                            break;
                        case RequestDTO.STABLEFORD_INDIVIDUAL:
                            xx = leaderBoardPointsUtil.getTournamentLeaderBoard(dto.getLeaderBoard().getTournamentID(), dataUtil);
                            break;
                        default:
                            xx = leaderBoardUtil.getTournamentLeaderBoard(dto.getLeaderBoard().getTournamentID(), dataUtil);
                            break;
                    }
                    
                    sendLeaderBoard(xx, dto.getLeaderBoard().getTournamentID());
                    break;
                case RequestDTO.UPDATE_TOURNAMENT_SCORE_TOTALS:
                    resp = dataUtil.updateTournamentScore(dto.getLeaderBoard());
                    break;
                case RequestDTO.ADD_TOURNAMENT_PLAYERS:
                    resp = dataUtil.addTournamentPlayers(dto.getIdList(), dto.getTournamentID(), platformUtil);
                    break;
                case RequestDTO.ADD_TOURNAMENT_PLAYER:
                    resp = dataUtil.addTournamentPlayer(dto.getLeaderBoard(), platformUtil);
                    break;

                case RequestDTO.ADD_TOURNAMENT:
                    resp = dataUtil.addTournament(dto.getTournament(), platformUtil);

                    break;
                case RequestDTO.ADD_PLAYER:
                    resp = dataUtil.addPlayer(dto.getPlayer(), dto.getGolfGroupID(), platformUtil);
                    break;
                case RequestDTO.UPDATE_PLAYER:
                    dataUtil.updatePlayer(dto.getPlayer());
                    break;

                case RequestDTO.ADD_PARENT:
                    resp = dataUtil.addParent(dto.getParent(), dto.getGolfGroupID(), platformUtil);
                    break;
                case RequestDTO.UPDATE_PARENT:
                    dataUtil.updateParent(dto.getParent());
                    break;

                case RequestDTO.ADD_ADMINISTRATOR:
                    resp = dataUtil.addGolfGroupAdmin(dto.getAdministrator());

                    break;
                case RequestDTO.ADD_GOLF_GROUP:
                    resp = dataUtil.addGolfGroup(dto.getGolfGroup(),
                            dto.getAdministrator(), platformUtil);
                    break;
                case RequestDTO.UPDATE_GOLF_GROUP:
                    dataUtil.updateGolfGroup(dto.getGolfGroup());
                    break;
                case RequestDTO.ADD_CLUB:
                    resp = dataUtil.addClub(dto.getClub());
                    break;
                case RequestDTO.GET_CLUBS_IN_COUNTRY:
                    resp = dataUtil.getClubsByCountry(dto.getCountryID());
                    break;

                case RequestDTO.GET_PROVINCES:
                    resp = dataUtil.getProvincesByCountry(dto.getCountryID());
                    break;

                case RequestDTO.UPDATE_PLAYER_PARENT:
                    //TODO - think!
                    break;
                case RequestDTO.UPDATE_ADMINISTRATOR:
                    dataUtil.updateAdmin(dto.getAdministrator());
                    break;
                case RequestDTO.UPDATE_CLUB:
                    dataUtil.updateClub(dto.getClub());
                    break;
                case RequestDTO.UPDATE_CLUB_COURSE:
                    dataUtil.updateClubCourse(dto.getClubCourse());
                    break;
                case RequestDTO.GET_AGE_GROUPS:
                    List<AgeGroupDTO> ageList = dataUtil.getAgeGroups(dto.getGolfGroupID());
                    resp.setAgeGroups(ageList);
                    break;
                case RequestDTO.GET_AGE_GROUPS_BOYS:
                    List<AgeGroupDTO> ageList1 = dataUtil.getAgeGroupsBoys(dto.getGolfGroupID());
                    resp.setAgeGroups(ageList1);
                    break;
                case RequestDTO.GET_AGE_GROUPS_GIRLS:
                    List<AgeGroupDTO> ageList2 = dataUtil.getAgeGroupsGirls(dto.getGolfGroupID());
                    resp.setAgeGroups(ageList2);
                    break;
                case RequestDTO.GET_COUNTRIES:
                    List<CountryDTO> cntrList = dataUtil.getCountries();
                    resp.setCountries(cntrList);
                    break;

                case RequestDTO.ADD_SCORER:
                    resp = dataUtil.addScorer(dto.getScorer(), dto.getGolfGroupID(), platformUtil);
                    break;
                case RequestDTO.UPDATE_SCORER:
                    dataUtil.updateScorer(dto.getScorer());
                    break;

                case RequestDTO.GET_TOURNAMENT_PLAYERS:
                    resp = dataUtil.getTournamentPlayers(dto.getTournamentID());
                    break;
                case RequestDTO.REMOVE_TOURNAMENT_PLAYER:
                    resp = dataUtil.removeTournamentPlayer(dto.getTournamentID(),
                            dto.getPlayerID());
                    break;
                case RequestDTO.UPDATE_TOURNAMENT:
                    resp = dataUtil.updateTournament(dto.getTournament());
                    break;
                case RequestDTO.GET_PLAYER_HISTORY:
                    resp = leaderBoardUtil.getPlayerHistory(dto.getPlayerID());
                    break;
                case RequestDTO.ADD_VIDEO_CLIP:
                    resp = dataUtil.addVideoClip(dto.getVideoClip());
                    break;
                case RequestDTO.GET_VIDEO_CLIPS_BY_GOLF_GROUP:
                    resp = dataUtil.getVideoClips(dto.getGolfGroupID());
                    break;
                default:
                    platformUtil.addErrorStore(7, "Request Type specified not on", "GolfAdminServlet");
                    resp.setStatusCode(7);
                    resp.setMessage("Request Type specified not on");
                    break;
            }

        } catch (DataException ex) {
            resp.setStatusCode(111);
            resp.setMessage("Data service failed to process your request");
            log.log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            resp.setStatusCode(112);
            resp.setMessage("Service failed to process your request");
            log.log(Level.SEVERE, null, ex);
        }
        ByteBuffer bb = null;
        try {
            bb = getZippedResponse(resp);
        } catch (Exception ex) {
            log.log(Level.SEVERE, null, ex);
        }
        return bb;
    }

    @OnOpen
    public void onOpen(Session session) {

        MGGolfSession m = new MGGolfSession();
        m.setSession(session);
        peers.add(m);
        try {
            ResponseDTO r = new ResponseDTO();
            r.setSessionID(session.getId());
            session.getBasicRemote().sendText(gson.toJson(r));
            log.log(Level.WARNING, "onOpen...sent session id: {0}", session.getId());
        } catch (IOException ex) {
            log.log(Level.SEVERE, "Failed to open websocket session", ex);
        } catch (Exception ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }

    @OnClose
    public void onClose(Session session
    ) {
        log.log(Level.WARNING, "onClose - remove session: {0}", session.getId());
        for (MGGolfSession mGGolfSession : peers) {
            if (session.getId().equalsIgnoreCase(mGGolfSession.getSession().getId())) {
                peers.remove(mGGolfSession);
                break;
            }
        }
    }

    @OnError
    public void onError(Throwable t) {
        log.log(Level.SEVERE, null, t);
    }
    static final Gson gson = new Gson();
    static final Logger log = Logger.getLogger(GolfWebSocket.class.getName());
}
