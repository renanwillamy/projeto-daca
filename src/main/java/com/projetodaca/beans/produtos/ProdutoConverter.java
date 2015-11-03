package com.projetodaca.beans.produtos;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Produto;


@FacesConverter(value = "produtoConverter")
public class ProdutoConverter implements Converter {
	private Fachada fachada;

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		fachada = new Fachada();
		
		if (value == null || (value.trim().length() == 0)) {
			return null;
		}
	
		int id = Integer.parseInt(value);
		Produto produto = null;
		try {
			produto = fachada.getProdutoById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return produto;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		String id = "";

		if (!(value instanceof Produto)) {
			return null;
		}

		if (value != null) {
			Produto prod = (Produto) value;
			id = prod.getId() + "";
			return id;
		}
		return null;
	}

}
