package com.projetodaca.beans.produtos;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.glassfish.api.admin.AccessRequired;

import com.projetodaca.beans.AbstractManageBean;
import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Produto;

@RequestScoped
@Named
public class ProdutoLista extends AbstractManageBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6405394078384105388L;
	@Inject
	private Produto produtoSelecionado;
	private List<Produto> produtos;
	@Inject
	private Fachada fachada;
	private String filtro;

	@PostConstruct
	public void start() {
		filtrar();
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public void excluir(Produto produto) {
		if (produto != null) {

			try {
				fachada.deleteProduto(produto);
				filtrar();
				showFlashMessageInfo("Produto excluido!");
			} catch (Exception e) {	
				showFlashMessageError("Ocorreu um erro ao tentar excluir o produto!");	
				e.printStackTrace();
			}
		}

	}

	public void filtrar() {

		try {
			if (filtro == null)
				filtro = "";
			
			produtos = fachada.listProdutoPorNome(filtro);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produto) {
		this.produtoSelecionado = produto;
	}

}
