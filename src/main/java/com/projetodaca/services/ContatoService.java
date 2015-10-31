/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.services;

import java.util.List;

import com.projetodaca.dao.ContatoDao;
import com.projetodaca.entities.Contato;

/**
 *
 * @author renan
 */
public class ContatoService {
    
    private ContatoDao dao;

    public ContatoService() {
       dao = new ContatoDao();
    }
    
    /**Persiste contato no banco de dados
     * 
     * @param contato 
     */
    public Contato save(Contato contato) throws Exception{
            dao.insert(contato);
            return contato;
    }
    
    public void update(Contato contato) throws Exception{
            dao.update(contato);
    }
    
    public void delete(Contato contato) throws Exception{
            dao.delete(contato);
    }
    
    public List<Contato> list() throws Exception{
        List<Contato> listContato= null;
          listContato = dao.list();
        return listContato;
    }
    
    public Contato getById(int id) throws Exception{
        Contato contato = null;
        contato =  dao.getById(id);
        return contato;
    }
    
}
