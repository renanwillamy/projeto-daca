package com.projetodaca.beans.pedidos;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projetodaca.beans.AbstractManageBean;
import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Fornecedor;
import com.projetodaca.entities.Pedido;
import com.projetodaca.entities.Produto;

@ViewScoped
@Named
public class PedidoInsert extends AbstractManageBean implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 257948894955858807L;
	@Inject
	private Fachada fachada;
	@Inject
	private Produto produto;
	@Inject
	private Pedido pedido;



	@PostConstruct
	public void start() {
		
	}
	
	public String buscaProduto(){
		return "/lista_prod?faces-redirect=true";
		
	}

	public String insertPedido() {
		try {

			//fachada.savePedido(pedido);		
		
			showFlashMessageInfo("Pedido Salva!");
		} catch (Exception e) {			
			e.printStackTrace();
			if(e.toString().contains("ConstraintViolationException")){
				showFlashMessageError("Essa Pedido já existe!");
			}else{
			showFlashMessageError("Erro ao tentar salvar a pedido!");
			}
			return "insert_cat";
		}	
		return "/insert_cat?faces-redirect=true";
	}
	
		

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	


}
