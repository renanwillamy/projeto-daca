package com.projetodaca.beans.produtos;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Fornecedor;


@FacesConverter(value = "fornecedorConverter", forClass = Fornecedor.class)
public class FornecedorConverter implements Converter {
	private Fachada fachada;

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		fachada = new Fachada();
		
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
