/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.services;


import java.util.Date;
import java.util.List;

import com.projetodaca.dao.CaixaDao;
import com.projetodaca.entities.Caixa;



/**
 *
 * @author renan
 */
public class CaixaService {
    
    private CaixaDao dao;

    public CaixaService() {
        dao = new CaixaDao();
    }
    
    public Caixa save(Caixa caixa) throws Exception{
            dao.insert(caixa);
            return caixa;
    }
    
    public void update(Caixa caixa) throws Exception{
            dao.update(caixa);
    }
    
    public void delete(Caixa caixa) throws Exception{
    }
    
    public List<Caixa> list() throws Exception{
        List<Caixa> listCaixa= null;
          listCaixa = dao.list();
        return listCaixa;
    }
    
    public List<Caixa> list(String where) throws Exception{
        List<Caixa> listCaixa= null;
          listCaixa = dao.list(where);
        return listCaixa;
    }
    
    public Caixa getById(int id) throws Exception{
        Caixa caixa = null;
        caixa =  dao.getById(id);
        return caixa;
    }  
    
   public List<Caixa> listPorPontoDeRecebimentoEData(int idPontoRecebimento,Date dataAbertInicial,Date dataAbertFinal) throws Exception{
       return dao.listPorPontoDeRecebimentoEData(idPontoRecebimento, dataAbertInicial, dataAbertFinal);
   }
    
}
