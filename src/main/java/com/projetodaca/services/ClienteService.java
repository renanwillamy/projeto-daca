/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.services;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import com.projetodaca.dao.ClienteDao;
import com.projetodaca.entities.Cliente;


/**
 *
 * @author renan
 */
public class ClienteService {
    @Inject
    private ClienteDao dao;

    
    
    /**Persiste cliente no banco de dados
     * 
     * @param cliente 
     */
    @Transactional
    public Cliente save(Cliente cliente) throws Exception{
            dao.insert(cliente);
            return cliente;
    }
    @Transactional
    public void update(Cliente cliente) throws Exception{
            dao.update(cliente);
    }
    @Transactional
    public void delete(Cliente cliente) throws Exception{
            dao.delete(cliente);
    }
    @Transactional
    public List<Cliente> list() throws Exception{
        List<Cliente> listCliente= null;
          listCliente = dao.list();
        return listCliente;
    }
    @Transactional
    private List<Cliente> list(String where) throws Exception{
        List<Cliente> listCliente= null;
          listCliente = dao.list(where);
        return listCliente;
    }
    @Transactional
    public Cliente getById(int id) throws Exception{
        Cliente cliente = null;
        cliente =  dao.getById(id);
        return cliente;
    }
    @Transactional
	public List<Cliente> listaClientePorNome(String nome) throws Exception {		
		return dao.listaClientePorNome(nome);
	}
    
}
