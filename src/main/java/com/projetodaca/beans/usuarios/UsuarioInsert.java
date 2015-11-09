package com.projetodaca.beans.usuarios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;

import com.projetodaca.beans.AbstractManageBean;
import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Categoria;
import com.projetodaca.entities.Fornecedor;
import com.projetodaca.entities.Usuario;

@ViewScoped
@ManagedBean
public class UsuarioInsert extends AbstractManageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8559018315064616512L;
	
	private Fachada fachada;
	private Usuario usuario;
	

	@PostConstruct
	public void start() {
		fachada = new Fachada();
		usuario = new Usuario();
		
	}

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
