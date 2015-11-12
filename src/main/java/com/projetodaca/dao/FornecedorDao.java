/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.projetodaca.entities.Contato;
import com.projetodaca.entities.Fornecedor;

/**
 *
 * @author renan
 */
public class FornecedorDao extends AbstractDao<Fornecedor> {


	public void insert(Fornecedor fornecedor) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.persist(fornecedor);
			em.refresh(fornecedor);
		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}
	}

	private List<Fornecedor> list(String where) throws Exception {
		EntityManager em = getEntityManager();
		List<Fornecedor> list = new ArrayList<Fornecedor>();
		try {
			list = (List<Fornecedor>) em.createQuery("SELECT e FROM Fornecedor e " + where, Fornecedor.class)
					.getResultList();

		} catch (Exception e) {			
			throw new Exception(e.getMessage());
		}
		return list;
	}

	public List<Fornecedor> listaFornecedorPorNomeFantasia(String nomeFantasia) throws Exception {
		String where = "WHERE e.nomeFantasia like '%" + nomeFantasia + "%'";
		return list(where);
	}

	public List<Fornecedor> list() throws Exception {
		EntityManager em = getEntityManager();
		List<Fornecedor> list = new ArrayList<Fornecedor>();
		try {
			list = (List<Fornecedor>) em.createQuery("SELECT e FROM Fornecedor e", Fornecedor.class).getResultList();

		} catch (Exception e) {			
			throw new Exception(e.getMessage());
		}
		return list;
	}

	public Fornecedor getById(int id) throws Exception {
		EntityManager em = getEntityManager();
		Fornecedor fornecedor = null;
		try {			
			fornecedor = em.find(Fornecedor.class, id);	
		} catch (Exception e) {
						throw new Exception(e.getMessage());
		} 

		return fornecedor;
	}

	public void update(Fornecedor fornecedor) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.merge(fornecedor);
		} catch (Exception ex) {

			throw new Exception(ex);
		}
	}

	public void delete(Fornecedor fornecedor) throws Exception {
		EntityManager em = getEntityManager();
		try {

			for (Contato contato : fornecedor.getContatos()) {
				em.remove(em.getReference(Contato.class, contato.getID()));
			}
			em.remove(em.getReference(Fornecedor.class, fornecedor.getId()));

		} catch (Exception ex) {

			throw new Exception(ex);
		}
	}

}
