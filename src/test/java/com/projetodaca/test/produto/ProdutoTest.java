package com.projetodaca.test.produto;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Produto;

public class ProdutoTest {
	private Fachada fachada;

	@Before
	public void setUp() throws Exception {
		fachada = new Fachada();
	}

	@Test
	public void test() throws Exception {
		Produto produto = null;
		boolean expected = false;
		boolean obtido = true;
		produto = fachada.getProdutoById(1);

		if (produto != null)
			obtido = false;

		assertEquals(expected, obtido);

	}

}
