/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.services;

import java.util.List;

import javax.inject.Inject;

import com.projetodaca.core.TransacionalCdi;
import com.projetodaca.dao.UsuarioDao;
import com.projetodaca.entities.Usuario;

/**
 *
 * @author renan
 */
public class UsuarioService {
    @Inject
    private UsuarioDao dao;

    public UsuarioService() {
       
    }
    
    /**Persiste usuario no banco de dados
     * 
     * @param usuario 
     */
    @TransacionalCdi
    public Usuario save(Usuario usuario) throws Exception{
            dao.insert(usuario);
            return usuario;
    }
    
    public void update(Usuario usuario) throws Exception{
            dao.update(usuario);
    }
    
    public void delete(Usuario usuario) throws Exception{
            dao.delete(usuario);
    }
    
    public List<Usuario> list() throws Exception{
        List<Usuario> listUsuario= null;
          listUsuario = dao.list();
        return listUsuario;
    }
    
    private List<Usuario> list(String where) throws Exception{
        List<Usuario> listUsuario= null;
          listUsuario = dao.list(where);
        return listUsuario;
    }
    
    public List<Usuario>listaUsuarioPorNome(String nome) throws Exception{
    	String where = "where e.nome like '%"+nome+"%'";
    	return list(where);
    }
    
    public Usuario getById(int id) throws Exception{
        Usuario usuario = null;
        usuario =  dao.getById(id);
        return usuario;
    }
    
    public Usuario autenticaUsuario(String login,String senha) throws Exception {
    	Usuario usuario = null;
        usuario =  dao.autenticaUsuario(login, senha);
        return usuario;
    }
    
}
