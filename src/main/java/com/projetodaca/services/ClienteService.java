/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.services;

import java.util.List;

import javax.inject.Inject;

import com.projetodaca.dao.ClienteDao;
import com.projetodaca.entities.Cliente;
import com.projetodaca.utils.TransacionalCdi;

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
    @TransacionalCdi
    public Cliente save(Cliente cliente) throws Exception{
            dao.insert(cliente);
            return cliente;
    }
    @TransacionalCdi
    public void update(Cliente cliente) throws Exception{
            dao.update(cliente);
    }
    @TransacionalCdi
    public void delete(Cliente cliente) throws Exception{
            dao.delete(cliente);
    }
    @TransacionalCdi
    public List<Cliente> list() throws Exception{
        List<Cliente> listCliente= null;
          listCliente = dao.list();
        return listCliente;
    }
    @TransacionalCdi
    private List<Cliente> list(String where) throws Exception{
        List<Cliente> listCliente= null;
          listCliente = dao.list(where);
        return listCliente;
    }
    @TransacionalCdi
    public Cliente getById(int id) throws Exception{
        Cliente cliente = null;
        cliente =  dao.getById(id);
        return cliente;
    }
    @TransacionalCdi
	public List<Cliente> listaClientePorNome(String nome) throws Exception {		
		return dao.listaClientePorNome(nome);
	}
    
}
