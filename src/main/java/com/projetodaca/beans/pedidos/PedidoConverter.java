package com.projetodaca.beans.pedidos;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Pedido;


@FacesConverter(value = "pedidoConverter")
public class PedidoConverter implements Converter {
	@Inject
	private Fachada fachada;

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {		
		
		if (value == null || (value.trim().length() == 0)) {
			return null;
		}
	
		int id = Integer.parseInt(value);
		Pedido pedido = null;
		try {
			pedido = fachada.getPedidoById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pedido;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		String id = "";

		if (!(value instanceof Pedido)) {
			return null;
		}

		if (value != null) {
			Pedido ped = (Pedido) value;
			id = ped.getId() + "";
			return id;
		}
		return null;
	}

}
