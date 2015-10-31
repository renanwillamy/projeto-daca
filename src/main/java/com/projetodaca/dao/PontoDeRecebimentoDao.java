/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.dao;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.projetodaca.entities.PontoDeRecebimento;

/**
 *
 * @author renan
 */
public class PontoDeRecebimentoDao implements IDAO<PontoDeRecebimento> {

    private EntityManager manager;
    private Conexao con;

    public PontoDeRecebimentoDao() {
        con = new Conexao();
        manager = con.getEntityManager();
    }

    
    public void insert(PontoDeRecebimento pontoDeRecebimento) throws Exception {        
        try {
            beginTransaction();
            manager.persist(pontoDeRecebimento);
            manager.refresh(pontoDeRecebimento);
            commitTransaction();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(e);
        }finally{
            manager.close();
        } 

    }

    
    public List<PontoDeRecebimento> list() throws Exception {
        List<PontoDeRecebimento> lista = null;
        try {
            beginTransaction();
            lista = manager.createQuery("SELECT e FROM PontoDeRecebimento e", PontoDeRecebimento.class).getResultList();
            commitTransaction();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(e);
        }finally{
            manager.close();
        } 

        return lista;
    }

    public List<PontoDeRecebimento> list(String where) throws Exception {
        List<PontoDeRecebimento> list = new ArrayList<PontoDeRecebimento>();
        try {
            beginTransaction();
            list = (List<PontoDeRecebimento>) manager.createQuery("SELECT e FROM PontoDeRecebimento e " + where, PontoDeRecebimento.class).getResultList();
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

    
    public PontoDeRecebimento getById(int id) throws Exception {
        PontoDeRecebimento pontoDeRecebimento = null;
        try {
            beginTransaction();
            pontoDeRecebimento = manager.find(PontoDeRecebimento.class, id);
            commitTransaction();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(e.getMessage());
        }finally{
            manager.close();
        } 

        return pontoDeRecebimento;
    }

    
    public void update(PontoDeRecebimento pontoDeRecebimento) throws Exception {
        try {
            beginTransaction();
            manager.merge(pontoDeRecebimento);
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

    
    public void delete(PontoDeRecebimento pontoDeRecebimento) throws Exception {
        try {
            beginTransaction();
            manager.remove(manager.getReference(PontoDeRecebimento.class, pontoDeRecebimento.getId()));
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
