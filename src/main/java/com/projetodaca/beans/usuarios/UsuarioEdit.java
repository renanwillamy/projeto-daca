package com.projetodaca.beans.usuarios;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import com.projetodaca.beans.AbstractManageBean;
import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Categoria;
import com.projetodaca.entities.Fornecedor;
import com.projetodaca.entities.Usuario;

@ViewScoped
@ManagedBean
public class UsuarioEdit extends AbstractManageBean {
	private Fachada fachada;
	private Usuario usuario;

	
	@PostConstruct
	public void start() {
		fachada = new Fachada();
		if(usuario==null){
			usuario = new Usuario();
		}
	
	}	

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
