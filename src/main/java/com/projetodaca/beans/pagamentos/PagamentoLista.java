package com.projetodaca.beans.pagamentos;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projetodaca.beans.AbstractManageBean;
import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Avista;
import com.projetodaca.entities.Pagamento;

@RequestScoped
@Named
public class PagamentoLista extends AbstractManageBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6405394078384105388L;
	@Inject
	private Avista pagamentoSelecionado;
	private List<Avista> pagamentos;
	@Inject
	private Fachada fachada;
	private String filtro;

	@PostConstruct
	public void start() {
		filtrar();
	}

	public List<Avista> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Avista> pagamentos) {
		this.pagamentos = pagamentos;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}


	public void filtrar() {

		try {
			if (filtro == null)
				filtro = "";
			
		//pagamentos = fachada.listPagamentoPorId(filtro);
		pagamentos = fachada.listPagamentosAvistaPorId(filtro);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Pagamento getPagamentoSelecionado() {
		return pagamentoSelecionado;
	}

	public void setPagamentoSelecionado(Avista pagamento) {
		this.pagamentoSelecionado = pagamento;
	}

}
