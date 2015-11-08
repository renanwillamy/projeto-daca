/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.services;

import java.util.List;

import com.projetodaca.dao.ClienteDao;
import com.projetodaca.entities.Cliente;

/**
 *
 * @author renan
 */
public class ClienteService {
    
    private ClienteDao dao;

    public ClienteService() {
       dao = new ClienteDao();
    }
    
    /**Persiste cliente no banco de dados
     * 
     * @param cliente 
     */
    public Cliente save(Cliente cliente) throws Exception{
            dao.insert(cliente);
            return cliente;
    }
    
    public void update(Cliente cliente) throws Exception{
            dao.update(cliente);
    }
    
    public void delete(Cliente cliente) throws Exception{
            dao.delete(cliente);
    }
    
    public List<Cliente> list() throws Exception{
        List<Cliente> listCliente= null;
          listCliente = dao.list();
        return listCliente;
    }
    
    private List<Cliente> list(String where) throws Exception{
        List<Cliente> listCliente= null;
          listCliente = dao.list(where);
        return listCliente;
    }
    
    public Cliente getById(int id) throws Exception{
        Cliente cliente = null;
        cliente =  dao.getById(id);
        return cliente;
    }

	public List<Cliente> listaClientePorNome(String nome) throws Exception {		
		return dao.listaClientePorNome(nome);
	}
    
}
