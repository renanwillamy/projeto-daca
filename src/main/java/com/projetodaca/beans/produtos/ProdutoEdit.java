package com.projetodaca.beans.produtos;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import com.projetodaca.beans.AbstractManageBean;
import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Categoria;
import com.projetodaca.entities.Fornecedor;
import com.projetodaca.entities.Produto;

@ManagedBean
public class ProdutoEdit extends AbstractManageBean {
	private Fachada fachada;
	private Produto produto;
	private List<Categoria> categorias;
	private Fornecedor fornecedor;
	private Categoria categoria;
	private List<Fornecedor> fornecedores;
	private List<SelectItem> selectList;
	private List<SelectItem> selectListCat;
	
	@PostConstruct
	public void start() {
		fachada = new Fachada();
		if(produto==null){
			produto = new Produto();
		}
		try {
			fornecedores = fachada.listFornecedor();
			categorias = fachada.listCategoria();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void filtrar(){
		
		
		
	}
		

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public String updateProduto(){
		try {
			fachada.updateProduto(produto);
			showFlashMessageInfo("Produto Alterado!");
		} catch (Exception e) {
			showFlashMessageError("Ocorreu um erro ao tentar alterar o produto!");
			e.printStackTrace();
			return "prod_edit";
		}
		return "lista_prod?faces-redirect=true";
	}
	
	public List<SelectItem> getSelectList() {
		if (selectList == null) {
			selectList = new ArrayList<SelectItem>();

			if (fornecedores != null) {
				SelectItem item;
				for (Fornecedor forn : fornecedores) {
					item = new SelectItem(forn, forn.getNomeFantasia());
					selectList.add(item);
				}
			}

		}

		return selectList;
	}

	public List<SelectItem> getSelectListCat() {
		if (selectListCat == null) {
			selectListCat = new ArrayList<SelectItem>();

			if (categorias != null) {
				SelectItem item;
				for (Categoria cat : categorias) {
					item = new SelectItem(cat, cat.getNome());
					selectListCat.add(item);
				}
			}

		}

		return selectListCat;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	

}
