package com.projetodaca.beans.fornecedores;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.projetodaca.beans.AbstractManageBean;
import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Contato;
import com.projetodaca.entities.Endereco;
import com.projetodaca.entities.Fornecedor;

@ViewScoped
@ManagedBean
public class FornecedorEdit extends AbstractManageBean {
	private Fachada fachada;
	private Fornecedor fornecedor;
	private Endereco endereco;
	private Contato contato;

	@PostConstruct
	public void start() {
		fachada = new Fachada();
		if (fornecedor == null)
			fornecedor = new Fornecedor();
		endereco = new Endereco();

	}

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
		System.out.println(endereco.toString());
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
