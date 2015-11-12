package com.projetodaca.beans.fornecedores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Fornecedor;


@FacesConverter(value = "fornecedorConverter")
public class FornecedorConverter implements Converter {
	@Inject
	private Fachada fachada;

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
				
		if (value == null || (value.trim().length() == 0)||value.equals("Selecione")) {
			return null;
		}
		int id = Integer.parseInt(value);
		Fornecedor fornecedor = null;
		try {
			fornecedor = fachada.getFornecedorById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fornecedor;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		String id = "";

		if (!(value instanceof Fornecedor)) {
			return null;
		}

		if (value != null) {
			Fornecedor forn = (Fornecedor) value;
			id = forn.getId() + "";
			return id;
		}
		return null;
	}

}
