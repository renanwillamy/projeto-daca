package com.projetodaca.beans.usuarios;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projetodaca.beans.AbstractManageBean;
import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Usuario;

@RequestScoped
@Named
public class UsuarioLista extends AbstractManageBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8463593762461424182L;
	private List<Usuario> usuarios;
	@Inject
	private Fachada fachada;
	private String filtro;

	@PostConstruct
	public void start() {
	
		filtrar();
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public void excluir(Usuario usuario) {
		if (usuario != null) {

			try {
				fachada.deleteUsuario(usuario);
				filtrar();
				showFlashMessageInfo("Usuario excluido!");
			} catch (Exception e) {
				showFlashMessageError("Ocorreu um erro ao tentar excluir o usuario!");
				e.printStackTrace();
			}
		}

	}

	public void filtrar() {

		try {
			if (filtro == null)
				filtro = "";
			
			usuarios = fachada.listaUsuarioPorNome(filtro);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

}
