package com.projetodaca.test.usuario;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import com.projetodaca.entities.Usuario;
import com.projetodaca.services.UsuarioService;

public class UsuarioTest {
	private UsuarioService service;
	@Before
	public void setUp() throws Exception {
		service = new UsuarioService();
	}

	@Test
	public void test() throws Exception {
		
		Usuario usuario = service.autenticaUsuario("rw23", "222");
		boolean actual = false;
		boolean expected = true;
		
		if(usuario!=null){
			actual = true;
		}
		
		assertEquals(expected, actual);
		
	}

}
