/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author renan
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("PG")
public class Pagamento implements Serializable {

    public Pagamento() {
        setStatus(status.ABERTO);
    }

    @Id
    @GeneratedValue
    private int id;

    @Enumerated(EnumType.STRING)
    private TipoPagto tipoDePgto;
    @Enumerated(EnumType.STRING)
    protected Status status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicial;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPagamento;
    private double valorPagamento;

    private double juros;

    private double desconto;

    protected double valorPago;

    @ManyToOne
    private Caixa caixa;

    @OneToMany(mappedBy = "pagamento", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Pagamento> pagamentos;

    @ManyToOne
    private Pagamento pagamento;

    @OneToOne
    private Pedido pedido;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoPagto getTipoDePgto() {
        return tipoDePgto;
    }

    public void setTipoDePgto(TipoPagto tipoDePgto) {
        this.tipoDePgto = tipoDePgto;
    }

    public Status getStatus() {
        Calendar calend = GregorianCalendar.getInstance();
        Date dataAtual = new Date(calend.getTimeInMillis());
        valorPago = getValorPago();
        if (round2(getRestanteDaDivida()) == 0) {
            setStatus(Status.LIQUIDADO);
        } else if (getRestanteDaDivida() < getValorPagamento()) {
            if (dataPagamento.compareTo(dataAtual) == -1) {
                setStatus(Status.ATRASADO);
            } else {
                setStatus(Status.PARCIALMENTE_LIQUIDADO);
            }
        } else {
            if (dataPagamento.compareTo(dataAtual) == -1) {
                setStatus(Status.ATRASADO);
            } else {
                setStatus(Status.ABERTO);
            }
        }
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public double getValorPagamento() {
        return valorPagamento;
    }

    public void setValorPagamento(double valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public void efetuarPagamento(Pagamento pagamento) {
        getPagamentos().add(pagamento);
        getStatus();
    }

    public double getRestanteDaDivida() {
        return (getValorPagamento() - getValorPago()) + getJuros() - getDesconto();
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
        getStatus();
    }

    public double getValorPago() {
        if (pagamentos != null) {
            if (pagamentos.size() > 0) {
                valorPago = 0;
            } else {
                return valorPago;
            }
            for (Pagamento pag : pagamentos) {
                if (pag.getStatus() == Status.LIQUIDADO) {
                    valorPago += pag.getValorPago();
                }
            }
        }
        return valorPago;
    }

    public Caixa getCaixa() {
        return caixa;
    }

    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }

    public double getJuros() {
        return juros;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    private Double round2(Double val) {
        return new BigDecimal(val.toString()).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.pedido);
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
        final Pagamento other = (Pagamento) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.pedido, other.pedido)) {
            return false;
        }
        return true;
    }

}
