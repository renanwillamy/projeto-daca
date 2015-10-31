package com.projetodaca.beans.produtos;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.projetodaca.entities.Produto;
import com.projetodaca.services.ProdutoService;

@ManagedBean
public class ProdutoLista {

	private List<Produto> produtos;
	private ProdutoService service;

	@PostConstruct
	public void start(){
	service = new ProdutoService();
		try {
			produtos = service.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	
	
}
