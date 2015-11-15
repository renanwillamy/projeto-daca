/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.services;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import com.projetodaca.dao.FornecedorDao;
import com.projetodaca.entities.Fornecedor;


/**
 *
 * @author renan
 */
public class FornecedorService {

	@Inject
	private FornecedorDao dao;


	/**
	 * Persiste fornecedor no banco de dados
	 * 
	 * @param fornecedor
	 */
	@Transactional
	public Fornecedor save(Fornecedor fornecedor) throws Exception {
		dao.insert(fornecedor);
		return fornecedor;
	}
	@Transactional
	public void update(Fornecedor fornecedor) throws Exception {
		dao.update(fornecedor);
	}
	@Transactional
	public void delete(Fornecedor fornecedor) throws Exception {
		dao.delete(fornecedor);
	}
	@Transactional
	public List<Fornecedor> list() throws Exception {
		List<Fornecedor> listFornecedor = null;
		listFornecedor = dao.list();
		return listFornecedor;
	}
	@Transactional
	public List<Fornecedor> listaFornecedorPorNomeFantasia(String nomeFantasia) throws Exception {
		List<Fornecedor> listFonecedor = null;
		listFonecedor = dao.listaFornecedorPorNomeFantasia(nomeFantasia);
		return listFonecedor;
	}
	@Transactional
	public Fornecedor getById(int id) throws Exception {
		Fornecedor fornecedor = null;
		fornecedor = dao.getById(id);
		return fornecedor;
	}

}
