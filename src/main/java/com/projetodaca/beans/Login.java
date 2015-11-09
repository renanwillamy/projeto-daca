package com.projetodaca.beans;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Usuario;

@ViewScoped
@ManagedBean
public class Login extends AbstractManageBean{
   
	private Fachada fachada;
	private String login;
	private String senha;
	
	@PostConstruct
	public void start(){
		fachada = new Fachada(); 
	}
	
	public String autentica(){
		
		try {
			Usuario usuario = fachada.autenticaUsuario(login, senha);
			if(usuario!=null){
				showFlashMessageInfo("Seja Bem vindo "+usuario.getNome()+"!");
				return "index?faces-redirect=true";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		showFlashMessageError("Usuário ou Senha não encontrado!");
		
		return null;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
}
