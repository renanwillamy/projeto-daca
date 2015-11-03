package com.projetodaca.beans.produtos;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("produtoCustoValidator")
public class ProdutoCustoValidator implements Validator {

	public void validate(FacesContext arg0, UIComponent arg1, Object value) throws ValidatorException {

		try {
			double valor = (Double) value;

			if (valor <= 0.0) {
				FacesMessage msg = new FacesMessage("Erro de Validação", "O valor deve ser maior que zero.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}

		} catch (Exception e) {
			FacesMessage msg = new FacesMessage("Erro de Validação", "O valor digitado é inválido");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}

	}

}
