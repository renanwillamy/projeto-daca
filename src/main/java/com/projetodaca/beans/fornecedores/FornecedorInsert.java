package com.projetodaca.beans.fornecedores;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projetodaca.beans.AbstractManageBean;
import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Contato;
import com.projetodaca.entities.Endereco;
import com.projetodaca.entities.Fornecedor;

@ViewScoped
@Named
public class FornecedorInsert extends AbstractManageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8559018315064616512L;
	@Inject
	private Fachada fachada;	
	@Inject
	private Fornecedor fornecedor;
	@Inject
	private Endereco endereco;
	@Inject
	private Contato contato;


	public String insertFornecedor() {

		try {					
					
			contato.setFornecedor(fornecedor);				
			ArrayList<Contato> contatos = new ArrayList<>();
			contatos.add(contato);			
			fornecedor.setContatos(contatos);		
			fornecedor.setEndereco(endereco);
			fachada.saveFornecedor(fornecedor);
			showFlashMessageInfo("Fornecedor Salvo!");
		} catch (Exception e) {
			System.out.println("Erro");
			e.printStackTrace();
			showFlashMessageError("Erro ao tentar salvar fornecedor!");
			return "insert_fornecedor";
		}
	
		return "/index?faces-redirect=true";
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	

}
