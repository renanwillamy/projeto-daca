package com.projetodaca.beans.categorias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projetodaca.beans.AbstractManageBean;
import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Categoria;
import com.projetodaca.entities.Fornecedor;
import com.projetodaca.entities.Produto;

@ViewScoped
@Named
public class CategoriaInsert extends AbstractManageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8559018315064616512L;
	@Inject
	private Fachada fachada;
	@Inject
	private Produto produto;
	private List<Categoria> categorias;
	@Inject
	private Fornecedor fornecedor;
	@Inject
	private Categoria categoria;

	@PostConstruct
	public void start() {
		try {			
			atualiza();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String insertCategoria() {
		try {

			fachada.saveCategoria(categoria);		
			atualiza();
			showFlashMessageInfo("Categoria Salva!");
		} catch (Exception e) {			
			e.printStackTrace();
			if(e.toString().contains("ConstraintViolationException")){
				showFlashMessageError("Essa Categoria já existe!");
			}else{
			showFlashMessageError("Erro ao tentar salvar a categoria!");
			}
			return "insert_cat";
		}	
		return "/insert_cat?faces-redirect=true";
	}
	
	public void excluir(Categoria categoria){
		try {
			fachada.deleteCategoria(categoria);
			atualiza();
			showFlashMessageInfo("Categoria excluida!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void atualiza() throws Exception {
		categorias = fachada.listCategoria();
		
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
	


}
