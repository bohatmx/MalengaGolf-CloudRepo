/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.servlet;

import com.boha.golfkids.util.DataException;
import com.boha.golfkids.util.DataUtil;
import com.boha.golfkids.util.Generator;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author aubreyM
 */
@WebServlet(name = "GeneratorServlet", urlPatterns = {"/generate"})
public class GeneratorServlet extends HttpServlet {
    @EJB
    DataUtil dataUtil;
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Logger.getLogger(GeneratorServlet.class.getName())
                    .log(Level.INFO, "GeneratorServlet starting ...");
        //
        long start = System.currentTimeMillis();
        PrintWriter out = response.getWriter();
        String sReq = request.getParameter("JSON");
        Gson gson = new Gson();
        GenRequest req = gson.fromJson(sReq, GenRequest.class);
        try {
            String sb = "";
            switch(req.getRequestType()) {
                case GenRequest.GENERATE_PLAYERS:
                   // sb = Generator.generatePlayers(req.getGolfGroupID(),dataUtil);
                    break;
                case GenRequest.GENERATE_TOURNAMENT:
                    //sb = Generator.generateTournament(req, dataUtil);
                    break;
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>MalengaGolf</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>GeneratorServlet completed at " 
                    + request.getContextPath() + "</h1>");
            out.println("<p>" + sb.toString());
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            Logger.getLogger(GeneratorServlet.class.getName()).log(Level.SEVERE, "FAILED GENERATOR", ex);
        } finally {            
            out.close();
            long end = System.currentTimeMillis();
            Logger.getLogger(GeneratorServlet.class.getName())
                    .log(Level.INFO, "#### GeneratorServlet completed, "
                    + "elapsed: {0} seconds", (end - start)/1000);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
