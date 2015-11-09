package com.projetodaca.beans.usuarios;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Usuario;


@FacesConverter(value = "usuarioConverter")
public class UsuarioConverter implements Converter {
	private Fachada fachada;

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		fachada = new Fachada();
		
		if (value == null || (value.trim().length() == 0)) {
			return null;
		}
	
		int id = Integer.parseInt(value);
		Usuario usuario = null;
		try {
			usuario = fachada.getUsuarioById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return usuario;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		String id = "";

		if (!(value instanceof Usuario)) {
			return null;
		}

		if (value != null) {
			Usuario prod = (Usuario) value;
			id = prod.getId() + "";
			return id;
		}
		return null;
	}

}
