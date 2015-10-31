/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.services;

import java.util.List;

import com.projetodaca.dao.CategoriaDao;
import com.projetodaca.entities.Categoria;



/**
 *
 * @author renan
 */
public class CategoriaService {
    
    private CategoriaDao dao;

    public CategoriaService() {
        dao = new CategoriaDao();
    }
    
    public Categoria save(Categoria categoria) throws Exception{
            dao.insert(categoria);
            return categoria;
    }
    
    public void update(Categoria categoria) throws Exception{
            dao.update(categoria);
    }
    
    public void delete(Categoria categoria) throws Exception{
    }
    
    public List<Categoria> list() throws Exception{
        List<Categoria> listCategoria= null;
          listCategoria = dao.list();
        return listCategoria;
    }
    
    public List<Categoria> list(String where) throws Exception{
        List<Categoria> listCategoria= null;
          listCategoria = dao.list(where);
        return listCategoria;
    }
    
    public Categoria getById(int id) throws Exception{
        Categoria categoria = null;
        categoria =  dao.getById(id);
        return categoria;
    }  
    
}
