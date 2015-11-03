package com.projetodaca.test.produto;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Produto;

public class ProdutoTest {
	private Fachada fachada;

	@Before
	public void setUp() throws Exception {
		fachada = new Fachada();
	}

	@Test
	public void testGetProdutoById1() throws Exception {
		Produto produto = null;
		boolean expected = false;
		boolean obtido = true;
		produto = fachada.getProdutoById(1);

		if (produto != null)
			obtido = false;

		assertEquals(expected, obtido);

	}
	
	@Test
	public void testGetProdutoByName() throws Exception {
		List<Produto>produtos = null;
		boolean expected = false;
		boolean obtido = true;
		produtos = fachada.listProduto("WHERE e.nome like '%Tv%'");

		if (produtos != null && !produtos.isEmpty())
			obtido = false;
		
		for(Produto p: produtos){
			System.out.println(p.toString());
		}
		
		assertEquals(expected, obtido);

	}

}
