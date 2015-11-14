/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.entities;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author renan
 */
@Entity
@DiscriminatorValue("CH")
public class Cheque extends Pagamento {

    /**
	 * 
	 */
	private static final long serialVersionUID = 329937007691322787L;

	private String banco;

    private String agencia;

    private String conta;

    private String titular;

    private String numeroCheque;

    @Enumerated(EnumType.STRING)
    private SituacaoCheque situacao;

    public Cheque() {
        setTipoDePgto(TipoPagto.CHEQUE);
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getNumeroCheque() {
        return numeroCheque;
    }

    public void setNumeroCheque(String numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    @Override
    public double getValorPago() {
        return valorPago;
    }

    @Override
    public void setValorPago(double valor) {
        valorPago = valor;
    }

    @Override
    public Status getStatus() {
        Calendar calend = GregorianCalendar.getInstance();
        Date dataAtual = new Date(calend.getTimeInMillis());

        //se os pagamentos > 0 significa que pagaram como outras formas de pagamento
        if (getPagamentos().size() > 0) {
            if(getDataPagamento().compareTo(dataAtual) == -1 && getRestanteDaDivida()>0){
                 setStatus(Status.ATRASADO);
            }else if(getValorPago()>0&& getRestanteDaDivida()>0){
                setStatus(Status.PARCIALMENTE_LIQUIDADO);
            }else{
                setStatus(Status.LIQUIDADO);
            }
        } else {

            if (getDataPagamento().compareTo(dataAtual) == -1 && getSituacao() != SituacaoCheque.DEPOSITADO && getSituacao() != SituacaoCheque.SACADO) {
                setStatus(Status.ATRASADO);
            } else {
                setStatus(Status.LIQUIDADO);
            }
        }
        return status;
    }

    public SituacaoCheque getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoCheque situacao) {
        this.situacao = situacao;
    }

}
