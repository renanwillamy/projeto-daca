/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import com.projetodaca.entities.ItensDoPedido;
import com.projetodaca.entities.Pagamento;
import com.projetodaca.entities.Pedido;
import com.projetodaca.entities.Produto;

/**
 *
 * @author renan
 */
public class PedidoDao extends AbstractDao<Pedido> {

	public void insert(Pedido pedido) throws Exception {
		EntityManager manager = getEntityManager();
		try {

			List<ItensDoPedido> listItens = pedido.getItensDoPedido();
			for (ItensDoPedido item : listItens) {
				Produto prod = item.getProduto();
				item.setPedido(pedido);
				prod.setQuantidade(prod.getQuantidade() - item.getQuantidade());
				manager.merge(prod);
			}
			manager.persist(pedido);
			manager.refresh(pedido);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void insert(Pedido pedido, List<Pagamento> listPagamento) throws Exception {
		EntityManager manager = getEntityManager();
		try {

			List<ItensDoPedido> listItens = pedido.getItensDoPedido();
			for (ItensDoPedido item : listItens) {
				Produto prod = item.getProduto();
				item.setPedido(pedido);
				prod.setQuantidade(prod.getQuantidade() - item.getQuantidade());
				manager.merge(prod);
			}
			manager.persist(pedido);
			manager.refresh(pedido);
			for (Pagamento pag : listPagamento) {
				pag.setPedido(pedido);
				manager.persist(pag);
				manager.refresh(pag);
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<Pedido> list() throws Exception {
		EntityManager manager = getEntityManager();
		List<Pedido> list = new ArrayList<Pedido>();
		try {

			list = (List<Pedido>) manager.createQuery("SELECT e FROM Pedido e", Pedido.class).getResultList();

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return list;
	}

	public List<Pedido> list(String where) throws Exception {
		EntityManager manager = getEntityManager();
		List<Pedido> list = new ArrayList<Pedido>();
		try {
			list = (List<Pedido>) manager.createQuery("SELECT e FROM Pedido e " + where, Pedido.class).getResultList();

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return list;
	}

	public List<Pedido> listWhereData(Date dataInicial, Date dataFinal) throws Exception {
		EntityManager manager = getEntityManager();
		List<Pedido> list = new ArrayList<Pedido>();
		try {
			// TypedQuery<Pedido> query = manager.createQuery("SELECT e FROM
			// Pedido e where e.dataDoPedido >= :dataInicial and e.dataDoPedido
			// <= :dataFinal " , Pedido.class);
			TypedQuery<Pedido> query = manager.createQuery(
					"SELECT e FROM Pedido e where e.dataDoPedido BETWEEN :dataInicial and  :dataFinal ", Pedido.class);
			query.setParameter("dataInicial", dataInicial, TemporalType.DATE);
			query.setParameter("dataFinal", dataFinal, TemporalType.DATE);
			list = query.getResultList();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return list;
	}

	public Pedido getById(int id) throws Exception {
		Pedido pedido = null;
		EntityManager manager = getEntityManager();
		try {
			pedido = manager.find(Pedido.class, id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return pedido;
	}

	public void update(Pedido pedido) throws Exception {
		EntityManager manager = getEntityManager();
		try {			
			manager.merge(pedido);			

		} catch (Exception ex) {
			throw new Exception(ex);
		}
	}

	public void update(Pedido pedido, List<Pagamento> listPagamento) throws Exception {
		EntityManager manager = getEntityManager();
		try {

			Query query = manager.createQuery("DELETE Pagamento e where e.pedido.id = :idPedido");
			query.setParameter("idPedido", pedido.getId());
			query.executeUpdate();
			ItensDoPedidoDao itensDao = new ItensDoPedidoDao();
			List<ItensDoPedido> novaLista = new ArrayList<ItensDoPedido>();
			List<ItensDoPedido> listItens = pedido.getItensDoPedido();

			// adiciona itens do pedido na nova lista
			for (ItensDoPedido item : listItens) {
				novaLista.add(item);
			}

			ProdutoDao prodDao = new ProdutoDao();
			List<ItensDoPedido> listaItens = itensDao.list("where e.pedido.id =" + pedido.getId());
			for (ItensDoPedido item : listaItens) {
				Produto prod = item.getProduto();
				prod.setQuantidade(prod.getQuantidade() + item.getQuantidade());
				manager.merge(prod);
			}
			pedido.getItensDoPedido().clear();
			manager.merge(pedido);
			for (Pagamento pag : listPagamento) {
				pag.setPedido(pedido);
				manager.persist(pag);
				manager.refresh(pag);
			}

			for (ItensDoPedido item : novaLista) {
				Produto prod = item.getProduto();
				item.setPedido(pedido);
				prod.setQuantidade(prod.getQuantidade() - item.getQuantidade());
				for (ItensDoPedido item2 : listaItens) {
					if (item.getProduto().getId() == item2.getProduto().getId()) {
						prod.setQuantidade(item2.getProduto().getQuantidade() - item.getQuantidade());
					}
				}
				manager.merge(prod);
			}
			pedido.setItensDoPedido(novaLista);
			manager.merge(pedido);
			itensDao = null;

		} catch (Exception ex) {
			throw new Exception(ex);
		}
	}

	public void delete(Pedido pedido) throws Exception {
		EntityManager manager = getEntityManager();
		try {
			manager.remove(manager.getReference(Pedido.class, pedido.getId()));

		} catch (Exception ex) {
			throw new Exception(ex);
		}
	}

	public List<Pedido> listPedidoPorId(String filtro) throws Exception {
		String where = "";
		try {
			int id = Integer.parseInt(filtro);
			where = "Where e.id = " + id;
		} catch (Exception e) {
			where = "";
		}

		return list(where);
	}

}
