package com.projetodaca.beans.pedidos;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.glassfish.api.admin.AccessRequired;

import com.projetodaca.beans.AbstractManageBean;
import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Pedido;

@RequestScoped
@Named
public class PedidoLista extends AbstractManageBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6405394078384105388L;
	@Inject
	private Pedido pedidoSelecionado;
	private List<Pedido> pedidos;
	@Inject
	private Fachada fachada;
	private String filtro;

	@PostConstruct
	public void start() {
		filtrar();
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public void excluir(Pedido pedido) {
		if (pedido != null) {

			try {
				fachada.deletePedido(pedido);
				filtrar();
				showFlashMessageInfo("Pedido excluido!");
			} catch (Exception e) {
				showFlashMessageError("Ocorreu um erro ao tentar excluir o pedido!");
				e.printStackTrace();
			}
		}

	}

	public void filtrar() {

		try {
			if (filtro == null)
				filtro = "";
			
		 pedidos = fachada.listPedidoPorId(filtro);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Pedido getPedidoSelecionado() {
		return pedidoSelecionado;
	}

	public void setPedidoSelecionado(Pedido pedido) {
		this.pedidoSelecionado = pedido;
	}

}
