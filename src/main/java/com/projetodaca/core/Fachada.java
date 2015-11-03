package com.projetodaca.core;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;

import com.projetodaca.entities.Categoria;
import com.projetodaca.entities.Fornecedor;
import com.projetodaca.entities.Produto;
import com.projetodaca.services.CategoriaService;
import com.projetodaca.services.FornecedorService;
import com.projetodaca.services.ProdutoService;
import com.projetodaca.services.Util;

public class Fachada implements Serializable{

	/**
	 * Persiste produto no banco de dados
	 * 
	 * @param produto
	 */
	public Produto saveProduto(Produto produto) throws Exception {
		ProdutoService service = new ProdutoService();
		Produto prod = service.save(produto);
		service = null;
		return prod;
	}

	public void updateProduto(Produto produto) throws Exception {
		ProdutoService service = new ProdutoService();
		service.update(produto);
		service = null;
	}

	public void deleteProduto(Produto produto) throws Exception {
		ProdutoService service = new ProdutoService();
		service.delete(produto);
		service = null;
	}

	public List<Produto> listProduto() throws Exception {
		List<Produto> listProduto = null;
		ProdutoService service = new ProdutoService();
		listProduto = service.list();
		service = null;
		return listProduto;
	}

	public List<Produto> listProduto(String where) throws Exception {
		List<Produto> listProduto = null;
		ProdutoService service = new ProdutoService();
		listProduto = service.list(where);
		return listProduto;
	}

	public Produto getProdutoById(int id) throws Exception {
		Produto produto = null;
		ProdutoService service = new ProdutoService();
		produto = service.getById(id);
		service = null;
		return produto;
	}

	public Categoria saveCategoria(Categoria categoria) throws Exception {
		CategoriaService service = new CategoriaService();
		Categoria cat = service.save(categoria);
		service = null;
		return cat;
	}

	public void updateCategoria(Categoria categoria) throws Exception {
		CategoriaService service = new CategoriaService();
		service.update(categoria);
		service = null;
	}

	public void deleteCategoria(Categoria categoria) throws Exception {
		CategoriaService service = new CategoriaService();
		service.delete(categoria);
		service = null;
	}

	public List<Categoria> listCategoria() throws Exception {
		List<Categoria> listCategoria = null;
		CategoriaService service = new CategoriaService();
		listCategoria = service.list();
		service = null;
		return listCategoria;
	}

	public List<Categoria> listCategoria(String where) throws Exception {

		List<Categoria> listCategoria = null;
		CategoriaService service = new CategoriaService();
		listCategoria = service.list(where);
		service = null;
		return listCategoria;
	}
	
	public Categoria getCategoriaById(int id) throws Exception{
		CategoriaService service = new CategoriaService();
		Categoria cat = service.getById(id);
		service = null;
		return cat;		
	}
	
	 /**Persiste fornecedor no banco de dados
     * 
     * @param fornecedor 
     */
    public Fornecedor saveFornecedor(Fornecedor fornecedor) throws Exception{
    	FornecedorService service  = new FornecedorService();
    	fornecedor = service.save(fornecedor);
    	service = null;
		return fornecedor;
    }
    
    public void updateFornecedor(Fornecedor fornecedor) throws Exception{
    	FornecedorService service  = new FornecedorService();
    	service.update(fornecedor);
    	service= null;
    }
    
    public void deleteFornecedor(Fornecedor fornecedor) throws Exception{
    	FornecedorService service  = new FornecedorService();
    	service.delete(fornecedor);
    	service= null;
    }
    
    public List<Fornecedor> listFornecedor() throws Exception{
        List<Fornecedor> listFornecedor= null;
        FornecedorService service  = new FornecedorService();
    	listFornecedor = service.list();
    	service= null;
    	return listFornecedor;
    }
    
    public List<Fornecedor> listFornecedor(String where) throws Exception{
    	 List<Fornecedor> listFornecedor= null;
         FornecedorService service  = new FornecedorService();
     	listFornecedor = service.list(where);
     	service= null;
     	return listFornecedor;
    }
    
    public Fornecedor getFornecedorById(int id) throws Exception{
        Fornecedor fornecedor = null;
        FornecedorService service  = new FornecedorService();
        fornecedor = service.getById(id);
        service = null;
        return fornecedor;
    }
    public static void addFlashMessage(FacesMessage.Severity facesMessage, String message) {
    	Util util = new Util();
    	util.addFlashMessage(facesMessage, message);
    	util = null;
    	
    }

}
