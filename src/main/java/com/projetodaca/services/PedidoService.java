/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.services;

import java.util.Date;
import java.util.List;

import com.projetodaca.dao.PedidoDao;
import com.projetodaca.entities.Pagamento;
import com.projetodaca.entities.Pedido;

/**
 *
 * @author renan
 */
public class PedidoService {
    
    private PedidoDao dao;

    public PedidoService() {
       dao = new PedidoDao();
    }
    
    /**Persiste pedido no banco de dados
     * 
     * @param pedido 
     */
    public Pedido save(Pedido pedido) throws Exception{
            dao.insert(pedido);
            return pedido;
    }
    /**Persiste pedido no banco de dados
     * 
     * @param pedido 
     */
    public Pedido save(Pedido pedido,List<Pagamento> listPagamento) throws Exception{
            dao.insert(pedido,listPagamento);
            return pedido;
    }
    
    public void update(Pedido pedido) throws Exception{
            dao.update(pedido);
    }    
    public void update(Pedido pedido,List<Pagamento> listPagamento) throws Exception{
            dao.update(pedido,listPagamento);
    }    
        
    public void delete(Pedido pedido) throws Exception{
            dao.delete(pedido);
    }
    
    public List<Pedido> list() throws Exception{
        List<Pedido> listPedido= null;
          listPedido = dao.list();
        return listPedido;
    }
    public List<Pedido> listWhereData(Date dataInicial,Date dataFinal) throws Exception{
        List<Pedido> listPedido= null;
          listPedido = dao.listWhereData(dataInicial, dataFinal);
        return listPedido;
    }
    
    public List<Pedido> list(String where) throws Exception{
        List<Pedido> listPedido= null;
          listPedido = dao.list(where);
        return listPedido;
    }
    
    public Pedido getById(int id) throws Exception{
        Pedido pedido = null;
        pedido =  dao.getById(id);
        return pedido;
    }
    
    
}
