package com.projetodaca.beans.categorias;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Categoria;


@FacesConverter(value = "categoriaConverter", forClass = Categoria.class)
public class CategoriaConverter implements Converter {
	@Inject
	private Fachada fachada;

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
				
		if (value == null || (value.trim().length() == 0)||value.equals("Selecione")) {
			return null;
		}

		int id = Integer.parseInt(value);
		Categoria Categoria = null;
		try {
			Categoria = fachada.getCategoriaById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Categoria;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		String id = "";

		if (!(value instanceof Categoria)) {
			return null;
		}

		if (value != null) {
			Categoria cat = (Categoria) value;
			id = cat.getId() + "";
			return id;
		}
		return null;
	}

}
