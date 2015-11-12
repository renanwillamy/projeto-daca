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
public class ClienteDao extends AbstractDao<Cliente>{
   
   
    public ClienteDao() {
    
    }

    
    public void insert(Cliente cliente) throws Exception {
    	EntityManager em = getEntityManager();
        try {
   
            em.persist(cliente);
            em.refresh(cliente);
 
        } catch (Exception e) {
            
            throw new Exception(e.getMessage());
        }
    }

    
    public List<Cliente> list() throws Exception {
    	EntityManager em = getEntityManager();
        List<Cliente> list = new ArrayList<Cliente>();        
        try {
         
            list = (List<Cliente>) em.createQuery("SELECT e FROM Cliente e", Cliente.class).getResultList();
     
        } catch (Exception e) {
          
            throw new Exception(e.getMessage());
        }
        return list;
    }

    public List<Cliente> list(String where) throws Exception {
    	EntityManager em = getEntityManager();
        List<Cliente> list = new ArrayList<Cliente>();
        try {

            list = (List<Cliente>) em.createQuery("SELECT e FROM Cliente e " + where, Cliente.class).getResultList();
      
        } catch (Exception e) {          
            throw new Exception(e.getMessage());
        }
        return list;
    }

    
    public Cliente getById(int id) throws Exception {
    	EntityManager em = getEntityManager();
        Cliente cliente = null;
        try {
         
            cliente = em.find(Cliente.class, id);
  
        } catch (Exception e) {
         
            throw new Exception(e.getMessage());
        }

        return cliente;
    }

    
    public void update(Cliente cliente) throws Exception {
    	EntityManager em = getEntityManager();
        try {
                     
            em.merge(cliente);
     
        } catch (Exception ex) {
        
            throw new Exception(ex);
        }
    }

    
    public void delete(Cliente cliente) throws Exception {
    	EntityManager em = getEntityManager();
        try {

            em.remove(em.getReference(Cliente.class, cliente.getId()));
  
        } catch (Exception ex) {
              throw new Exception(ex);
        }
    }


	public List<Cliente> listaClientePorNome(String nome) throws Exception {
		String where = "where e.nome like '%"+nome+"%'";
		return list(where);
	}

}
