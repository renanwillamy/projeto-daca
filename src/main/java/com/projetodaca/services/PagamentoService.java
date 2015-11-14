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
import com.projetodaca.entities.Avista;
import com.projetodaca.entities.Pagamento;
import com.projetodaca.entities.Status;
import com.projetodaca.entities.TipoPagto;
import com.projetodaca.utils.TransacionalCdi;

/**
 *
 * @author renan
 */
public class PagamentoService {
    @Inject
    private PagamentoDao dao;

   
    
    /**Persiste pagamento no banco de dados
     * 
     * @param pagamento 
     */
    @TransacionalCdi
    public Pagamento save(Pagamento pagamento) throws Exception{
            dao.insert(pagamento);
            return pagamento;
    }
    @TransacionalCdi
    public void update(Pagamento pagamento) throws Exception{
            dao.update(pagamento);
    }
    @TransacionalCdi
    public void delete(Pagamento pagamento) throws Exception{
            dao.delete(pagamento);
    }
    @TransacionalCdi
    public void deleteTodosPorIdPedido(int idPedido) throws Exception{
            dao.deleteTodosPorIdPedido(idPedido);
    }
    @TransacionalCdi
    public List<Pagamento> list() throws Exception{
        List<Pagamento> listPagamento= null;
          listPagamento = dao.list();
        return listPagamento;
    }
    
    @TransacionalCdi
    public List<Pagamento> listPor(Date dataInicial, Date dataFinal, Date dataPagamentoInicial, Date dataPagamentoFinal, String nomeCliente, int idPedido, Status status,TipoPagto tipoPagto ,double valorDoPagamentoInicial, double valorDoPagamentoFinal) throws Exception{
        List<Pagamento> listPagamento= null;
          listPagamento = dao.listPor(dataInicial, dataFinal, dataPagamentoInicial, dataPagamentoFinal, nomeCliente, idPedido, status,tipoPagto, valorDoPagamentoInicial, valorDoPagamentoFinal);
        return listPagamento;
    }
    @TransacionalCdi
    public List<Pagamento> list(String where) throws Exception{
        List<Pagamento> listPagamento= null;
          listPagamento = dao.list(where);
        return listPagamento;
    }
    @TransacionalCdi
    public List<Pagamento> listPorIdPedido(int idPedido) throws Exception{
        List<Pagamento> listPagamento= null;
          listPagamento = dao.listPorIdPedido(idPedido);
        return listPagamento;
    }
    @TransacionalCdi
    public Pagamento getById(int id) throws Exception{
        Pagamento pagamento = null;
        pagamento =  dao.getById(id);
        return pagamento;
    }
    
     /**Atualiza os pagamentos para atrazado caso a data do pagamento seja 
     * inferior a data atual e atualiza a situação do cliente como bloqueado
     */
    @TransacionalCdi
    public void atualizaPagamento() throws Exception{
        dao.atualizaPagamento();
    }
    @TransacionalCdi
    public List<Avista> listAvista() throws Exception {
    	 List<Avista> listPagamento= null;
         listPagamento = dao.listAvista();
       return listPagamento;
    }
    @TransacionalCdi
	public List<Avista> listAvistaPorId(String id) throws Exception {
		 List<Avista> listPagamento= null;
         listPagamento = dao.listAvistaPorId(id);
       return listPagamento;
	}
}
