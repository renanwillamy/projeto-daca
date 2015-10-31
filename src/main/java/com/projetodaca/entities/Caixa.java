/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.entities;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author renan
 */
@Entity
public class Caixa implements Serializable {

    @Id
    @GeneratedValue
    private int ID;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAbertura;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFechamento;

    private double valorInicial;

    private double valorFinal;

    private boolean aberto;

    @ManyToOne
    private PontoDeRecebimento pontoDeRecebimento;

    @OneToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "caixa",cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Pagamento> pagamentos;

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Date getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(Date dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public double getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(double valorInicial) {
        this.valorInicial = valorInicial;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    public PontoDeRecebimento getPontoDeRecebimento() {
        return pontoDeRecebimento;
    }

    public void setPontoDeRecebimento(PontoDeRecebimento pontoDeRecebimento) {
        this.pontoDeRecebimento = pontoDeRecebimento;
    }

    public boolean isAberto() {
        return aberto;
    }

    public void setAberto(boolean aberto) {
        this.aberto = aberto;
    }

    public double getValorAvista() {
        double valor = 0;
        if (getPagamentos() != null) {
            for (Pagamento pag : getPagamentos()) {
                if (pag.getTipoDePgto() == TipoPagto.AVISTA) {
                    valor += pag.getValorPago();
                }
            }
        }
        return valor;
    }

    public double getValorCheque() {
        double valor = 0;
        if (getPagamentos() != null) {
            for (Pagamento pag : getPagamentos()) {
                if (pag.getTipoDePgto() == TipoPagto.CHEQUE) {
                    valor += pag.getValorPago();
                }
            }
        }
        return valor;
    }

    public double getValorCartaoDeCredito() {
        double valor = 0;
        if (getPagamentos() != null) {
            for (Pagamento pag : getPagamentos()) {
                if (pag.getTipoDePgto() == TipoPagto.CartaoDeCredito) {
                    valor += pag.getValorPago();
                }
            }
        }
        return valor;
    }

    public double getValorTotal() {
        double valor = 0;
        if (getPagamentos() != null) {
            for (Pagamento pag : getPagamentos()) {
                valor += pag.getValorPago();
            }
        }
        return valor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.ID;
        hash = 67 * hash + Objects.hashCode(this.dataAbertura);
        hash = 67 * hash + Objects.hashCode(this.dataFechamento);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.valorInicial) ^ (Double.doubleToLongBits(this.valorInicial) >>> 32));
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.valorFinal) ^ (Double.doubleToLongBits(this.valorFinal) >>> 32));
        hash = 67 * hash + Objects.hashCode(this.usuario);
        hash = 67 * hash + Objects.hashCode(this.pagamentos);
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
        final Caixa other = (Caixa) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (!Objects.equals(this.dataAbertura, other.dataAbertura)) {
            return false;
        }
        if (!Objects.equals(this.dataFechamento, other.dataFechamento)) {
            return false;
        }
        if (Double.doubleToLongBits(this.valorInicial) != Double.doubleToLongBits(other.valorInicial)) {
            return false;
        }
        if (Double.doubleToLongBits(this.valorFinal) != Double.doubleToLongBits(other.valorFinal)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.pagamentos, other.pagamentos)) {
            return false;
        }
        return true;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

}
