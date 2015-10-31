/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.services;

import java.util.List;

import com.projetodaca.dao.EmpresaDao;
import com.projetodaca.entities.Empresa;

/**
 *
 * @author renan
 */
public class EmpresaService {
    
    private EmpresaDao dao;

    public EmpresaService() {
       dao = new EmpresaDao();
    }
    
    /**Persiste empresa no banco de dados
     * 
     * @param empresa 
     */
    public Empresa save(Empresa empresa) throws Exception{
            dao.insert(empresa);
            return empresa;
    }
    
    public void update(Empresa empresa) throws Exception{
            dao.update(empresa);
    }
    
    public void delete(Empresa empresa) throws Exception{
            dao.delete(empresa);
    }
    
    public List<Empresa> list() throws Exception{
        List<Empresa> listEmpresa= null;
          listEmpresa = dao.list();
        return listEmpresa;
    }
    public List<Empresa> list(String where) throws Exception{
        List<Empresa> listEmpresa= null;
          listEmpresa = dao.list(where);
        return listEmpresa;
    }
    
    public Empresa getById(int id) throws Exception{
        Empresa empresa = null;
        empresa =  dao.getById(id);
        return empresa;
    }
    
}
