/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.services;

import java.util.List;

import com.projetodaca.dao.ProdutoDao;
import com.projetodaca.entities.Produto;

/**
 *
 * @author renan
 */
public class ProdutoService {   
    
    private ProdutoDao dao;

    public ProdutoService() {
       dao = new ProdutoDao();
    }
    
    /**Persiste produto no banco de dados
     * 
     * @param produto 
     */
    public Produto save(Produto produto) throws Exception{
            dao.insert(produto);
            return produto;
    }
    
    public void update(Produto produto) throws Exception{
            dao.update(produto);
    }
    
    public void delete(Produto produto) throws Exception{
            dao.delete(produto);
    }
    
    public List<Produto> list() throws Exception{
        List<Produto> listProduto= null;
          listProduto = dao.list();
        return listProduto;
    }   
    
    public List<Produto> list(String where) throws Exception{
        List<Produto> listProduto= null;
          listProduto = dao.list(where);
        return listProduto;
    }
    
    
    public Produto getById(int id) throws Exception{
        Produto produto = null;
        produto =  dao.getById(id);
        return produto;
    }
    
}
