/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetodaca.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author renan
 */
@Entity
public class Config implements Serializable {
    
    @Id
    @GeneratedValue
    private int id;
    @Column
    private boolean showErrorMessages;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isShowErrorMessages() {
        return showErrorMessages;
    }

    public void setShowErrorMessages(boolean showErrorMessages) {
        this.showErrorMessages = showErrorMessages;
    }
    
    
    
}
