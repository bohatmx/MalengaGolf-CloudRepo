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
import com.boha.golfkids.data.Parent;
import com.boha.golfkids.data.Player;
import com.boha.golfkids.data.Province;
import com.boha.golfkids.data.TeeTime;
import com.boha.golfkids.data.Tournament;
import com.boha.golfkids.data.TourneyPlayerScore;
import com.boha.golfkids.data.TourneyScoreByRound;
import com.boha.golfkids.dto.AdministratorDTO;
import com.boha.golfkids.dto.AgeGroupDTO;
import com.boha.golfkids.dto.ClubCourseDTO;
import com.boha.golfkids.dto.ClubDTO;
import com.boha.golfkids.dto.CountryDTO;
import com.boha.golfkids.dto.GolfGroupDTO;
import com.boha.golfkids.dto.GolfGroupPlayerDTO;
import com.boha.golfkids.dto.ParentDTO;
import com.boha.golfkids.dto.PlayerDTO;
import com.boha.golfkids.dto.ResponseDTO;
import com.boha.golfkids.dto.TeeTimeDTO;
import com.boha.golfkids.dto.TournamentDTO;
import com.boha.golfkids.dto.TourneyPlayerScoreDTO;
import com.boha.golfkids.dto.TourneyScoreByRoundDTO;
import java.util.ArrayList;
import java.util.Collections;
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
import javax.validation.ConstraintViolationException;

/**
 *
 * @author Aubrey Malabie
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DataUtil {

    @PersistenceContext
    EntityManager em;
    
    public  String getErrorString(Exception e) {
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

    public List<ClubDTO> getClubsByCountry(int countryID) throws DataException {

        List<ClubDTO> cList = new ArrayList<>();
        try {
            Query q = em.createNamedQuery("Club.findByCountry", Club.class);
            q.setParameter("id", countryID);
            List<Club> list = q.getResultList();
            for (Club club : list) {
                cList.add(new ClubDTO(club));
            }
        } catch (Exception e) {
            throw new DataException(getErrorString(e));
        }
        return cList;
    }

    public List<ClubDTO> getClubsByProvince(int provinceID) throws DataException {

        List<ClubDTO> cList = new ArrayList<>();
        try {
            Query q = em.createNamedQuery("Club.findByProvince", Club.class);
            q.setParameter("id", provinceID);
            List<Club> list = q.getResultList();
            for (Club club : list) {
                cList.add(new ClubDTO(club));
            }
        } catch (Exception e) {
            throw new DataException(getErrorString(e));
        }
        return cList;
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

    public List<TeeTimeDTO> getTeeTimes(int tournamentID) throws DataException {

        List<TeeTimeDTO> cList = new ArrayList<>();
        try {
            Query q = em.createNamedQuery("TeeTime.findByTournament", TeeTime.class);
            q.setParameter("id", tournamentID);
            List<TeeTime> list = q.getResultList();
            for (TeeTime g : list) {
                cList.add(new TeeTimeDTO(g));
            }
        } catch (Exception e) {
            throw new DataException(getErrorString(e));
        }
        logger.log(Level.OFF, "teetime list found: {0}", cList.size());
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

    public List<TourneyPlayerScoreDTO> getScoresByTournament(int tournamentID, boolean orderByName) {

        Query q = em.createNamedQuery("TourneyPlayerScore.findByTournament", TourneyPlayerScore.class);
        q.setParameter("id", tournamentID);
        List<TourneyPlayerScore> tpsList = q.getResultList();
        List<TourneyPlayerScoreDTO> list = new ArrayList<>();
        for (TourneyPlayerScore tourneyPlayerScore : tpsList) {
            list.add(new TourneyPlayerScoreDTO(tourneyPlayerScore, true, orderByName));
        }
        Collections.sort(list);
        return list;
    }

    public List<TourneyPlayerScoreDTO> getScoresByTournamentBoys(int tournamentID, boolean orderByName) {

        List<TourneyPlayerScoreDTO> list = getScoresByTournament(tournamentID, true);
        List<TourneyPlayerScoreDTO> tpList = new ArrayList<>();
        for (TourneyPlayerScoreDTO tourneyPlayerScore : list) {
            if (tourneyPlayerScore.getAgeGroup().getGender() == 1) {
                tpList.add(tourneyPlayerScore);
            }
        }
        Collections.sort(tpList);
        return tpList;
    }

    public List<TourneyPlayerScoreDTO> getScoresByTournamentGirls(int tournamentID, boolean orderByName) {

        List<TourneyPlayerScoreDTO> list = getScoresByTournament(tournamentID, true);
        List<TourneyPlayerScoreDTO> tpList = new ArrayList<>();
        for (TourneyPlayerScoreDTO tourneyPlayerScore : list) {
            if (tourneyPlayerScore.getAgeGroup().getGender() == 2) {
                tpList.add(tourneyPlayerScore);
            }
        }
        Collections.sort(tpList);
        return tpList;
    }

    public List<TournamentDTO> getTournamentByGroup(int groupID) {

        List<TournamentDTO> list = new ArrayList<>();
        Query q = em.createNamedQuery("Tournament.findByGolfGroup", Tournament.class);
        q.setParameter("id", groupID);
        List<Tournament> tList = q.getResultList();
        GolfGroup t = em.find(GolfGroup.class, groupID);

        for (Tournament tournament : tList) {
            list.add(new TournamentDTO(tournament));
        }

        Collections.sort(list);
        return list;
    }

    public Tournament getTournamentByID(int tournamentID) {

        Tournament t = em.find(Tournament.class, tournamentID);
        return t;
    }

    public void addTournamentScoreByRound(List<TourneyScoreByRoundDTO> scoreByRoundList) throws DataException {
        try {

            for (TourneyScoreByRoundDTO tsbr : scoreByRoundList) {
                TourneyPlayerScore tps = getTourneyPlayerScoreByID(tsbr.getTourneyPlayerScoreID());
                TourneyScoreByRound t = new TourneyScoreByRound();
                t.setGolfRound(tsbr.getGolfRound());
                t.setTourneyPlayerScore(tps);
                //

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
                em.persist(t);

            }
        } catch (Exception e) {
            logger.log(Level.INFO, "Unable to add score", e);
            throw new DataException(getErrorString(e));

        }
    }

    public void updateTournamentScoreByRound(List<TourneyScoreByRoundDTO> scoreByRoundList) throws DataException {

        try {
            for (TourneyScoreByRoundDTO tsbr : scoreByRoundList) {
                TourneyScoreByRound t = getTourneyScoreByRoundByID(tsbr.getTourneyScoreByRoundID());
                TourneyPlayerScore tps = getTourneyPlayerScoreByID(tsbr.getTourneyPlayerScoreID());
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
                scoreTotals(tps, em);
            }

        } catch (Exception e) {
            logger.log(Level.INFO, "Unable to update score", e);
            throw new DataException(getErrorString(e));
        }
    }

    private void scoreTotals(TourneyPlayerScore tpScore, EntityManager em) throws DataException {
        Query q = em.createNamedQuery("TourneyScoreByRound.getScoreTotals", TourneyScoreByRound.class);
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
        try {
            em.merge(tpScore);
            logger.log(Level.INFO, "### Scores totalled OK, total: {0}", tpScore.getTotalScore());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unable to total scores", e);
            throw new DataException(getErrorString(e));
        }
    }

    public TourneyScoreByRound getTourneyScoreByRoundByID(int id) {
        TourneyScoreByRound tsbr = em.find(TourneyScoreByRound.class, id);
        return tsbr;
    }

    public void updateTourneyPlayerScores(List<TourneyPlayerScoreDTO> tourneyPlayers) throws DataException {
        for (TourneyPlayerScoreDTO dto : tourneyPlayers) {
            updateTournamentScore(dto);
        }
    }

    public TourneyPlayerScore getTourneyPlayerScoreByID(int id) {
        TourneyPlayerScore t = em.find(TourneyPlayerScore.class, id);
        return t;
    }

    public void updateTournamentScore(TourneyPlayerScoreDTO d) throws DataException {

        TourneyPlayerScore s = em.find(TourneyPlayerScore.class, d.getTourneyPlayerScoreID());
        s.setAdministrator(em.find(Administrator.class, d.getAdministrator().getAdministratorID()));
        s.setAgeGroup(em.find(Agegroup.class, d.getAgeGroup().getAgeGroupID()));
        s.setDateUpdated(new Date());
        s.setPaidFlag(d.getPaidFlag());
        s.setScoreRound1(d.getScoreRound1());
        s.setScoreRound2(d.getScoreRound2());
        s.setScoreRound3(d.getScoreRound3());
        s.setScoreRound4(d.getScoreRound4());
        s.setTotalScore(d.getScoreRound1() + d.getScoreRound2() + d.getScoreRound3() + d.getScoreRound4());
        s.setTourneyPosition(d.getTourneyPosition());
        s.setTourneyPositionTied(d.getTourneyPositionTied());
        try {
            em.merge(s);

            logger.log(Level.INFO, "\n### Updated Tournament player score: {0} {1} to {2}",
                    new Object[]{s.getPlayer().getFirstName(), s.getPlayer().getLastName(), s.getTournament().getTourneyName()});
        } catch (Exception e) {
            logger.log(Level.INFO, "Unable to update score", e);
            throw new DataException(getErrorString(e));
        }
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
        g.setDateOfBirth(new Date(player.getDateOfBirth()));
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

    public void updateClubCourse(ClubCourseDTO d) throws DataException {

        ClubCourse s = getClubCourseByID(d.getClubCourseID());
        s.setCourseName(d.getCourseName());
        s.setLatitude(d.getLatitude());
        s.setLongitude(d.getLongitude());
        s.setPar(d.getPar());
        s.setPar1(d.getPar1());
        s.setPar2(d.getPar1());
        s.setPar3(d.getPar1());
        s.setPar4(d.getPar1());
        s.setPar5(d.getPar1());
        s.setPar6(d.getPar1());
        s.setPar7(d.getPar1());
        s.setPar8(d.getPar1());
        s.setPar9(d.getPar1());
        s.setPar10(d.getPar1());
        s.setPar11(d.getPar1());
        s.setPar12(d.getPar1());
        s.setPar13(d.getPar1());
        s.setPar14(d.getPar1());
        s.setPar15(d.getPar1());
        s.setPar16(d.getPar1());
        s.setPar17(d.getPar1());
        s.setPar18(d.getPar1());

        try {
            em.merge(s);

            logger.log(Level.INFO, "\n### Updated clubCourse: {0}",
                    new Object[]{d.getCourseName()});
        } catch (Exception e) {
            logger.log(Level.INFO, "Unable to update clubCourse", e);
            throw new DataException(getErrorString(e));
        }
    }

    public Agegroup getAgeGroupByID(int id) {
        Agegroup g = em.find(Agegroup.class, id);
        return g;
    }

    public ClubCourse getClubCourseByID(int id) {
        ClubCourse g = em.find(ClubCourse.class, id);
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

    public ResponseDTO addTeeTimes(List<TeeTimeDTO> list) throws DataException, DuplicateException {
        ResponseDTO resp = new ResponseDTO();
        resp.setTeeTimeList(new ArrayList<TeeTimeDTO>());
        for (TeeTimeDTO t : list) {
            TeeTimeDTO d = addTeeTime(t);
            resp.getTeeTimeList().add(d);
        }

        return resp;
    }

    public TeeTimeDTO addTeeTime(TeeTimeDTO d) throws DataException, DuplicateException {
        TeeTime s = new TeeTime();
        s.setGolfRound(d.getGolfRound());
        s.setTeeTime(new Date(d.getTeeTime()));
        s.setTourneyPlayerScore(getTourneyPlayerScoreByID(d.getTourneyPlayerScoreID()));
        try {
            em.persist(s);
            logger.log(Level.INFO, "\n### Added Tournament tee time");
        } catch (ConstraintViolationException e) {
            throw new DuplicateException();
        } catch (IllegalStateException e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding Tournament teetime", e);
            throw new DataException(getErrorString(e));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding Tournament teetime", e);
            throw new DataException(getErrorString(e));
        } finally {
        }
        return new TeeTimeDTO(s);
    }

    public void updateTeeTimes(List<TeeTimeDTO> list) throws DataException, DuplicateException {
        for (TeeTimeDTO t : list) {
            updateTeeTime(t);
        }
    }

    public void updateTeeTime(TeeTimeDTO d) throws DataException, DuplicateException {

        TeeTime s = em.find(TeeTime.class, d.getTeeTimeID());
        s.setGolfRound(d.getGolfRound());
        s.setTeeTime(new Date(d.getTeeTime()));
        try {
            em.merge(s);
            logger.log(Level.INFO, "\n### updated Tournament player tee time");
        } catch (ConstraintViolationException e) {
            throw new DuplicateException();
        } catch (IllegalStateException e) {
            logger.log(Level.SEVERE, "***ERROR*** upd Tournament teetime", e);
            throw new DataException(getErrorString(e));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "***ERROR*** upd Tournament teetime", e);
            throw new DataException(getErrorString(e));
        } finally {
        }

    }

    public ResponseDTO addTournamentPlayers(List<TourneyPlayerScoreDTO> list) throws DataException {
        ResponseDTO d = new ResponseDTO();
        d.setTourneyPlayers(new ArrayList<TourneyPlayerScoreDTO>());
        for (TourneyPlayerScoreDTO s : list) {
            TourneyPlayerScoreDTO dto = addTournamentPlayer(s);
            d.getTourneyPlayers().add(s);
        }

        return d;
    }

    public TourneyPlayerScoreDTO addTournamentPlayer(TourneyPlayerScoreDTO d) throws DataException {

        TourneyPlayerScore s = new TourneyPlayerScore();
        s.setAgeGroup(em.find(Agegroup.class, d.getAgeGroup().getAgeGroupID()));
        s.setDateRegistered(new Date());
        s.setPaidFlag(d.getPaidFlag());
        s.setPlayer(getPlayerByID(d.getPlayer().getPlayerID()));
        s.setTournament(getTournamentByID(d.getTournamentID()));
        try {
            em.persist(s);

            List<TourneyScoreByRoundDTO> list = new ArrayList<>();
            for (int i = 0; i < s.getTournament().getGolfRounds(); i++) {
                TourneyScoreByRoundDTO dto = new TourneyScoreByRoundDTO();
                dto.setGolfRound(i + 1);
                dto.setTourneyPlayerScoreID(s.getTourneyPlayerScoreID());
                list.add(dto);
            }
            addTournamentScoreByRound(list);
            logger.log(Level.INFO, "\n### Added Tournament player: {0} {1} to {2}",
                    new Object[]{s.getPlayer().getFirstName(), s.getPlayer().getLastName(), s.getTournament().getTourneyName()});
        } catch (ConstraintViolationException e) {
            throw new DataException(getErrorString(e));
        } catch (IllegalStateException e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding Tournament player", e);
            throw new DataException(getErrorString(e));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding Tournament player", e);
            throw new DataException(getErrorString(e));
        } finally {
        }
        return new TourneyPlayerScoreDTO(s, false, false);
    }

    public void addClubCourse(ClubCourseDTO d) throws DataException {

        ClubCourse s = new ClubCourse();
        s.setClub(getClubByID(d.getClubID()));
        s.setCourseName(d.getCourseName());
        s.setLatitude(d.getLatitude());
        s.setLongitude(d.getLongitude());
        s.setPar(d.getPar());
        s.setPar1(d.getPar1());
        s.setPar2(d.getPar1());
        s.setPar3(d.getPar1());
        s.setPar4(d.getPar1());
        s.setPar5(d.getPar1());
        s.setPar6(d.getPar1());
        s.setPar7(d.getPar1());
        s.setPar8(d.getPar1());
        s.setPar9(d.getPar1());
        s.setPar10(d.getPar1());
        s.setPar11(d.getPar1());
        s.setPar12(d.getPar1());
        s.setPar13(d.getPar1());
        s.setPar14(d.getPar1());
        s.setPar15(d.getPar1());
        s.setPar16(d.getPar1());
        s.setPar17(d.getPar1());
        s.setPar18(d.getPar1());

        try {
            em.persist(s);

            d = new ClubCourseDTO(s);
            logger.log(Level.INFO, "\n### Added ClubCourse: {0}", d.getCourseName());
        } catch (ConstraintViolationException e) {
            throw new DataException();
        } catch (IllegalStateException e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding ClubCourse", e);
            throw new DataException(getErrorString(e));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding ClubCourse", e);
            throw new DataException(getErrorString(e));
        } finally {
        }

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
        t.setClub(getClubByID(dto.getClub().getClubID()));
        t.setClubCourse(getClubCourseByID(dto.getClubCourse().getClubCourseID()));
        t.setGolfRounds(dto.getGolfRounds());
        t.setGolfGroup(getGroupByID(dto.getGolfGroupID()));
        t.setTourneyName(dto.getTourneyName());
        try {
            em.persist(t);
            Query q = em.createNamedQuery("Tournament.findByClubAndCourse", Tournament.class);
            q.setParameter("id", dto.getClub().getClubID());
            q.setParameter("id2", dto.getClubCourse().getClubCourseID());
            List<TournamentDTO> dtoList = new ArrayList<>();
            List<Tournament> list = q.getResultList();
            for (Tournament tx : list) {
                dtoList.add(new TournamentDTO(tx));
            }
            r.setTournaments(dtoList);
            logger.log(Level.INFO, "\n### Added Tournament {0} group: {1}",
                    new Object[]{t.getTourneyName(), t.getGolfGroup().getGolfGroupName()});
        } catch (ConstraintViolationException e) {
            logger.log(Level.SEVERE, "***ERROR*** Duplicate GolfGroup", e);
            r.setStatusCode(ResponseDTO.DUPLICATE_EXCEPTION);
            r.setMessage("This tournament already exists");

        } catch (IllegalStateException e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding Tournament ", e);
            throw new DataException(getErrorString(e));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding Tournament", e);
            throw new DataException(getErrorString(e));
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
            addGolfGroupParent(p.getParentID(), golfGroupID);
            Query q = em.createNamedQuery("Parent.findByGolfGroup", Parent.class);
            q.setParameter("id", golfGroupID);
            List<Parent> list = q.getResultList();
            List<ParentDTO> dtoList = new ArrayList<>();
            for (Parent parent : list) {
                dtoList.add(new ParentDTO(parent));
            }
            q = em.createNamedQuery("Player.findByGolfGroup", Player.class);
            q.setParameter("id", golfGroupID);
            List<PlayerDTO> dList = new ArrayList<>();
            List<Player> listp = q.getResultList();
            for (Player player : listp) {
                dList.add(new PlayerDTO(player));
            }
            for (ParentDTO px : dtoList) {
                px.setPlayers(new ArrayList<PlayerDTO>());
                for (PlayerDTO pd : dList) {
                    if (pd.getParentID() == px.getParentID()) {
                        px.getPlayers().add(pd);
                    }
                }
            }
            r.setParents(dtoList);
            logger.log(Level.INFO, "\n### Added Parent {0}  {1}", new Object[]{p.getFirstName(), p.getLastName()});

        } catch (ConstraintViolationException e) {
            logger.log(Level.SEVERE, "***ERROR*** Duplicate GolfGroup", e);
            r.setStatusCode(ResponseDTO.DUPLICATE_EXCEPTION);
            r.setMessage("This parent already has an account");

        } catch (IllegalStateException e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding Parent ", e);
            throw new DataException(getErrorString(e));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding Parent", e);
            throw new DataException(getErrorString(e));
        } finally {
        }
        return r;
    }

    public void addGolfGroupParent(int parentID, int golfGroupID)
            throws DuplicateException, DataException {

        GolfGroup group = getGroupByID(golfGroupID);
        GolfGroupParent gg = new GolfGroupParent();
        gg.setDateRegistered(new Date());
        gg.setGolfGroup(group);
        gg.setParent(getParentByID(parentID));
        try {
            em.persist(gg);
            logger.log(Level.INFO, "\n### Added Parent to GolfGroup");
            } catch (ConstraintViolationException e) {
             throw new DuplicateException();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding GGParent", e);
            throw new DataException(getErrorString(e));
        } finally {
        }
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
                dList.add(new GolfGroupPlayerDTO(gg));
            }
            r.setGolfGroupPlayers(dList);
            logger.log(Level.INFO, "\n### Added Player to GolfGroup");
            } catch (ConstraintViolationException e) {
            logger.log(Level.SEVERE, "***ERROR*** Duplicate GolfGroup", e);
            r.setStatusCode(ResponseDTO.DUPLICATE_EXCEPTION);
            r.setMessage("This player already belongs to the Group");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding GGPlayer", e);
            throw new DataException(getErrorString(e));
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
       } catch (ConstraintViolationException e) {
            logger.log(Level.SEVERE, "***ERROR*** Duplicate GolfGroup", e);
            r.setStatusCode(ResponseDTO.DUPLICATE_EXCEPTION);
            r.setMessage("This club already exists");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding Club", e);
            throw new DataException(getErrorString(e));
        } finally {
        }
        return r;

    }

    public ResponseDTO addPlayer(PlayerDTO d, int golfGroupID) throws DataException {
        ResponseDTO r = new ResponseDTO();
        Player p = new Player();
        p.setCellphone(d.getCellphone());
        p.setDateOfBirth(new Date(d.getDateOfBirth()));
        p.setDateRegistered(new Date());
        p.setEmail(d.getEmail());
        p.setFirstName(d.getFirstName());
        p.setLastName(d.getLastName());
        p.setMiddleName(d.getMiddleName());
        p.setGender(d.getGender());
        p.setPin(d.getPin());
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
            logger.log(Level.INFO, "\n### Added Player {0} {1}", new Object[]{p.getFirstName(), p.getLastName()});
        } catch (ConstraintViolationException e) {
            logger.log(Level.SEVERE, "***ERROR*** Duplicate GolfGroup", e);
            r.setStatusCode(ResponseDTO.DUPLICATE_EXCEPTION);
            r.setMessage("This player email already has an account");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding Player", e);
            throw new DataException(getErrorString(e));
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
        if (d.getGolfGroup() != null) {
            GolfGroup gg = getGroupByID(d.getGolfGroup().getGolfGroupID());
            a.setGolfGroup(gg);
        }

        try {
            em.persist(a);
            Query q = em.createNamedQuery("Administrator.findByEmail", Administrator.class);
            q.setMaxResults(1);
            q.setParameter("email", d.getEmail());
            Administrator gg = (Administrator) q.getSingleResult();
            r.setAdministrator(new AdministratorDTO(gg));
            logger.log(Level.INFO, "\n### Added Admin {0} {1} ",
                    new Object[]{d.getFirstName(), d.getLastName()});
        } catch (ConstraintViolationException e) {
            logger.log(Level.SEVERE, "***ERROR*** Duplicate GolfGroup", e);
            r.setStatusCode(ResponseDTO.DUPLICATE_EXCEPTION);
            r.setMessage("This golf group or administrator email already has an account");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding Admin", e);
            throw new DataException(getErrorString(e));
            
        } finally {
        }
        return r;
    }

    public ResponseDTO addGolfGroup(GolfGroupDTO d, AdministratorDTO admin) 
            throws DataException {
        ResponseDTO r = new ResponseDTO();
        GolfGroup g = new GolfGroup();
        g.setCellphone(d.getCellphone());
        g.setDateRegistered(new Date());
        g.setEmail(d.getEmail());
        g.setGolfGroupName(d.getGolfGroupName());
        if (d.getCountryID() > 0) {
            g.setCountry(getCountryByID(d.getCountryID()));
        }

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
            admin.setGolfGroup(r.getGolfGroup());
            
            ResponseDTO r2 = addGolfGroupAdmin(admin);
            r.setAdministrator(r2.getAdministrator());
            logger.log(Level.INFO, "\n### Added GolfGroup {0}", g.getGolfGroupName());

       
        } catch (DataException e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding GolfGroup", e);
            throw new DataException(getErrorString(e));
        } catch (ConstraintViolationException e) {
            logger.log(Level.SEVERE, "***ERROR*** Duplicate GolfGroup Admin", e);
            r.setStatusCode(ResponseDTO.DUPLICATE_EXCEPTION);
            r.setMessage("This golf group or administrator email already has an account");
        } finally {
        }
        return r;
    }
    private final Logger logger = Logger.getLogger("DataUtil");
}
