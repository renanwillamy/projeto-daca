/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author renan
 */
@Entity
public class Permissoes implements Serializable{

    public Permissoes() {
    }
    
    @Id
    @GeneratedValue
    private int id;
    
    private boolean manterUsuario;
    private boolean manterCliente;
    private boolean manterEmpresa;
    private boolean manterFuncionarios;
    private boolean manterProdutos;
    private boolean manterCategorias;
    private boolean gerarRelatorios;
    private boolean consultarTitulos;
    private boolean consultarClientesInadimplentes;
    private boolean manterFornecedor;
    private boolean vendaInterna;
    private boolean caixa;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isManterUsuario() {
        return manterUsuario;
    }

    public void setManterUsuario(boolean manterUsuario) {
        this.manterUsuario = manterUsuario;
    }

    public boolean isManterCliente() {
        return manterCliente;
    }

    public void setManterCliente(boolean manterCliente) {
        this.manterCliente = manterCliente;
    }

    public boolean isGerarRelatorios() {
        return gerarRelatorios;
    }

    public void setGerarRelatorios(boolean gerarRelatorios) {
        this.gerarRelatorios = gerarRelatorios;
    }

    public boolean isManterFornecedor() {
        return manterFornecedor;
    }

    public void setManterFornecedor(boolean manterFornecedor) {
        this.manterFornecedor = manterFornecedor;
    }

    public boolean isVendaInterna() {
        return vendaInterna;
    }

    public void setVendaInterna(boolean vendaInterna) {
        this.vendaInterna = vendaInterna;
    }

    public boolean isManterEmpresa() {
        return manterEmpresa;
    }

    public void setManterEmpresa(boolean manterEmpresa) {
        this.manterEmpresa = manterEmpresa;
    }

    public boolean isManterFuncionarios() {
        return manterFuncionarios;
    }

    public void setManterFuncionarios(boolean manterFuncionarios) {
        this.manterFuncionarios = manterFuncionarios;
    }

    public boolean isManterProdutos() {
        return manterProdutos;
    }

    public void setManterProdutos(boolean manterProdutos) {
        this.manterProdutos = manterProdutos;
    }

    public boolean isManterCategorias() {
        return manterCategorias;
    }

    public void setManterCategorias(boolean manterCategorias) {
        this.manterCategorias = manterCategorias;
    }

    public boolean isConsultarTitulos() {
        return consultarTitulos;
    }

    public void setConsultarTitulos(boolean consultarTitulos) {
        this.consultarTitulos = consultarTitulos;
    }

    public boolean isConsultarClientesInadimplentes() {
        return consultarClientesInadimplentes;
    }

    public void setConsultarClientesInadimplentes(boolean consultarClientesInadimplentes) {
        this.consultarClientesInadimplentes = consultarClientesInadimplentes;
    }

    public boolean isCaixa() {
        return caixa;
    }

    public void setCaixa(boolean caixa) {
        this.caixa = caixa;
    }

        
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
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
        final Permissoes other = (Permissoes) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
   
    
}
