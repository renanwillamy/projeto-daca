package com.projetodaca.beans.produtos;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Produto;

@ManagedBean
public class ProdutoLista {

	private Produto produtoSelecionado;
	private List<Produto> produtos;
	private Fachada fachada;
	private String filtro;

	@PostConstruct
	public void start() {
		//
		fachada = new Fachada();
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
							
			} catch (Exception e) {
				fachada.addFlashMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um erro ao tentar excluir o produto!");

				e.printStackTrace();
			}
		}

	}

	public void filtrar() {

		try {
			if (filtro == null)
				filtro = "";
			String where = "WHERE e.nome like '%" + filtro + "%'";
			produtos = fachada.listProduto(where);

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
