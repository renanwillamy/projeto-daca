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
import javax.persistence.PersistenceContext;

import com.projetodaca.entities.Endereco;

/**
 *
 * @author renan
 */
public class EnderecoDao extends AbstractDao<Endereco> {

	
       
    public void insert(Endereco endereco) throws Exception {
    	EntityManager manager = getEntityManager();
        try {           
            manager.persist(endereco);
            manager.refresh(endereco);            
        } catch (Exception e) {          
            throw new Exception(e.getMessage());
        }
    }

    
    public List<Endereco> list() throws Exception {
    	EntityManager manager = getEntityManager();
        List<Endereco> list = new ArrayList<Endereco>();
        try {          
            list = (List<Endereco>) manager.createQuery("SELECT e FROM Endereco e", Endereco.class).getResultList();            
        } catch (Exception e) {           
            throw new Exception(e.getMessage());
        }
        return list;
    }

    
    public Endereco getById(int id) throws Exception {
    	EntityManager manager = getEntityManager();
        Endereco endereco = null;
        try {            
            endereco = manager.find(Endereco.class, id);
            
        } catch (Exception e) {           
            throw new Exception(e.getMessage());
        }
        return endereco;
    }

    
    public void update(Endereco endereco) throws Exception {
    	EntityManager manager = getEntityManager();
        try {           
            manager.merge(endereco);         
        } catch (Exception ex) {            
            throw new Exception(ex);
        } 
    }

    
    public void delete(Endereco endereco) throws Exception {
    	EntityManager manager = getEntityManager();
        try {           
            manager.remove(manager.getReference(Endereco.class, endereco.getId()));            
        } catch (Exception ex) {
            
            throw new Exception(ex);
        } 

    }
    
    

}
