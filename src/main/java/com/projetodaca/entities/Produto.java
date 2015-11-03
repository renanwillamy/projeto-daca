/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author renan
 */
@Entity
public class Produto implements Serializable{

    public Produto() {
    }    

    public Produto(String nome, String und, double quantidade, double precoVenda, Categoria categoria, Fornecedor fornecedor) {
        this.nome = nome;
        this.und = und;
        this.quantidade = quantidade;
        this.precoVenda = precoVenda;
        this.categoria = categoria;
        this.fornecedor = fornecedor;
    }
    
    
        
    @Id
    @GeneratedValue
    private int id;
    
    private String nome;
    
    private String und;
    
    private double quantidade;
    
    private String codigoDeBarras;
    
    private int estoqueMinimo;
    
    private double precoVenda;
    
    private double precoCusto;
    
    private boolean ativo;
    
    @ManyToOne
    @JoinColumn
    private Categoria categoria;
    
    @ManyToOne
    @JoinColumn
    private Fornecedor fornecedor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnd() {
        return und;
    }

    public void setUnd(String und) {
        this.und = und;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(int estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.nome);
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.quantidade) ^ (Double.doubleToLongBits(this.quantidade) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (Double.doubleToLongBits(this.quantidade) != Double.doubleToLongBits(other.quantidade)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
    	String txt = "Id: "+id+" Nome: "+nome+" Quantidade: "+quantidade+" Und: "+und+" Cod. Barras: "+codigoDeBarras+
    			" Estoque Min. :"+estoqueMinimo+" Preço de Venda: "+precoVenda+" Preço Custo:"+precoCusto+" Ativo: "+ativo+" Fornecedor: "+
    			fornecedor.getNomeFantasia()+" Categoria:"+categoria.getNome();
    	return txt;
    }
    
    
    
}
