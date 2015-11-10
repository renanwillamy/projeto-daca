/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.dao;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author renan
 */
public interface IDAO<T> {

    public void insert(T object) throws Exception;

    public List<T> list() throws Exception;

    public T getById(int id) throws Exception;

    public void update(T object) throws Exception;

    public void delete(T object) throws Exception;

   

}
