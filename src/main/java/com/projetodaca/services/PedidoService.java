/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.services;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.projetodaca.dao.PagamentoDao;
import com.projetodaca.dao.PedidoDao;
import com.projetodaca.entities.Pagamento;
import com.projetodaca.entities.Pedido;
import com.projetodaca.entities.Promissoria;
import com.projetodaca.utils.TransacionalCdi;

/**
 *
 * @author renan
 */
public class PedidoService {
	@Inject
	private PedidoDao dao;
	@Inject
	private PagamentoDao pagDao;

	/**
	 * Persiste pedido no banco de dados
	 * 
	 * @param pedido
	 */
	@TransacionalCdi
	public Pedido save(Pedido pedido) throws Exception {
		dao.insert(pedido);
		return pedido;
	}

	/**
	 * Persiste pedido no banco de dados
	 * 
	 * @param pedido
	 */
	@TransacionalCdi
	public Pedido save(Pedido pedido, List<Pagamento> listPagamento) throws Exception {
		dao.insert(pedido, listPagamento);
		return pedido;
	}

	@TransacionalCdi
	public void update(Pedido pedido) throws Exception {
		dao.update(pedido);
	}

	@TransacionalCdi
	public void update(Pedido pedido, List<Pagamento> listPagamento) throws Exception {
		dao.update(pedido, listPagamento);
	}

	@TransacionalCdi
	public void delete(Pedido pedido) throws Exception {
		pagDao.deleteTodosPorIdPedido(pedido.getId());
		dao.delete(pedido);
	}

	@TransacionalCdi
	public List<Pedido> list() throws Exception {
		List<Pedido> listPedido = null;
		listPedido = dao.list();
		return listPedido;
	}

	@TransacionalCdi
	public List<Pedido> listWhereData(Date dataInicial, Date dataFinal) throws Exception {
		List<Pedido> listPedido = null;
		listPedido = dao.listWhereData(dataInicial, dataFinal);
		return listPedido;
	}

	@TransacionalCdi
	private List<Pedido> list(String where) throws Exception {
		List<Pedido> listPedido = null;
		listPedido = dao.list(where);
		return listPedido;
	}

	@TransacionalCdi
	public Pedido getById(int id) throws Exception {
		Pedido pedido = null;
		pedido = dao.getById(id);
		return pedido;
	}

	@TransacionalCdi
	public void save(Pedido pedido, Pagamento pagamento) throws Exception {
		dao.insert(pedido);
		pagamento.setPedido(pedido);
		pagDao.insert(pagamento);
	}
	
	@TransacionalCdi
	public void update(Pedido pedido, Pagamento pagamento) throws Exception {
		dao.update(pedido);
		pagamento.setPedido(pedido);
		pagDao.deleteTodosPorIdPedido(pedido.getId());
		pagDao.insert(pagamento);
	}

	public List<Pedido> listaPedidoPorId(String filtro) throws Exception {
		List<Pedido> listPedido = null;
		listPedido = dao.listPedidoPorId(filtro);
		return listPedido;
	
	}
	

}
