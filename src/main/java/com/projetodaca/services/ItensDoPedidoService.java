/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.services;


import java.util.List;

import com.projetodaca.dao.ItensDoPedidoDao;
import com.projetodaca.entities.ItensDoPedido;

/**
 *
 * @author renan
 */
public class ItensDoPedidoService {   
    
    private ItensDoPedidoDao dao;

    public ItensDoPedidoService() {
       dao = new ItensDoPedidoDao();
    }
    
    /**Persiste itensDoPedido no banco de dados
     * 
     * @param itensDoPedido 
     */
    public ItensDoPedido save(ItensDoPedido itensDoPedido) throws Exception{
            dao.insert(itensDoPedido);
            return itensDoPedido;
    }
    
    public void update(ItensDoPedido itensDoPedido) throws Exception{
            dao.update(itensDoPedido);
    }
    
    public void delete(ItensDoPedido itensDoPedido) throws Exception{
            dao.delete(itensDoPedido);
    }
    
    public List<ItensDoPedido> list() throws Exception{
        List<ItensDoPedido> listItensDoPedido= null;
          listItensDoPedido = dao.list();
        return listItensDoPedido;
    }   
    
    public List<ItensDoPedido> list(String where) throws Exception{
        List<ItensDoPedido> listItensDoPedido= null;
          listItensDoPedido = dao.list(where);
        return listItensDoPedido;
    }
    
    
    public ItensDoPedido getById(int id) throws Exception{
        ItensDoPedido itensDoPedido = null;
        itensDoPedido =  dao.getById(id);
        return itensDoPedido;
    }
    
}
