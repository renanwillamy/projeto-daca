/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.services;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import com.projetodaca.dao.ProdutoDao;
import com.projetodaca.entities.Produto;


/**
 *
 * @author renan
 */
public class ProdutoService {   
    @Inject
    private ProdutoDao dao;
   
    
    /**Persiste produto no banco de dados
     * 
     * @param produto 
     */
    @Transactional
    public Produto save(Produto produto) throws Exception{
            dao.insert(produto);
            return produto;
    }
    @Transactional
    public void update(Produto produto) throws Exception{
            dao.update(produto);
    }
    @Transactional
    public void delete(Produto produto) throws Exception{
            dao.delete(produto);
    }
    @Transactional
    public List<Produto> list() throws Exception{
        List<Produto> listProduto= null;
          listProduto = dao.list();
        return listProduto;
    }   
    @Transactional
    public List<Produto> listaProdutoPorNome(String nome) throws Exception{
        List<Produto> listProduto= null;
          listProduto = dao.listaProdutoPorNome(nome);
        return listProduto;
    }   
   
    
    @Transactional
    public Produto getById(int id) throws Exception{
        Produto produto = null;
        produto =  dao.getById(id);
        return produto;
    }
    @Transactional
	public List<Produto> listaProdutoAtivos() throws Exception {
    	   List<Produto> listProduto= null;
           listProduto = dao.listaProdutoAtivos();
         return listProduto;
    	
	}
    
}
