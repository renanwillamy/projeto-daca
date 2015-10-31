/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.projetodaca.entities.ItensDoPedido;

/**
 *
 * @author renan
 */
public class ItensDoPedidoDao implements IDAO<ItensDoPedido> {

    private EntityManager manager;
    private Conexao con;

    public ItensDoPedidoDao() {
        con = new Conexao();
        manager = con.getEntityManager();
    }

    
    public void insert(ItensDoPedido itensDoPedido) throws Exception {
        try {
            beginTransaction();
            manager.persist(itensDoPedido);
            manager.refresh(itensDoPedido);
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

    
    public List<ItensDoPedido> list() throws Exception {
        List<ItensDoPedido> list = new ArrayList<ItensDoPedido>();
        try {
            beginTransaction();
            list = (List<ItensDoPedido>) manager.createQuery("SELECT e FROM ItensDoPedido e", ItensDoPedido.class).getResultList();
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

    public List<ItensDoPedido> list(String where) throws Exception {
        List<ItensDoPedido> list = new ArrayList<ItensDoPedido>();
        try {
            beginTransaction();
            list = (List<ItensDoPedido>) manager.createQuery("SELECT e FROM ItensDoPedido e " + where, ItensDoPedido.class).getResultList();
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

    
    public ItensDoPedido getById(int id) throws Exception {
        ItensDoPedido itensDoPedido = null;
        try {
            beginTransaction();
            itensDoPedido = manager.find(ItensDoPedido.class, id);
            commitTransaction();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(e.getMessage());
        }finally{
            manager.close();
        }

        return itensDoPedido;
    }

    
    public void update(ItensDoPedido itensDoPedido) throws Exception {
        try {
            beginTransaction();
            manager.merge(itensDoPedido);
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

    
    public void delete(ItensDoPedido itensDoPedido) throws Exception {
        try {
            beginTransaction();
            manager.remove(manager.getReference(ItensDoPedido.class, itensDoPedido.getId()));
            commitTransaction();
        } catch (Exception ex) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception("Mensagem: "+ex.getMessage()+" causa: "+ex.getCause());
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
