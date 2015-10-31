package com.projetodaca.beans.produtos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.projetodaca.entities.Produto;
import com.projetodaca.services.ProdutoService;


@ViewScoped
@ManagedBean
public class ProdutoInsert {

	private ProdutoService prodService = new ProdutoService();
	private Produto produto = new Produto();

	
	
	public String insertProduto() {

		try {
			produto.setAtivo(true);
			produto.setEstoqueMinimo(100);
			
			prodService.save(produto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "./index.xhtml?faces-redirect=true";
	}



	public Produto getProduto() {
		return produto;
	}



	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	

}
