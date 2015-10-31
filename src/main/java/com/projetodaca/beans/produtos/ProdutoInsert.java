package com.projetodaca.beans.produtos;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Categoria;
import com.projetodaca.entities.Produto;


@ViewScoped
@ManagedBean
public class ProdutoInsert {

	private Fachada fachada;
	private Produto produto;
	private List<Categoria> categorias;

	@PostConstruct
	public void  start(){
		fachada = new Fachada();
		produto = new Produto();
		try {
			categorias = fachada.listCategoria();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public String insertProduto() {

		try {
			produto.setAtivo(true);
			produto.setEstoqueMinimo(100);
			
			fachada.saveProduto(produto);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("newPassword", new FacesMessage("Error: Ao salvar Teste"));
			e.printStackTrace();
		}
		
		FacesContext.getCurrentInstance().addMessage("Mensagem", new FacesMessage("Usuário salvo"));
		
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
	
	

}
