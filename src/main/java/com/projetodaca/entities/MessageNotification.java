/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.entities;

import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Classe criada para gerar notificações em modo desenvolvimento
 *
 * @author renan
 */
public class MessageNotification {

    public void showErrorMessage(Component component, String message) {
        // create a JTextArea
        JTextArea textArea = new JTextArea(6, 25);
        textArea.setText(message);
        textArea.setColumns(40);
        textArea.setRows(13);        
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        // wrap a scrollpane around it
        JScrollPane scrollPane = new JScrollPane(textArea);       
        // display them in a message dialog
        JOptionPane.showMessageDialog(component, scrollPane, "Erro", JOptionPane.ERROR_MESSAGE);
    }
    public void showMessage(Component component, String message) {
        JOptionPane.showMessageDialog(component, message, "Mensagem", JOptionPane.WARNING_MESSAGE);
    }

}
