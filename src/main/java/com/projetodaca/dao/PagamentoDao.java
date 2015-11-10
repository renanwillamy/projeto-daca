/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.dao;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import com.projetodaca.entities.Cliente;
import com.projetodaca.entities.Pagamento;
import com.projetodaca.entities.Status;
import com.projetodaca.entities.TipoPagto;

/**
 *
 * @author renan
 */
public class PagamentoDao implements IDAO<Pagamento> {

    private EntityManager manager;
   

    public PagamentoDao() {
       
    }

    
    public void insert(Pagamento pagamento) throws Exception {
        try {
            beginTransaction();
            manager.persist(pagamento);
            manager.refresh(pagamento);
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

    
    public List<Pagamento> list() throws Exception {
        List<Pagamento> list = new ArrayList<Pagamento>();
        try {
            beginTransaction();
            list = (List<Pagamento>) manager.createQuery("SELECT e FROM Pagamento e", Pagamento.class).getResultList();
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

    public List<Pagamento> listPorIdPedido(int idPedido) throws Exception {
        List<Pagamento> list = new ArrayList<Pagamento>();
        try {
            beginTransaction();
            Query query = manager.createQuery("SELECT e FROM Pagamento e WHERE e.pedido.id = :idPedido", Pagamento.class);
            query.setParameter("idPedido", idPedido);
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

    public List<Pagamento> list(String where) throws Exception {
        List<Pagamento> list = new ArrayList<Pagamento>();
        try {
            beginTransaction();
            list = (List<Pagamento>) manager.createQuery("SELECT e FROM Pagamento e " + where, Pagamento.class).getResultList();
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

    public List<Pagamento> listPor(Date dataInicial, Date dataFinal, Date dataPagamentoInicial, Date dataPagamentoFinal, String nomeCliente, int idPedido, Status status, TipoPagto tipoPagto, double valorDoPagamentoInicial, double valorDoPagamentoFinal) throws Exception {
        List<Pagamento> list = new ArrayList<Pagamento>();
        try {
            beginTransaction();
            String jpql = "";
            if (dataInicial != null) {
                jpql += "WHERE date(e.dataInicial) >= :dataInicial";
            }
            if (dataFinal != null) {
                if (!jpql.isEmpty()) {
                    jpql += " AND ";
                } else {
                    jpql += " WHERE ";
                }
                jpql += " date(e.dataInicial) <= :dataFinal";
            }
            if (dataPagamentoInicial != null) {
                if (!jpql.isEmpty()) {
                    jpql += " AND ";
                } else {
                    jpql += " WHERE ";
                }
                jpql += " date(e.dataPagamento) >= :dataPagamentoInicial";
            }
            if (dataPagamentoFinal != null) {
                if (!jpql.isEmpty()) {
                    jpql += " AND ";
                } else {
                    jpql += " WHERE ";
                }
                jpql += " date(e.dataPagamento) <= :dataPagamentoFinal";
            }
            if (nomeCliente != null) {
                if (!jpql.isEmpty()) {
                    jpql += " AND ";
                } else {
                    jpql += " WHERE ";
                }
                jpql += " e.pedido.cliente.nome like :nomeCliente";
            }
            if (idPedido != 0) {
                if (!jpql.isEmpty()) {
                    jpql += " AND ";
                } else {
                    jpql += " WHERE ";
                }
                jpql += " e.pedido.id = :idPedido";
            }
            if (status != null) {
                if (!jpql.isEmpty()) {
                    jpql += " AND ";
                } else {
                    jpql += " WHERE ";
                }
                jpql += " e.status = :status";
            }
            if (tipoPagto != null) {
                if (!jpql.isEmpty()) {
                    jpql += " AND ";
                } else {
                    jpql += " WHERE ";
                }
                jpql += " e.tipoDePgto = :tipoPagto";
            }
            if (valorDoPagamentoInicial > 0) {
                if (!jpql.isEmpty()) {
                    jpql += " AND ";
                } else {
                    jpql += " WHERE ";
                }
                jpql += " e.valorPagamento >= :valorDoPagamentoInicial";
            }
            if (valorDoPagamentoFinal > 0) {
                if (!jpql.isEmpty()) {
                    jpql += " AND ";
                } else {
                    jpql += " WHERE ";
                }
                jpql += " e.valorPagamento <= :valorDoPagamentoFinal";
            }
            
            if (!jpql.isEmpty()) {
                    jpql += " AND ";
                } else {
                    jpql += " WHERE ";
                }
            jpql += " e.pagamento.id = NULL";

            Query query = manager.createQuery("SELECT e FROM Pagamento e " + jpql, Pagamento.class);
            if (dataInicial != null) {
                query.setParameter("dataInicial", dataInicial, TemporalType.DATE);
            }
            if (dataFinal != null) {
                query.setParameter("dataFinal", dataFinal, TemporalType.DATE);
            }
            if (dataPagamentoInicial != null) {
                query.setParameter("dataPagamentoInicial", dataPagamentoInicial, TemporalType.DATE);
            }
            if (dataPagamentoFinal != null) {
                query.setParameter("dataPagamentoFinal", dataPagamentoFinal, TemporalType.DATE);
            }
            if (nomeCliente != null) {
                query.setParameter("nomeCliente", "%" + nomeCliente + "%");
            }
            if (idPedido > 0) {
                query.setParameter("idPedido", idPedido);
            }
            if (status != null) {
                query.setParameter("status", status);
            }
            if (tipoPagto != null) {
                query.setParameter("tipoPagto", tipoPagto);
            }
            if (valorDoPagamentoInicial > 0) {
                query.setParameter("valorDoPagamentoInicial", valorDoPagamentoInicial);
            }
            if (valorDoPagamentoFinal > 0) {
                query.setParameter("valorDoPagamentoFinal", valorDoPagamentoFinal);
            }
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

    /**
     * Atualiza os pagamentos para atrazado caso a data do pagamento seja
     * inferior a data atual e atualiza a situação do cliente como bloqueado
     */
    public void atualizaPagamento() throws Exception {
       

            Calendar calend = GregorianCalendar.getInstance();
            Calendar calend2 = GregorianCalendar.getInstance();
            calend.add(Calendar.DAY_OF_MONTH, -5);
            Date dataAnterior = new Date(calend.getTimeInMillis());
            Date dataOntem = new Date(calend2.getTimeInMillis());
            List<Pagamento> listPagto = listPor(dataAnterior, dataOntem, null, null, null, 0, null, null, 0, 0);
            try {
            beginTransaction();
            for (Pagamento pag : listPagto) {
                if (pag.getStatus() != Status.LIQUIDADO) {
                    Cliente cliente = pag.getPedido().getCliente();
                    cliente.setBloqueado(true);                    
                    manager.merge(cliente);
                    manager.merge(pag);
                }
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

    
    public Pagamento getById(int id) throws Exception {
        Pagamento pagamento = null;
        try {
            beginTransaction();
            pagamento = manager.find(Pagamento.class, id);
            commitTransaction();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                rollBack();
            }
            throw new Exception(e.getMessage());
        } finally {
            manager.close();
        }

        return pagamento;
    }

    
    public void update(Pagamento pagamento) throws Exception {
        try {
            beginTransaction();
            manager.merge(pagamento);
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

    
    public void delete(Pagamento pagamento) throws Exception {
        try {
            beginTransaction();            
            manager.remove(manager.getReference(Pagamento.class, pagamento.getId()));
            commitTransaction();
        } catch (Exception ex) {  
            if (manager.getTransaction().isActive()) {
            }
            ex.printStackTrace();
            throw new Exception(ex);            
        } finally {
            manager.close();
        }
    }

    public void deleteTodosPorIdPedido(int idPedido) throws Exception {
        try {
            beginTransaction();
            Query query = manager.createQuery("DELETE Pagamento e where e.pedido.id = :idPedido");
            query.setParameter("idPedido", idPedido);
            query.executeUpdate();
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
