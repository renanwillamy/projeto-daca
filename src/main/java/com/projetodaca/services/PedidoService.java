/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.services;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import com.projetodaca.dao.PagamentoDao;
import com.projetodaca.dao.PedidoDao;
import com.projetodaca.entities.Pagamento;
import com.projetodaca.entities.Pedido;
import com.projetodaca.entities.Promissoria;


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
	@Transactional
	public Pedido save(Pedido pedido) throws Exception {
		dao.insert(pedido);
		return pedido;
	}

	/**
	 * Persiste pedido no banco de dados
	 * 
	 * @param pedido
	 */
	@Transactional
	public Pedido save(Pedido pedido, List<Pagamento> listPagamento) throws Exception {
		dao.insert(pedido, listPagamento);
		return pedido;
	}

	@Transactional
	public void update(Pedido pedido) throws Exception {
		dao.update(pedido);
	}

	@Transactional
	public void update(Pedido pedido, List<Pagamento> listPagamento) throws Exception {
		dao.update(pedido, listPagamento);
	}

	@Transactional
	public void delete(Pedido pedido) throws Exception {
		pagDao.deleteTodosPorIdPedido(pedido.getId());
		dao.delete(pedido);
	}

	@Transactional
	public List<Pedido> list() throws Exception {
		List<Pedido> listPedido = null;
		listPedido = dao.list();
		return listPedido;
	}

	@Transactional
	public List<Pedido> listWhereData(Date dataInicial, Date dataFinal) throws Exception {
		List<Pedido> listPedido = null;
		listPedido = dao.listWhereData(dataInicial, dataFinal);
		return listPedido;
	}

	@Transactional
	private List<Pedido> list(String where) throws Exception {
		List<Pedido> listPedido = null;
		listPedido = dao.list(where);
		return listPedido;
	}

	@Transactional
	public Pedido getById(int id) throws Exception {
		Pedido pedido = null;
		pedido = dao.getById(id);
		return pedido;
	}

	@Transactional
	public void save(Pedido pedido, Pagamento pagamento) throws Exception {
		dao.insert(pedido);
		pagamento.setPedido(pedido);
		pagDao.insert(pagamento);
	}
	
	@Transactional
	public void update(Pedido pedido, Pagamento pagamento) throws Exception {
		dao.update(pedido);
		pagamento.setPedido(pedido);
		pagDao.deleteTodosPorIdPedido(pedido.getId());
		pagDao.insert(pagamento);
	}
	@Transactional
	public List<Pedido> listaPedidoPorId(String filtro) throws Exception {
		List<Pedido> listPedido = null;
		listPedido = dao.listPedidoPorId(filtro);
		return listPedido;
	
	}
	

}
