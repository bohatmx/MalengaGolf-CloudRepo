/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.util;

import com.boha.golfkids.data.Administrator;
import com.boha.golfkids.data.AgeGroup;
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
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author Aubrey Malabie
 */
public class DataUtil {

    //private static EntityManager em;
    public static List<ClubDTO> getClubsByCountry(int countryID) throws DataException {
        EntityManager em = EMUtil.getEntityManager();
        List<ClubDTO> cList = new ArrayList<ClubDTO>();
        try {
            Query q = em.createQuery("select a from Club a where a.province.country.countryID = : id "
                    + " order by a.clubName ");
            q.setParameter("id", countryID);
            List<Club> list = q.getResultList();

            for (Club club : list) {
                cList.add(new ClubDTO(club));
            }
        } catch (Exception e) {
            throw new DataException();
        }
        return cList;
    }

    public static List<ClubDTO> getClubsByProvince(int provinceID) throws DataException {
        EntityManager em = EMUtil.getEntityManager();
        List<ClubDTO> cList = new ArrayList<ClubDTO>();
        try {
            Query q = em.createQuery("select a from Club a where a.province.provinceID= : id "
                    + " order by a.clubName ");
            q.setParameter("id", provinceID);
            List<Club> list = q.getResultList();

            for (Club club : list) {
                cList.add(new ClubDTO(club));
            }
        } catch (Exception e) {
            throw new DataException();
        }
        return cList;
    }

    public static List<AgeGroupDTO> getAgeGroups() throws DataException {
        EntityManager em = EMUtil.getEntityManager();
        List<AgeGroupDTO> cList = new ArrayList<AgeGroupDTO>();
        try {
            Query q = em.createQuery("select a from AgeGroup a  "
                    + " order by a.ageGroupName ");

            List<AgeGroup> list = q.getResultList();

            for (AgeGroup g : list) {
                cList.add(new AgeGroupDTO(g));
            }
        } catch (Exception e) {
            throw new DataException();
        }
        return cList;
    }

    public static List<AgeGroupDTO> getAgeGroupsBoys() throws DataException {
        EntityManager em = EMUtil.getEntityManager();
        List<AgeGroupDTO> cList = new ArrayList<AgeGroupDTO>();
        try {
            Query q = em.createQuery("select a from AgeGroup a  where a.gender = 1 "
                    + " order by a.ageGroupID ");

            List<AgeGroup> list = q.getResultList();

            for (AgeGroup g : list) {
                cList.add(new AgeGroupDTO(g));
            }
        } catch (Exception e) {
            throw new DataException();
        }
        return cList;
    }

    public static List<AgeGroupDTO> getAgeGroupsGirls() throws DataException {
        EntityManager em = EMUtil.getEntityManager();
        List<AgeGroupDTO> cList = new ArrayList<AgeGroupDTO>();
        try {
            Query q = em.createQuery("select a from AgeGroup a  where a.gender = 2 "
                    + " order by a.ageGroupID ");

            List<AgeGroup> list = q.getResultList();

            for (AgeGroup g : list) {
                cList.add(new AgeGroupDTO(g));
            }
        } catch (Exception e) {
            throw new DataException();
        }
        return cList;
    }

    public static List<TeeTimeDTO> getTeeTimes(int tournamentID) throws DataException {
        EntityManager em = EMUtil.getEntityManager();
        List<TeeTimeDTO> cList = new ArrayList<TeeTimeDTO>();
        Tournament tn = getTournamentByID(tournamentID);
        try {
            Query q = em.createQuery("select a from TeeTime a   "
                    + " where a.tourneyPlayerScore.tournament = :t "
                    + " order by a.teeTime ");
            q.setParameter("t", tn);
            List<TeeTime> list = q.getResultList();
            for (TeeTime g : list) {
                cList.add(new TeeTimeDTO(g));
            }
        } catch (Exception e) {
            throw new DataException();
        }
        logger.log(Level.OFF, "teetime list found: {0}", cList.size());
        return cList;
    }
    public static List<CountryDTO> getCountries() throws DataException {
        EntityManager em = EMUtil.getEntityManager();
        List<CountryDTO> cList = new ArrayList<CountryDTO>();
        try {
            Query q = em.createQuery("select a from Country a   "
                    + " order by a.countryName ");
            List<Country> list = q.getResultList();
            for (Country g : list) {
                cList.add(new CountryDTO(g));
            }
        } catch (Exception e) {
            throw new DataException();
        }
        logger.log(Level.OFF, "Country list found: {0}", cList.size());
        return cList;
    }

    public static AdministratorDTO getAdminLoggedIn(String email, String pin) throws LoginException, DataException {
        EntityManager em = EMUtil.getEntityManager();
        Query q = em.createQuery("select a from Administrator a where a.email = : email "
                + " and a.pin = :pin");
        q.setMaxResults(1);
        q.setParameter("email", email);
        q.setParameter("pin", pin);

        Administrator a = (Administrator) q.getSingleResult();
        if (a == null) {
            throw new LoginException();
        }
        return new AdministratorDTO(a);

    }

    public static List<TourneyPlayerScoreDTO> getScoresByTournament(int tournamentID, boolean orderByName) {

        Tournament t = getTournamentByID(tournamentID);
        List<TourneyPlayerScoreDTO> list = new ArrayList<TourneyPlayerScoreDTO>();
        List<TourneyPlayerScore> tpList = t.getTourneyPlayerScoreList();
        for (TourneyPlayerScore tourneyPlayerScore : tpList) {
            list.add(new TourneyPlayerScoreDTO(tourneyPlayerScore, true, orderByName));
        }
        Collections.sort(list);
        return list;
    }

    public static List<TourneyPlayerScoreDTO> getScoresByTournamentBoys(int tournamentID, boolean orderByName) {

        Tournament t = getTournamentByID(tournamentID);
        List<TourneyPlayerScoreDTO> list = new ArrayList<TourneyPlayerScoreDTO>();
        List<TourneyPlayerScore> tpList = t.getTourneyPlayerScoreList();
        for (TourneyPlayerScore tourneyPlayerScore : tpList) {
            if (tourneyPlayerScore.getAgeGroup().getGender() == 1) {
                list.add(new TourneyPlayerScoreDTO(tourneyPlayerScore, true, orderByName));
            }
        }
        Collections.sort(list);
        return list;
    }

    public static List<TourneyPlayerScoreDTO> getScoresByTournamentGirls(int tournamentID, boolean orderByName) {

        Tournament t = getTournamentByID(tournamentID);
        List<TourneyPlayerScoreDTO> list = new ArrayList<TourneyPlayerScoreDTO>();
        List<TourneyPlayerScore> tpList = t.getTourneyPlayerScoreList();
        for (TourneyPlayerScore tourneyPlayerScore : tpList) {
            if (tourneyPlayerScore.getAgeGroup().getGender() == 2) {
                list.add(new TourneyPlayerScoreDTO(tourneyPlayerScore, true, orderByName));
            }
        }
        Collections.sort(list);
        return list;
    }

    public static List<TournamentDTO> getTournamentByGroup(int groupID) {
        EntityManager em = EMUtil.getEntityManager();
        List<TournamentDTO> list = new ArrayList<TournamentDTO>();
        GolfGroup t = em.find(GolfGroup.class, groupID);
        if (t != null) {
            for (Tournament tournament : t.getTournamentList()) {
                list.add(new TournamentDTO(tournament));
            }
        }
        Collections.sort(list);
        return list;
    }

    public static Tournament getTournamentByID(int tournamentID) {
        EntityManager em = EMUtil.getEntityManager();
        Tournament t = em.find(Tournament.class, tournamentID);
        return t;
    }

    public static void addTournamentScoreByRound(List<TourneyScoreByRoundDTO> scoreByRoundList) throws DataException {
        EntityManager em = EMUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        tran.begin();
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
            throw new DataException();

        }
    }

    public static void updateTournamentScoreByRound(List<TourneyScoreByRoundDTO> scoreByRoundList) throws DataException {
        EntityManager em = EMUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        tran.begin();

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
            tran.commit();
        } catch (Exception e) {
            logger.log(Level.INFO, "Unable to update score", e);
            throw new DataException();
        }
    }

    public static void scoreTotals(TourneyPlayerScore tpScore, EntityManager em) throws DataException {
        Query q = null;
        q = em.createQuery("select a from TourneyScoreByRound a "
                + " where a.tourneyPlayerScore = :t");
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
            throw new DataException();
        }
    }

    public static TourneyScoreByRound getTourneyScoreByRoundByID(int id) {
        EntityManager em = EMUtil.getEntityManager();
        TourneyScoreByRound tsbr = em.find(TourneyScoreByRound.class, id);

        return tsbr;
    }

    public static void updateTourneyPlayerScores(List<TourneyPlayerScoreDTO> tourneyPlayers) throws DataException {

        for (TourneyPlayerScoreDTO dto : tourneyPlayers) {
            updateTournamentScore(dto);
        }
    }

    public static TourneyPlayerScore getTourneyPlayerScoreByID(int id) {
        EntityManager em = EMUtil.getEntityManager();
        TourneyPlayerScore t = em.find(TourneyPlayerScore.class, id);
        return t;
    }

    public static void updateTournamentScore(TourneyPlayerScoreDTO d) throws DataException {
        EntityManager em = EMUtil.getEntityManager();
        TourneyPlayerScore s = em.find(TourneyPlayerScore.class, d.getTourneyPlayerScoreID());
        s.setAdministrator(em.find(Administrator.class, d.getAdministrator().getAdministratorID()));
        s.setAgeGroup(em.find(AgeGroup.class, d.getAgeGroup().getAgeGroupID()));
        s.setDateUpdated(new Date());
        s.setPaidFlag(d.getPaidFlag());
        s.setScoreRound1(d.getScoreRound1());
        s.setScoreRound2(d.getScoreRound2());
        s.setScoreRound3(d.getScoreRound3());
        s.setScoreRound4(d.getScoreRound4());
        s.setTotalScore(d.getScoreRound1() + d.getScoreRound2() + d.getScoreRound3() + d.getScoreRound4());
        s.setTourneyPosition(d.getTourneyPosition());
        s.setTourneyPositionTied(d.getTourneyPositionTied());
        EntityTransaction tran = em.getTransaction();
        tran.begin();
        try {
            em.merge(s);
            tran.commit();
            logger.log(Level.INFO, "\n### Updated Tournament player score: {0} {1} to {2}",
                    new Object[]{s.getPlayer().getFirstName(), s.getPlayer().getLastName(), s.getTournament().getTourneyName()});
        } catch (Exception e) {
            logger.log(Level.INFO, "Unable to update score", e);
            throw new DataException();
        }
    }

    public static void updateGolfGroup(GolfGroupDTO group) throws DataException {
        EntityManager em = EMUtil.getEntityManager();

        GolfGroup g = getGroupByID(group.getGolfGroupID());
        g.setGolfGroupName(group.getGolfGroupName());
        g.setEmail(group.getEmail());
        g.setCellphone(group.getCellphone());

        EntityTransaction tran = em.getTransaction();
        tran.begin();
        try {
            em.merge(g);
            tran.commit();
            logger.log(Level.INFO, "\n### Updated golf group: {0} ",
                    new Object[]{g.getGolfGroupName()});
        } catch (Exception e) {
            logger.log(Level.INFO, "Unable to update score", e);
            throw new DataException();
        }
    }

    public static void removeParentInPlayer(PlayerDTO p) throws DataException {
        EntityManager em = EMUtil.getEntityManager();

        Player g = getPlayerByID(p.getPlayerID());
        g.setParent(null);

        EntityTransaction tran = em.getTransaction();
        tran.begin();
        try {
            em.merge(g);
            tran.commit();
            logger.log(Level.INFO, "\n### Updated player: '{'0'}' {0} {1}", new Object[]{p.getFirstName(), p.getLastName()});
        } catch (Exception e) {
            logger.log(Level.INFO, "Unable to update player", e);
            throw new DataException();
        }
    }

    public static void updatePlayer(PlayerDTO player) throws DataException {
        EntityManager em = EMUtil.getEntityManager();

        Player g = getPlayerByID(player.getPlayerID());
        g.setCellphone(player.getCellphone());
        g.setEmail(player.getEmail());
        g.setDateOfBirth(new Date(player.getDateOfBirth()));
        g.setFirstName(player.getFirstName());
        g.setLastName(player.getLastName());
        g.setGender(player.getGender());
        g.setMiddleName(player.getMiddleName());
        g.setYearJoined(player.getYearJoined());
        if (player.getParent().getParentID() > 0) {
            removeParentInPlayer(player);
            Parent parent = getParentByID(player.getParent().getParentID());
            g.setParent(parent);
        }

        EntityTransaction tran = em.getTransaction();
        tran.begin();
        try {
            em.merge(g);
            tran.commit();
            logger.log(Level.INFO, "\n### Updated player: '{'0'}' {0} {1}", new Object[]{player.getFirstName(), player.getLastName()});
        } catch (Exception e) {
            logger.log(Level.INFO, "Unable to update player", e);
            throw new DataException();
        }
    }

    public static void updateParent(ParentDTO dto) throws DataException {
        EntityManager em = EMUtil.getEntityManager();

        Parent g = getParentByID(dto.getParentID());
        g.setCellphone(dto.getCellphone());
        g.setEmail(dto.getEmail());
        g.setFirstName(dto.getFirstName());
        g.setLastName(dto.getLastName());
        g.setMiddleName(dto.getMiddleName());


        EntityTransaction tran = em.getTransaction();
        tran.begin();
        try {
            em.merge(g);
            tran.commit();
            logger.log(Level.INFO, "\n### Updated parent: '{'0'}' {0} {1}", new Object[]{dto.getFirstName(), dto.getLastName()});
        } catch (Exception e) {
            logger.log(Level.INFO, "Unable to update parent", e);
            throw new DataException();
        }
    }

    public static void updateClub(ClubDTO dto) throws DataException {
        EntityManager em = EMUtil.getEntityManager();

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


        EntityTransaction tran = em.getTransaction();
        tran.begin();
        try {
            em.merge(g);
            tran.commit();
            logger.log(Level.INFO, "\n### Updated club: {0}", new Object[]{dto.getClubName()});
        } catch (Exception e) {
            logger.log(Level.INFO, "Unable to update club", e);
            throw new DataException();
        }
    }

    public static void updateAdmin(AdministratorDTO d) throws DataException {
        EntityManager em = EMUtil.getEntityManager();

        Administrator g = em.find(Administrator.class, d.getAdministratorID());
        g.setCellphone(d.getCellphone());
        g.setEmail(d.getEmail());
        g.setFirstName(d.getFirstName());
        g.setLastName(d.getLastName());


        EntityTransaction tran = em.getTransaction();
        tran.begin();
        try {
            em.merge(g);
            tran.commit();
            logger.log(Level.INFO, "\n### Updated admin: {0}", new Object[]{d.getFirstName() + " " + d.getLastName()});
        } catch (Exception e) {
            logger.log(Level.INFO, "Unable to update admin", e);
            throw new DataException();
        }
    }

    public static void updateClubCourse(ClubCourseDTO d) throws DataException {
        EntityManager em = EMUtil.getEntityManager();

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


        EntityTransaction tran = em.getTransaction();
        tran.begin();
        try {
            em.merge(s);
            tran.commit();
            logger.log(Level.INFO, "\n### Updated clubCourse: {0}",
                    new Object[]{d.getCourseName()});
        } catch (Exception e) {
            logger.log(Level.INFO, "Unable to update clubCourse", e);
            throw new DataException();
        }
    }

    public static AgeGroup getAgeGroupByID(int id) {
        EntityManager em = EMUtil.getEntityManager();
        AgeGroup g = em.find(AgeGroup.class, id);
        return g;
    }

    public static ClubCourse getClubCourseByID(int id) {
        EntityManager em = EMUtil.getEntityManager();
        ClubCourse g = em.find(ClubCourse.class, id);
        return g;
    }

    public static Administrator getAdministratorByID(int id) {
        EntityManager em = EMUtil.getEntityManager();
        Administrator g = em.find(Administrator.class, id);
        return g;
    }

   
    public static Club getClubByID(int id) {
        EntityManager em = EMUtil.getEntityManager();
        Club g = em.find(Club.class, id);
        return g;
    }

    public static Country getCountryByID(int id) {
        EntityManager em = EMUtil.getEntityManager();
        Country g = em.find(Country.class, id);
        return g;
    }

    public static Province getProvinceByID(int id) {
        EntityManager em = EMUtil.getEntityManager();
        Province g = em.find(Province.class, id);
        return g;
    }

    public static GolfGroup getGroupByID(int id) {
        EntityManager em = EMUtil.getEntityManager();
        GolfGroup g = em.find(GolfGroup.class, id);
        return g;
    }

    public static Player getPlayerByID(int id) {
        EntityManager em = EMUtil.getEntityManager();
        Player g = em.find(Player.class, id);
        return g;
    }

    public static Parent getParentByID(int id) {
        EntityManager em = EMUtil.getEntityManager();
        Parent g = em.find(Parent.class, id);
        return g;
    }

    public static ResponseDTO addTeeTimes(List<TeeTimeDTO> list) throws DataException, DuplicateException {
        ResponseDTO resp = new ResponseDTO();
        resp.setTeeTimeList(new ArrayList<TeeTimeDTO>());
        for (TeeTimeDTO t : list) {
           TeeTimeDTO d = addTeeTime(t);
           resp.getTeeTimeList().add(d);
        }
        
        return resp;
    }

    public static TeeTimeDTO addTeeTime(TeeTimeDTO d) throws DataException, DuplicateException {
        EntityManager em = EMUtil.getEntityManager();

        TeeTime s = new TeeTime();
        s.setGolfRound(d.getGolfRound());
        s.setTeeTime(new Date(d.getTeeTime()));
        s.setTourneyPlayerScore(getTourneyPlayerScoreByID(d.getTourneyPlayerScoreID()));

        EntityTransaction tran = em.getTransaction();
        tran.begin();
        try {
            em.persist(s);
            tran.commit();
            logger.log(Level.INFO, "\n### Added Tournament tee time");
        } catch (ConstraintViolationException e) {
            throw new DuplicateException();
        } catch (IllegalStateException e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding Tournament teetime", e);
            throw new DataException();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding Tournament teetime", e);
            throw new DataException();
        } finally {
            if (tran.isActive()) {
                tran.rollback();
            }
            em.close();
        }
        return new TeeTimeDTO(s);
    }

    public static void updateTeeTimes(List<TeeTimeDTO> list) throws DataException, DuplicateException {
        for (TeeTimeDTO t : list) {
            updateTeeTime(t);
        }
    }

    public static void updateTeeTime(TeeTimeDTO d) throws DataException, DuplicateException {
        EntityManager em = EMUtil.getEntityManager();

        TeeTime s = em.find(TeeTime.class, d.getTeeTimeID());
        s.setGolfRound(d.getGolfRound());
        s.setTeeTime(new Date(d.getTeeTime()));
        //s.setTourneyPlayerScore(getTourneyPlayerScoreByID(d.getTourneyPlayerScoreID()));

        EntityTransaction tran = em.getTransaction();
        tran.begin();
        try {
            em.merge(s);
            tran.commit();

            logger.log(Level.INFO, "\n### updated Tournament player tee time");
        } catch (ConstraintViolationException e) {
            throw new DuplicateException();
        } catch (IllegalStateException e) {
            logger.log(Level.SEVERE, "***ERROR*** upd Tournament teetime", e);
            throw new DataException();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "***ERROR*** upd Tournament teetime", e);
            throw new DataException();
        } finally {
            if (tran.isActive()) {
                tran.rollback();
            }
            em.close();
        }

    }

    public static ResponseDTO addTournamentPlayers(List<TourneyPlayerScoreDTO> list) throws DataException, DuplicateException {
        ResponseDTO d = new ResponseDTO();
        d.setTourneyPlayers(new ArrayList<TourneyPlayerScoreDTO>());
        for (TourneyPlayerScoreDTO s : list) {
            TourneyPlayerScoreDTO dto = addTournamentPlayer(s);
            d.getTourneyPlayers().add(s);
        }
        
        return d;
    }

    public static TourneyPlayerScoreDTO addTournamentPlayer(TourneyPlayerScoreDTO d) throws DataException, DuplicateException {
        EntityManager em = EMUtil.getEntityManager();

        TourneyPlayerScore s = new TourneyPlayerScore();
        s.setAgeGroup(em.find(AgeGroup.class, d.getAgeGroup().getAgeGroupID()));
        s.setDateRegistered(new Date());
        s.setPaidFlag(d.getPaidFlag());
        s.setPlayer(getPlayerByID(d.getPlayer().getPlayerID()));
        s.setTournament(getTournamentByID(d.getTournamentID()));

        EntityTransaction tran = em.getTransaction();
        tran.begin();
        try {
            em.persist(s);
            tran.commit();
            List<TourneyScoreByRoundDTO> list = new ArrayList<TourneyScoreByRoundDTO>();
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
            throw new DuplicateException();
        } catch (IllegalStateException e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding Tournament player", e);
            throw new DataException();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding Tournament player", e);
            throw new DataException();
        } finally {
            if (tran.isActive()) {
                tran.rollback();
            }
            em.close();
        }
        return new TourneyPlayerScoreDTO(s, false, false);
    }

    public static void addClubCourse(ClubCourseDTO d) throws DataException, DuplicateException {
        EntityManager em = EMUtil.getEntityManager();

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

        EntityTransaction tran = em.getTransaction();
        tran.begin();
        try {
            em.persist(s);
            tran.commit();
            d = new ClubCourseDTO(s);
            logger.log(Level.INFO, "\n### Added ClubCourse: {0}", d.getCourseName());
        } catch (ConstraintViolationException e) {
            throw new DuplicateException();
        } catch (IllegalStateException e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding ClubCourse", e);
            throw new DataException();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding ClubCourse", e);
            throw new DataException();
        } finally {
            if (tran.isActive()) {
                tran.rollback();
            }
            em.close();
        }

    }

    public static TournamentDTO addTournament(TournamentDTO dto) throws DataException, DuplicateException {
        EntityManager em = EMUtil.getEntityManager();
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
        EntityTransaction tran = em.getTransaction();
        tran.begin();
        try {
            em.persist(t);
            tran.commit();
            logger.log(Level.INFO, "\n### Added Tournament {0} group: {1}",
                    new Object[]{t.getTourneyName(), t.getGolfGroup().getGolfGroupName()});
        } catch (ConstraintViolationException e) {
            throw new DuplicateException();
        } catch (IllegalStateException e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding Tournament ", e);
            throw new DataException();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding Tournament", e);
            throw new DataException();
        } finally {
            if (tran.isActive()) {
                tran.rollback();
            }
            em.close();
        }
        return new TournamentDTO(t);
    }

    public static ParentDTO addParent(ParentDTO dto, int golfGroupID) throws DataException, DuplicateException {
        EntityManager em = EMUtil.getEntityManager();
        Parent p = new Parent();
        p.setCellphone(dto.getCellphone());
        p.setEmail(dto.getEmail());
        p.setFirstName(dto.getFirstName());
        p.setLastName(dto.getLastName());
        p.setMiddleName(dto.getMiddleName());
        p.setParentType(dto.getParentType());
        p.setPin(dto.getPin());

        EntityTransaction tran = em.getTransaction();
        tran.begin();
        try {
            em.persist(p);
            tran.commit();
            addGolfGroupParent(p.getParentID(), golfGroupID);
            logger.log(Level.INFO, "\n### Added Parent {0}  {1}", new Object[]{p.getFirstName(), p.getLastName()});

        } catch (ConstraintViolationException e) {
            throw new DuplicateException();
        } catch (IllegalStateException e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding Parent ", e);
            throw new DataException();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding Parent", e);
            throw new DataException();
        } finally {
            if (tran.isActive()) {
                tran.rollback();
            }
            em.close();
        }
        return new ParentDTO(p, true);
    }

    public static void addGolfGroupParent(int parentID, int golfGroupID) throws DuplicateException, DataException {
        EntityManager em = EMUtil.getEntityManager();
        GolfGroup group = DataUtil.getGroupByID(golfGroupID);
        GolfGroupParent gg = new GolfGroupParent();
        gg.setDateRegistered(new Date());
        gg.setGolfGroup(group);
        gg.setParent(DataUtil.getParentByID(parentID));
        EntityTransaction tran = em.getTransaction();
        tran.begin();
        try {
            em.persist(gg);
            tran.commit();
            logger.log(Level.INFO, "\n### Added Parent to GolfGroup");

        } catch (ConstraintViolationException e) {
            throw new DuplicateException();
        } catch (IllegalStateException e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding GGParent ", e);
            throw new DataException();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding GGParent", e);
            throw new DataException();
        } finally {
            if (tran.isActive()) {
                tran.rollback();
            }
            em.close();
        }
    }

    public static void addGolfGroupPlayer(int playerID, int golfGroupID) throws DuplicateException, DataException {
        EntityManager em = EMUtil.getEntityManager();
        GolfGroup group = DataUtil.getGroupByID(golfGroupID);
        GolfGroupPlayer gg = new GolfGroupPlayer();
        gg.setDateRegistered(new Date());
        gg.setGolfGroup(group);
        gg.setPlayer(DataUtil.getPlayerByID(playerID));
        EntityTransaction tran = em.getTransaction();
        tran.begin();
        try {
            em.persist(gg);
            tran.commit();
            logger.log(Level.INFO, "\n### Added Player to GolfGroup");

        } catch (ConstraintViolationException e) {
            throw new DuplicateException();
        } catch (IllegalStateException e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding GGPlayert ", e);
            throw new DataException();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding GGPlayer", e);
            throw new DataException();
        } finally {
            if (tran.isActive()) {
                tran.rollback();
            }
            em.close();
        }
    }

    public static ClubDTO addClub(ClubDTO d) throws DataException, DuplicateException {
        EntityManager em = EMUtil.getEntityManager();
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
        EntityTransaction tran = em.getTransaction();
        tran.begin();
        try {
            em.persist(club);
            tran.commit();
            logger.log(Level.INFO, "\n### Added Club {0}", club.getClubName() + " - " + club.getProvince().getProvinceName());
        } catch (ConstraintViolationException e) {
            throw new DuplicateException();
        } catch (IllegalStateException e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding Club ", e);
            throw new DataException();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding Club", e);
            throw new DataException();
        } finally {
            if (tran.isActive()) {
                tran.rollback();
            }
            em.close();
        }
        return new ClubDTO(club);

    }

    public static List<PlayerDTO> addPlayers(List<PlayerDTO> list, int golfGroupID) throws DataException, DuplicateException {
        List<PlayerDTO> pList = new ArrayList<PlayerDTO>();

        for (PlayerDTO playerDTO : list) {
            pList.add(addPlayer(playerDTO, golfGroupID));
        }

        return pList;
    }

    private static PlayerDTO addPlayer(PlayerDTO d, int golfGroupID) throws DataException, DuplicateException {
        EntityManager em = EMUtil.getEntityManager();
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
        if (d.getParent() != null) {
            Parent parent = getParentByID(d.getParent().getParentID());
            p.setParent(parent);
        }
        EntityTransaction tran = em.getTransaction();
        tran.begin();
        try {
            em.persist(p);
            tran.commit();
            logger.log(Level.INFO, "\n### Added Player {0} {1}", new Object[]{p.getFirstName(), p.getLastName()});
            addGolfGroupPlayer(p.getPlayerID(), golfGroupID);
        } catch (ConstraintViolationException e) {
            throw new DuplicateException();
        } catch (IllegalStateException e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding Player ", e);
            throw new DataException();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding Player", e);
            throw new DataException();
        } finally {
            if (tran.isActive()) {
                tran.rollback();
            }
            em.close();
        }
        return new PlayerDTO(p, false);
    }

    public static AdministratorDTO addGolfGroupAdmin(AdministratorDTO d) throws DataException, DuplicateException {
        EntityManager em = EMUtil.getEntityManager();
        Administrator a = new Administrator();
        a.setCellphone(d.getCellphone());
        a.setEmail(d.getEmail());
        a.setFirstName(d.getFirstName());
        a.setLastName(d.getLastName());
        a.setPin(d.getPin());
        if (d.getGolfGroup() != null) {
            GolfGroup gg = getGroupByID(d.getGolfGroup().getGolfGroupID());
            a.setGolfGroup(gg);
        }
        EntityTransaction tran = em.getTransaction();
        tran.begin();
        try {
            em.persist(a);
            tran.commit();
            logger.log(Level.INFO, "\n### Added Admin {0} {1} group: {2}",
                    new Object[]{a.getFirstName(), a.getLastName(), a.getGolfGroup().getGolfGroupName()});
        } catch (ConstraintViolationException e) {
            throw new DuplicateException();
        } catch (IllegalStateException e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding Admin ", e);
            throw new DataException();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding Admin", e);
            throw new DataException();
        } finally {
            if (tran.isActive()) {
                tran.rollback();
            }
            em.close();
        }
        return new AdministratorDTO(a);
    }

    public static GolfGroupDTO addGolfGroup(GolfGroupDTO d) throws DataException, DuplicateException {
        EntityManager em = EMUtil.getEntityManager();
        GolfGroup g = new GolfGroup();
        g.setCellphone(d.getCellphone());
        g.setDateRegistered(new Date());
        g.setEmail(d.getEmail());
        g.setGolfGroupName(d.getGolfGroupName());
        if (d.getCountryID() > 0) {
            g.setCountry(getCountryByID(d.getCountryID()));
        }
        EntityTransaction tran = em.getTransaction();
        tran.begin();
        try {
            em.persist(g);
            tran.commit();
            logger.log(Level.INFO, "\n### Added GolfGroup {0}", g.getGolfGroupName());
            return new GolfGroupDTO(g);
        } catch (ConstraintViolationException e) {
            throw new DuplicateException();
        } catch (IllegalStateException e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding GolfGroup ", e);
            throw new DataException();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "***ERROR*** Adding GolfGroup", e);
            throw new DataException();
        } finally {
            if (tran.isActive()) {
                tran.rollback();
            }
            em.close();
        }

    }
    private static final Logger logger = Logger.getLogger("DataUtil");
}
