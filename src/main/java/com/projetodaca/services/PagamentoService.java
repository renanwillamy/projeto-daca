/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.services;

import java.util.Date;
import java.util.List;

import com.projetodaca.dao.PagamentoDao;
import com.projetodaca.entities.Pagamento;
import com.projetodaca.entities.Status;
import com.projetodaca.entities.TipoPagto;

/**
 *
 * @author renan
 */
public class PagamentoService {
    
    private PagamentoDao dao;

    public PagamentoService() {
       dao = new PagamentoDao();
    }
    
    /**Persiste pagamento no banco de dados
     * 
     * @param pagamento 
     */
    public Pagamento save(Pagamento pagamento) throws Exception{
            dao.insert(pagamento);
            return pagamento;
    }
    
    public void update(Pagamento pagamento) throws Exception{
            dao.update(pagamento);
    }
    
    public void delete(Pagamento pagamento) throws Exception{
            dao.delete(pagamento);
    }
    public void deleteTodosPorIdPedido(int idPedido) throws Exception{
            dao.deleteTodosPorIdPedido(idPedido);
    }
        
    public List<Pagamento> list() throws Exception{
        List<Pagamento> listPagamento= null;
          listPagamento = dao.list();
        return listPagamento;
    }
    
    public List<Pagamento> listPor(Date dataInicial, Date dataFinal, Date dataPagamentoInicial, Date dataPagamentoFinal, String nomeCliente, int idPedido, Status status,TipoPagto tipoPagto ,double valorDoPagamentoInicial, double valorDoPagamentoFinal) throws Exception{
        List<Pagamento> listPagamento= null;
          listPagamento = dao.listPor(dataInicial, dataFinal, dataPagamentoInicial, dataPagamentoFinal, nomeCliente, idPedido, status,tipoPagto, valorDoPagamentoInicial, valorDoPagamentoFinal);
        return listPagamento;
    }
    public List<Pagamento> list(String where) throws Exception{
        List<Pagamento> listPagamento= null;
          listPagamento = dao.list(where);
        return listPagamento;
    }
    public List<Pagamento> listPorIdPedido(int idPedido) throws Exception{
        List<Pagamento> listPagamento= null;
          listPagamento = dao.listPorIdPedido(idPedido);
        return listPagamento;
    }
    
    public Pagamento getById(int id) throws Exception{
        Pagamento pagamento = null;
        pagamento =  dao.getById(id);
        return pagamento;
    }
    
     /**Atualiza os pagamentos para atrazado caso a data do pagamento seja 
     * inferior a data atual e atualiza a situação do cliente como bloqueado
     */
    public void atualizaPagamento() throws Exception{
        dao.atualizaPagamento();
    }
}
