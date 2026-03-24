/**
 * @ (#) JPAUtils.java   1.0     10/3/2026
 * <p>
 * Copyright (c) 2026 IUH. All rights reserved
 */
package com.ngviet291.infracstructor.db;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * @description
 * @author: Nguyen Tran Quoc Viet 
 * @version: 1.0
 * @created: 10/3/2026
 */

public class JPAUtils {
    private static final String PERSISTENCE_UNIT_NAME = "maria-pu";
    private static EntityManagerFactory factory;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return factory;
    }
    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }
    public static void shutdown() {
        if (factory != null) {
            factory.close();
        }
    }
}
