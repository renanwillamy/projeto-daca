package com.projetodaca.beans.fornecedores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.projetodaca.beans.AbstractManageBean;
import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Fornecedor;

@RequestScoped
@Named
public class FornecedorLista extends AbstractManageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2785235253437136575L;
	@Inject
	private Fornecedor fornecedorSelecionado;
	private List<Fornecedor> fornecedores;
	@Inject
	private Fachada fachada;
	private String filtro;

	@PostConstruct
	public void start() {			
		filtrar();
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public void excluir(Fornecedor fornecedor) {
		if (fornecedor != null) {

			try {
				fachada.deleteFornecedor(fornecedor);
				showFlashMessageInfo("Fornecedor excluido!");
				filtrar();
			} catch (Exception e) {

				showFlashMessageError("Ocorreu um erro ao tentar excluir o fornecedor, verifique se existem produtos cadastrados com esse Fornecedor");

			}
		}

	}

	public void filtrar() {

		try {
			if (filtro == null)
				filtro = "";

			fornecedores = fachada.listaFornecedorPorNomeFantasia(filtro);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Fornecedor getFornecedorSelecionado() {
		return fornecedorSelecionado;
	}

	public void setFornecedorSelecionado(Fornecedor fornecedor) {
		this.fornecedorSelecionado = fornecedor;
	}

}
