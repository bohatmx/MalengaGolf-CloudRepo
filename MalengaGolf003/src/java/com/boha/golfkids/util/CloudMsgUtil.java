/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.util;

import com.boha.golfkids.data.GcmDevice;
import com.boha.golfkids.data.LeaderboardViewer;
import com.boha.golfkids.dto.LeaderBoardDTO;
import com.boha.golfkids.dto.ResponseDTO;
import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
 * @author aubreyM
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CloudMsgUtil {

    @PersistenceContext
    EntityManager em;
    private static final int RETRIES = 5;
    public static final String API_KEY = CloudMessagingRegistrar.API_KEY;
    static final Gson gson = new Gson();

    public ResponseDTO sendScoreUpdate(LeaderBoardDTO lb, PlatformUtil platformUtil) throws
            Exception, DataException {

        ResponseDTO resp = new ResponseDTO();
        List<GcmDevice> deviceList = new ArrayList<>();
        Query q = em.createNamedQuery("LeaderboardViewer.findByTournament",
                LeaderboardViewer.class);
        q.setParameter("tid", lb.getTournamentID());
        List<LeaderboardViewer> list = q.getResultList();
        LOG.log(Level.INFO, "##### Leaderboard viewers found: {0}", list.size());
        for (LeaderboardViewer lbv : list) {
            if (lb.getTournamentName() == null) {
                lb.setTournamentName(lbv.getTournament().getTourneyName());
            }
            if (lbv.getAdministrator() != null) {
                q = em.createNamedQuery("GcmDevice.findByAdministrator", GcmDevice.class);
                q.setParameter("id", lbv.getAdministrator().getAdministratorID());
            }
            if (lbv.getAppUser() != null) {
                q = em.createNamedQuery("GcmDevice.findByAppUser", GcmDevice.class);
                q.setParameter("id", lbv.getAppUser().getAppUserID());
            }
            if (lbv.getScorer() != null) {
                q = em.createNamedQuery("GcmDevice.findByScorer", GcmDevice.class);
                q.setParameter("id", lbv.getScorer().getScorerID());
            }
            if (lbv.getPlayer() != null) {
                q = em.createNamedQuery("GcmDevice.findByPlayer", GcmDevice.class);
                q.setParameter("id", lbv.getPlayer().getPlayerID());
            }
            //
            List<GcmDevice> gList = q.getResultList();
            for (GcmDevice g : gList) {
                deviceList.add(g);
            }
        }

         LOG.log(Level.INFO, "GCM devices found: {0}", deviceList.size());
        //send a max 100 messages to google at a time
        String txJSON = gson.toJson(lb);
        if (deviceList.size() < 101) {
            int ret = sendMessage(txJSON, deviceList, platformUtil);
            LOG.log(Level.INFO, "GCM message return code (100 or fewer msgs): {0}", ret);
            return resp;
        }

        List<GcmDevice> pageList = new ArrayList<>();
        if (deviceList.size() < 101) {
            int ok = sendMessage(txJSON, deviceList, platformUtil);
            resp.setStatusCode(ok);
            LOG.log(Level.INFO, "GCM message return code - (pack of < 101 msgs): {0}", ok);
            return resp;
        } 
        int pages = deviceList.size() / 100;
        int rem = deviceList.size() % 100;
        if (rem > 0) {
            pages++;
        }

        for (int i = 0; i < pages; i++) {
            pageList = new ArrayList<>();
            for (int j = 0; j < 100; j++) {
                if (i == 0) {
                    pageList.add(deviceList.get(j));
                } else {
                    int index = (i * 100) + j;
                    pageList.add(deviceList.get(index));
                }
            }
            int ret = sendMessage(txJSON, pageList, platformUtil);
            resp.setStatusCode(ret);
            LOG.log(Level.INFO, "GCM message return code - (pack of 100 msgs): {0}", ret);
        }

        return resp;

    }

    public static final int GCM_MESSAGE_ERROR = 3, ALL_OK = 0;

    private static int sendMessage(String json, List<GcmDevice> deviceList, PlatformUtil platformUtil) {
        try {
            List<String> registrationIDs = new ArrayList<>();
            for (GcmDevice d : deviceList) {
                registrationIDs.add(d.getGcmRegistrationID());
                LOG.log(Level.OFF, "GCM regID address: {0}", d.getGcmRegistrationID());
            }
            LOG.log(Level.OFF, "....sending {0} gcm messages", registrationIDs.size());
            Sender sender = new Sender(API_KEY);
            Message message = new Message.Builder()
                    .addData("message", json)
                    .addData("dateStamp", "" + new Date().getTime()).build();

            boolean OK;
            
            
            if (registrationIDs.size() == 1) {
                Result result = sender.send(message, registrationIDs.get(0), RETRIES);
                OK = handleResult(result, platformUtil);
            } else {
                MulticastResult multiCastResult = sender.send(
                        message, registrationIDs, RETRIES);
                OK = handleMultiCastResult(multiCastResult, platformUtil);
            }
            if (!OK) {
                platformUtil.addErrorStore(889, "Google GCM - message has not been sent. Error occured", "Cloud Message Services");
                return GCM_MESSAGE_ERROR;
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "GCM FAIL", e);
            return GCM_MESSAGE_ERROR;
        }
        return ALL_OK;
    }

    private static boolean handleResult(Result result, PlatformUtil platformUtil)
            throws Exception {

        LOG.log(Level.INFO, "Handle result from Google GCM servers: {0}", result.toString());
        if (result.getErrorCodeName() != null) {
            if (result.getErrorCodeName().equals(
                    Constants.ERROR_NOT_REGISTERED)) {
                // TODO remove the registration from the database
                LOG.log(Level.SEVERE, "#### GCM device not registered");
                platformUtil.addErrorStore(889, "#### GCM device not registered", "Cloud Message Services");
                return false;
            }
            if (result.getErrorCodeName().equals(
                    Constants.ERROR_UNAVAILABLE)) {
                LOG.log(Level.SEVERE, "#### GCM servers not available");
                platformUtil.addErrorStore(889, "#### GCM servers not available", "Cloud Message Services");
                return false;
            }
            LOG.log(Level.SEVERE, "#### GCM message send error : {0}",
                    result.getErrorCodeName());
            platformUtil.addErrorStore(889, "#### GCM message send error\nErrorCodeName: " + result.getErrorCodeName(), "Cloud Message Services");
            return false;
        }

        if (result.getMessageId() != null) {
            LOG.log(Level.INFO, "Result messageID from GCM: {0}", result.getMessageId());
            if (result.getCanonicalRegistrationId() != null) {
                LOG.log(Level.INFO,
                        "### Google GCM - canonical registration id found, updating db ...");
                //TODO update device registration id
                //EntityManager em = EMUtil.getEntityManager();

            }
        }
        return true;
    }

    private static boolean handleMultiCastResult(MulticastResult multiCastResult, PlatformUtil platformUtil)
            throws Exception {
        LOG.log(Level.INFO, "Handle result from Google GCM servers: {0}", multiCastResult.toString());
        if (multiCastResult.getFailure() == 0
                && multiCastResult.getCanonicalIds() == 0) {
            LOG.log(Level.INFO, "### Google Cloud message send is OK, messages: {0}", multiCastResult.getTotal());
            return true;
        }
        LOG.log(Level.INFO,
                "### Google GCM - iterating through multicast Result for errors...");
        for (Result result : multiCastResult.getResults()) {
            if (result.getErrorCodeName() != null) {
                if (result.getErrorCodeName().equals(
                        Constants.ERROR_NOT_REGISTERED)) {                  
                    // TODO remove the registration from the database
                    LOG.log(Level.SEVERE, "#### GCM device not registered, error: {0} \nmessageID: {1}", new Object[]{result.getErrorCodeName(), result.getMessageId()});
                    platformUtil.addErrorStore(889, "#### GCM device not registered", "Cloud Message Services");
                    return false;
                }
                if (result.getErrorCodeName().equals(
                        Constants.ERROR_UNAVAILABLE)) {
                    // TODO resubmit this message after back-off
                    LOG.log(Level.SEVERE, "#### GCM servers not available");
                    platformUtil.addErrorStore(889, "#### GCM servers not available", "Cloud Message Services");
                    return false;
                }
                LOG.log(Level.SEVERE, "#### GCM message send error : {0}",
                        result.getErrorCodeName());
                platformUtil.addErrorStore(889, "#### GCM message send error\nErrorCodeName: " + result.getErrorCodeName(), "Cloud Message Services");
                return false;
            }

            if (result.getMessageId() != null) {
                LOG.log(Level.INFO, "Result messageID from GCM: {0}", result.getMessageId());
                if (result.getCanonicalRegistrationId() != null) {
                    LOG.log(Level.INFO,
                            "### Google GCM - canonical registration id found, updating db ...");
                    //update device registration id - query by gcmdevice by reg id ???????????

                }
            }
        }
        return true;
    }
    static final Logger LOG = Logger.getLogger("CloudMsgUtil");
}
