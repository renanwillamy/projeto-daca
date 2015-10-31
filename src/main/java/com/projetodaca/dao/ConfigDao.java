/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.dao;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

import com.projetodaca.entities.Config;

/**
 *
 * @author renan
 */
public class ConfigDao implements IDAO<Config> {

    private EntityManager manager;
    private Conexao con;
    public ConfigDao() {
        con = new Conexao();
        manager = con.getEntityManager();
    }

    public void insert(Config config) throws Exception {
        try {
            beginTransaction();
            manager.persist(config);
            commitTransaction();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(e.getMessage());
        }finally{
            manager.close();
        }
    }

    
    public List<Config> list() throws Exception {
        List<Config> list = new ArrayList<Config>();
        try {
            beginTransaction();
            list = (List<Config>) manager.createQuery("SELECT e FROM Config e", Config.class).getResultList();
            commitTransaction();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(e.getMessage());
        }finally{
            manager.close();
        }
        return list;
    }

    
    public Config getById(int id) throws Exception {
        Config config = null;
        try {
            beginTransaction();
            config = manager.find(Config.class, id);
            commitTransaction();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(e.getMessage());
        }finally{
            manager.close();
        }

        return config;
    }

    
    public void update(Config config) throws Exception {
        try {
            
            beginTransaction();
           manager.merge(config);
            commitTransaction();
        } catch (Exception ex) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(ex);
        }finally{
            manager.close();
        }
    }

    
    public void delete(Config config) throws Exception {
        try {
            beginTransaction();
            manager.remove(manager.getReference(Config.class, config.getId()));
            commitTransaction();
        } catch (Exception ex) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(ex);
        }finally{
            manager.close();
        }
    }

    
    public void beginTransaction() {
        manager = con.getEntityManager();
        manager.getTransaction().begin();
    }

    
    public void commitTransaction() {
        manager.getTransaction().commit();
    }

    
    public void rollBack() {
        manager.getTransaction().rollback();
    }

}
