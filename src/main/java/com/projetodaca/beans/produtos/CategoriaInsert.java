package com.projetodaca.beans.produtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;

import com.projetodaca.beans.AbstractManageBean;
import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Categoria;
import com.projetodaca.entities.Fornecedor;
import com.projetodaca.entities.Produto;

@ViewScoped
@ManagedBean
public class CategoriaInsert extends AbstractManageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8559018315064616512L;
	
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
		produto = new Produto();

		try {
			fornecedores = fachada.listFornecedor();
			categorias = fachada.listCategoria();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String insertProduto() {

		try {

			fachada.saveProduto(produto);
			showFlashMessageInfo("Produto Salvo!");
		} catch (Exception e) {
			System.out.println("Erro");
			e.printStackTrace();
			showFlashMessageError("Erro ao tentar salvar produto!");
			return "insert_prod";
		}
	
		return "/index?faces-redirect=true";
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
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

}
