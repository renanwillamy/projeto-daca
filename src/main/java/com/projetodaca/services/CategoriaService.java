/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.services;

import java.util.List;

import javax.inject.Inject;

import com.projetodaca.dao.CategoriaDao;
import com.projetodaca.entities.Categoria;
import com.projetodaca.utils.TransacionalCdi;



/**
 *
 * @author renan
 */
public class CategoriaService {
    
	@Inject
    private CategoriaDao dao;    
    
	@TransacionalCdi
    public Categoria save(Categoria categoria) throws Exception{
            dao.insert(categoria);
            return categoria;
    }
	@TransacionalCdi
    public void update(Categoria categoria) throws Exception{
            dao.update(categoria);
    }
	@TransacionalCdi
    public void delete(Categoria categoria) throws Exception{
    	dao.delete(categoria);
    }
	@TransacionalCdi
    public List<Categoria> list() throws Exception{
        List<Categoria> listCategoria= null;
          listCategoria = dao.list();
        return listCategoria;
    }
	@TransacionalCdi
    public List<Categoria> list(String where) throws Exception{
        List<Categoria> listCategoria= null;
          listCategoria = dao.list(where);
        return listCategoria;
    }
	@TransacionalCdi
    public Categoria getById(int id) throws Exception{
        Categoria categoria = null;
        categoria =  dao.getById(id);
        return categoria;
    }  
    
}
