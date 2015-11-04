package com.projetodaca.beans.produtos;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("produtoAtivoConverter")
public class ProdutoAtivoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		if(value==null||value.equals(""))
			return null;
		
		Boolean ativo = Boolean.valueOf(value);
		
		return ativo;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if(value==null)
			return null;
		
		if(((Boolean)value) == true)
			return "Sim";
		
		return "Não";
	}

}
