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

import com.projetodaca.entities.Categoria;

/**
 *
 * @author renan
 */
public class CategoriaDao extends AbstractDao<Categoria>{       

     
    public void insert(Categoria categoria) throws Exception {   
    	EntityManager manager = getEntityManager();
        try {           
            manager.persist(categoria);
            manager.refresh(categoria);
            
        } catch (Exception e) {           
            throw new Exception(e);
        }

    }

    
    public List<Categoria> list() throws Exception {
    	EntityManager manager = getEntityManager();
        List<Categoria> lista = null;
        try {            
            lista = manager.createQuery("SELECT e FROM Categoria e", Categoria.class).getResultList();           
        } catch (Exception e) {           
            throw new Exception(e);
        }

        return lista;
    }

    public List<Categoria> list(String where) throws Exception {
    	EntityManager manager = getEntityManager();
        List<Categoria> list = new ArrayList<Categoria>();
        try {           
            list = (List<Categoria>) manager.createQuery("SELECT e FROM Categoria e " + where, Categoria.class).getResultList();
           
        } catch (Exception e) {           
            throw new Exception(e.getMessage());
        }
        return list;
    }

    
    public Categoria getById(int id) throws Exception {
    	EntityManager manager = getEntityManager();
        Categoria categoria = null;
        try {           
            categoria = manager.find(Categoria.class, id);           
        } catch (Exception e) {            
            throw new Exception(e.getMessage());
        }
        return categoria;
    }

    
    public void update(Categoria categoria) throws Exception {
    	EntityManager manager = getEntityManager();
        try {
            Categoria cat = getById(categoria.getId());      
            Query query =  manager.createQuery("UPDATE Categoria cat set cat.categoriaPai = :catPai , cat.nome = :nome where cat.id =:id");
            query.setParameter("catPai", categoria.getCategoriaPai());
            query.setParameter("nome", categoria.getNome());
            query.setParameter("id", categoria.getId());
            query.executeUpdate();            
            
        } catch (Exception ex) {            
            throw new Exception(ex);
        }
    }

    
    public void delete(Categoria categoria) throws Exception {
    	EntityManager manager = getEntityManager();
        try {            
            manager.remove(manager.getReference(Categoria.class, categoria.getId()));           
        } catch (Exception ex) {            
            throw new Exception(ex);
        }        
    }  
   

}
