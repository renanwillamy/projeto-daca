package com.projetodaca.test.categoria;

import org.junit.Before;
import org.junit.Test;

import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Categoria;

public class CategoriaTest {

	private Fachada fachada;
	@Before
	public void setUp() throws Exception {
		fachada = new Fachada();
	}

	@Test
	public void test() throws Exception {
		Categoria cat = new Categoria("CatTest",null);
		fachada.saveCategoria(cat);
	}

}
