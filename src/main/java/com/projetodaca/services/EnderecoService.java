/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.services;

import java.util.List;

import com.projetodaca.dao.EnderecoDao;
import com.projetodaca.entities.Endereco;

/**
 *
 * @author renan
 */
public class EnderecoService {
    
    private EnderecoDao dao;

    public EnderecoService() {
       dao = new EnderecoDao();
    }
    
    /**Persiste endereco no banco de dados
     * 
     * @param endereco 
     */
    public Endereco save(Endereco endereco) throws Exception{
            dao.insert(endereco);
            return endereco;
    }
    
    public void update(Endereco endereco) throws Exception{
            dao.update(endereco);
    }
    
    public void delete(Endereco endereco) throws Exception{
            dao.delete(endereco);
    }
    
    public List<Endereco> list() throws Exception{
        List<Endereco> listEndereco= null;
          listEndereco = dao.list();
        return listEndereco;
    }
    
    public Endereco getById(int id) throws Exception{
        Endereco endereco = null;
        endereco =  dao.getById(id);
        return endereco;
    }
    
}
