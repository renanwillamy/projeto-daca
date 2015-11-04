/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.dao;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.projetodaca.entities.Produto;

/**
 *
 * @author renan
 */
public class ProdutoDao implements IDAO<Produto> {

    private EntityManager manager;
    private Conexao con;

    public ProdutoDao() {
        con = new Conexao();
        manager = con.getEntityManager();
    }

    
    public void insert(Produto produto) throws Exception {
        try {
            beginTransaction();
            manager.persist(produto);
            manager.refresh(produto);
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

    
    public List<Produto> list() throws Exception {
        List<Produto> list = new ArrayList<Produto>();
        try {
            beginTransaction();
            list = (List<Produto>) manager.createQuery("SELECT e FROM Produto e", Produto.class).getResultList();
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

    private List<Produto> list(String where) throws Exception {
        List<Produto> list = new ArrayList<Produto>();
        try {
            beginTransaction();
            list = (List<Produto>) manager.createQuery("SELECT e FROM Produto e " + where, Produto.class).getResultList();
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
    
    public List<Produto> listaProdutoPorNome(String nome) throws Exception {
    	String where = "WHERE e.nome like '%" + nome + "%'";
    	return list(where);
    }

    
    public Produto getById(int id) throws Exception {
        Produto produto = null;
        try {
            beginTransaction();
            produto = manager.find(Produto.class, id);
            commitTransaction();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(e.getMessage());
        }finally{
            manager.close();
        }

        return produto;
    }

    
    public void update(Produto produto) throws Exception {
        try {           
            beginTransaction();
            manager.merge(produto);
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

    
    public void delete(Produto produto) throws Exception {
        try {
            beginTransaction();
            manager.remove(manager.getReference(Produto.class, produto.getId()));
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
