/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.services;

import java.util.List;

import javax.inject.Inject;

import com.projetodaca.dao.ProdutoDao;
import com.projetodaca.entities.Produto;
import com.projetodaca.utils.TransacionalCdi;

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
    @TransacionalCdi
    public Produto save(Produto produto) throws Exception{
            dao.insert(produto);
            return produto;
    }
    @TransacionalCdi
    public void update(Produto produto) throws Exception{
            dao.update(produto);
    }
    @TransacionalCdi
    public void delete(Produto produto) throws Exception{
            dao.delete(produto);
    }
    @TransacionalCdi
    public List<Produto> list() throws Exception{
        List<Produto> listProduto= null;
          listProduto = dao.list();
        return listProduto;
    }   
    @TransacionalCdi
    public List<Produto> listaProdutoPorNome(String nome) throws Exception{
        List<Produto> listProduto= null;
          listProduto = dao.listaProdutoPorNome(nome);
        return listProduto;
    }   
   
    
    @TransacionalCdi
    public Produto getById(int id) throws Exception{
        Produto produto = null;
        produto =  dao.getById(id);
        return produto;
    }
    @TransacionalCdi
	public List<Produto> listaProdutoAtivos() throws Exception {
    	   List<Produto> listProduto= null;
           listProduto = dao.listaProdutoAtivos();
         return listProduto;
    	
	}
    
}
