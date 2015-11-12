/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.dao;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.projetodaca.entities.Contato;

/**
 *
 * @author renan
 */
public class ContatoDao implements IDAO<Contato> {

    private EntityManager manager;
   

    public ContatoDao() {        
       
    }

    
    public void insert(Contato contato) throws Exception {
        try {
            beginTransaction();
            manager.persist(contato);
            manager.refresh(contato);
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

    
    public List<Contato> list() throws Exception {
        List<Contato> list = new ArrayList<Contato>();
        try {
            beginTransaction();
            list = (List<Contato>) manager.createQuery("SELECT e FROM Contato e", Contato.class).getResultList();
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

    
    public Contato getById(int id) throws Exception {
        Contato contato = null;
        try {
            beginTransaction();
            contato = manager.find(Contato.class, id);
            commitTransaction();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(e.getMessage());
        }finally{
            manager.close();
        }

        return contato;
    }

    
    public void update(Contato contato) throws Exception {
        try {
           
            beginTransaction();
            manager.merge(contato);
            commitTransaction();
        } catch (Exception ex) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(ex.getMessage()+"\n"+ex.getCause());
        }finally{
            manager.close();
        }
    }

    
    public void delete(Contato contato) throws Exception {
        try {
            beginTransaction();
            manager.remove(manager.getReference(Contato.class, contato.getID()));            
            commitTransaction();
        } catch (Exception ex) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            ex.printStackTrace();
            throw new Exception(ex);
        }finally{
            manager.close();
        }
    }

    
    public void beginTransaction() {
       
        manager.getTransaction().begin();
    }

    
    public void commitTransaction() {
        manager.getTransaction().commit();
    }

    
    public void rollBack() {
        manager.getTransaction().rollback();
    }

}
