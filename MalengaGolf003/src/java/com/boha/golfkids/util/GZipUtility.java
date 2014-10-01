/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.util;

/**
 *
 * @author aubreymalabie
 */
import com.boha.golfkids.dto.ResponseDTO;
import com.google.gson.Gson;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.*;
import org.apache.commons.io.IOUtils;

public class GZipUtility {

    static final Gson gson = new Gson();
    static final int ZIP_THRESHOLD = 512;
    private static final Logger logger = Logger.getLogger(GZipUtility.class.getName());

    public static ByteBuffer getZippedResponse(ResponseDTO resp) throws IOException {
        String json = gson.toJson(resp);
        byte[] bytes = null;
        if (json.length() < ZIP_THRESHOLD) {
            bytes = json.getBytes();
        } else {
            bytes = getZippedBytes(json);
        }
        ByteBuffer buf = ByteBuffer.wrap(bytes);
        return buf;
    }

    private static byte[] getZippedBytes(String json)
            throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream zos = new GZIPOutputStream(byteArrayOutputStream);
        zos.write(json.getBytes());
        IOUtils.closeQuietly(zos);

        byte[] bytes = byteArrayOutputStream.toByteArray();

        return bytes;

    }
  public static File getZipped(String data) throws Exception {
        String gzipFileName;
        File zip;

        File dir = GolfProperties.getTemporaryDir();
        if (!dir.exists()) {
            dir.mkdir();
        }
        Random rand = new Random(System.currentTimeMillis());
        gzipFileName = "cm" + System.currentTimeMillis()
                + "_" + rand.nextInt(99999999) + ".data.gz";
        zip = new File(dir, gzipFileName);
        //logger.log(Level.INFO, "### new zip file to be filled: {0}", zip.getAbsolutePath());
        ZipOutputStream out;

        out = new ZipOutputStream(new FileOutputStream(zip));
        // Create the input file to be compressed
        File file = FileUtility.getFile(data);
        try (FileInputStream in = new FileInputStream(file)) {
            ZipEntry ze = new ZipEntry(file.getName());
            out.putNextEntry(ze);
            int c = -1;
            while ((c = in.read()) != -1) {
                out.write(c);
            }

            out.closeEntry();
        }
        out.close();
        file.delete();

        return zip;

    }

    private static final int BUFFER = 1024;

    public static void unzip(File zippedFile) {
        File dir = zippedFile.getParentFile();
        try {
            BufferedOutputStream dest = null;
            FileInputStream fis = new FileInputStream(zippedFile);
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                int count;
                byte data[] = new byte[BUFFER];
                // write the files to the disk
                File imgFile = new File(dir, entry.getName());
                FileOutputStream fos = new FileOutputStream(imgFile);
                dest = new BufferedOutputStream(fos, BUFFER);
                while ((count = zis.read(data, 0, BUFFER)) != -1) {
                    dest.write(data, 0, count);
                }
                dest.flush();
                dest.close();
                logger.log(Level.INFO, "\n### File de-compressed: {0}", imgFile.getAbsolutePath());
            }
            zis.close();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unable to de-compress zipped file", e);
        }

    }
}
