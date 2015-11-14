/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


/**
 *
 * @author renan
 */
@Entity
@DiscriminatorValue("PR")
public class Promissoria extends Pagamento implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 4647355801003312889L;
	private int prazo;//dias para vencimento

    public Promissoria() {
        setTipoDePgto(TipoPagto.PROMISSORIA);
    }   

    public Promissoria(double valor,Date dataInicio,int prazo){
        this.prazo = prazo;
        setValorPagamento(valor);
        setDataInicial(dataInicio);
        Calendar calend = (GregorianCalendar) GregorianCalendar.getInstance();
        calend.setTime(dataInicio);
        calend.add(Calendar.DAY_OF_MONTH, prazo);        
        setDataPagamento(new Date(calend.getTimeInMillis()));
        setTipoDePgto(TipoPagto.PROMISSORIA);
    }

    public int getPrazo() {
        return prazo;
    }

    public void setPrazo(int prazo) {
        this.prazo = prazo;
    }
    
    
    
}
