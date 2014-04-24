/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.servlet;

import com.boha.golfkids.dto.PhotoUploadDTO;
import com.boha.golfkids.dto.RequestDTO;
import com.boha.golfkids.dto.ResponseDTO;
import com.boha.golfkids.util.FileUtility;
import com.boha.golfkids.util.GolfProperties;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.joda.time.DateTime;

/**
 * This servlet accepts image files uploaded from CourseMaker devices and saves
 * them on disk according to the requestor's role.
 *
 * @author aubreyM
 */
@WebServlet(name = "PhotoServlet", urlPatterns = {"/photo"})
public class PhotoServlet extends HttpServlet {

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
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        long start = System.currentTimeMillis();

        ResponseDTO ur = new ResponseDTO();
        String json;                      
        Gson gson = new Gson();
        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (isMultipart) {
                ur = downloadPhotos(request);
            } else {
                RequestDTO dto = getRequest(gson, request);
                switch (dto.getRequestType()) {
                    case RequestDTO.GET_TOURNAMENT_PICTURES:
                        ur.setImageFileNames(FileUtility.getImageFilesTournament(
                                dto.getGolfGroupID(), dto.getTournamentID(), RequestDTO.PICTURES_FULL_SIZE));
                        break;
                    case RequestDTO.GET_TOURNAMENT_THUMBNAILS:
                        ur.setImageFileNames(FileUtility.getImageFilesTournament(
                                dto.getGolfGroupID(), dto.getTournamentID(), RequestDTO.PICTURES_THUMBNAILS));
                        break;
                    case RequestDTO.GET_GOLFGROUP_PICTURES:
                        ur.setImageFileNames(FileUtility.getImageFilesGolfGroup(
                                dto.getGolfGroupID(), RequestDTO.PICTURES_FULL_SIZE));
                        break;
                    case RequestDTO.GET_GOLFGROUP_THUMBNAILS:
                        ur.setImageFileNames(FileUtility.getImageFilesGolfGroup(
                                dto.getGolfGroupID(), RequestDTO.PICTURES_THUMBNAILS));
                        break;

                }

            }

        } catch (FileUploadException ex) {
            Logger.getLogger(PhotoServlet.class.getName()).log(Level.SEVERE, "File upload fucked", ex);
            ur.setStatusCode(ResponseDTO.GENERIC_EXCEPTION);
            ur.setMessage("Error. Unable to download file(s) sent. Contact Support");

        } catch (Exception e) {
            Logger.getLogger(PhotoServlet.class.getName()).log(Level.SEVERE, "Servlet file upload fucked", e);
            ur.setStatusCode(ResponseDTO.GENERIC_EXCEPTION);
            ur.setMessage("Error. Generic server exception");

        } finally {
            json = gson.toJson(ur);
            out.println(json);
            out.close();
            long end = System.currentTimeMillis();
            logger.log(Level.INFO, "PhotoServlet done, elapsed: {0} seconds", getElapsed(start, end));
        }
    }

    private ResponseDTO downloadPhotos(HttpServletRequest request) throws FileUploadException {
        logger.log(Level.INFO, "######### starting PHOTO DOWNLOAD process\n\n");
        ResponseDTO resp = new ResponseDTO();
        FileOutputStream fos = null;
        InputStream stream = null;
        File rootDir;
        try {
            rootDir = GolfProperties.getImageDir();
            logger.log(Level.INFO, "rootDir - {0}", rootDir.getAbsolutePath());
            if (!rootDir.exists()) {
                rootDir.mkdir();
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Properties file problem", ex);
            resp.setMessage("Server file unavailable. Please try later");
            resp.setStatusCode(ResponseDTO.GENERIC_EXCEPTION);

            return resp;
        }

        int fileCnt = 0;
        PhotoUploadDTO dto = null;
        Gson gson = new Gson();
        File golfGroupDir = null, tournamentDir = null,
                golfGroupDirThumbs = null, tournamentDirThumbs = null;
        try {
            ServletFileUpload upload = new ServletFileUpload();
            FileItemIterator iter = upload.getItemIterator(request);
            while (iter.hasNext()) {
                FileItemStream item = iter.next();
                String name = item.getFieldName();
                stream = item.openStream();
                if (item.isFormField()) {
                    if (name.equalsIgnoreCase("JSON")) {
                        String json = Streams.asString(stream);
                        if (json != null) {
                            logger.log(Level.INFO, "picture with associated json: {0}", json);
                            dto = gson.fromJson(json, PhotoUploadDTO.class);
                            if (dto != null) {
                                golfGroupDir = new File(rootDir, GOLF_GROUP_PREFIX + dto.getGolfGroupID());
                                if (!golfGroupDir.exists()) {
                                    golfGroupDir.mkdir();
                                    logger.log(Level.INFO, "golfgroup directory created - {0}",
                                            golfGroupDir.getAbsolutePath());
                                }
                                golfGroupDirThumbs = new File(golfGroupDir, THUMB_PREFIX + dto.getGolfGroupID());
                                if (!golfGroupDirThumbs.exists()) {
                                    golfGroupDirThumbs.mkdir();
                                    logger.log(Level.INFO, "golfgroup thumbs directory created - {0}",
                                            golfGroupDirThumbs.getAbsolutePath());
                                }

                                if (dto.getTournamentID() > 0) {
                                    logger.log(Level.INFO, "tournament photo to be downloaded");
                                    tournamentDir = new File(golfGroupDir, TOURNAMENT_PREFIX + dto.getTournamentID());
                                    if (!tournamentDir.exists()) {
                                        tournamentDir.mkdir();
                                        logger.log(Level.INFO, "tournament  directory created - {0}",
                                                tournamentDir.getAbsolutePath());
                                    }
                                    tournamentDirThumbs = new File(tournamentDir, THUMB_PREFIX + dto.getTournamentID());
                                    if (!tournamentDirThumbs.exists()) {
                                        tournamentDirThumbs.mkdir();
                                        logger.log(Level.INFO, "tournament thumbs directory created - {0}",
                                                tournamentDirThumbs.getAbsolutePath());
                                    }
                                }
                            }
                        } else {
                            logger.log(Level.WARNING, "JSON input seems fucked up! is NULL..");
                        }
                    }
                } else {
                    logger.log(Level.OFF, "name of item to be processed into file: {0}", name);
                    File imageFile = null;
                    if (dto == null) {
                        continue;
                    }
                    DateTime dt = new DateTime();
                    String suffix = "" + dt.getMillis() + ".jpg";
                    if (dto.getTournamentID() == 0) {
                        switch (dto.getType()) {
                            case PhotoUploadDTO.PICTURES_FULL_SIZE:
                                imageFile = new File(golfGroupDir, suffix);
                                break;
                            case PhotoUploadDTO.PICTURES_THUMBNAILS:
                                imageFile = new File(golfGroupDirThumbs, suffix);
                                break;

                        }
                    } else {
                        switch (dto.getType()) {
                            case PhotoUploadDTO.PICTURES_FULL_SIZE:
                                imageFile = new File(tournamentDir, suffix);
                                break;
                            case PhotoUploadDTO.PICTURES_THUMBNAILS:
                                imageFile = new File(tournamentDirThumbs, suffix);
                                break;

                        }
                    }

                    fos = new FileOutputStream(imageFile);
                    int read;
                    byte[] bytes = new byte[2048];
                    while ((read = stream.read(bytes)) != -1) {
                        fos.write(bytes, 0, read);
                    }
                    stream.close();
                    fos.flush();
                    fos.close();

                    fileCnt++;
                    resp.setMessage("Photos downloaded from mobile app " + fileCnt);

                    logger.log(Level.INFO, "\n### File downloaded: {0} size: {1}",
                            new Object[]{imageFile.getAbsolutePath(), imageFile.length()});

                }
            }

        } catch (FileUploadException | IOException | JsonSyntaxException ex) {
            logger.log(Level.SEVERE, "Servlet failed on IOException, images NOT uploaded", ex);
            throw new FileUploadException();
        } finally {
            try {
                if (fos != null) {
                    fos.flush();
                    fos.close();
                }
            } catch (IOException ex) {
                logger.log(Level.WARNING, "closing stream", ex);
            }

        }

        return resp;
    }

    public static double getElapsed(long start, long end) {
        BigDecimal m = new BigDecimal(end - start).divide(new BigDecimal(1000));
        return m.doubleValue();
    }
    static final Logger logger = Logger.getLogger("PhotoServlet");
    public static final String GOLF_GROUP_PREFIX = "golfgroup";
    public static final String TOURNAMENT_PREFIX = "tournament";
    public static final String THUMB_PREFIX = "thumbnails";
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

    private RequestDTO getRequest(Gson gson, HttpServletRequest req) {

        String json = req.getParameter("JSON");
        RequestDTO cr = gson.fromJson(json, RequestDTO.class);

        if (cr == null) {
            cr = new RequestDTO();
        }

        return cr;
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
