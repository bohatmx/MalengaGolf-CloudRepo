/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.servlet;

import com.boha.golfkids.util.GolfProperties;
import com.boha.golfkids.util.PlatformUtil;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author malengatiger
 */
@WebServlet(name = "HouseKeeper", urlPatterns = {"/HouseKeeper"}, loadOnStartup = 3)
public class HouseKeeper extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        } finally {
            out.close();
        }
    }

    @Override
    public void init() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n### ##########################################################################\n");
        sb.append("### MalengaGolf Back-End is UP and STARTED\n");
        sb.append("### ##########################################################################\n\n");
        logger.log(Level.INFO, sb.toString());

        Timer timer = new Timer();
        Calendar cal = GregorianCalendar.getInstance();
        for (int i = 0; i < 5; i++) {
            cal.roll(Calendar.MINUTE, true);
        }

        Date date = cal.getTime();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //clean up temporary files created
                startDiskCleanup();
            }
        }, date, FOUR_HOUR);

    }

    private void startDiskCleanup() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n\n### ##########################################################################\n");
        sb.append("### MalengaGolf Disk Cleanup STARTED\n");
        sb.append("### ##########################################################################\n\n");
        logger.log(Level.INFO, sb.toString());

        int count = 0;
        File dir = GolfProperties.getTemporaryDir();
        if (dir.exists()) {
            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                long now = new Date().getTime();
                long cutOff = now - ONE_HOUR;
                if (file.lastModified() < cutOff) {
                    boolean OK = file.delete();
                    if (OK) {
                        count++;
                    }
                }
            }
        }
        logger.log(Level.INFO, "### MalengaGolf HouseKeeping cleaned up {0} temporary files", count);
        try {
            platformUtil.addErrorStore(133, "MGGolf temporary files cleaned up", "HouseKeeper");
        } catch (Exception e) {

        }
    }
    @EJB
    PlatformUtil platformUtil;
    private static final Logger logger = Logger.getLogger("HouseKeeper");
    private final static int ONE_HOUR = 1000 * 60 * 60;
    private final static int FOUR_HOUR = 1000 * 60 * 60 * 60 * 4;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
