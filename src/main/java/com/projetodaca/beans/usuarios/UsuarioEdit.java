package com.projetodaca.beans.usuarios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projetodaca.beans.AbstractManageBean;
import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Categoria;
import com.projetodaca.entities.Fornecedor;
import com.projetodaca.entities.Usuario;

@SessionScoped
@Named
public class UsuarioEdit extends AbstractManageBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4750876725767583010L;
	@Inject
	private Fachada fachada;
	@Inject
	private Usuario usuario;

	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String updateUsuario(){
		try {
			fachada.updateUsuario(usuario);
			showFlashMessageInfo("Usuário Alterado!");
		} catch (Exception e) {
			showFlashMessageError("Ocorreu um erro ao tentar alterar o usuário!");
			e.printStackTrace();
			return "edit_usu.xhtml";
		}
		return "lista_usuarios?faces-redirect=true";
	}
		
}
