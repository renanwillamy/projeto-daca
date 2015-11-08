/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.dao;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import com.projetodaca.entities.Cliente;

/**
 *
 * @author renan
 */
public class ClienteDao implements IDAO<Cliente> {

    private EntityManager manager;
    private Conexao con;

    public ClienteDao() {
        con = new Conexao();
        manager = con.getEntityManager();
    }

    
    public void insert(Cliente cliente) throws Exception {
        try {
            beginTransaction();
            manager.persist(cliente);
            manager.refresh(cliente);
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

    
    public List<Cliente> list() throws Exception {
        List<Cliente> list = new ArrayList<Cliente>();        
        try {
            beginTransaction();
            list = (List<Cliente>) manager.createQuery("SELECT e FROM Cliente e", Cliente.class).getResultList();
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

    public List<Cliente> list(String where) throws Exception {
        List<Cliente> list = new ArrayList<Cliente>();
        try {
            beginTransaction();
            list = (List<Cliente>) manager.createQuery("SELECT e FROM Cliente e " + where, Cliente.class).getResultList();
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

    
    public Cliente getById(int id) throws Exception {
        Cliente cliente = null;
        try {
            beginTransaction();
            cliente = manager.find(Cliente.class, id);
            commitTransaction();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(e.getMessage());
        }finally{
            manager.close();
        }

        return cliente;
    }

    
    public void update(Cliente cliente) throws Exception {
        try {
            beginTransaction();             
            manager.merge(cliente);
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

    
    public void delete(Cliente cliente) throws Exception {
        try {
            beginTransaction();
            manager.remove(manager.getReference(Cliente.class, cliente.getId()));
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


	public List<Cliente> listaClientePorNome(String nome) throws Exception {
		String where = "where e.nome like '%"+nome+"%'";
		return list(where);
	}

}
