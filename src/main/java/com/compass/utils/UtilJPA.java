package com.compass.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.Serial;
import java.io.Serializable;

public class UtilJPA implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private static EntityManagerFactory manager;

    public UtilJPA(){
        if(manager == null){
            manager = Persistence.createEntityManagerFactory("jpa_conf");
        }
    }

    private static EntityManager getManager(){
        return manager.createEntityManager();
    }

    private static void close(){
        manager.close();
    }
}
