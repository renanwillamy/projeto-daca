package com.projetodaca.beans.clientes;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Cliente;


@FacesConverter(value = "clienteConverter")
public class ClienteConverter implements Converter {
	@Inject
	private Fachada fachada;

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
	
		
		if (value == null || (value.trim().length() == 0)||value.equals("Selecione")) {
			return null;
		}
		int id = Integer.parseInt(value);
		Cliente cliente = null;
		try {
			cliente = fachada.getClienteById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cliente;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		String id = "";

		if (!(value instanceof Cliente)) {
			return null;
		}

		if (value != null) {
			Cliente forn = (Cliente) value;
			id = forn.getId() + "";
			return id;
		}
		return null;
	}

}
