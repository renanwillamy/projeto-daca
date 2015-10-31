    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.services;



import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import sun.util.calendar.Gregorian;

/**
 *
 * @author renan
 */
public class Util {

    /**
     * convert double em um texto monetario
     *
     * @param valor
     * @return
     */
    public String formatCurrency(Double valor) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        String v = format.format(valor);
        String valorFormatado = v.replace("R", "").replace("$", "");

        return valorFormatado.trim();
    }

    /**
     * Convert uma string em um double, substititui a virgula por ponto
     *
     */
    public Double stringToDouble(String valor) throws AssertionError {
        if (valor == null||valor.isEmpty()) {
            valor = "0.0";
        }
        if (valor.contains(",")) {
            valor = valor.replace(".", "");
        }

        valor = valor.replace(",", ".");

        return Double.parseDouble(valor);

    }

    /**
     * Calcula a porcentagem da venda a partir da margem
     *
     * @param margem
     * @param custo
     * @return
     */
    public double calculaPrecoDeVenda(double margem, double custo) {
        if(margem<0)
            return 0.0;
        margem = margem / 100;
        double venda = custo + (custo * margem);
        return venda;
    }

    /**
     * Calcula o desconto da venda a partir da margem
     *
     * @param margem
     * @param valorVenda
     * @return
     */
    public double calculaDescontoDeVenda(double margem, double valorVenda) {
        
        if(margem<=0)
            return valorVenda;
        if(valorVenda<=0)
            return 0;
        
        margem = margem / 100;
        double venda = valorVenda - (valorVenda * margem);        
        if(venda<0)
            return 0;
        return venda;
    }

    /**
     * Calcula a margem de lucro a partir do preço de custo e de venda
     *
     * @param custo
     * @param venda
     * @return
     */
    public double calculaMargemLucro(double custo, double venda)throws Exception {
        double margem = ((venda / custo) - 1) * 100;
        if(custo<=0)
            throw new Exception("O custo não pode ser igual ou menor que zero");
        margem = Math.round(margem * 100.0) / 100.0;
                
        return margem;
    }

    /**
     * Calcula o valor de desconto em porcentagem
     *
     * @param valorSemDesc
     * @param valorComDesc
     * @return
     */
    public double calculaMargemDesconto(double valorSemDesc, double valorComDesc)throws Exception {
        if(valorComDesc<0)
            return 100;
        if(valorComDesc>valorSemDesc){
            throw new Exception("O valor sem desconto não deve ser menor que o valor com desconto");
        }else if(valorComDesc==valorSemDesc){
            return 0;
        }
        double result = (valorComDesc / valorSemDesc) * 100;
        result = result * 100;
        result = Math.round(result);
        result = result / 100;        
        if (result != 0.0) {
            result = 100 - result;
        }
        if(valorComDesc==0)
            return 100;
        return result;
    }

    public boolean isValidCPF(String cpf) {
        if(cpf!=null)
        cpf = cpf.replace(".", "").replace("-", "").trim();
        int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
        if ((cpf == null) || (cpf.length() != 11)) {
            return false;
        }

        Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
        Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
        return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
    }

    /**
     * Usado para validar cpf
     *
     * @param str
     * @param peso
     * @return
     */
    private int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
            digito = Integer.parseInt(str.substring(indice, indice + 1));
            soma += digito * peso[peso.length - str.length() + indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    /**
     * Formata a um objeto Date em uma string com formato dd/mm/aaaa
     *
     * @param data
     * @return String
     */
    public String formataData(Date data) {

        String dataString = String.format("%td/%tm/%tY %n", data, data, data);

        return dataString.trim();
    }

    /**
     * Converte uma data no formato dd/mm/aaaa para um objeto Date
     *
     * @param String data
     * @return Date
     * @throws DataInvalidaExeption
     */
    public Date formatStringToDate(String data) throws Exception {
        Date novaData = null;
        try {
            if (data.trim().length() != 10) {
                return null;
            }
            String dataArray[] = data.split("/");
            String dia = dataArray[0];
            String mes = dataArray[1];
            String ano = dataArray[2];
            Calendar calend = GregorianCalendar.getInstance();
            calend.set(Integer.parseInt(ano), Integer.parseInt(mes)-1, Integer.parseInt(dia));
            novaData = new Date(calend.getTimeInMillis());            
        } catch (Exception ex) {
            throw new Exception("Data Inválida!");
        }
        return novaData;

    }

}
