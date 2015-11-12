package com.projetodaca.beans.clientes;

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
import com.projetodaca.entities.Cliente;

@ViewScoped
@Named
public class ClienteInsert extends AbstractManageBean implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 158806803289219795L;
	@Inject
	private Fachada fachada;	
	@Inject
	private Cliente cliente;
	@Inject
	private Endereco endereco;
	@Inject
	private Contato contato;


	public String insertCliente() {

		try {					
					
			contato.setCliente(cliente);				
			ArrayList<Contato> contatos = new ArrayList<>();
			contatos.add(contato);			
			cliente.setContatos(contatos);		
			cliente.setEndereco(endereco);
			fachada.saveCliente(cliente);
			showFlashMessageInfo("Cliente Salvo!");
		} catch (Exception e) {
			System.out.println("Erro");
			e.printStackTrace();
			showFlashMessageError("Erro ao tentar salvar cliente!");
			return "insert_cliente";
		}
	
		return "insert_cliente?faces-redirect=true";
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
