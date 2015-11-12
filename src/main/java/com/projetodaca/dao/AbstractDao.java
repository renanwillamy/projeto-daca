package com.projetodaca.dao;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractDao<T> implements IDAO<T> ,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8520285033478118775L;
	@Inject
	protected EntityManager manager;
	
	protected EntityManager getEntityManager() {
		return manager;
	}
	
	
}
