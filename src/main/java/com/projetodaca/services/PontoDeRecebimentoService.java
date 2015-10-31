/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.services;

import java.util.List;

import com.projetodaca.dao.PontoDeRecebimentoDao;
import com.projetodaca.entities.PontoDeRecebimento;



/**
 *
 * @author renan
 */
public class PontoDeRecebimentoService {
    
    private PontoDeRecebimentoDao dao;
    

    public PontoDeRecebimentoService() {
        dao = new PontoDeRecebimentoDao();
    }
    
    public PontoDeRecebimento save(PontoDeRecebimento pontoDeRecebimento) throws Exception{
            dao.insert(pontoDeRecebimento);
            return pontoDeRecebimento;
    }
    
    public void update(PontoDeRecebimento pontoDeRecebimento) throws Exception{
            dao.update(pontoDeRecebimento);
    }
    
    public void delete(PontoDeRecebimento pontoDeRecebimento) throws Exception{
            dao.delete(pontoDeRecebimento);
    }
    
    public List<PontoDeRecebimento> list() throws Exception{
        List<PontoDeRecebimento> listPontoDeRecebimento= null;
          listPontoDeRecebimento = dao.list();
        return listPontoDeRecebimento;
    }
    
    public List<PontoDeRecebimento> list(String where) throws Exception{
        List<PontoDeRecebimento> listPontoDeRecebimento= null;
          listPontoDeRecebimento = dao.list(where);
        return listPontoDeRecebimento;
    }
    
    public PontoDeRecebimento getById(int id) throws Exception{
        PontoDeRecebimento pontoDeRecebimento = null;
        pontoDeRecebimento =  dao.getById(id);
        return pontoDeRecebimento;
    }  
    
}
