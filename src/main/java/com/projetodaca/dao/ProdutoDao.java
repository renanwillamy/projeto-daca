/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.projetodaca.entities.Produto;

/**
 *
 * @author renan
 */
public class ProdutoDao extends AbstractDao<Produto> {

	public void insert(Produto produto) throws Exception {
		EntityManager manager = getEntityManager();
		try {
			manager.persist(produto);
			manager.refresh(produto);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<Produto> list() throws Exception {
		EntityManager manager = getEntityManager();
		List<Produto> list = new ArrayList<Produto>();
		try {
			list = (List<Produto>) manager.createQuery("SELECT e FROM Produto e", Produto.class).getResultList();

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return list;
	}

	private List<Produto> list(String where) throws Exception {
		EntityManager manager = getEntityManager();
		List<Produto> list = new ArrayList<Produto>();
		try {
			list = (List<Produto>) manager.createQuery("SELECT e FROM Produto e " + where, Produto.class)
					.getResultList();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return list;
	}

	public List<Produto> listaProdutoPorNome(String nome) throws Exception {
		String where = "WHERE e.nome like '%" + nome + "%'";
		return list(where);
	}
	
	public List<Produto> listaProdutoAtivos() throws Exception {
		String where = "WHERE e.ativo = 1";
		return list(where);
	}

	public Produto getById(int id) throws Exception {
		EntityManager manager = getEntityManager();
		Produto produto = null;
		try {
			produto = manager.find(Produto.class, id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return produto;
	}

	public void update(Produto produto) throws Exception {
		EntityManager manager = getEntityManager();
		try {
			manager.merge(produto);
		} catch (Exception ex) {

			throw new Exception(ex);
		} 
	}

	public void delete(Produto produto) throws Exception {
		EntityManager manager = getEntityManager();
		try {
			manager.remove(manager.getReference(Produto.class, produto.getId()));
		} catch (Exception ex) {

			throw new Exception(ex);
		}
	}

}
