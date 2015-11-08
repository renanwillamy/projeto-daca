package com.projetodaca.beans.clientes;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.projetodaca.beans.AbstractManageBean;
import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Cliente;

@ManagedBean
public class ClienteLista extends AbstractManageBean implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4358464300229331650L;
	private Cliente clienteSelecionado;
	private List<Cliente> clientes;
	private Fachada fachada;
	private String filtro;

	@PostConstruct
	public void start() {
		//
		fachada = new Fachada();
		filtrar();
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public void excluir(Cliente cliente) {
		if (cliente != null) {

			try {
				fachada.deleteCliente(cliente);
				showFlashMessageInfo("Cliente excluido!");
				filtrar();
			} catch (Exception e) {

				showFlashMessageError("Ocorreu um erro ao tentar excluir o cliente, verifique se existem produtos cadastrados com esse Cliente");

			}
		}

	}

	public void filtrar() {

		try {
			if (filtro == null)
				filtro = "";

			clientes = fachada.listaClientePorNome(filtro);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente cliente) {
		this.clienteSelecionado = cliente;
	}

}
