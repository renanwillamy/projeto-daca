package com.projetodaca.test.fornecedores;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import com.projetodaca.entities.Contato;
import com.projetodaca.entities.Endereco;
import com.projetodaca.entities.Fornecedor;
import com.projetodaca.services.FornecedorService;

public class FornecedorTest {
	private FornecedorService service;

	@Before
	public void setUp() {
		service = new FornecedorService();
	}

	@Test
	public void testCadastraFornecedor() throws Exception {

		boolean actual = false;
		boolean expected = true;
		
		Endereco endereco = new Endereco();
		endereco.setLogradouro("Rua José Augusto Gomes");
		endereco.setCep("58500-000");
		endereco.setBairro("Centro");
		endereco.setCidade("Cidade");
		endereco.setEstado("Paraiba");
		endereco.setNumResidencia(131);

		Contato contato = new Contato();

		contato.setCelular("8399922454");
		contato.setTelefone("833351154");

		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setCnpj("111.111/0001-33");
		fornecedor.setNomeFantasia("Nestle");
		fornecedor.setRazaoSocial("Maria da Silva");

		contato.setFornecedor(fornecedor);

		ArrayList<Contato> contatos = new ArrayList<>();
		contatos.add(contato);

		fornecedor.setContatos(contatos);

		fornecedor.setEndereco(endereco);

		fornecedor = service.save(fornecedor);
		
		int id = fornecedor.getId();
		
		fornecedor = null;
		
		fornecedor = service.getById(id);
		
		if(fornecedor!=null){
			actual = true;
		}
		
		assertEquals(expected, actual);
		service.delete(fornecedor);
		

	}

}
