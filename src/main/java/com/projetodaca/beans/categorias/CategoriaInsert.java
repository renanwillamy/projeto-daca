package com.projetodaca.beans.categorias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.hibernate.JDBCException;
import org.hibernate.exception.ConstraintViolationException;

import com.projetodaca.beans.AbstractManageBean;
import com.projetodaca.core.Fachada;
import com.projetodaca.entities.Categoria;
import com.projetodaca.entities.Fornecedor;

@ViewScoped
@ManagedBean
public class CategoriaInsert extends AbstractManageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8559018315064616512L;
	
	private Fachada fachada;
	private Categoria categoria;
	private List<Categoria> categorias;

	@PostConstruct
	public void start() {
		fachada = new Fachada();
		categoria = new Categoria();
		listaCategorias();
	}

	private void listaCategorias() {
		try {
			categorias = fachada.listCategoria();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String insertCategoria() {

		try {

			fachada.saveCategoria(categoria);
			showFlashMessageInfo("Categoria Salva!");			
		} catch (Exception e) {
			if(e.toString().contains("ConstraintViolationException")){
				showFlashMessageError("Essa Categoria já existe!");
			}else{
			showFlashMessageError("Erro ao tentar salvar categoria!");
			}
			e.printStackTrace();
			
		}
	
		return "insert_cat?faces-redirect=true";
	}

	public void excluir(Categoria categoria){
		try {
			fachada.deleteCategoria(categoria);
			showFlashMessageInfo("Categoria Excluida!");	
			listaCategorias();
		} catch (Exception e) {
			showFlashMessageError("Erro ao tentar excluir categoria!");
			e.printStackTrace();
		}
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}


}
