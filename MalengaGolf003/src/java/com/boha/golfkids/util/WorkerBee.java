/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.util;

/**
 *
 * @author aubreyM
 */
import com.boha.golfkids.dto.ClubDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WorkerBee {

    public WorkerBee() {
        try {
            initialize();
        } catch (Exception ex) {
            Logger.getLogger(WorkerBee.class.getName()).log(Level.SEVERE, "Cannot initialize", ex);
        }
    }
    private Connection connect = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public static final String DEV_URL = "jdbc:mysql://localhost:8889/kidsgolf?user=root&password=root";
    public static final String PRODUCTION_URL = "jdbc:mysql://localhost:3306/kidsgolf?user=root&password=kktiger3$";
    public static final String SQL_STATEMENT = "select *, ( ? * acos( cos( radians(?) ) * cos( radians( latitude) ) "
            + "* cos( radians( longitude ) - radians(?) ) + sin( radians(?) ) "
            + "* sin( radians( latitude ) ) ) ) "
            + "AS distance FROM club HAVING distance < ? order by distance";
    public static final int KILOMETRES = 1, MILES = 2, PARM_KM = 6371, PARM_MILES = 3959;

    private void initialize() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        try {
            connect = DriverManager
                    .getConnection(PRODUCTION_URL);
        } catch (SQLException ex) {
            Logger.getLogger(WorkerBee.class.getName()).log(Level.WARNING,
                    "Still hacking on it, Jack! Gimme the dev connection!");
            try {
                connect = DriverManager
                        .getConnection(DEV_URL);
            } catch (SQLException ex1) {
                Logger.getLogger(WorkerBee.class.getName()).log(Level.SEVERE, "We have a database connection error", ex1);
                throw new Exception();
            }
        }
        System.out.println("###### Connection OK, preparing statement: " + connect.getSchema());
        preparedStatement = connect
                .prepareStatement(SQL_STATEMENT);
    }

    public List<ClubDTO> getClubsWithinRadius(double latitude, double longitude, int radius, int type) 
            throws Exception {
        switch (type) {
            case KILOMETRES:
                preparedStatement.setInt(1, PARM_KM);
                break;
            case MILES:
                preparedStatement.setInt(1, PARM_MILES);
                break;
            case 0:
                preparedStatement.setInt(1, PARM_KM);
                break;
        }

        preparedStatement.setDouble(2, latitude);
        preparedStatement.setDouble(3, longitude);
        preparedStatement.setDouble(4, latitude);
        preparedStatement.setInt(5, radius);
        resultSet = preparedStatement.executeQuery();
        List<ClubDTO> list = buildClubList(resultSet);
        Logger.getLogger(WorkerBee.class.getName()).log(Level.OFF, "Found nearby clubs, "
                + "radius: {0} count: {1}", new Object[]{radius, list.size()});
        
        return list;

    }

    private List<ClubDTO> buildClubList(ResultSet resultSet) throws SQLException {
        // resultSet is initialised before the first data set
        List<ClubDTO> list = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("clubID");
            String name = resultSet.getString("clubName");
            double distance = resultSet.getDouble("distance");
            double lat = resultSet.getDouble("latitude");
            double lng = resultSet.getDouble("longitude");
            System.out.println("clubName: " + id + " " + distance + " " + name);
            ClubDTO club = new ClubDTO();
            club.setClubID(id);
            club.setClubName(name);
            club.setDistance(distance);
            club.setLatitude(lat);
            club.setLongitude(lng);
            list.add(club);
        }
        System.out.println("---- clubs found: " + list.size());
        return list;
    }

    public static void main(String[] args) {
        WorkerBee bee = new WorkerBee();
        try {
            bee.getClubsWithinRadius(-32.690986, 26.291829, 50, KILOMETRES);
            bee.getClubsWithinRadius(-32.690986, 26.291829, 100, KILOMETRES);
            bee.getClubsWithinRadius(-32.690986, 26.291829, 150, KILOMETRES);
        } catch (Exception ex) {
            Logger.getLogger(WorkerBee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
