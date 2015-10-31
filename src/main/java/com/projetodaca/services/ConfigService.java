/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.services;

import java.util.List;

import com.projetodaca.dao.ConfigDao;
import com.projetodaca.entities.Config;

/**
 *
 * @author renan
 */
public class ConfigService {
    
    private ConfigDao dao;

    public ConfigService() {
       dao = new ConfigDao();
    }
    
    /**Persiste config no banco de dados
     * 
     * @param config 
     */
    public void save(Config config) throws Exception{
            dao.insert(config);
    }
    
    public void update(Config config) throws Exception{
            dao.update(config);
    }
    
    public void delete(Config config) throws Exception{
            dao.delete(config);
    }
    
    public List<Config> list() throws Exception{
        List<Config> listConfig= null;
          listConfig = dao.list();
        return listConfig;
    }
    
    public Config getById(int id) throws Exception{
        Config config = null;
        config =  dao.getById(id);
        return config;
    }
    
    
}
