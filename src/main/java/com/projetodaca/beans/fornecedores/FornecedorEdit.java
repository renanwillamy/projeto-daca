package com.projetodaca.beans.fornecedores;

import java.io.Serializable;
import java.util.ArrayList;


import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projetodaca.beans.AbstractManageBean;
import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Contato;
import com.projetodaca.entities.Endereco;
import com.projetodaca.entities.Fornecedor;

@SessionScoped
@Named
public class FornecedorEdit extends AbstractManageBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4134744186371788146L;
	@Inject
	private Fachada fachada;
	@Inject
	private Fornecedor fornecedor;
	@Inject
	private Endereco endereco;
	@Inject
	private Contato contato;
	
	public String updateFornecedor() {
		try {			
			fachada.updateFornecedor(fornecedor);
			showFlashMessageInfo("Fornecedor Alterado!");
		} catch (Exception e) {
			showFlashMessageError("Ocorreu um erro ao tentar alterar o fornecedor!");
			e.printStackTrace();
			return "edit_fornecedores";
		}
		return "lista_fornecedores?faces-redirect=true";
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Endereco getEndereco() {
		endereco = fornecedor.getEndereco();
		return endereco;
	}

	public void setEndereco(Endereco endereco) {		
		this.endereco = endereco;
	}

	public Contato getContato() {
		contato = fornecedor.getContatos().get(0);
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

}
