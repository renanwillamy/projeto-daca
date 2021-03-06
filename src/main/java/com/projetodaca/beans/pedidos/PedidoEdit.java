package com.projetodaca.beans.pedidos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.projetodaca.beans.AbstractManageBean;
import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Avista;
import com.projetodaca.entities.Cliente;
import com.projetodaca.entities.ItensDoPedido;
import com.projetodaca.entities.Pedido;
import com.projetodaca.entities.Produto;
import com.projetodaca.entities.Promissoria;

@ViewScoped
@Named
public class PedidoEdit extends AbstractManageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7084789732538455286L;
	@Inject
	private Fachada fachada;
	@Inject
	private Produto produto;
	private List<Produto> produtos;
	private double valorTotal;
	@Inject
	private ItensDoPedido itemSelecionado;
	private List<ItensDoPedido> itensDoPedido;
	private List<Cliente> clientes;
	@Inject
	private Cliente cliente;
	@Inject
	private Pedido pedido;
	@Inject
	private Avista avista;
	

	public void selecionaProduto() {
		showFlashMessageInfo(produto.getNome());
		addProduto();
	}

	public void selecionaCliente() {

		showFlashMessageInfo(getCliente().getNome());
	}

	public void removeItem() {
		showFlashMessageInfo(itemSelecionado.getProduto().getNome() + " removido");

		pedido.getItensDoPedido().remove(itemSelecionado);
		getValorTotal();
	}

	public String updatePedido() {
		try {

			if(getCliente().getId()==0)
				throw new Exception("Selecione um cliente");
			if(pedido.getItensDoPedido().size()==0)
				throw new Exception("O pedido está vazio!");			
			
			pedido.setDataDoPedido(new Date());				
			avista.setValorPagamento(valorTotal);
			avista.setValorPago(valorTotal);
			pedido.setValorTotal(valorTotal);
			fachada.updatePedido(pedido, avista);

			showFlashMessageInfo("Pedido Salvo!");
		} catch (Exception e) {
			e.printStackTrace();
			if (e.toString().contains("ConstraintViolationException")) {
				showFlashMessageError("Essa Pedido já existe!");
			} else if(e.toString().contains("Selecione um cliente")||e.toString().contains("O pedido está vazio!")){
				showFlashMessageError(e.getMessage());
			}else{
				showFlashMessageError("Erro ao tentar salvar a pedido!");
			}
			return null;
		//	return "insert_pedido?faces-redirect=true";
		}
		return "lista_pedidos?faces-redirect=true";
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		try {
			produtos = fachada.listaProdutosAtivos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return produtos;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Cliente> getClientes() {
		try {
			clientes = fachada.listCliente();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clientes;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Cliente getCliente() {		
		return pedido.getCliente();
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public void setProdutoSelecionado(ItensDoPedido itemSelecionado) {
		this.itemSelecionado = itemSelecionado;
	}

	public List<ItensDoPedido> getItensDoPedido() {
		
		for(ItensDoPedido item:pedido.getItensDoPedido()){
			item.setValorTotal(item.getProduto().getPrecoVenda()*item.getQuantidade());
		}
		
		return pedido.getItensDoPedido();
	}

	

	public ItensDoPedido getItemSelecionado() {
		return itemSelecionado;
	}

	public void setItemSelecionado(ItensDoPedido itemSelecionado) {
		this.itemSelecionado = itemSelecionado;
	}

	private void addProduto() {
		
		ItensDoPedido it = new ItensDoPedido();
		it.setProduto(produto);
		it.setPedido(pedido);
		it.setValor(produto.getPrecoVenda());
		it.setQuantidade(1);
		boolean contem = false;
		
		for (ItensDoPedido item : pedido.getItensDoPedido()) {
			if (it.getProduto().getId() == item.getProduto().getId()) {
				contem = true;
			}
		}		
		if (!contem) {
			pedido.getItensDoPedido().add(it);
		}else{
			for (ItensDoPedido item : pedido.getItensDoPedido()) {
				if (it.getProduto().getId() == item.getProduto().getId()) {
					item.setQuantidade(item.getQuantidade()+1);
				}
			}
		}
		getValorTotal();
	}

	public double getValorTotal() {
		valorTotal = 0;
		for (ItensDoPedido item : pedido.getItensDoPedido()) {
			valorTotal += item.getValor()*item.getQuantidade();
		}

		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	

	public Avista getAvista() {
		return avista;
	}

	public void setAvista(Avista avista) {
		this.avista = avista;
	}

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Produto Edited",
				((ItensDoPedido) event.getObject()).getProduto().getNome());

		ItensDoPedido item = ((ItensDoPedido) event.getObject());
		for (ItensDoPedido it : pedido.getItensDoPedido()) {
			if (it.getProduto().getId() == item.getProduto().getId()) {
				it = item;
			}
		}

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", ((ItensDoPedido) event.getObject()).getId() + "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void setItensDoPedido(List<ItensDoPedido> itensDoPedido) {
		pedido.setItensDoPedido(itensDoPedido);
	}
	
	

}
