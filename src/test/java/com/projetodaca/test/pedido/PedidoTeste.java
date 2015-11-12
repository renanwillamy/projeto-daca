package com.projetodaca.test.pedido;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Categoria;
import com.projetodaca.entities.Cliente;
import com.projetodaca.entities.Contato;
import com.projetodaca.entities.Endereco;
import com.projetodaca.entities.Fornecedor;
import com.projetodaca.entities.ItensDoPedido;
import com.projetodaca.entities.Pedido;
import com.projetodaca.entities.Produto;
import com.projetodaca.services.PedidoService;

import static org.junit.Assert.*;

public class PedidoTeste {
	@Inject
	PedidoService pedService;
	@Inject
	Fachada fachada;
	
	@Before
	public void setUp(){
		
	}
	@Test
	public void testInsertPedido() throws Exception{
		
	Produto produto = fachada.getProdutoById(4);
	Cliente cliente = fachada.getClienteById(1);
	
	Pedido pedido = new Pedido();
	pedido.setCliente(cliente);
	List<ItensDoPedido> itensDoPedido = new ArrayList<>();
	ItensDoPedido itemDoPedido = new ItensDoPedido();
	itemDoPedido.setProduto(produto);
	itemDoPedido.setPedido(pedido);
	itensDoPedido.add(itemDoPedido);
	
	pedido.setItensDoPedido(itensDoPedido);
	pedido.setDataDoPedido(new Date());
	
	pedido = pedService.save(pedido);
	
	boolean actual = false;
	boolean expected = true;
	
	if(pedido.getId()>0)
		actual = true;
	
	assertEquals(expected, actual);
	
		
	}

}
