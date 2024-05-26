package com.compass.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.logging.Level;

public class JpaUtil {

    private static EntityManagerFactory entityManagerFactory;

    public static void init() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("exemplo-jpa");
            java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        }
    }

    public static EntityManager getEntityManager() {
        if (entityManagerFactory == null) {
            throw new IllegalStateException("EntityManagerFactory is not initialized. Call JpaUtil.init() first.");
        }
        return entityManagerFactory.createEntityManager();
    }

    public static void close() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}