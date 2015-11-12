package com.projetodaca.beans.clientes;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projetodaca.beans.AbstractManageBean;
import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Cliente;
import com.projetodaca.entities.Contato;
import com.projetodaca.entities.Endereco;

@SessionScoped
@Named
public class ClienteEdit extends AbstractManageBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3854803910581636710L;
	@Inject
	private Fachada fachada;
	@Inject
	private Cliente cliente;
	@Inject
	private Endereco endereco;
	@Inject
	private Contato contato;

	
	public String updateCliente() {
		try {				

			fachada.updateCliente(cliente);
			showFlashMessageInfo("Cliente Alterado!");
		} catch (Exception e) {
			showFlashMessageError("Ocorreu um erro ao tentar alterar o Cliente!");
			e.printStackTrace();
			return "edit_Clientes";
		}
		return "lista_clientes?faces-redirect=true";
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente Cliente) {
		this.cliente = Cliente;
	}

	public Endereco getEndereco() {
		endereco = cliente.getEndereco();
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		System.out.println(endereco.toString());
		this.endereco = endereco;
	}

	public Contato getContato() {
		//vai existir apenas um contato que é obrigatório
		contato = cliente.getContatos().get(0);
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

}
