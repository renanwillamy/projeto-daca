/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.services;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

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
    @Transactional
    public Usuario save(Usuario usuario) throws Exception{
    		usuario.setSenha(criptografarSenha(usuario.getSenha()));
            dao.insert(usuario);
            return usuario;
    }
    @Transactional
    public void update(Usuario usuario) throws Exception{
            dao.update(usuario);
    }
    @Transactional
    public void delete(Usuario usuario) throws Exception{
            dao.delete(usuario);
    }
    @Transactional
    public List<Usuario> list() throws Exception{
        List<Usuario> listUsuario= null;
          listUsuario = dao.list();
        return listUsuario;
    }
    @Transactional
    private List<Usuario> list(String where) throws Exception{
        List<Usuario> listUsuario= null;
          listUsuario = dao.list(where);
        return listUsuario;
    }
   
    public List<Usuario>listaUsuarioPorNome(String nome) throws Exception{
    	String where = "where e.nome like '%"+nome+"%'";
    	return list(where);
    }
    @Transactional
    public Usuario getById(int id) throws Exception{
        Usuario usuario = null;
        usuario =  dao.getById(id);
        return usuario;
    }
    @Transactional
    public Usuario autenticaUsuario(String login,String senha) throws Exception {
    	Usuario usuario = null;
        usuario =  dao.autenticaUsuario(login, senha);
        return usuario;
    }
    
    /**
	 * Método que criptografa uma dada senha usando o método hash SHA-256.
	 * 
	 * @param password
	 *            senha a ser criptografada
	 * @return senha criptografada
	 * @throws DacaServiceException
	 *             lançada caso ocorra algum erro durante o processo
	 */
	public  String criptografarSenha(String password) throws Exception {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes("UTF-8"));
			byte[] digest = md.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			String output = bigInt.toString(16);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("Não foi possível criptografar a senha!");
		} catch (UnsupportedEncodingException e) {
			throw new Exception("Não foi possível criptografar a senha!");
		}
	}

	public Usuario getUsuarioPorLogin(String login) throws Exception {
		Usuario usuario = dao.getUsuarioPorLogin(login);
		return usuario;
	}
    
}
