package com.projetodaca.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractDao<T> implements IDAO<T> {

	@PersistenceContext(unitName = "AutomacaoPU")
	protected EntityManager manager;
	
	
}
