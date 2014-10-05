/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.servlet;

import com.boha.golfkids.data.LeaderboardViewer;
import com.boha.golfkids.dto.AgeGroupDTO;
import com.boha.golfkids.dto.CountryDTO;
import com.boha.golfkids.dto.LeaderBoardDTO;
import com.boha.golfkids.dto.RequestDTO;
import com.boha.golfkids.dto.ResponseDTO;
import com.boha.golfkids.dto.TournamentDTO;
import com.boha.golfkids.util.CloudMessagingRegistrar;
import com.boha.golfkids.util.CloudMsgUtil;
import com.boha.golfkids.util.DataException;
import com.boha.golfkids.util.DataUtil;
import com.boha.golfkids.util.FileUtility;
import com.boha.golfkids.util.GZipUtility;
import com.boha.golfkids.util.LeaderBoardPointsUtil;
import com.boha.golfkids.util.LeaderBoardUtil;
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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    @EJB
    CloudMsgUtil cloudMsgUtil;
    @PersistenceContext
    EntityManager em;

    static final String SOURCE = "GolfWebSocket";
//TODO - clean up expired sessions!!!!
    public static final Set<Session> peers
            = Collections.synchronizedSet(new HashSet<Session>());

    public void sendUpdatedScore(LeaderBoardDTO lb) {
        if (lb == null) {
            return;
        }
        ResponseDTO resp = new ResponseDTO();
        resp.setLeaderBoard(lb);
        Query q = em.createNamedQuery("LeaderboardViewer.findByTournament", LeaderboardViewer.class);
        q.setParameter("tid", lb.getTournamentID());
        List<LeaderboardViewer> list = q.getResultList();
        log.log(Level.INFO, "##### Leaderboard viewers found: {0}", list.size());
        int count = 0;
        for (LeaderboardViewer lbv : list) {
            for (Session session : peers) {
                if (session.getId().equalsIgnoreCase(lbv.getSessionID())) {
                    try {
                        session.getBasicRemote().sendBinary(getZippedResponse(resp));
                        count++;
                        if (lbv.getAdministrator() != null) {
                            log.log(Level.WARNING, "Updated score sent to admin: {0} {1}",
                                    new Object[]{lbv.getAdministrator().getFirstName(), lbv.getAdministrator().getLastName()});
                        }
                        if (lbv.getAppUser() != null) {
                            log.log(Level.WARNING, "Updated score sent to appUser: {0} id: {1}",
                                    new Object[]{lbv.getAppUser().getEmail(), lbv.getAppUser().getAppUserID()});
                        }
                        if (lbv.getScorer() != null) {
                            log.log(Level.WARNING, "Updated score sent to scorer: {0} {1}",
                                    new Object[]{lbv.getScorer().getFirstName(), lbv.getScorer().getLastName()});
                        }
                        if (lbv.getPlayer() != null) {
                            log.log(Level.WARNING, "Updated score sent to player: {0} {1}",
                                    new Object[]{lbv.getPlayer().getFirstName(), lbv.getPlayer().getLastName()});
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(GolfWebSocket.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        }
        log.log(Level.WARNING, "##### Updated score pushed to devices: {0}", count);
    }

    @OnMessage
    public ByteBuffer onMessage(String message) {
        log.log(Level.WARNING, "onMessage: {0}", message);
        ResponseDTO resp = new ResponseDTO();
        try {
            RequestDTO dto = gson.fromJson(message, RequestDTO.class);
            switch (dto.getRequestType()) {
                case RequestDTO.REGISTER_ADMIN_FOR_TOURNAMENT_UPDATES:
                    dataUtil.addTournamentViewer(dto.getTournamentID(),
                            dto.getAdministratorID(), DataUtil.ADMIN, dto.getSessionID());
                    resp.setMessage("Admin added as viewer");
                    break;
                case RequestDTO.REGISTER_APPUSER_FOR_TOURNAMENT_UPDATES:
                    dataUtil.addTournamentViewer(dto.getTournamentID(),
                            dto.getAppUserID(), DataUtil.APP_USER, dto.getSessionID());
                    resp.setMessage("Appuser added as viewer");
                    break;
                case RequestDTO.REGISTER_SCORER_FOR_TOURNAMENT_UPDATES:
                    dataUtil.addTournamentViewer(dto.getTournamentID(),
                            dto.getScorerID(), DataUtil.SCORER, dto.getSessionID());
                    resp.setMessage("Scorer added as viewer");
                    break;
                case RequestDTO.REGISTER_PLAYER_FOR_TOURNAMENT_UPDATES:
                    dataUtil.addTournamentViewer(dto.getTournamentID(),
                            dto.getPlayerID(), DataUtil.PLAYER, dto.getSessionID());
                    resp.setMessage("Player added as viewer");
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
                    resp = dataUtil.signInAppUser(dto.getEmail(),
                            dto.getGcmDevice(), platformUtil);
                    platformUtil.addErrorStore(PlatformUtil.SIGNIFICANT_EVENT,
                            "Leaderboard app user signed in", SOURCE);
                    break;
                case RequestDTO.REGISTER_APP_USER:
                    resp = dataUtil.addAppUser(dto.getGolfGroupID(),
                            dto.getEmail(), platformUtil);
                    break;
                case RequestDTO.SIGN_IN_SCORER:
                    resp = dataUtil.signInScorer(dto.getEmail(),
                            dto.getPin(), dto.getGcmDevice(), platformUtil);
                    platformUtil.addErrorStore(PlatformUtil.SIGNIFICANT_EVENT,
                            "Scorer app user signed in", SOURCE);
                    break;
                case RequestDTO.SIGN_IN_PLAYER:
                    resp = dataUtil.signInPlayer(dto.getEmail(),
                            dto.getPin(), dto.getGcmDevice(), platformUtil);
                    platformUtil.addErrorStore(PlatformUtil.SIGNIFICANT_EVENT,
                            "Player app user signed in", SOURCE);
                    break;
                case RequestDTO.ADMIN_LOGIN:
                    resp = dataUtil.signInAdministrator(dto.getEmail(),
                            dto.getPin(), dto.getGcmDevice(), platformUtil);
                    platformUtil.addErrorStore(PlatformUtil.SIGNIFICANT_EVENT,
                            "Administrator app user signed in", SOURCE);
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
                    if (dto.getAdministratorID() > 0) {
                        int x = dataUtil.addTournamentViewer(dto.getTournamentID(),
                                dto.getAdministratorID(), DataUtil.ADMIN, dto.getSessionID());
                        if (x == 1) {
                            resp.setMessage("Admin added as viewer");
                            log.log(Level.OFF, "Admin added as viewer, tournamentID: {0}",
                                    dto.getTournamentID());
                        }
                    }
                    if (dto.getAppUserID() > 0) {
                        int y = dataUtil.addTournamentViewer(dto.getTournamentID(),
                                dto.getAppUserID(), DataUtil.APP_USER, dto.getSessionID());
                        if (y == 1) {
                            resp.setMessage("Appuser added as viewer");
                            log.log(Level.OFF, "AppUser added as viewer, tournamentID: {0}",
                                    dto.getTournamentID());
                        }

                    }
                    if (dto.getScorerID() > 0) {
                        int z = dataUtil.addTournamentViewer(dto.getTournamentID(),
                                dto.getScorerID(), DataUtil.SCORER, dto.getSessionID());

                        if (z == 1) {
                            resp.setMessage("Scorer added as viewer");
                            log.log(Level.OFF, "Scorer added as viewer, tournamentID: {0}",
                                    dto.getTournamentID());
                        }

                    }
                    if (dto.getPlayerID() > 0) {
                        int t = dataUtil.addTournamentViewer(dto.getTournamentID(),
                                dto.getPlayerID(), DataUtil.PLAYER, dto.getSessionID());
                        if (t == 1) {
                            resp.setMessage("Player added as viewer");
                            log.log(Level.OFF, "Player added as viewer, tournamentID: {0}",
                                    dto.getTournamentID());
                        }
                    }

                    break;

                case RequestDTO.UPDATE_TOURNAMENT_SCORES:
                    resp = dataUtil.updateTournamentScoreByRound(dto.getLeaderBoard());
                    LeaderBoardDTO x = null;

                    for (LeaderBoardDTO lb : resp.getLeaderBoardList()) {
                        if (lb.getLeaderBoardID() == dto.getLeaderBoard().getLeaderBoardID()) {
                            x = lb;
                        }
                    }
                    if (dto.getAdministratorID() > 0) {
                        dataUtil.addTournamentViewer(dto.getLeaderBoard().getTournamentID(),
                                dto.getAdministratorID(), DataUtil.ADMIN, dto.getSessionID());
                    }
                    if (dto.getScorerID() > 0) {
                        dataUtil.addTournamentViewer(dto.getLeaderBoard().getTournamentID(),
                                dto.getScorerID(), DataUtil.SCORER, dto.getSessionID());
                    }
                    if (x != null) {
                        resp.setLeaderBoardUpdateRecord(true);
                        sendUpdatedScore(x);
                        cloudMsgUtil.sendScoreUpdate(x, platformUtil);
                    }

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
                    platformUtil.addErrorStore(PlatformUtil.SIGNIFICANT_EVENT,
                            "Golf Group registered: "
                            + dto.getGolfGroup().getGolfGroupName(), SOURCE);
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
                case RequestDTO.GET_TOURNAMENT_PICTURES:
                    resp.setImageFileNames(FileUtility.getImageFilesTournament(
                            dto.getGolfGroupID(), dto.getTournamentID(),
                            RequestDTO.PICTURES_FULL_SIZE));
                    break;
                case RequestDTO.GET_TOURNAMENT_THUMBNAILS:
                    resp.setImageFileNames(FileUtility.getImageFilesTournament(
                            dto.getGolfGroupID(), dto.getTournamentID(),
                            RequestDTO.PICTURES_THUMBNAILS));
                    break;
                case RequestDTO.GET_GOLFGROUP_PICTURES:
                    resp.setImageFileNames(FileUtility.getImageFilesGolfGroup(
                            dto.getGolfGroupID(), RequestDTO.PICTURES_FULL_SIZE));
                    break;
                case RequestDTO.GET_GOLFGROUP_THUMBNAILS:
                    resp.setImageFileNames(FileUtility.getImageFilesGolfGroup(
                            dto.getGolfGroupID(), RequestDTO.PICTURES_THUMBNAILS));
                    break;
                default:
                    platformUtil.addErrorStore(PlatformUtil.ERROR_UNKNOWN_REQUEST,
                            "Request Type specified not on", SOURCE);
                    resp.setStatusCode(7);
                    resp.setMessage("Request Type specified not on");
                    break;
            }

        } catch (DataException ex) {
            resp.setStatusCode(PlatformUtil.ERROR_DATABASE);
            resp.setMessage("Data service failed to process your request");
            log.log(Level.SEVERE, "Database related failure", ex);
            platformUtil.addErrorStore(PlatformUtil.ERROR_DATABASE,
                    "Database related failure:\n"
                    + ex.description, SOURCE);
        } catch (Exception ex) {
            resp.setStatusCode(PlatformUtil.ERROR_SERVER);
            resp.setMessage("Service failed to process your request");
            log.log(Level.SEVERE, null, ex);
            platformUtil.addErrorStore(PlatformUtil.ERROR_SERVER,
                    "Server generic failure\n" + ex.getMessage(), SOURCE);
        }
        //pack the response object before sending
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

        peers.add(session);
        try {
            ResponseDTO r = new ResponseDTO();
            r.setSessionID(session.getId());
            session.getBasicRemote().sendText(gson.toJson(r));
            log.log(Level.WARNING, "onOpen...sent session id: {0}", session.getId());
        } catch (IOException ex) {
            log.log(Level.SEVERE, "Failed to send websocket sessionID", ex);
        } catch (Exception ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }

    @OnClose
    public void onClose(Session session
    ) {
        log.log(Level.WARNING, "onClose - remove session: {0}", session.getId());
        for (Session mGGolfSession : peers) {
            if (session.getId().equalsIgnoreCase(mGGolfSession.getId())) {
                peers.remove(mGGolfSession);
                break;
            }
        }
    }

    @OnError
    public void onError(Throwable t) {
        log.log(Level.SEVERE, null, t);
        platformUtil.addErrorStore(PlatformUtil.ERROR_WEBSOCKET,
                "WebScocket related failure\n" + t.getMessage(), "GolfWebSocket");
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

        file.delete();
        return buf;
    }
    static final Gson gson = new Gson();
    static final Logger log = Logger.getLogger(GolfWebSocket.class.getName());
}
