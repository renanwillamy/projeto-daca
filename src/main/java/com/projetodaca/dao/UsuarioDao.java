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
public class UsuarioDao extends AbstractDao<Usuario> {

    public UsuarioDao() {
      
    }

    
    public void insert(Usuario usuario) throws Exception {
    	EntityManager em = getEntityManager();
    	try {
           
            em.persist(usuario);
            em.refresh(usuario);
            
        } catch (Exception e) {
            
            throw new Exception(e.toString());
        }
    }

    
    public List<Usuario> list() throws Exception {
    	EntityManager em = getEntityManager();
        List<Usuario> list = new ArrayList<Usuario>();        
        try {
           
            list = (List<Usuario>) em.createQuery("SELECT e FROM Usuario e", Usuario.class).getResultList();
            
        } catch (Exception e) {
            
            throw new Exception(e.getMessage());
        }
        return list;
    }

    public List<Usuario> list(String where) throws Exception {
    	EntityManager em = getEntityManager();
        List<Usuario> list = new ArrayList<Usuario>();
        try {           
            list = (List<Usuario>) em.createQuery("SELECT e FROM Usuario e " + where, Usuario.class).getResultList();
            
        } catch (Exception e) {            
            throw new Exception(e.getMessage());
        }
        return list;
    }

    
    public Usuario getById(int id) throws Exception {
        Usuario usuario = null;
        EntityManager em = getEntityManager();
        try {           
            usuario = em.find(Usuario.class, id);            
        } catch (Exception e) {            
            throw new Exception(e.getMessage());
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
    	EntityManager em = getEntityManager();
        try {
                        
            em.merge(usuario);
            
        } catch (Exception ex) {
            
            throw new Exception(ex);
        }
    }

    
    public void delete(Usuario usuario) throws Exception {
    	EntityManager em = getEntityManager();
        try {
           
            em.remove(em.getReference(Usuario.class, usuario.getId()));
            
        } catch (Exception ex) {  
            throw new Exception(ex);
        }
    }
  
   

}
