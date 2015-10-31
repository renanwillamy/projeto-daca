/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.entities;

/**
 *
 * @author renan
 */
public enum SituacaoCheque {
    NAO_DEPOSITADO("Não depositado"),DEPOSITADO("Depositado"),SACADO("Sacado"),DEVOLVIDO("Devolvido");
    
    protected String nome;

    private SituacaoCheque(String nome) {
        this.nome = nome;
    }
   
    
}
