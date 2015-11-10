/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.dao;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.projetodaca.entities.Empresa;

/**
 *
 * @author renan
 */
public class EmpresaDao implements IDAO<Empresa> {

    private EntityManager manager;
    

    public EmpresaDao() {
       
    }

    
    public void insert(Empresa empresa) throws Exception {
        try {
            beginTransaction();
            manager.persist(empresa);
            manager.refresh(empresa);
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

    
    public List<Empresa> list() throws Exception {
        List<Empresa> list = new ArrayList<Empresa>();
        try {
            beginTransaction();
            list = (List<Empresa>) manager.createQuery("SELECT e FROM Empresa e", Empresa.class).getResultList();
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
    public List<Empresa> list(String where) throws Exception {
        List<Empresa> list = new ArrayList<Empresa>();
        try {
            beginTransaction();
            list = (List<Empresa>) manager.createQuery("SELECT e FROM Empresa e "+where, Empresa.class).getResultList();
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

    
    public Empresa getById(int id) throws Exception {
        Empresa empresa = null;
        try {
            beginTransaction();
            empresa = manager.find(Empresa.class, id);
            commitTransaction();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(e.getMessage());
        }finally{
            manager.close();
        }

        return empresa;
    }

    
    public void update(Empresa empresa) throws Exception {
        try {
            beginTransaction();
            manager.merge(empresa);
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

    
    public void delete(Empresa empresa) throws Exception {
        try {
            beginTransaction();
            manager.remove(manager.getReference(Empresa.class, empresa.getId()));
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
     
        manager.getTransaction().begin();
    }

    
    public void commitTransaction() {
        manager.getTransaction().commit();
    }

    
    public void rollBack() {
        manager.getTransaction().rollback();
    }

}
