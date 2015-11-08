/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.services;

import java.util.List;

import com.projetodaca.dao.FornecedorDao;
import com.projetodaca.entities.Fornecedor;

/**
 *
 * @author renan
 */
public class FornecedorService {
    
     
    private FornecedorDao dao;

    public FornecedorService() {
       dao = new FornecedorDao();
    }
    
    /**Persiste fornecedor no banco de dados
     * 
     * @param fornecedor 
     */
    public Fornecedor save(Fornecedor fornecedor) throws Exception{
            dao.insert(fornecedor);
            return fornecedor;
    }
    
    public void update(Fornecedor fornecedor) throws Exception{
            dao.update(fornecedor);
    }
    
    public void delete(Fornecedor fornecedor) throws Exception{
            dao.delete(fornecedor);
    }
    
    public List<Fornecedor> list() throws Exception{
        List<Fornecedor> listFornecedor= null;
          listFornecedor = dao.list();
        return listFornecedor;
    }
    
    public List<Fornecedor> listaFornecedorPorNomeFantasia(String nomeFantasia) throws Exception{
        List<Fornecedor> listFonecedor= null;
          listFonecedor = dao.listaFornecedorPorNomeFantasia(nomeFantasia);
        return listFonecedor;
    }
    
    public Fornecedor getById(int id) throws Exception{
        Fornecedor fornecedor = null;
        fornecedor =  dao.getById(id);
        return fornecedor;
    }
    
}
