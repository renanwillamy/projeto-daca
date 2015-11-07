package com.projetodaca.beans.fornecedores;

import java.io.Serializable;
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
public class FornecedorInsert extends AbstractManageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8559018315064616512L;
	
	private Fachada fachada;		
	private Fornecedor fornecedor;
	private Endereco endereco;
	private Contato contato;


	@PostConstruct
	public void start() {
		fachada = new Fachada();
		fornecedor = new Fornecedor();
		contato = new Contato();
		endereco = new Endereco();
		
	}

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
