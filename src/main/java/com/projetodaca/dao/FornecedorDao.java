/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.projetodaca.entities.Fornecedor;

/**
 *
 * @author renan
 */
public class FornecedorDao implements IDAO<Fornecedor> {

    private EntityManager manager;
    private Conexao con;

    public FornecedorDao() {
        con = new Conexao();
        manager = con.getEntityManager();
    }

    
    public void insert(Fornecedor fornecedor) throws Exception {
        try {
            beginTransaction();
            manager.persist(fornecedor);
            manager.refresh(fornecedor);
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

    public List<Fornecedor> list(String where) throws Exception {
        List<Fornecedor> list = new ArrayList<Fornecedor>();
        try {
            beginTransaction();
            list = (List<Fornecedor>) manager.createQuery("SELECT e FROM Fornecedor e " + where, Fornecedor.class).getResultList();
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

    
    public List<Fornecedor> list() throws Exception {
        List<Fornecedor> list = new ArrayList<Fornecedor>();
        try {
            beginTransaction();
            list = (List<Fornecedor>) manager.createQuery("SELECT e FROM Fornecedor e", Fornecedor.class).getResultList();
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

    
    public Fornecedor getById(int id) throws Exception {
        Fornecedor fornecedor = null;
        try {
            beginTransaction();
            fornecedor = manager.find(Fornecedor.class, id);
            commitTransaction();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(e.getMessage());
        }finally{
            manager.close();
        }

        return fornecedor;
    }

    
    public void update(Fornecedor fornecedor) throws Exception {
        try {
            beginTransaction();
            manager.merge(fornecedor);
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

    
    public void delete(Fornecedor fornecedor) throws Exception {
        try {
            beginTransaction();
            manager.remove(manager.getReference(Fornecedor.class, fornecedor.getId()));
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
