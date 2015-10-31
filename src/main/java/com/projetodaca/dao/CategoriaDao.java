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
public class CategoriaDao implements IDAO<Categoria> {

    private EntityManager manager;
    private Conexao con;

    public CategoriaDao() {
        con = new Conexao();
        manager = con.getEntityManager();
    }

    
    public void insert(Categoria categoria) throws Exception {        
        try {
            beginTransaction();
            manager.persist(categoria);
            manager.refresh(categoria);
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

    
    public List<Categoria> list() throws Exception {
        List<Categoria> lista = null;
        try {
            beginTransaction();
            lista = manager.createQuery("SELECT e FROM Categoria e", Categoria.class).getResultList();
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

    public List<Categoria> list(String where) throws Exception {
        List<Categoria> list = new ArrayList<Categoria>();
        try {
            beginTransaction();
            list = (List<Categoria>) manager.createQuery("SELECT e FROM Categoria e " + where, Categoria.class).getResultList();
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

    
    public Categoria getById(int id) throws Exception {
        Categoria categoria = null;
        try {
            beginTransaction();
            categoria = manager.find(Categoria.class, id);
            commitTransaction();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(e.getMessage());
        }finally{
            manager.close();
        } 

        return categoria;
    }

    
    public void update(Categoria categoria) throws Exception {
        try {
            Categoria cat = getById(categoria.getId());
            beginTransaction();
            Query query =  manager.createQuery("UPDATE Categoria cat set cat.categoriaPai = :catPai , cat.nome = :nome where cat.id =:id");
            query.setParameter("catPai", categoria.getCategoriaPai());
            query.setParameter("nome", categoria.getNome());
            query.setParameter("id", categoria.getId());
            query.executeUpdate();            
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

    
    public void delete(Categoria categoria) throws Exception {
        try {
            beginTransaction();
            manager.remove(manager.getReference(Categoria.class, categoria.getId()));
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
