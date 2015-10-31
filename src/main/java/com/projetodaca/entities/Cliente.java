/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


/**
 *
 * @author renan
 */
@Entity
public class Cliente implements Serializable{

    public Cliente() {
    }

    public Cliente(String nome, String cpf, String RG, String sexo, String informacoes, double limiteCredito, boolean bloqueado, Endereco endereco, ArrayList<Contato> contatos, ArrayList<Pedido> pedidos) {
        this.nome = nome;
        this.cpf = cpf;
        this.RG = RG;
        this.sexo = sexo;
        this.informacoes = informacoes;
        this.limiteCredito = limiteCredito;
        this.bloqueado = bloqueado;
        this.endereco = endereco;
        this.contatos = contatos;
        this.pedidos = pedidos;
    }

    public Cliente(String nome, String cpf, String RG, String sexo, String informacoes, double limiteCredito, boolean bloqueado) {
        this.nome = nome;
        this.cpf = cpf;
        this.RG = RG;
        this.sexo = sexo;
        this.informacoes = informacoes;
        this.limiteCredito = limiteCredito;
        this.bloqueado = bloqueado;
    }
    
    
    @Id
    @GeneratedValue
    private int id;
    
    private String nome;
    
    private String cpf;
    
    private String RG;    
    
    private String sexo;
    
    private String informacoes;
    
    private double limiteCredito;
    
    private boolean bloqueado;
    
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    private Endereco endereco;
    
    
    @OneToMany(mappedBy = "cliente",fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Contato> contatos;
    
    @OneToMany(mappedBy = "cliente",fetch = FetchType.EAGER)
    private List<Pedido> pedidos;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getInformacoes() {
        return informacoes;
    }

    public void setInformacoes(String informacoes) {
        this.informacoes = informacoes;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 53 * hash + Objects.hashCode(this.nome);
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
        final Cliente other = (Cliente) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
