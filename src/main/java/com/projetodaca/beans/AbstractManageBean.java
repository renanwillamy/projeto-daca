package com.projetodaca.beans;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class AbstractManageBean {
	
	private void addFlashMessageInfo(boolean isError,String message) {
		Severity facesMessage = FacesMessage.SEVERITY_INFO;
		if(isError){
			facesMessage = FacesMessage.SEVERITY_ERROR;
		}
		
	    FacesContext context = FacesContext.getCurrentInstance();
	    context.getExternalContext().getFlash().setKeepMessages(true);
	    context.addMessage(null, new FacesMessage(facesMessage, message, null));
	}
	
	public void showFlashMessageError(String message){
		addFlashMessageInfo(true, message);
	}
	
	public void showFlashMessageInfo(String message){
		addFlashMessageInfo(false, message);
	}
	
}
