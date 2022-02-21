/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agente.Bean;

import java.util.Date;

/**
 *
 * @author nosli
 */
public class Temperatura {
    private int id;
    private int idDosador;
    private float temperatura;
    private Date create;
    private int idGalpoes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDosador() {
        return idDosador;
    }

    public void setIdDosador(int idDosador) {
        this.idDosador = idDosador;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public int getIdGalpoes() {
        return idGalpoes;
    }

    public void setIdGalpoes(int idGalpoes) {
        this.idGalpoes = idGalpoes;
    }
    
    
    
    
}
