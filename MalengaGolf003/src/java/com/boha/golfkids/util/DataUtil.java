/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.util;

import com.boha.golfkids.data.Administrator;
import com.boha.golfkids.data.Agegroup;
import com.boha.golfkids.data.Club;
import com.boha.golfkids.data.ClubCourse;
import com.boha.golfkids.data.Country;
import com.boha.golfkids.data.GolfGroup;
import com.boha.golfkids.data.GolfGroupParent;
import com.boha.golfkids.data.GolfGroupPlayer;
import com.boha.golfkids.data.LeaderBoard;
import com.boha.golfkids.data.OrderOfMeritPoint;
import com.boha.golfkids.data.Parent;
import com.boha.golfkids.data.Player;
import com.boha.golfkids.data.Province;
import com.boha.golfkids.data.Scorer;
import com.boha.golfkids.data.Tournament;
import com.boha.golfkids.data.TournamentCourse;
import com.boha.golfkids.data.TourneyScoreByRound;
import com.boha.golfkids.data.VideoClip;
import com.boha.golfkids.dto.AdministratorDTO;
import com.boha.golfkids.dto.AgeGroupDTO;
import com.boha.golfkids.dto.ClubCourseDTO;
import com.boha.golfkids.dto.ClubDTO;
import com.boha.golfkids.dto.CountryDTO;
import com.boha.golfkids.dto.GolfGroupDTO;
import com.boha.golfkids.dto.GolfGroupPlayerDTO;
import com.boha.golfkids.dto.LeaderBoardDTO;
import com.boha.golfkids.dto.ParentDTO;
import com.boha.golfkids.dto.PlayerDTO;
import com.boha.golfkids.dto.ProvinceDTO;
import com.boha.golfkids.dto.ResponseDTO;
import com.boha.golfkids.dto.ScorerDTO;
import com.boha.golfkids.dto.TournamentCourseDTO;
import com.boha.golfkids.dto.TournamentDTO;
import com.boha.golfkids.dto.TourneyScoreByRoundDTO;
import com.boha.golfkids.dto.VideoClipDTO;
import com.boha.golfkids.util.golfdata.LoaderResponseDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.Years;

/**
 *
 * @author Aubrey Malabie
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DataUtil {

    @PersistenceContext
    EntityManager em;
    WorkerBee workerBee = new WorkerBee();

    public LoaderResponseDTO findClubsWithinRadius(
            double latitude, double longitude, int radius, int type) throws DataException {
        LoaderResponseDTO r = new LoaderResponseDTO();
        r.setClubList(new ArrayList<ClubDTO>());
        try {
            r.setClubList(workerBee.getClubsWithinRadius(latitude, longitude, radius, type));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to findClubsWithinRadius");
            throw new DataException("Failed to findClubsWithinRadius\n"
                    + getErrorString(e));
        }
        return r;
    }

    public ResponseDTO withdrawPlayer(int tournamentID, int leaderBoardID) throws DataException {
        ResponseDTO r = new ResponseDTO();
        try {
            LeaderBoard lb = em.find(LeaderBoard.class, leaderBoardID);
            lb.setWithDrawn(1);
            em.merge(lb);
            Query y = em.createNamedQuery("TourneyScoreByRound.getByTourneyPlayer", TourneyScoreByRound.class);
            y.setParameter("tID", tournamentID);
            y.setParameter("pID", lb.getPlayer().getPlayerID());
            List<TourneyScoreByRound> tsbrList = y.getResultList();
            for (TourneyScoreByRound tsbr : tsbrList) {
                if (tsbr.getLeaderBoard().getLeaderBoardID() == lb.getLeaderBoardID()) {
                    tsbr.setScoringComplete(1);
                    em.merge(tsbr);
                }
            }
            logger.log(Level.SEVERE, "Player withdrawn: {0} {1} - from: {2}",
                    new Object[]{lb.getPlayer().getFirstName(), lb.getPlayer().getLastName(),
                        lb.getTournament().getTourneyName()});
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to withdrawPlayer");
            throw new DataException("Failed to withdrawPlayer\n"
                    + getErrorString(e));
        }
        return r;
    }

    public ResponseDTO addVideoClip(VideoClipDTO clip) throws DataException {
        ResponseDTO r = new ResponseDTO();
        try {
            VideoClip c = new VideoClip();
            GolfGroup gg = getGroupByID(clip.getGolfGroupID());
            c.setGolfGroup(gg);
            if (clip.getTournamentID() > 0) {
                c.setTournament(getTournamentByID(clip.getTournamentID()));
            }
            c.setYouTubeID(clip.getYouTubeID());
            c.setComment(clip.getComment());
            c.setLength(clip.getLength());
            em.persist(c);
            Query a = em.createNamedQuery("VideoClip.findAllInGroup", VideoClip.class);
            a.setParameter("gID", clip.getGolfGroupID());
            a.setParameter("tID", clip.getTournamentID());
            List<VideoClip> list = a.getResultList();
            List<VideoClipDTO> dList = new ArrayList<>();
            for (VideoClip videoClip : list) {
                dList.add(new VideoClipDTO(videoClip));
            }
            r.setVideoClips(dList);

            logger.log(Level.INFO, "Video clip added");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to add videoClip");
            throw new DataException("Failed to add videoClip\n"
                    + getErrorString(e));
        }

        return r;
    }

    public ResponseDTO getVideoClips(int golfGroupID) throws DataException {
        ResponseDTO r = new ResponseDTO();
        try {
            Query a = em.createNamedQuery("VideoClip.findAllInGroup", VideoClip.class);
            a.setParameter("gID", golfGroupID);
            a.setParameter("tID", 0);
            List<VideoClip> list = a.getResultList();
            List<VideoClipDTO> dList = new ArrayList<>();
            for (VideoClip videoClip : list) {
                dList.add(new VideoClipDTO(videoClip));
            }
            r.setVideoClips(dList);

        } catch (Exception e) {
            logger.log(Level.INFO, "Failed to get videoClips");
            throw new DataException("Failed to get videoClips\n"
                    + getErrorString(e));
        }

        return r;
    }

    public ResponseDTO closeTournament(int tournamentID) throws DataException {
        ResponseDTO r = new ResponseDTO();
        try {
            Tournament tx = getTournamentByID(tournamentID);
            tx.setClosedForScoringFlag(1);
            em.merge(tx);
            logger.log(Level.INFO, "Tournament closed - {0}", tx.getTourneyName());
        } catch (Exception e) {
            logger.log(Level.INFO, "Failed to close tournament");
            throw new DataException("Failed to close tournament\n"
                    + getErrorString(e));
        }

        return r;
    }

    public ResponseDTO updateTournament(TournamentDTO t) throws DataException {
        ResponseDTO r = new ResponseDTO();
        try {
            Tournament tx = getTournamentByID(t.getTournamentID());
            tx.setTourneyName(t.getTourneyName());
            tx.setStartDate(new Date(t.getStartDate()));
            tx.setEndDate(new Date(t.getEndDate()));
            if (t.getClubID() > 0) {
                tx.setClub(getClubByID(t.getClubID()));
            }
            tx.setClosedForScoringFlag(t.getClosedForScoringFlag());
            tx.setClosedForRegistrationFlag(t.getClosedForRegistrationFlag());
            tx.setGolfRounds(t.getGolfRounds());
            em.merge(tx);
            logger.log(Level.INFO, "Tournament updated OK");
            r.setMessage("Tournament is updated");
        } catch (Exception e) {
            logger.log(Level.INFO, "Failed to update tournament");
            throw new DataException("Failed to update tournament\n"
                    + getErrorString(e));
        }
        return r;
    }

    public ResponseDTO getTournamentPlayers(int tournamentID) throws DataException {
        ResponseDTO r = new ResponseDTO();
        try {
            Query q = em.createNamedQuery("LeaderBoard.findByTournament", LeaderBoard.class);
            q.setParameter("id", tournamentID);
            List<LeaderBoard> list = q.getResultList();
            List<LeaderBoardDTO> dto = new ArrayList<>();
            for (LeaderBoard tps : list) {
                if (tps.getWithDrawn() > 0) {
                    continue;
                }
                dto.add(new LeaderBoardDTO(tps));
            }
            Query qx = em.createNamedQuery("TourneyScoreByRound.getByTourney", TourneyScoreByRound.class);
            qx.setParameter("id", tournamentID);
            List<TourneyScoreByRound> xList = qx.getResultList();

            for (LeaderBoardDTO tps : dto) {
                tps.setTourneyScoreByRoundList(new ArrayList<TourneyScoreByRoundDTO>());
                for (TourneyScoreByRound tbr : xList) {
                    if (tbr.getLeaderBoard().getLeaderBoardID() == tps.getLeaderBoardID()) {
                        tps.getTourneyScoreByRoundList().add(new TourneyScoreByRoundDTO(tbr));
                    }
                }
            }
            r.setLeaderBoardList(dto);

        } catch (Exception e) {
            logger.log(Level.INFO, "Failed to get Tourney players");
            throw new DataException("Failed to get Tourney players\n"
                    + getErrorString(e));
        }

        return r;
    }

    public ResponseDTO getGolfGroupData(int golfGroupID, int countryID)
            throws DataException {

        //get players, tours, parents, admins, scorers
        ResponseDTO r = new ResponseDTO();
        try {
            Query q = em.createNamedQuery("Player.findByGolfGroup", Player.class);
            q.setParameter("id", golfGroupID);
            List<Player> playerList = q.getResultList();
            r.setPlayers(getPlayerDTOList(playerList));
            //
            Query z = em.createNamedQuery("LeaderBoard.countByPlayer");
            z.setParameter("id", golfGroupID);
            List<Object[]> results = z.getResultList();
            for (PlayerDTO tn : r.getPlayers()) {
                for (Object[] o : results) {
                    int id = ((Number) o[0]).intValue();
                    if (tn.getPlayerID() == id) {
                        tn.setNumberOfTournaments(((Number) o[1]).intValue());
                    }
                }
            }

            q = em.createNamedQuery("Tournament.findByGolfGroup", Tournament.class);
            q.setParameter("id", golfGroupID);
            List<Tournament> tourList = q.getResultList();
            r.setTournaments(getTournamentDTOList(tourList));

            //get tournament courses
            Query y = em.createNamedQuery("TournamentCourse.findByGolfGroup", TournamentCourse.class);
            y.setParameter("id", golfGroupID);
            List<TournamentCourse> tcList = y.getResultList();
            logger.log(Level.OFF, "TournamentCourses found: {0}", tcList.size());
            Query qx = em.createNamedQuery("LeaderBoard.countByTourney");
            qx.setParameter("id", golfGroupID);
            List<Object[]> resultsx = qx.getResultList();
            for (TournamentDTO tn : r.getTournaments()) {
                for (Object[] object : resultsx) {
                    int id = ((Number) object[0]).intValue();
                    if (tn.getTournamentID() == id) {
                        tn.setNumberOfRegisteredPlayers(((Number) object[1]).intValue());
                    }
                }
                tn.setTournamentCourses(new ArrayList<TournamentCourseDTO>());
                for (TournamentCourse tc : tcList) {
                    if (tc.getTournament().getTournamentID() == tn.getTournamentID()) {
                        tn.getTournamentCourses().add(new TournamentCourseDTO(tc));
                    }
                }
            }
            //
            q = em.createNamedQuery("Parent.findbyGolfGroup", Parent.class);
            q.setParameter("id", golfGroupID);
            List<Parent> parentList = q.getResultList();
            r.setParents(getParentDTOList(parentList));
            q = em.createNamedQuery("Player.findByGolfGroup", Player.class);
            q.setParameter("id", golfGroupID);
            List<PlayerDTO> dList = new ArrayList<>();
            List<Player> listp = q.getResultList();
            for (Player player : listp) {
                dList.add(new PlayerDTO(player));
            }
            for (ParentDTO px : r.getParents()) {
                px.setPlayers(new ArrayList<PlayerDTO>());
                for (PlayerDTO pd : dList) {
                    if (pd.getParentID() == px.getParentID()) {
                        px.getPlayers().add(pd);
                    }
                }
            }
            //
            q = em.createNamedQuery("Scorer.findByGolfGroup", Scorer.class);
            q.setParameter("id", golfGroupID);
            List<Scorer> scorerList = q.getResultList();
            r.setScorers(getScorerDTOList(scorerList));
            //
            q = em.createNamedQuery("Administrator.getByGroup", Administrator.class);
            q.setParameter("id", golfGroupID);
            List<Administrator> adminList = q.getResultList();
            r.setAdministrators(getAdministratorDTOList(adminList));
            //
            q = em.createNamedQuery("Province.findByCountry", Province.class);
            q.setParameter("id", countryID);
            List<Province> provList = q.getResultList();
            r.setProvinces(getProvinceDTOList(provList));
           
            logger.log(Level.OFF, "GolfGroup data retrieved");
        } catch (Exception e) {
            logger.log(Level.INFO, "Failed to get GolfGroup");
            throw new DataException("Failed to get GolfGroup\n"
                    + getErrorString(e));
        }

        return r;
    }

    private List<ProvinceDTO> getProvinceDTOList(List<Province> list) {
        List<ProvinceDTO> aList = new ArrayList<>();
        for (Province p : list) {
            aList.add(new ProvinceDTO(p));
        }
        logger.log(Level.OFF, "Province data retrieved: {0}", aList.size());
        return aList;
    }

    private List<ClubDTO> getClubDTOList(List<Club> list) {
        List<ClubDTO> aList = new ArrayList<>();
        for (Club player : list) {
            aList.add(new ClubDTO(player));
        }
        logger.log(Level.OFF, "Club data retrieved: {0}", aList.size());
        return aList;
    }

    private List<PlayerDTO> getPlayerDTOList(List<Player> list) {
        List<PlayerDTO> playerList = new ArrayList<>();
        for (Player player : list) {
            playerList.add(new PlayerDTO(player));
        }
        logger.log(Level.OFF, "Player data retrieved: {0}", playerList.size());
        return playerList;
    }

    private List<TournamentDTO> getTournamentDTOList(List<Tournament> list) {
        List<TournamentDTO> aList = new ArrayList<>();
        for (Tournament t : list) {
            aList.add(new TournamentDTO(t));
        }
        logger.log(Level.OFF, "Tourney data retrieved: {0}", aList.size());
        return aList;
    }

    private List<ParentDTO> getParentDTOList(List<Parent> list) {
        List<ParentDTO> aList = new ArrayList<>();
        for (Parent t : list) {
            aList.add(new ParentDTO(t));
        }
        logger.log(Level.OFF, "Parent data retrieved: {0}", aList.size());
        return aList;
    }

    private List<ScorerDTO> getScorerDTOList(List<Scorer> list) {
        List<ScorerDTO> aList = new ArrayList<>();
        for (Scorer t : list) {
            aList.add(new ScorerDTO(t));
        }
        logger.log(Level.OFF, "Scorer data retrieved: {0}", aList.size());
        return aList;
    }

    private List<AdministratorDTO> getAdministratorDTOList(List<Administrator> list) {
        List<AdministratorDTO> aList = new ArrayList<>();
        for (Administrator t : list) {
            aList.add(new AdministratorDTO(t));
        }
        logger.log(Level.OFF, "Admins data retrieved: {0}", aList.size());
        return aList;
    }

    public String getErrorString(Exception e) {
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

    public ResponseDTO getClubsByCountry(int countryID) throws DataException {
        ResponseDTO r = new ResponseDTO();
        try {
            Query qx = em.createNamedQuery("Province.findByCountry", Province.class);
            qx.setParameter("id", countryID);
            List<Province> pList = qx.getResultList();
            List<ProvinceDTO> dList = new ArrayList<>();
            for (Province p : pList) {
                dList.add(new ProvinceDTO(p));
            }
            Query q = em.createNamedQuery("Club.findByCountry", Club.class);
            q.setParameter("id", countryID);
            List<Club> clubList = q.getResultList();
            Query z = em.createNamedQuery("ClubCourse.findByCountry", ClubCourse.class);
            z.setParameter("id", countryID);
            List<ClubCourse> ccList = z.getResultList();

            for (ProvinceDTO p : dList) {
                p.setClubs(new ArrayList<ClubDTO>());
                for (Club club : clubList) {
                    if (club.getProvince().getProvinceID() == p.getProvinceID()) {
                        ClubDTO cdto = new ClubDTO(club);
                        cdto.setClubCourses(new ArrayList<ClubCourseDTO>());
                        for (ClubCourse cc : ccList) {
                            if (cc.getClub().getClubID() == club.getClubID()) {
                                cdto.getClubCourses().add(new ClubCourseDTO(cc));
                            }
                        }
                        p.getClubs().add(cdto);
                    }
                }
            }
            r.setProvinces(dList);
        } catch (Exception e) {
            throw new DataException(getErrorString(e));
        }
        return r;
    }

    public ResponseDTO getClubsByProvince(int provinceID) throws DataException {
        ResponseDTO r = new ResponseDTO();
        List<ClubDTO> cList = new ArrayList<>();
        try {
            Query q = em.createNamedQuery("Club.findByProvince", Club.class);
            q.setParameter("id", provinceID);
            List<Club> list = q.getResultList();
            Query z = em.createNamedQuery("ClubCourse.findByProvince", ClubCourse.class);
            z.setParameter("id", provinceID);
            List<ClubCourse> ccList = z.getResultList();
            r.setClubs(new ArrayList<ClubDTO>());
            for (Club club : list) {
                ClubDTO cdto = new ClubDTO(club);
                cdto.setClubCourses(new ArrayList<ClubCourseDTO>());
                for (ClubCourse cc : ccList) {
                    if (cc.getClub().getClubID() == club.getClubID()) {
                        cdto.getClubCourses().add(new ClubCourseDTO(cc));
                    }
                }
                r.getClubs().add(cdto);
            }

            r.setClubs(cList);
        } catch (Exception e) {
            throw new DataException(getErrorString(e));
        }
        return r;
    }

    public List<AgeGroupDTO> getAgeGroups(int golfGroupID)
            throws DataException {

        List<AgeGroupDTO> cList = new ArrayList<>();
        try {
            Query q = em.createNamedQuery("AgeGroup.findByGolfGroup",
                    Agegroup.class);
            q.setParameter("id", golfGroupID);
            List<Agegroup> list = q.getResultList();
            for (Agegroup g : list) {
                cList.add(new AgeGroupDTO(g));
            }
        } catch (Exception e) {
            throw new DataException(getErrorString(e));
        }
        return cList;
    }

    public List<AgeGroupDTO> getAgeGroupsBoys(int golfGroupID) throws DataException {

        List<AgeGroupDTO> cList = new ArrayList<>();
        try {
            Query q = em.createNamedQuery("AgeGroup.findByGender", Agegroup.class);
            q.setParameter("id", golfGroupID);
            q.setParameter("gender", 1);
            List<Agegroup> list = q.getResultList();

            for (Agegroup g : list) {
                cList.add(new AgeGroupDTO(g));
            }
        } catch (Exception e) {
            throw new DataException(getErrorString(e));
        }
        return cList;
    }

    public List<AgeGroupDTO> getAgeGroupsGirls(int golfGroupID) throws DataException {

        List<AgeGroupDTO> cList = new ArrayList<>();
        try {
            Query q = em.createNamedQuery("AgeGroup.findByGender", Agegroup.class);
            q.setParameter("id", golfGroupID);
            q.setParameter("gender", 2);

            List<Agegroup> list = q.getResultList();

            for (Agegroup g : list) {
                cList.add(new AgeGroupDTO(g));
            }
        } catch (Exception e) {
            throw new DataException(getErrorString(e));
        }
        return cList;
    }

    public List<CountryDTO> getCountries() throws DataException {

        List<CountryDTO> cList = new ArrayList<>();
        try {
            Query q = em.createNamedQuery("Country.findAll", Country.class);
            List<Country> list = q.getResultList();
            for (Country g : list) {
                cList.add(new CountryDTO(g));
            }
        } catch (Exception e) {
            throw new DataException(getErrorString(e));
        }
        logger.log(Level.OFF, "Country list found: {0}", cList.size());
        return cList;
    }

    public ResponseDTO signInAdministrator(String email,
            String pin) throws LoginException, DataException {
        ResponseDTO r = new ResponseDTO();
        Query q = em.createNamedQuery("Administrator.login", Administrator.class);
        q.setMaxResults(1);
        q.setParameter("email", email);
        q.setParameter("pin", pin);

        Administrator a = (Administrator) q.getSingleResult();
        if (a == null) {
            throw new LoginException();
        }
        r.setAdministrator(new AdministratorDTO(a));
        r.setGolfGroup(new GolfGroupDTO(a.getGolfGroup()));
        return r;

    }

    public ResponseDTO signInPlayer(String email,
            String pin) throws LoginException, DataException {
        ResponseDTO r = new ResponseDTO();
        Query q = em.createNamedQuery("Player.login", Administrator.class);
        q.setMaxResults(1);
        q.setParameter("email", email);
        q.setParameter("pin", pin);

        Administrator a = (Administrator) q.getSingleResult();
        if (a == null) {
            throw new LoginException();
        }
        r.setAdministrator(new AdministratorDTO(a));
        r.setGolfGroup(new GolfGroupDTO(a.getGolfGroup()));
        return r;

    }

    public List<TournamentDTO> getTournamentByGroup(int groupID) {

        List<TournamentDTO> list = new ArrayList<>();
        Query q = em.createNamedQuery("Tournament.findByGolfGroup", Tournament.class);
        q.setParameter("id", groupID);
        List<Tournament> tList = q.getResultList();
        GolfGroup t = em.find(GolfGroup.class, groupID);

        for (Tournament tournament : tList) {
            TournamentDTO tx = new TournamentDTO(tournament);
            list.add(tx);
        }

        Query qx = em.createNamedQuery("LeaderBoard.countByTourney");
        qx.setParameter("id", groupID);
        List<Object[]> results = qx.getResultList();
        for (TournamentDTO tn : list) {
            for (Object[] o : results) {
                int id = ((Number) o[0]).intValue();
                if (tn.getTournamentID() == id) {
                    tn.setNumberOfRegisteredPlayers(((Number) o[1]).intValue());
                }
            }
        }
        Collections.sort(list);
        return list;
    }

    public Tournament getTournamentByID(int tournamentID) {

        Tournament t = em.find(Tournament.class, tournamentID);
        return t;
    }

    public ClubCourse getClubCourseByID(int id) {
        ClubCourse cc = em.find(ClubCourse.class, id);
        return cc;
    }

    public LeaderBoard getLeaderBoardByID(int id) {
        LeaderBoard cc = em.find(LeaderBoard.class, id);
        return cc;
    }

    public ResponseDTO updateWinnerFlag(int leaderBoardID, int winnerFlag) throws DataException {
        ResponseDTO r = new ResponseDTO();
        logger.log(Level.OFF, "updating winner, leaderBoardID = {0}", leaderBoardID);
        try {
            LeaderBoard b = getLeaderBoardByID(leaderBoardID);
            Query q = em.createNamedQuery("LeaderBoard.findByWinnerFlag", LeaderBoard.class);
            q.setParameter("tID", b.getTournament().getTournamentID());
            List<LeaderBoard> list = q.getResultList();
            for (LeaderBoard lb : list) {
                lb.setWinnerFlag(0);
                em.merge(lb);
            }
            for (LeaderBoard lb : list) {
                if (lb.getLeaderBoardID() == leaderBoardID) {
                    lb.setWinnerFlag(winnerFlag);
                    em.merge(lb);
                    logger.log(Level.INFO, "Winner updated in database: {0} {1} - {2}",
                            new Object[]{lb.getPlayer().getFirstName(),
                                lb.getPlayer().getLastName(), lb.getTournament().getTourneyName()});
                }
            }

            b.setWinnerFlag(winnerFlag);
            em.merge(b);
            logger.log(Level.INFO, "Winner flag has been updated. Congratulations to {0} {1}",
                    new Object[]{b.getPlayer().getFirstName(), b.getPlayer().getLastName()});
        } catch (Exception e) {
            logger.log(Level.INFO, "Unable to add update winner flag", e);
            throw new DataException("Unable to add update winner flag\n" + getErrorString(e));

        }
        return r;
    }

    private List<TourneyScoreByRoundDTO> addTournamentScoreByRound(
            LeaderBoard tps, List<TourneyScoreByRoundDTO> scoreByRoundList)
            throws DataException {
        List<TourneyScoreByRoundDTO> dto = new ArrayList<>();
        try {
            for (TourneyScoreByRoundDTO tsbr : scoreByRoundList) {
                TourneyScoreByRound t = new TourneyScoreByRound();
                t.setGolfRound(tsbr.getGolfRound());
                t.setTournamentIDx(tsbr.getTournamentID());
                t.setClubCourse(getClubCourseByID(tsbr.getClubCourse().getClubCourseID()));
                t.setLeaderBoard(tps);

                if (tps.getTournament().getHolesPerRound() == 9) {
                    t.setPar(tps.getTournament().getPar());
                } else {
                    t.setPar(t.getClubCourse().getPar());
                }
                t.setHolesPerRound(tps.getTournament().getHolesPerRound());
                //

                em.persist(t);
            }
            //
            Query q = em.createNamedQuery("TourneyScoreByRound.getByTourneyPlayer",
                    TourneyScoreByRound.class);
            q.setParameter("pID", tps.getPlayer().getPlayerID());
            q.setParameter("tID", tps.getTournament().getTournamentID());
            List<TourneyScoreByRound> list = q.getResultList();
            for (TourneyScoreByRound ts : list) {
                dto.add(new TourneyScoreByRoundDTO(ts));
            }
            //logger.log(Level.OFF, "TourneyScoreByRound added, list: {0}", dto.size());
        } catch (Exception e) {
            logger.log(Level.INFO, "Unable to add scoreByRound record", e);
            throw new DataException("Unable to add scoreByRound records\n" + getErrorString(e));

        }
        return dto;
    }

    public ResponseDTO updateTeeTime(TourneyScoreByRoundDTO score) throws DataException {
        ResponseDTO r = new ResponseDTO();
        try {
            TourneyScoreByRound tsbr = getTourneyScoreByRoundByID(score.getTourneyScoreByRoundID());
            tsbr.setTee(score.getTee());
            tsbr.setTeeTime(new Date(score.getTeeTime()));
            em.merge(tsbr);

            r = getTournamentPlayers(score.getTournamentID());
            logger.log(Level.INFO, "Tee Time updated: {0} on tee: {1}",
                    new Object[]{new Date(score.getTeeTime()), score.getTee()});
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unable to update TeeTime", e);
            throw new DataException("Unable to update TeeTime\n" + getErrorString(e));

        }

        return r;
    }

    public ResponseDTO updateTournamentScoreByRound(LeaderBoardDTO leaderboard) throws DataException {
        ResponseDTO r = new ResponseDTO();
        LeaderBoard tps = getLeaderBoardByID(leaderboard.getLeaderBoardID());
        try {
            for (TourneyScoreByRoundDTO tsbr : leaderboard.getTourneyScoreByRoundList()) {
                TourneyScoreByRound t = getTourneyScoreByRoundByID(tsbr.getTourneyScoreByRoundID());
                t.setScoringComplete(tsbr.getScoringComplete());
                if (tsbr.getScore1() > 0) {
                    t.setScore1(tsbr.getScore1());
                }
                if (tsbr.getScore2() > 0) {
                    t.setScore2(tsbr.getScore2());
                }
                if (tsbr.getScore3() > 0) {
                    t.setScore3(tsbr.getScore3());
                }
                if (tsbr.getScore4() > 0) {
                    t.setScore4(tsbr.getScore4());
                }
                if (tsbr.getScore5() > 0) {
                    t.setScore5(tsbr.getScore5());
                }
                if (tsbr.getScore6() > 0) {
                    t.setScore6(tsbr.getScore6());
                }
                if (tsbr.getScore7() > 0) {
                    t.setScore7(tsbr.getScore7());
                }
                if (tsbr.getScore8() > 0) {
                    t.setScore8(tsbr.getScore8());
                }
                if (tsbr.getScore9() > 0) {
                    t.setScore9(tsbr.getScore9());
                }
                if (tsbr.getScore10() > 0) {
                    t.setScore10(tsbr.getScore10());
                }
                if (tsbr.getScore11() > 0) {
                    t.setScore11(tsbr.getScore11());
                }
                if (tsbr.getScore12() > 0) {
                    t.setScore12(tsbr.getScore12());
                }
                if (tsbr.getScore13() > 0) {
                    t.setScore13(tsbr.getScore13());
                }
                if (tsbr.getScore14() > 0) {
                    t.setScore14(tsbr.getScore14());
                }
                if (tsbr.getScore15() > 0) {
                    t.setScore15(tsbr.getScore15());
                }
                if (tsbr.getScore16() > 0) {
                    t.setScore16(tsbr.getScore16());
                }
                if (tsbr.getScore17() > 0) {
                    t.setScore17(tsbr.getScore17());
                }
                if (tsbr.getScore18() > 0) {
                    t.setScore18(tsbr.getScore18());
                }
                em.merge(t);

            }
            scoreTotals(tps);
            int cnt = 0;
            for (TourneyScoreByRoundDTO tsbr : leaderboard.getTourneyScoreByRoundList()) {
                if (tsbr.getScoringComplete() > 0) {
                    cnt++;
                }
            }
            if (cnt == leaderboard.getTourneyScoreByRoundList().size()) {
                leaderboard.setScoringComplete(true);
                tps.setScoringComplete(1);
                em.merge(tps);
            }
            r = getTournamentPlayers(leaderboard.getTournamentID());
            //check if everybody done, close the tourney scoring
            int incomplete = 0;
            for (LeaderBoardDTO lb : r.getLeaderBoardList()) {
                if (!lb.isScoringComplete()) {
                    incomplete++;
                }
            }
            if (incomplete == 0) {
                Tournament t = tps.getTournament();
                t.setClosedForScoringFlag(1);
                em.merge(t);
                logger.log(Level.INFO, "Tournament scoring closed: {0}", t.getTourneyName());
            }
            logger.log(Level.INFO, "Player scores by hole updated");
        } catch (Exception e) {
            logger.log(Level.INFO, "Unable to update score", e);
            throw new DataException("Unable to update score\n" + getErrorString(e));
        }
        return r;
    }

    public void scoreTotals(LeaderBoard tpScore) throws DataException {

        Query q = em.createNamedQuery("TourneyScoreByRound.getScoreTotals",
                TourneyScoreByRound.class);
        q.setParameter("t", tpScore);
        List<TourneyScoreByRound> xlist = q.getResultList();
        for (TourneyScoreByRound tsbr : xlist) {
            switch (tsbr.getGolfRound()) {
                case 1:
                    int tot1 = 0;
                    tot1 += tsbr.getScore1();
                    tot1 += tsbr.getScore2();
                    tot1 += tsbr.getScore3();
                    tot1 += tsbr.getScore4();
                    tot1 += tsbr.getScore5();
                    tot1 += tsbr.getScore6();
                    tot1 += tsbr.getScore7();
                    tot1 += tsbr.getScore8();
                    tot1 += tsbr.getScore9();
                    tot1 += tsbr.getScore10();
                    tot1 += tsbr.getScore11();
                    tot1 += tsbr.getScore12();
                    tot1 += tsbr.getScore13();
                    tot1 += tsbr.getScore14();
                    tot1 += tsbr.getScore15();
                    tot1 += tsbr.getScore16();
                    tot1 += tsbr.getScore17();
                    tot1 += tsbr.getScore18();
                    tpScore.setScoreRound1(tot1);
                    tsbr.setTotalScore(tot1);
                    em.merge(tsbr);
                    break;
                case 2:
                    int tot2 = 0;
                    tot2 += tsbr.getScore1();
                    tot2 += tsbr.getScore2();
                    tot2 += tsbr.getScore3();
                    tot2 += tsbr.getScore4();
                    tot2 += tsbr.getScore5();
                    tot2 += tsbr.getScore6();
                    tot2 += tsbr.getScore7();
                    tot2 += tsbr.getScore8();
                    tot2 += tsbr.getScore9();
                    tot2 += tsbr.getScore10();
                    tot2 += tsbr.getScore11();
                    tot2 += tsbr.getScore12();
                    tot2 += tsbr.getScore13();
                    tot2 += tsbr.getScore14();
                    tot2 += tsbr.getScore15();
                    tot2 += tsbr.getScore16();
                    tot2 += tsbr.getScore17();
                    tot2 += tsbr.getScore18();
                    tpScore.setScoreRound2(tot2);
                    tsbr.setTotalScore(tot2);
                    em.merge(tsbr);
                    break;
                case 3:
                    int tot3 = 0;
                    tot3 += tsbr.getScore1();
                    tot3 += tsbr.getScore2();
                    tot3 += tsbr.getScore3();
                    tot3 += tsbr.getScore4();
                    tot3 += tsbr.getScore5();
                    tot3 += tsbr.getScore6();
                    tot3 += tsbr.getScore7();
                    tot3 += tsbr.getScore8();
                    tot3 += tsbr.getScore9();
                    tot3 += tsbr.getScore10();
                    tot3 += tsbr.getScore11();
                    tot3 += tsbr.getScore12();
                    tot3 += tsbr.getScore13();
                    tot3 += tsbr.getScore14();
                    tot3 += tsbr.getScore15();
                    tot3 += tsbr.getScore16();
                    tot3 += tsbr.getScore17();
                    tot3 += tsbr.getScore18();
                    tpScore.setScoreRound3(tot3);
                    tsbr.setTotalScore(tot3);
                    em.merge(tsbr);
                    break;
                case 4:
                    int tot4 = 0;
                    tot4 += tsbr.getScore1();
                    tot4 += tsbr.getScore2();
                    tot4 += tsbr.getScore3();
                    tot4 += tsbr.getScore4();
                    tot4 += tsbr.getScore5();
                    tot4 += tsbr.getScore6();
                    tot4 += tsbr.getScore7();
                    tot4 += tsbr.getScore8();
                    tot4 += tsbr.getScore9();
                    tot4 += tsbr.getScore10();
                    tot4 += tsbr.getScore11();
                    tot4 += tsbr.getScore12();
                    tot4 += tsbr.getScore13();
                    tot4 += tsbr.getScore14();
                    tot4 += tsbr.getScore15();
                    tot4 += tsbr.getScore16();
                    tot4 += tsbr.getScore17();
                    tot4 += tsbr.getScore18();
                    tpScore.setScoreRound4(tot4);
                    tsbr.setTotalScore(tot4);
                    em.merge(tsbr);
                    break;
                case 5:
                    int tot5 = 0;
                    tot5 += tsbr.getScore1();
                    tot5 += tsbr.getScore2();
                    tot5 += tsbr.getScore3();
                    tot5 += tsbr.getScore4();
                    tot5 += tsbr.getScore5();
                    tot5 += tsbr.getScore6();
                    tot5 += tsbr.getScore7();
                    tot5 += tsbr.getScore8();
                    tot5 += tsbr.getScore9();
                    tot5 += tsbr.getScore10();
                    tot5 += tsbr.getScore11();
                    tot5 += tsbr.getScore12();
                    tot5 += tsbr.getScore13();
                    tot5 += tsbr.getScore14();
                    tot5 += tsbr.getScore15();
                    tot5 += tsbr.getScore16();
                    tot5 += tsbr.getScore17();
                    tot5 += tsbr.getScore18();
                    tpScore.setScoreRound5(tot5);
                    tsbr.setTotalScore(tot5);
                    em.merge(tsbr);
                    break;
                case 6:
                    int tot6 = 0;
                    tot6 += tsbr.getScore1();
                    tot6 += tsbr.getScore2();
                    tot6 += tsbr.getScore3();
                    tot6 += tsbr.getScore4();
                    tot6 += tsbr.getScore5();
                    tot6 += tsbr.getScore6();
                    tot6 += tsbr.getScore7();
                    tot6 += tsbr.getScore8();
                    tot6 += tsbr.getScore9();
                    tot6 += tsbr.getScore10();
                    tot6 += tsbr.getScore11();
                    tot6 += tsbr.getScore12();
                    tot6 += tsbr.getScore13();
                    tot6 += tsbr.getScore14();
                    tot6 += tsbr.getScore15();
                    tot6 += tsbr.getScore16();
                    tot6 += tsbr.getScore17();
                    tot6 += tsbr.getScore18();
                    tpScore.setScoreRound6(tot6);
                    tsbr.setTotalScore(tot6);
                    em.merge(tsbr);
                    break;
            }
        }
        tpScore.setTotalScore(tpScore.getScoreRound1()
                + tpScore.getScoreRound2() + tpScore.getScoreRound3()
                + tpScore.getScoreRound4() + tpScore.getScoreRound5() + tpScore.getScoreRound6());
        try {
            em.merge(tpScore);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unable to total scores", e);
            throw new DataException("Unable to total & update scores\n" + getErrorString(e));
        }
    }

    public TourneyScoreByRound getTourneyScoreByRoundByID(int id) {
        TourneyScoreByRound tsbr = em.find(TourneyScoreByRound.class, id);
        return tsbr;
    }

    public ResponseDTO updateTournamentScore(LeaderBoardDTO d) throws DataException {
        ResponseDTO r = new ResponseDTO();
        LeaderBoard s = em.find(LeaderBoard.class, d.getLeaderBoardID());

        s.setScoreRound1(d.getScoreRound1());
        s.setScoreRound2(d.getScoreRound2());
        s.setScoreRound3(d.getScoreRound3());
        s.setScoreRound4(d.getScoreRound4());
        s.setScoreRound5(d.getScoreRound5());
        s.setScoreRound6(d.getScoreRound6());
        s.setTotalScore(d.getScoreRound1() + d.getScoreRound2()
                + d.getScoreRound3() + d.getScoreRound4()
                + d.getScoreRound5() + d.getScoreRound6());
        s.setPosition(d.getPosition());
        if (d.isTied()) {
            s.setTied(1);
        } else {
            s.setTied(0);
        }
        try {
            em.merge(s);
            r = getTournamentPlayers(d.getTournamentID());
            logger.log(Level.INFO, "\n### Updated Tournament player score: {0} {1} to {2}",
                    new Object[]{s.getPlayer().getFirstName(), s.getPlayer().getLastName(), s.getTournament().getTourneyName()});
        } catch (Exception e) {
            logger.log(Level.INFO, "Unable to update score", e);
            throw new DataException("Unable to update score\n" + getErrorString(e));
        }
        return r;
    }

    public void updateGolfGroup(GolfGroupDTO group) throws DataException {

        GolfGroup g = getGroupByID(group.getGolfGroupID());
        g.setGolfGroupName(group.getGolfGroupName());
        g.setEmail(group.getEmail());
        g.setCellphone(group.getCellphone());

        try {
            em.merge(g);

            logger.log(Level.INFO, "\n### Updated golf group: {0} ",
                    new Object[]{g.getGolfGroupName()});
        } catch (Exception e) {
            logger.log(Level.INFO, "Unable to update score", e);
            throw new DataException(getErrorString(e));
        }
    }

    public void removeParentInPlayer(PlayerDTO p) throws DataException {
        Player g = getPlayerByID(p.getPlayerID());
        g.setParent(null);
        try {
            em.merge(g);

            logger.log(Level.INFO, "\n### Updated player: '{'0'}' {0} {1}", new Object[]{p.getFirstName(), p.getLastName()});
        } catch (Exception e) {
            logger.log(Level.INFO, "Unable to update player", e);
            throw new DataException(getErrorString(e));
        }
    }

    public void updatePlayer(PlayerDTO player) throws DataException {

        Player g = getPlayerByID(player.getPlayerID());
        g.setCellphone(player.getCellphone());
        g.setEmail(player.getEmail());
        if (player.getDateOfBirth() > 0) {
            g.setDateOfBirth(new Date(player.getDateOfBirth()));
        }
        g.setFirstName(player.getFirstName());
        g.setLastName(player.getLastName());
        g.setGender(player.getGender());
        g.setMiddleName(player.getMiddleName());
        g.setYearJoined(player.getYearJoined());
        if (player.getParentID() > 0) {
            removeParentInPlayer(player);
            Parent parent = getParentByID(player.getParentID());
            g.setParent(parent);
        }

        try {
            em.merge(g);

            logger.log(Level.INFO, "\n### Updated player: '{'0'}' {0} {1}", new Object[]{player.getFirstName(), player.getLastName()});
        } catch (Exception e) {
            logger.log(Level.INFO, "Unable to update player", e);
            throw new DataException(getErrorString(e));
        }
    }

    public void updateParent(ParentDTO dto) throws DataException {
        Parent g = getParentByID(dto.getParentID());
        g.setCellphone(dto.getCellphone());
        g.setEmail(dto.getEmail());
        g.setFirstName(dto.getFirstName());
        g.setLastName(dto.getLastName());
        g.setMiddleName(dto.getMiddleName());
        try {
            em.merge(g);

            logger.log(Level.INFO, "\n### Updated parent: '{'0'}' {0} {1}", new Object[]{dto.getFirstName(), dto.getLastName()});
        } catch (Exception e) {
            logger.log(Level.INFO, "Unable to update parent", e);
            throw new DataException(getErrorString(e));
        }
    }

    public Scorer getScorerByID(int id) {
        Scorer s = em.find(Scorer.class, id);
        return s;
    }

    public void updateScorer(ScorerDTO dto) throws DataException {
        Scorer g = getScorerByID(dto.getScorerID());
        g.setCellphone(dto.getCellphone());
        g.setEmail(dto.getEmail());
        g.setFirstName(dto.getFirstName());
        g.setLastName(dto.getLastName());
        try {
            em.merge(g);

            logger.log(Level.INFO, "\n### Updated scorer: '{'0'}' {0} {1}", new Object[]{dto.getFirstName(), dto.getLastName()});
        } catch (Exception e) {
            logger.log(Level.INFO, "Unable to update scorer", e);
            throw new DataException(getErrorString(e));
        }
    }

    public void updateClub(ClubDTO dto) throws DataException {

        Club g = getClubByID(dto.getClubID());
        g.setTelephone(dto.getTelephone());
        g.setEmail(dto.getEmail());
        g.setClubName(dto.getClubName());
        g.setAddress(dto.getAddress());
        g.setLatitude(dto.getLatitude());
        g.setLongitude(dto.getLongitude());

        if (dto.getProvinceID() > 0) {
            Province prov = getProvinceByID(dto.getProvinceID());
            g.setProvince(prov);
        }

        try {
            em.merge(g);

            logger.log(Level.INFO, "\n### Updated club: {0}", new Object[]{dto.getClubName()});
        } catch (Exception e) {
            logger.log(Level.INFO, "Unable to update club", e);
            throw new DataException(getErrorString(e));
        }
    }

    public void updateAdmin(AdministratorDTO d) throws DataException {

        Administrator g = em.find(Administrator.class, d.getAdministratorID());
        g.setCellphone(d.getCellphone());
        g.setEmail(d.getEmail());
        g.setFirstName(d.getFirstName());
        g.setLastName(d.getLastName());
        try {
            em.merge(g);

            logger.log(Level.INFO, "\n### Updated admin: {0}", new Object[]{d.getFirstName() + " " + d.getLastName()});
        } catch (Exception e) {
            logger.log(Level.INFO, "Unable to update admin", e);
            throw new DataException(getErrorString(e));
        }
    }

    public Agegroup getAgeGroupByID(int id) {
        Agegroup g = em.find(Agegroup.class, id);
        return g;
    }

    public Administrator getAdministratorByID(int id) {
        Administrator g = em.find(Administrator.class, id);
        return g;
    }

    public Club getClubByID(int id) {
        Club g = em.find(Club.class, id);
        return g;
    }

    public Country getCountryByID(int id) {
        Country g = em.find(Country.class, id);
        return g;
    }

    public Province getProvinceByID(int id) {
        Province g = em.find(Province.class, id);
        return g;
    }

    public GolfGroup getGroupByID(int id) {
        GolfGroup g = em.find(GolfGroup.class, id);
        return g;
    }

    public Player getPlayerByID(int id) {
        Player g = em.find(Player.class, id);
        return g;
    }

    public Parent getParentByID(int id) {

        Parent g = em.find(Parent.class, id);
        return g;
    }

    public ResponseDTO removeTournamentPlayer(int tournamentID, int playerID) throws DataException {

        ResponseDTO r = new ResponseDTO();
        try {
            Query q = em.createNamedQuery("TourneyScoreByRound.removeTourneyPlayer", TourneyScoreByRound.class);
            q.setParameter("tID", tournamentID);
            q.setParameter("pID", playerID);
            int deleted = q.executeUpdate();
            logger.log(Level.OFF, "Removed TourneyScoreByRounds: {0}", deleted);
            Query qx = em.createNamedQuery("LeaderBoard.removePlayer");
            qx.setParameter("tID", tournamentID);
            qx.setParameter("pID", playerID);
            deleted = qx.executeUpdate();
            logger.log(Level.OFF, "Removed LeaderBoard: {0}", deleted);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to remove Tournament player", e);
            throw new DataException("Failed to remove Tournament player\n" + getErrorString(e));
        }

        return r;
    }

    public ResponseDTO addTournamentPlayer(LeaderBoardDTO leaderBoardDTO) throws DataException {

        ResponseDTO r = new ResponseDTO();
        LeaderBoard s = new LeaderBoard();
        s.setDateRegistered(new Date());
        s.setPlayer(getPlayerByID(leaderBoardDTO.getPlayer().getPlayerID()));
        s.setTournament(getTournamentByID(leaderBoardDTO.getTournamentID()));
        try {
            em.persist(s);
            Query q = em.createNamedQuery("LeaderBoard.findByPlayerTourney", LeaderBoard.class);
            q.setParameter("pID", leaderBoardDTO.getPlayer().getPlayerID());
            q.setParameter("tID", leaderBoardDTO.getTournamentID());
            q.setMaxResults(1);
            LeaderBoard leaderBoard = (LeaderBoard) q.getSingleResult();
            List<TourneyScoreByRoundDTO> list = new ArrayList<>();
            //
            Query z = em.createNamedQuery("TournamentCourse.findByTourney", TournamentCourse.class);
            z.setParameter("id", leaderBoardDTO.getTournamentID());
            List<TournamentCourse> tcList = z.getResultList();

            for (TournamentCourse tc : tcList) {
                TourneyScoreByRoundDTO dto = new TourneyScoreByRoundDTO();
                dto.setGolfRound(tc.getRound());
                dto.setClubCourse(new ClubCourseDTO(tc.getClubCourse()));
                dto.setHolesPerRound(s.getTournament().getHolesPerRound());
                dto.setPar(tc.getClubCourse().getPar());
                dto.setTournamentID(leaderBoard.getTournament().getTournamentID());
                dto.setLeaderBoardID(leaderBoard.getLeaderBoardID());
                list.add(dto);
            }
            //
            if (s.getTournament().getUseAgeGroups() == 1) {
                setAgeGroup(leaderBoard, s.getTournament().getStartDate().getTime());
            }
            r.setLeaderBoard(new LeaderBoardDTO(leaderBoard));
            List<TourneyScoreByRoundDTO> tsbrList = addTournamentScoreByRound(leaderBoard, list);
            r.getLeaderBoard().setTourneyScoreByRoundList(tsbrList);

            
        } catch (PersistenceException e) {
            r.setStatusCode(ResponseDTO.DUPLICATE_EXCEPTION);
            r.setMessage("Duplicate detected. Record already exists");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to add Tournament player", e);
            throw new DataException("Failed to add Tournament player\n" + getErrorString(e));
        } finally {
        }
        return r;
    }

    public ResponseDTO addTournament(TournamentDTO dto) throws DataException {

        ResponseDTO r = new ResponseDTO();
        Tournament t = new Tournament();
        if (dto.getClosingDate() > 0) {
            t.setClosingDate(new Date(dto.getClosingDate()));
        }
        if (dto.getStartDate() > 0) {
            t.setStartDate(new Date(dto.getStartDate()));
        }
        if (dto.getEndDate() > 0) {
            t.setEndDate(new Date(dto.getEndDate()));
        }
        t.setClub(getClubByID(dto.getClubID()));
        t.setGolfRounds(dto.getGolfRounds());
        t.setGolfGroup(getGroupByID(dto.getGolfGroupID()));
        t.setTourneyName(dto.getTourneyName());
        t.setHolesPerRound(dto.getHolesPerRound());
        t.setPar(dto.getPar());
        t.setExampleFlag(dto.getExampleFlag());
        t.setUseAgeGroups(dto.getUseAgeGroups());

        try {
            em.persist(t);
            //add tournamentCourses - if only one course but > 1 round
            Query w = em.createNamedQuery("ClubCourse.findByClub", ClubCourse.class);
            w.setParameter("id", dto.getClubID());
            List<ClubCourse> cclist = w.getResultList();
            if (cclist.isEmpty()) {
                r.setStatusCode(ResponseDTO.DATA_EXCEPTION);
                r.setMessage("The club for the tournament has no course defined");
                return r;
            }
            if (cclist.size() == 1) {
                ClubCourse cc = cclist.get(0);
                for (int i = 0; i < dto.getGolfRounds(); i++) {
                    TournamentCourse x = new TournamentCourse();
                    x.setTournament(t);
                    x.setRound(i + 1);
                    x.setClubCourse(cc);
                    em.persist(x);
                }

            } else {
                //get club courses selected by user --- 
                for (TournamentCourseDTO tc : dto.getTournamentCourses()) {
                    TournamentCourse x = new TournamentCourse();
                    x.setTournament(t);
                    x.setRound(tc.getRound());
                    x.setClubCourse(getClubCourseByID(tc.getClubCourse().getClubCourseID()));
                    em.persist(x);
                }
            }
            Query q = em.createNamedQuery("Tournament.findByGolfGroup", Tournament.class);
            q.setParameter("id", dto.getGolfGroupID());
            List<TournamentDTO> dtoList = new ArrayList<>();
            List<Tournament> list = q.getResultList();
            for (Tournament tx : list) {
                dtoList.add(new TournamentDTO(tx));
            }
            r.setTournaments(dtoList);

            logger.log(Level.INFO, "\n### Added Tournament {0} group: {1}",
                    new Object[]{t.getTourneyName(), t.getGolfGroup().getGolfGroupName()});
        } catch (PersistenceException e) {
            r.setStatusCode(ResponseDTO.DUPLICATE_EXCEPTION);
            r.setMessage("Duplicate detected. Record already exists");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to add Tournament", e);
            throw new DataException("Failed to add Tournament\n" + getErrorString(e));
        } finally {
        }
        return r;
    }

    public ResponseDTO addParent(ParentDTO dto, int golfGroupID) throws DataException {

        ResponseDTO r = new ResponseDTO();
        Parent p = new Parent();
        p.setCellphone(dto.getCellphone());
        p.setEmail(dto.getEmail());
        p.setFirstName(dto.getFirstName());
        p.setLastName(dto.getLastName());
        p.setMiddleName(dto.getMiddleName());
        p.setParentType(dto.getParentType());
        p.setPin(dto.getPin());

        try {
            em.persist(p);
            Query q = em.createNamedQuery("Parent.findByEmail", Parent.class);
            q.setParameter("email", p.getEmail());
            p = (Parent) q.getSingleResult();
            addGolfGroupParent(p.getParentID(), golfGroupID);
            List<ParentDTO> dtoList = new ArrayList<>();
            dtoList.add(new ParentDTO(p));
            r.setParents(dtoList);
            logger.log(Level.INFO, "\n### Added Parent {0}  {1}", new Object[]{p.getFirstName(), p.getLastName()});

        } catch (PersistenceException e) {
            logger.log(Level.WARNING, " Parent GolfGroup", e);
            r.setStatusCode(ResponseDTO.DUPLICATE_EXCEPTION);
            r.setMessage("This parent already has an account");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to add Parent", e);
            throw new DataException("Failed to add Parent\n" + getErrorString(e));
        } finally {
        }
        return r;
    }

    public ResponseDTO addGolfGroupParent(int parentID, int golfGroupID)
            throws DuplicateException, DataException {

        ResponseDTO r = new ResponseDTO();
        GolfGroup group = getGroupByID(golfGroupID);
        GolfGroupParent gg = new GolfGroupParent();
        gg.setDateRegistered(new Date());
        gg.setGolfGroup(group);
        gg.setParent(getParentByID(parentID));
        try {
            em.persist(gg);
            logger.log(Level.INFO, "\n### Added Parent to GolfGroup");
        } catch (PersistenceException e) {
            logger.log(Level.SEVERE, "***ERROR*** Duplicate GolfGroup player", e);
            r.setStatusCode(ResponseDTO.DUPLICATE_EXCEPTION);
            r.setMessage("This parent already belongs to the Group");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to add GolfGroupParent", e);
            throw new DataException("Failed to add GolfGroupParent\n" + getErrorString(e));
        } finally {
        }
        return r;
    }

    public ResponseDTO addGolfGroupPlayer(int playerID, int golfGroupID) throws DuplicateException, DataException {
        ResponseDTO r = new ResponseDTO();
        GolfGroup group = getGroupByID(golfGroupID);

        GolfGroupPlayer gg = new GolfGroupPlayer();
        gg.setDateRegistered(new Date());
        gg.setGolfGroup(group);
        gg.setPlayer(getPlayerByID(playerID));

        try {
            em.persist(gg);
            Query q = em.createNamedQuery("GolfGroupPlayer.findByGolfGroup", GolfGroupPlayer.class);
            q.setParameter("id", golfGroupID);
            List<GolfGroupPlayer> gList = q.getResultList();
            List<GolfGroupPlayerDTO> dList = new ArrayList<>();
            for (GolfGroupPlayer ggp : gList) {
                dList.add(new GolfGroupPlayerDTO(ggp));
            }
            r.setGolfGroupPlayers(dList);
            logger.log(Level.INFO, "\n### Added Player to GolfGroup");
        } catch (PersistenceException e) {
            logger.log(Level.SEVERE, "***ERROR*** Duplicate GolfGroup player", e);
            r.setStatusCode(ResponseDTO.DUPLICATE_EXCEPTION);
            r.setMessage("This player already belongs to the Group");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to add Golf Group Player", e);
            throw new DataException("Failed to add Golf Group Player\n" + getErrorString(e));
        } finally {
        }
        return r;
    }

    public ResponseDTO addClub(ClubDTO d) throws DataException {
        ResponseDTO r = new ResponseDTO();
        Club club = new Club();
        
        club.setAddress(d.getAddress());
        club.setClubName(d.getClubName());
        club.setEmail(d.getEmail());
        club.setTelephone(d.getTelephone());
        club.setLatitude(d.getLatitude());
        club.setLongitude(d.getLongitude());
        if (d.getProvinceID() > 0) {
            club.setProvince(getProvinceByID(d.getProvinceID()));
        }

        try {
            em.persist(club);
            Query q = em.createNamedQuery("Club.findByProvince", Club.class);
            q.setParameter("id", d.getProvinceID());
            List<Club> list = q.getResultList();
            List<ClubDTO> dList = new ArrayList<>();
            for (Club c : list) {
                dList.add(new ClubDTO(c));
            }
            r.setClubs(dList);
            logger.log(Level.INFO, "\n### Added Club {0}", club.getClubName() + " - " + club.getProvince().getProvinceName());
        } catch (PersistenceException e) {
            logger.log(Level.SEVERE, "***ERROR*** Duplicate Club", e);
            r.setStatusCode(ResponseDTO.DUPLICATE_EXCEPTION);
            r.setMessage("This club already exists");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to add Club", e);
            throw new DataException("Failed to add Club\n" + getErrorString(e));
        } finally {
        }
        return r;

    }

    private String getRandomPin() {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random(System.currentTimeMillis());
        int x = rand.nextInt(9);
        if (x == 0) {
            x = 3;
        }
        sb.append(x);
        sb.append(rand.nextInt(9));
        sb.append(rand.nextInt(9));
        sb.append(rand.nextInt(9));
        sb.append(rand.nextInt(9));
        sb.append(rand.nextInt(9));
        return sb.toString();
    }

    public ResponseDTO addPlayer(PlayerDTO d, int golfGroupID) throws DataException {
        ResponseDTO r = new ResponseDTO();
        Player p = new Player();
        p.setCellphone(d.getCellphone());
        if (d.getDateOfBirth() > 0) {
            p.setDateOfBirth(new Date(d.getDateOfBirth()));
        }
        p.setDateRegistered(new Date());
        p.setEmail(d.getEmail());
        p.setFirstName(d.getFirstName());
        p.setLastName(d.getLastName());
        p.setMiddleName(d.getMiddleName());

        p.setGender(d.getGender());
        p.setPin(getRandomPin());
        p.setYearJoined(d.getYearJoined());
        if (d.getParentID() > 0) {
            Parent parent = getParentByID(d.getParentID());
            p.setParent(parent);
        }
        try {
            em.persist(p);
            Query q = em.createNamedQuery("Player.findByEmail", Player.class);
            q.setParameter("email", p.getEmail());
            q.setMaxResults(1);
            Player player = (Player) q.getSingleResult();
            r = addGolfGroupPlayer(player.getPlayerID(), golfGroupID);
            r.setPlayers(new ArrayList<PlayerDTO>());
            r.getPlayers().add(new PlayerDTO(p));
            logger.log(Level.INFO, "\n### Added Player {0} {1}", new Object[]{p.getFirstName(), p.getLastName()});
        } catch (PersistenceException e) {
            logger.log(Level.SEVERE, "Duplicate Player", e);
            r.setStatusCode(ResponseDTO.DUPLICATE_EXCEPTION);
            r.setMessage("This player email already has an account");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to add Player", e);
            throw new DataException("Failed to add Player\n" + getErrorString(e));
        } finally {
        }
        return r;
    }

    public ResponseDTO addScorer(ScorerDTO d, int golfGroupID) throws DataException {
        ResponseDTO r = new ResponseDTO();
        Scorer p = new Scorer();
        p.setCellphone(d.getCellphone());
        p.setDateRegistered(new Date());
        p.setEmail(d.getEmail());
        p.setFirstName(d.getFirstName());
        p.setLastName(d.getLastName());
        p.setGolfGroup(getGroupByID(golfGroupID));
        p.setPin(getRandomPin());
        try {
            em.persist(p);
            Query q = em.createNamedQuery("Scorer.findByEmail", Scorer.class);
            q.setParameter("email", p.getEmail());
            q.setMaxResults(1);
            Scorer s = (Scorer) q.getSingleResult();
            r.setScorers(new ArrayList<ScorerDTO>());
            r.getScorers().add(new ScorerDTO(s));
            logger.log(Level.INFO, "\n### Added Scorer {0} {1}", new Object[]{p.getFirstName(), p.getLastName()});
        } catch (PersistenceException e) {
            logger.log(Level.SEVERE, "***ERROR*** Duplicate Scorer", e);
            r.setStatusCode(ResponseDTO.DUPLICATE_EXCEPTION);
            r.setMessage("This Scorer email already has an account");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to add Scorer", e);
            throw new DataException("Failed to add Scorer\n" + getErrorString(e));
        } finally {
        }
        return r;
    }

    public ResponseDTO addGolfGroupAdmin(AdministratorDTO d)
            throws DataException {
        ResponseDTO r = new ResponseDTO();
        Administrator a = new Administrator();
        a.setCellphone(d.getCellphone());
        a.setEmail(d.getEmail());
        a.setFirstName(d.getFirstName());
        a.setLastName(d.getLastName());
        a.setPin(d.getPin());
        a.setSuperUserFlag(d.getSuperUserFlag());
        if (d.getGolfGroupID() > 0) {
            GolfGroup gg = getGroupByID(d.getGolfGroupID());
            a.setGolfGroup(gg);
        }

        try {
            em.persist(a);
            Query q = em.createNamedQuery("Administrator.findByEmail", Administrator.class);
            q.setMaxResults(1);
            q.setParameter("email", d.getEmail());
            Administrator gg = (Administrator) q.getSingleResult();
            r.setAdministrator(new AdministratorDTO(gg));
            logger.log(Level.INFO, "\n### Added Administrator {0} {1} ",
                    new Object[]{d.getFirstName(), d.getLastName()});
        } catch (PersistenceException e) {
            logger.log(Level.WARNING, "***ERROR*** Duplicate GolfGroup admin", e);
            r.setStatusCode(ResponseDTO.DUPLICATE_EXCEPTION);
            r.setMessage("This golf group or administrator email already has an account");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to add Admin", e);
            throw new DataException("Failed to add Admin\n" + getErrorString(e));

        } finally {
        }
        return r;
    }

    public ResponseDTO addGolfGroup(GolfGroupDTO d, AdministratorDTO admin, PlatformUtil platformUtil)
            throws DataException {
        ResponseDTO r = new ResponseDTO();
        GolfGroup g = new GolfGroup();
        g.setCellphone(d.getCellphone());
        g.setDateRegistered(new Date());
        g.setEmail(d.getEmail());
        g.setGolfGroupName(d.getGolfGroupName());
        g.setCountry(getCountryByID(d.getCountryID()));

        try {
            em.persist(g);
            Query q = em.createNamedQuery("GolfGroup.findByEmail", GolfGroup.class);
            q.setMaxResults(1);
            q.setParameter("email", d.getEmail());
            GolfGroup gg = (GolfGroup) q.getSingleResult();
            if (gg == null) {
                throw new DataException("Failed to add or get golfgroup after insert");
            }
            r.setGolfGroup(new GolfGroupDTO(gg));
            admin.setSuperUserFlag(1);
            admin.setGolfGroupID(r.getGolfGroup().getGolfGroupID());

            ResponseDTO r2 = addGolfGroupAdmin(admin);
            r.setAdministrator(r2.getAdministrator());
            addInitialOrderOfMerit(gg);
            addInitialAgeGroups(gg);
            NewGolfGroupUtil.generate(gg, this, platformUtil);
            logger.log(Level.INFO, "\n### Added GolfGroup {0}", g.getGolfGroupName());

        } catch (PersistenceException e) {
            r.setStatusCode(ResponseDTO.DUPLICATE_EXCEPTION);
            r.setMessage("This golf group or administrator email already has an account");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to add GolfGroup", e);
            throw new DataException("Failed to add GolfGroup\n" + getErrorString(e));
        }
        return r;
    }

    private void addInitialOrderOfMerit(GolfGroup gg) throws DataException {
        try {
            OrderOfMeritPoint p = new OrderOfMeritPoint();
            p.setWin(100);           
            p.setTiedFirst(80);
            p.setRunnerUp(70);
            p.setTop3(60);
            p.setTop5(40);
            p.setTop10(30);
            p.setTop20(15);
            p.setTop30(7);
            p.setTop40(5);
            p.setTop50(2);
            p.setTop100(1);
            p.setGolfGroup(gg);
            em.persist(p);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to add GolfGroup", e);
            throw new DataException("Failed to add initial orderOfMerit\n" + getErrorString(e));
        }
    }

    private void addInitialAgeGroups(GolfGroup gg) throws DataException {
        try {
            Agegroup a1 = new Agegroup();
            a1.setGroupName("Boys 5 - 6");
            a1.setAgeFrom(5);
            a1.setAgeTo(6);
            a1.setGender(1);
            a1.setGolfGroup(gg);
            em.persist(a1);
            Agegroup a2 = new Agegroup();
            a2.setGroupName("Boys 7 - 8");
            a2.setAgeFrom(7);
            a2.setAgeTo(8);
            a2.setGender(1);
            a2.setGolfGroup(gg);
            em.persist(a2);
            Agegroup a3 = new Agegroup();
            a3.setGroupName("Boys 9 - 10");
            a3.setAgeFrom(9);
            a3.setAgeTo(10);
            a3.setGender(1);
            a3.setGolfGroup(gg);
            em.persist(a3);
            Agegroup a4 = new Agegroup();
            a4.setGroupName("Boys 11 - 12");
            a4.setAgeFrom(11);
            a4.setAgeTo(12);
            a4.setGender(1);
            a4.setGolfGroup(gg);
            em.persist(a4);
            Agegroup a5 = new Agegroup();
            a5.setGroupName("Boys 13 - 14");
            a5.setAgeFrom(13);
            a5.setAgeTo(14);
            a5.setGender(1);
            a5.setGolfGroup(gg);
            em.persist(a5);
            Agegroup a6 = new Agegroup();
            a6.setGroupName("Boys 15 - 16");
            a6.setAgeFrom(15);
            a6.setAgeTo(16);
            a6.setGender(1);
            a6.setGolfGroup(gg);
            em.persist(a6);
            Agegroup a7 = new Agegroup();
            a7.setGroupName("Boys 17 - 18");
            a7.setAgeFrom(17);
            a7.setAgeTo(18);
            a7.setGender(1);
            a7.setGolfGroup(gg);
            em.persist(a7);
            //GIRLS
            Agegroup af1 = new Agegroup();
            af1.setGroupName("Girls 5 - 7");
            af1.setAgeFrom(5);
            af1.setAgeTo(7);
            af1.setGender(2);
            af1.setGolfGroup(gg);
            em.persist(af1);
            
            Agegroup af2 = new Agegroup();
            af2.setGroupName("Girls 8 - 10");
            af2.setAgeFrom(8);
            af2.setAgeTo(10);
            af2.setGender(2);
            af2.setGolfGroup(gg);          
            em.persist(af2);
            
            Agegroup af3 = new Agegroup();
            af3.setGroupName("Girls 11 - 13");
            af3.setAgeFrom(11);
            af3.setAgeTo(13);
            af3.setGender(2);
            af3.setGolfGroup(gg);           
            em.persist(af3);
            
            Agegroup af4 = new Agegroup();
            af4.setGroupName("Girls 14 - 16");
            af4.setAgeFrom(14);
            af4.setAgeTo(16);
            af4.setGender(2);
            af4.setGolfGroup(gg);
            em.persist(af4);
            
            Agegroup af5 = new Agegroup();
            af5.setGroupName("Girls 17 - 18");
            af5.setAgeFrom(17);
            af5.setAgeTo(18);
            af5.setGender(2);
            af5.setGolfGroup(gg);
            em.persist(af5);
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to add GolfGroup Age Groups", e);
            throw new DataException("Failed to add initial ageGroups\n" + getErrorString(e));
        }
    }

    private void setAgeGroup(LeaderBoard lb, long tournamentStartDate) throws DataException {

        LocalDateTime birthday = new LocalDateTime(lb.getPlayer().getDateOfBirth());
        LocalDateTime start = new LocalDateTime(tournamentStartDate);

        Years years = Years.yearsBetween(birthday, start);
        int age = years.getYears();

        //
        int id = lb.getTournament().getGolfGroup().getGolfGroupID();
        try {
            if (lb.getPlayer().getGender() == 1) {
                Query q = em.createNamedQuery("AgeGroup.findByAgeBoys", Agegroup.class);
                q.setParameter("age", age);
                q.setParameter("gID", id);
                Agegroup ageGroupBoys = (Agegroup) q.getSingleResult();
                lb.setAgeGroup(ageGroupBoys);
                lb.setAge(age);
            }
            if (lb.getPlayer().getGender() == 2) {
                Query q = em.createNamedQuery("AgeGroup.findByAgeGirls", Agegroup.class);
                q.setParameter("age", age);
                q.setParameter("gID", id);
                Agegroup ageGroupGirls = (Agegroup) q.getSingleResult();
                lb.setAgeGroup(ageGroupGirls);
                lb.setAge(age);
            }
            em.merge(lb);

        } catch (NoResultException e) {
            logger.log(Level.WARNING, "AgeGroup not found, ignoring update");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to get ageGroup", e);
            throw new DataException("Failed to get ageGroup\n" + getErrorString(e));
        }
    }
    private final Logger logger = Logger.getLogger("DataUtil");
}
