/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.services;


import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.projetodaca.entities.Contato;
import com.projetodaca.entities.Empresa;
import com.projetodaca.entities.Endereco;
import com.projetodaca.entities.Promissoria;

/**
 *
 * @author renan
 */
public class PDFService {

    private Util util;
    private EmpresaService empService;
    private int FONT_SIZE = 10;
    private int FONT_SIZE_TITULO = 16;
    private String FONT_TYPE = "Courier 10 Pitch";

    public PDFService() {
        util = new Util();
        empService = new EmpresaService();
    }

    public void createPdfPromissoria(String path, Promissoria promissoria) throws Exception {

        List<Empresa> listEmpresa = empService.list();

        if (!listEmpresa.isEmpty()) {
                Empresa empresa = listEmpresa.get(0);
                Endereco endEmpresa = empresa.getEndereco();
                String endEmpresaStr = endEmpresa.getLogradouro() + ", " + endEmpresa.getNumResidencia() + ", " + endEmpresa.getBairro() + ", " + endEmpresa.getCidade() + " - " + endEmpresa.getEstado();
                String empresaContato = "";
                if(!empresa.getContatos().isEmpty()){
                    Contato contato= empresa.getContatos().get(0);
                    
                    empresaContato+= "Cel: "+contato.getCelular();
                    if(!contato.getEmail().isEmpty()){
                        empresaContato+=" - email: "+contato.getEmail();
                    }
                }
                    
                
                Endereco enderecoCliente = promissoria.getPedido().getCliente().getEndereco();
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(path));

                document.open();

                PdfPTable table = new PdfPTable(2);
                table.setWidths(new int[]{50, 100});
                Paragraph paragrafo = new Paragraph();

                PdfPCell cell = new PdfPCell(new Paragraph("Nota Promissória", FontFactory.getFont(FONT_TYPE, FONT_SIZE_TITULO, new BaseColor(0, 0, 100))));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setColspan(2);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("N° " + promissoria.getId()));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setColspan(2);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("Vencimento:" + util.formataData(promissoria.getDataPagamento()), FontFactory.getFont(FONT_TYPE, FONT_SIZE, Font.NORMAL)));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("Valor: " + util.formatCurrency(promissoria.getValorPagamento()), FontFactory.getFont(FONT_TYPE, FONT_SIZE, Font.NORMAL)));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("Favorecido:", FontFactory.getFont(FONT_TYPE, FONT_SIZE, Font.BOLD)));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.LEFT | Rectangle.TOP);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph(empresa.getRazaoSocial(), FontFactory.getFont(FONT_TYPE, FONT_SIZE, Font.NORMAL)));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.RIGHT);
                table.addCell(cell);
                   
                if(!empresa.getContatos().isEmpty()){
                     cell = new PdfPCell(new Paragraph("Contato:", FontFactory.getFont(FONT_TYPE, FONT_SIZE, Font.BOLD)));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.LEFT);
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(empresaContato, FontFactory.getFont(FONT_TYPE, FONT_SIZE, Font.NORMAL)));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.RIGHT);
                    table.addCell(cell);
                }
                if (!empresa.getCnpj().isEmpty()) {
                    cell = new PdfPCell(new Paragraph("CNPJ:", FontFactory.getFont(FONT_TYPE, FONT_SIZE, Font.BOLD)));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.LEFT);
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(empresa.getCnpj(), FontFactory.getFont(FONT_TYPE, FONT_SIZE, Font.NORMAL)));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.RIGHT);
                    table.addCell(cell);
                }
                if (!empresa.getCpf().isEmpty()) {
                    cell = new PdfPCell(new Paragraph("CPF:", FontFactory.getFont(FONT_TYPE, FONT_SIZE, Font.BOLD)));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.LEFT);
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(empresa.getCpf(), FontFactory.getFont(FONT_TYPE, FONT_SIZE, Font.NORMAL)));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.RIGHT);
                    table.addCell(cell);
                }
                if (!empresa.getInscricaoEstadual().isEmpty()) {
                    cell = new PdfPCell(new Paragraph("Inscrição Estatual:", FontFactory.getFont(FONT_TYPE, FONT_SIZE, Font.BOLD)));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.LEFT);
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(empresa.getInscricaoEstadual(), FontFactory.getFont(FONT_TYPE, FONT_SIZE, Font.NORMAL)));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.RIGHT);
                    table.addCell(cell);
                }                
                cell = new PdfPCell(new Paragraph("Endereço:", FontFactory.getFont(FONT_TYPE, FONT_SIZE, Font.BOLD)));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.LEFT);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph(endEmpresaStr, FontFactory.getFont(FONT_TYPE, FONT_SIZE, Font.NORMAL)));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.RIGHT);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("Cep:", FontFactory.getFont(FONT_TYPE, FONT_SIZE, Font.BOLD)));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph(endEmpresa.getCep(), FontFactory.getFont(FONT_TYPE, FONT_SIZE, Font.NORMAL)));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.RIGHT | Rectangle.BOTTOM);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("Emitente:", FontFactory.getFont(FONT_TYPE, FONT_SIZE, Font.BOLD)));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.LEFT);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph(promissoria.getPedido().getCliente().getNome(), FontFactory.getFont(FONT_TYPE, FONT_SIZE, Font.NORMAL)));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.RIGHT);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("CPF:", FontFactory.getFont(FONT_TYPE, FONT_SIZE, Font.BOLD)));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.LEFT);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph(promissoria.getPedido().getCliente().getCpf(), FontFactory.getFont(FONT_TYPE, FONT_SIZE, Font.NORMAL)));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.RIGHT);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("Endereço:", FontFactory.getFont(FONT_TYPE, FONT_SIZE, Font.BOLD)));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.LEFT);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph(enderecoCliente.getLogradouro() + ", " + enderecoCliente.getNumResidencia() + ", " + enderecoCliente.getBairro() + ", " + enderecoCliente.getCidade() + " - " + enderecoCliente.getEstado(), FontFactory.getFont(FONT_TYPE, FONT_SIZE, Font.NORMAL)));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.RIGHT);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("Cep:", FontFactory.getFont(FONT_TYPE, FONT_SIZE, Font.BOLD)));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph(enderecoCliente.getCep(), FontFactory.getFont(FONT_TYPE, FONT_SIZE, Font.NORMAL)));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.RIGHT | Rectangle.BOTTOM);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("                 "));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                cell.setColspan(2);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("Assinatura Favorecido:", FontFactory.getFont(FONT_TYPE, FONT_SIZE, Font.BOLD)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                cell.setColspan(2);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("________________________________", FontFactory.getFont(FONT_TYPE, FONT_SIZE, Font.NORMAL)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                cell.setColspan(2);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("                 "));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                cell.setColspan(2);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("Assinatura Cliente:", FontFactory.getFont(FONT_TYPE, FONT_SIZE, Font.BOLD)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                cell.setColspan(2);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("________________________________", FontFactory.getFont(FONT_TYPE, FONT_SIZE, Font.NORMAL)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                cell.setColspan(2);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("                    "));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                cell.setColspan(2);
                table.addCell(cell);

                Calendar calend = (GregorianCalendar) GregorianCalendar.getInstance();
                cell = new PdfPCell(new Paragraph(endEmpresa.getCidade() + " " + util.formataData(new Date(calend.getTimeInMillis())), FontFactory.getFont(FONT_TYPE, FONT_SIZE, Font.NORMAL)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
                cell.setColspan(2);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("                    "));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT);
                cell.setColspan(2);
                table.addCell(cell);
                document.add(table);

                document.close();
        } else {
            throw new Exception("Não existe uma Empresa cadastrada!");
        }
    }

}
