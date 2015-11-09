/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.dao;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.projetodaca.entities.Usuario;

/**
 *
 * @author renan
 */
public class UsuarioDao implements IDAO<Usuario> {

    private EntityManager manager;
    private Conexao con;

    public UsuarioDao() {
        con = new Conexao();
        manager = con.getEntityManager();
    }

    
    public void insert(Usuario usuario) throws Exception {
        try {
            beginTransaction();
            manager.persist(usuario);
            manager.refresh(usuario);
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

    
    public List<Usuario> list() throws Exception {
        List<Usuario> list = new ArrayList<Usuario>();        
        try {
            beginTransaction();
            list = (List<Usuario>) manager.createQuery("SELECT e FROM Usuario e", Usuario.class).getResultList();
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

    public List<Usuario> list(String where) throws Exception {
        List<Usuario> list = new ArrayList<Usuario>();
        try {
            beginTransaction();
            list = (List<Usuario>) manager.createQuery("SELECT e FROM Usuario e " + where, Usuario.class).getResultList();
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

    
    public Usuario getById(int id) throws Exception {
        Usuario usuario = null;
        try {
            beginTransaction();
            usuario = manager.find(Usuario.class, id);
            commitTransaction();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(e.getMessage());
        }finally{
            manager.close();
        }

        return usuario;
    }
    
    public Usuario autenticaUsuario(String login,String senha) throws Exception {
       Usuario usuario = null;
                 
            String where = "where e.login = '"+login+"' AND e.senha = '"+senha+"'";
            ArrayList<Usuario> lista = (ArrayList<Usuario>) list(where);
            if(lista!=null && !lista.isEmpty()){
            	usuario = lista.get(0);
            }         
       

        return usuario;
    }

    
    public void update(Usuario usuario) throws Exception {
        try {
            beginTransaction();             
            manager.merge(usuario);
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

    
    public void delete(Usuario usuario) throws Exception {
        try {
            beginTransaction();
            manager.remove(manager.getReference(Usuario.class, usuario.getId()));
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
