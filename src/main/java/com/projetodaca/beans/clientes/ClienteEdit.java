package com.projetodaca.beans.clientes;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.projetodaca.beans.AbstractManageBean;
import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Contato;
import com.projetodaca.entities.Endereco;
import com.projetodaca.entities.Cliente;

@ViewScoped
@ManagedBean
public class ClienteEdit extends AbstractManageBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3854803910581636710L;
	private Fachada fachada;
	private Cliente cliente;
	private Endereco endereco;
	private Contato contato;

	@PostConstruct
	public void start() {
		fachada = new Fachada();
		if (cliente == null)
			cliente = new Cliente();
		endereco = new Endereco();

	}

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
