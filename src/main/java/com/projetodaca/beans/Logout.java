package com.projetodaca.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class Logout extends AbstractManageBean {

	public String efetuarLogout() {

		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.invalidate();
		return "/index.xhtml?faces-redirect=true";
	}

}
