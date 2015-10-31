/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author renan
 */
public class Conexao {

    private static EntityManagerFactory factory;   

    static {
        if(factory==null)
            factory = Persistence.createEntityManagerFactory("AutomacaoPU");
    }

    public EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

    public static void setFactory(String unitPersistence) {
        Conexao.factory = Persistence.createEntityManagerFactory(unitPersistence);
    }
    
    

}
