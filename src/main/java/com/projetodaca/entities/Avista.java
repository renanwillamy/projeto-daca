/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package com.projetodaca.entities;

package com.projetodaca.entities;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.projetodaca.entities.Pagamento;



/**
 *
 * @author renan
 */
@Entity
@DiscriminatorValue("AV")
public class Avista extends Pagamento{

    
    
    public Avista() {
        Calendar calend = (GregorianCalendar) GregorianCalendar.getInstance();
        Date dataPagamento = new Date(calend.getTimeInMillis());
        setDataInicial(dataPagamento);
        setDataPagamento(dataPagamento);     
        setTipoDePgto(TipoPagto.AVISTA);
    }
    
    public Avista(Date dataPagamento) {
        setDataInicial(dataPagamento);
        setDataPagamento(dataPagamento);
        setTipoDePgto(TipoPagto.AVISTA);
    }
   
    
}
