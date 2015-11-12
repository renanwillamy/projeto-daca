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
public class PedidoDao implements IDAO<Pedido> {

    private EntityManager manager;
   
    public PedidoDao() {
       
    }

    
    public void insert(Pedido pedido) throws Exception {
        try {
            beginTransaction();
            List<ItensDoPedido> listItens = pedido.getItensDoPedido();
            for (ItensDoPedido item : listItens) {
                Produto prod = item.getProduto();
                item.setPedido(pedido);
                prod.setQuantidade(prod.getQuantidade() - item.getQuantidade());
                manager.merge(prod);
            }
            manager.persist(pedido);
            manager.refresh(pedido);
            commitTransaction();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(e.getMessage());
        } finally {
            manager.close();
        }
    }

    public void insert(Pedido pedido, List<Pagamento> listPagamento) throws Exception {
        try {
            beginTransaction();
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
            commitTransaction();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(e.getMessage());
        } finally {
            manager.close();
        }
    }

    
    public List<Pedido> list() throws Exception {
        List<Pedido> list = new ArrayList<Pedido>();
        try {
            beginTransaction();
            list = (List<Pedido>) manager.createQuery("SELECT e FROM Pedido e", Pedido.class).getResultList();
            commitTransaction();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(e.getMessage());
        } finally {
            manager.close();
        }
        return list;
    }

    public List<Pedido> list(String where) throws Exception {
        List<Pedido> list = new ArrayList<Pedido>();
        try {
            beginTransaction();
            list = (List<Pedido>) manager.createQuery("SELECT e FROM Pedido e " + where, Pedido.class).getResultList();
            commitTransaction();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(e.getMessage());
        } finally {
            manager.close();
        }

        return list;
    }

    public List<Pedido> listWhereData(Date dataInicial, Date dataFinal) throws Exception {
        List<Pedido> list = new ArrayList<Pedido>();
        try {
            beginTransaction();
            // TypedQuery<Pedido> query =  manager.createQuery("SELECT e FROM Pedido e where e.dataDoPedido >= :dataInicial and e.dataDoPedido <= :dataFinal " , Pedido.class);
            TypedQuery<Pedido> query = manager.createQuery("SELECT e FROM Pedido e where e.dataDoPedido BETWEEN :dataInicial and  :dataFinal ", Pedido.class);
            query.setParameter("dataInicial", dataInicial, TemporalType.DATE);
            query.setParameter("dataFinal", dataFinal, TemporalType.DATE);
            list = query.getResultList();
            commitTransaction();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(e.getMessage());
        } finally {
            manager.close();
        }

        return list;
    }

    
    public Pedido getById(int id) throws Exception {
        Pedido pedido = null;
        try {
            beginTransaction();
            pedido = manager.find(Pedido.class, id);
            commitTransaction();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(e.getMessage());
        } finally {
            manager.close();
        }
        return pedido;
    }

    
    public void update(Pedido pedido) throws Exception {
        try {
            beginTransaction();
            ItensDoPedidoDao itensDao = new ItensDoPedidoDao();
            List<ItensDoPedido> novaLista = new ArrayList<ItensDoPedido>();
            List<ItensDoPedido> listItens = pedido.getItensDoPedido();

            //adiciona itens do pedido na nova lista
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
            commitTransaction();
        } catch (Exception ex) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(ex);
        } finally {
            manager.close();
        }
    }

    public void update(Pedido pedido, List<Pagamento> listPagamento) throws Exception {
        try {
            beginTransaction();
            Query query = manager.createQuery("DELETE Pagamento e where e.pedido.id = :idPedido");
            query.setParameter("idPedido", pedido.getId());
            query.executeUpdate();
            ItensDoPedidoDao itensDao = new ItensDoPedidoDao();
            List<ItensDoPedido> novaLista = new ArrayList<ItensDoPedido>();
            List<ItensDoPedido> listItens = pedido.getItensDoPedido();

            //adiciona itens do pedido na nova lista
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
            commitTransaction();
        } catch (Exception ex) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(ex);
        } finally {
            manager.close();
        }
    }

    
    public void delete(Pedido pedido) throws Exception {
        try {
            beginTransaction();
            Query query = manager.createQuery("DELETE Pagamento e where e.pedido.id = :idPedido");
            query.setParameter("idPedido", pedido.getId());
            query.executeUpdate();
            ItensDoPedidoDao itensDao = new ItensDoPedidoDao();
            List<ItensDoPedido> listaItens = itensDao.list("where e.pedido.id =" + pedido.getId());
            //devolve a quantidade retirada do estoque
            for (ItensDoPedido item : listaItens) {
                Produto prod = item.getProduto();
                prod.setQuantidade(prod.getQuantidade() + item.getQuantidade());
                manager.merge(prod);
            }
            manager.remove(manager.getReference(Pedido.class, pedido.getId()));
            itensDao = null;
            commitTransaction();
        } catch (Exception ex) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(ex);
        } finally {
            manager.close();
        }
    }

    
    public void beginTransaction() {
        
        manager.getTransaction().begin();
    }

    
    public void commitTransaction() {
        manager.getTransaction().commit();
    }

    
    public void rollBack() {
        manager.getTransaction().rollback();
    }

}
