package com.projetodaca.beans.pedidos;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projetodaca.beans.AbstractManageBean;
import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Produto;

@RequestScoped
@Named
public class PedidoInsert extends AbstractManageBean implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7084789732538455286L;
	@Inject
	private Fachada fachada;
	@Inject
	private Produto produto;
	private List<Produto> produtos;


	
	public String buscaProduto(){
		
		
		showFlashMessageInfo(produto.getNome());
		
	//	return "/produtos/lista_prod?faces-redirect=true";
		return "insert_pedido";
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

	public List<Produto> getProdutos() {
		try {
			produtos = fachada.listProduto();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	


	


}
