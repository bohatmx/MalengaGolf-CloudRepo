/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

/**
 *
 * @author malengatiger
 */
public class EMUtil {
    private static final Logger logger = Logger.getLogger("EMUtil");
    private static EntityManagerFactory emf;
    private static EntityManager em;

    private static void setEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("MGGolf003bPU");
        }
        if (em == null) {
            em = emf.createEntityManager();
            logger.log(Level.INFO, "\n\n### MalengaGolf EntityManager created OK: {0}", em.toString());
        } else {
            if (!em.isOpen()) {
                em = emf.createEntityManager();
            }
        }
    }

    public static EntityManager getEntityManager() {
        setEntityManager();
        em.setFlushMode(FlushModeType.AUTO);
        return em;
    }
}
