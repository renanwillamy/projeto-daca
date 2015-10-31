/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.dao;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.projetodaca.entities.Endereco;

/**
 *
 * @author renan
 */
public class EnderecoDao implements IDAO<Endereco> {

    private EntityManager manager;
    private Conexao con;

    public EnderecoDao() {
        con = new Conexao();
        manager = con.getEntityManager();
    }

    
    public void insert(Endereco endereco) throws Exception {
        try {
            beginTransaction();
            manager.persist(endereco);
            manager.refresh(endereco);
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

    
    public List<Endereco> list() throws Exception {
        List<Endereco> list = new ArrayList<Endereco>();
        try {
            beginTransaction();
            list = (List<Endereco>) manager.createQuery("SELECT e FROM Endereco e", Endereco.class).getResultList();
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

    
    public Endereco getById(int id) throws Exception {
        Endereco endereco = null;
        try {
            beginTransaction();
            endereco = manager.find(Endereco.class, id);
            commitTransaction();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(e.getMessage());
        }finally{
            manager.close();
        }

        return endereco;
    }

    
    public void update(Endereco endereco) throws Exception {
        try {
            beginTransaction();
            manager.merge(endereco);
            commitTransaction();
        } catch (Exception ex) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(ex);
        } finally {
            manager.close();
        }
    }

    
    public void delete(Endereco endereco) throws Exception {
        try {
            beginTransaction();
            manager.remove(manager.getReference(Endereco.class, endereco.getId()));
            commitTransaction();
        } catch (Exception ex) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(ex);
        } finally {
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
