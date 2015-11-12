package com.projetodaca.beans.usuarios;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projetodaca.beans.AbstractManageBean;
import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Usuario;

@ViewScoped
@Named
public class UsuarioInsert extends AbstractManageBean implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2358902591681399695L;
	@Inject
	private Fachada fachada;
	@Inject
	private Usuario usuario;
	


	public String insertUsuario() {

		try {

			fachada.saveUsuario(usuario);
			showFlashMessageInfo("Usuario Salvo!");	
			usuario = new Usuario();
		} catch (Exception e) {
			System.out.println("Erro");
			e.printStackTrace();
			if(e.toString().contains("ConstraintViolationException"))
				showFlashMessageError("Já existe um usuário com este login!");	
			else
			showFlashMessageError("Erro ao tentar salvar usuario!");	
			return null;
		}	
	
		return "insert_usuario.xhtml?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}	

}
