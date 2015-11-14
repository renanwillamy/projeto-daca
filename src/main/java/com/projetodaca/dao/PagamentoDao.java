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

import com.projetodaca.entities.Avista;
import com.projetodaca.entities.Cliente;
import com.projetodaca.entities.Pagamento;
import com.projetodaca.entities.Status;
import com.projetodaca.entities.TipoPagto;

/**
 *
 * @author renan
 */
public class PagamentoDao extends AbstractDao<Pagamento> {

   
    
    public void insert(Pagamento pagamento) throws Exception {
    	EntityManager manager = getEntityManager();
        try {        
            manager.persist(pagamento);
            manager.refresh(pagamento);          
        } catch (Exception e) {            
            throw new Exception(e.getMessage());
        }
    }

    
    public List<Pagamento> list() throws Exception {
    	EntityManager manager = getEntityManager();
        List<Pagamento> list = new ArrayList<Pagamento>();
        try {           
            list = (List<Pagamento>) manager.createQuery("SELECT e FROM Pagamento e", Pagamento.class).getResultList();            
        } catch (Exception e) {           
            throw new Exception(e.getMessage());
        }
        return list;
    }
    
    public List<Avista> listAvista() throws Exception {
    	EntityManager manager = getEntityManager();
        List<Pagamento> list = new ArrayList<Pagamento>();
        List<Avista> listAvista = new ArrayList<Avista>();
        try {           
            list = (List<Pagamento>) manager.createQuery("SELECT e FROM Pagamento e WHERE e.tipoDePgto = 'AVISTA'", Pagamento.class).getResultList();            
            for(Pagamento pag:list){
            	if(pag instanceof Avista){
            		listAvista.add((Avista)pag);
            	}
            }
           
        } catch (Exception e) {           
            throw new Exception(e.getMessage());
        }
        return listAvista;
    }

    public List<Pagamento> listPorIdPedido(int idPedido) throws Exception {
    	EntityManager manager = getEntityManager();
        List<Pagamento> list = new ArrayList<Pagamento>();
        try {           
            Query query = manager.createQuery("SELECT e FROM Pagamento e WHERE e.pedido.id = :idPedido", Pagamento.class);
            query.setParameter("idPedido", idPedido);
            list = query.getResultList();           
        } catch (Exception e) {           
            throw new Exception(e.getMessage());
        } 
        return list;
    }

    public List<Pagamento> list(String where) throws Exception {
    	EntityManager manager = getEntityManager();
        List<Pagamento> list = new ArrayList<Pagamento>();
        try {           
            list = (List<Pagamento>) manager.createQuery("SELECT e FROM Pagamento e " + where, Pagamento.class).getResultList();
           
        } catch (Exception e) {           
            throw new Exception(e.getMessage());
        } 
        return list;
    }

    public List<Pagamento> listPor(Date dataInicial, Date dataFinal, Date dataPagamentoInicial, Date dataPagamentoFinal, String nomeCliente, int idPedido, Status status, TipoPagto tipoPagto, double valorDoPagamentoInicial, double valorDoPagamentoFinal) throws Exception {
    	EntityManager manager = getEntityManager();
    	List<Pagamento> list = new ArrayList<Pagamento>();        
        try {
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
        } catch (Exception e) {           
            throw new Exception(e.getMessage());
        } 
        return list;
    }

    /**
     * Atualiza os pagamentos para atrazado caso a data do pagamento seja
     * inferior a data atual e atualiza a situação do cliente como bloqueado
     */
    public void atualizaPagamento() throws Exception {
    	EntityManager manager = getEntityManager();

            Calendar calend = GregorianCalendar.getInstance();
            Calendar calend2 = GregorianCalendar.getInstance();
            calend.add(Calendar.DAY_OF_MONTH, -5);
            Date dataAnterior = new Date(calend.getTimeInMillis());
            Date dataOntem = new Date(calend2.getTimeInMillis());
            List<Pagamento> listPagto = listPor(dataAnterior, dataOntem, null, null, null, 0, null, null, 0, 0);
            try {
           
            for (Pagamento pag : listPagto) {
                if (pag.getStatus() != Status.LIQUIDADO) {
                    Cliente cliente = pag.getPedido().getCliente();
                    cliente.setBloqueado(true);                    
                    manager.merge(cliente);
                    manager.merge(pag);
                }
            }
           
        } catch (Exception e) {           
            throw new Exception(e.getMessage());
        } 
    }

    
    public Pagamento getById(int id) throws Exception {
    	EntityManager manager = getEntityManager();
        Pagamento pagamento = null;
        try {      
            pagamento = manager.find(Pagamento.class, id);           
        } catch (Exception e) {            
            throw new Exception(e.getMessage());
        } 
        return pagamento;
    }

    
    public void update(Pagamento pagamento) throws Exception {
    	EntityManager manager = getEntityManager();
        try {           
            manager.merge(pagamento);            
        } catch (Exception ex) {            
            throw new Exception(ex);
        } 
    }

    
    public void delete(Pagamento pagamento) throws Exception {
    	EntityManager manager = getEntityManager();
        try {          
            manager.remove(manager.getReference(Pagamento.class, pagamento.getId()));           
        } catch (Exception ex) {  
                       ex.printStackTrace();
            throw new Exception(ex);            
        } 
    }

    public void deleteTodosPorIdPedido(int idPedido) throws Exception {
    	EntityManager manager = getEntityManager();
        try {            
            Query query = manager.createQuery("DELETE Pagamento e where e.pedido.id = :idPedido");
            query.setParameter("idPedido", idPedido);
            query.executeUpdate();            
        } catch (Exception ex) {           
            throw new Exception(ex);
        } 
    }
    
    public List<Avista> listAvistaPorId(String id) throws Exception {
    	EntityManager manager = getEntityManager();
        List<Pagamento> list = new ArrayList<Pagamento>();
        List<Avista> listAvista = new ArrayList<Avista>();
        String query = "SELECT e FROM Pagamento e WHERE e.tipoDePgto = 'AVISTA' AND e.pedido.id = "+id;
        if(id.isEmpty()||id.length()<1){
        	query = "SELECT e FROM Pagamento e WHERE e.tipoDePgto = 'AVISTA'";
        }

        try {           
            list = (List<Pagamento>) manager.createQuery(query, Pagamento.class).getResultList();            
            for(Pagamento pag:list){
            	if(pag instanceof Avista){
            		listAvista.add((Avista)pag);
            	}
            }
           
        } catch (Exception e) {           
            throw new Exception(e.getMessage());
        }
        return listAvista;
    }

   

}
