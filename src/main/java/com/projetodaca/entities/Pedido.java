/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author renan
 */
@Entity
public class Pedido implements Serializable {

    public Pedido() {
    }
    
    @Id
    @GeneratedValue
    private int id;
    
    private Date dataDoPedido;
    
    private double descontoReais;
    
    private double descontoPorCento;    
    
    @OneToMany(fetch =FetchType.EAGER,mappedBy = "pedido",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ItensDoPedido>itensDoPedido;    
    
    @ManyToOne
    @JoinColumn
    private Cliente cliente;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataDoPedido() {
        return dataDoPedido;
    }

    public void setDataDoPedido(Date dataDoPedido) {
        this.dataDoPedido = dataDoPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public double getDescontoReais() {
        return descontoReais;
    }

    public void setDescontoReais(double descontoReais) {
        this.descontoReais = descontoReais;
    }

    public double getDescontoPorCento() {
        return descontoPorCento;
    }

    public void setDescontoPorCento(double descontoPorCento) {
        this.descontoPorCento = descontoPorCento;
    }

    public List<ItensDoPedido> getItensDoPedido() {
        return itensDoPedido;
    }

    public void setItensDoPedido(List<ItensDoPedido> itensDoPedido) {
        this.itensDoPedido = itensDoPedido;
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
        final Pedido other = (Pedido) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
}
