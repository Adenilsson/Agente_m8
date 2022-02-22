/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agente.Bean;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author nosli
 */
public class Baias {
    private int id;
    private String nome;
    private int capacidade;
    private Date data;
    private Date update;
    private int excluido;
    private int tbGalpaoId;
    private int tbCoordenadorId;
    private int tbSiloId;
    private int setor;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

    public int getExcluido() {
        return excluido;
    }

    public void setExcluido(int excluido) {
        this.excluido = excluido;
    }

    public int getTbGalpaoId() {
        return tbGalpaoId;
    }

    public void setTbGalpaoId(int tbGalpaoId) {
        this.tbGalpaoId = tbGalpaoId;
    }

    public int getTbCoordenadorId() {
        return tbCoordenadorId;
    }

    public void setTbCoordenadorId(int tbCoordenadorId) {
        this.tbCoordenadorId = tbCoordenadorId;
    }

    public int getTbSiloId() {
        return tbSiloId;
    }

    public void setTbSiloId(int tbSiloId) {
        this.tbSiloId = tbSiloId;
    }

    public int getSetor() {
        return setor;
    }

    public void setSetor(int setor) {
        this.setor = setor;
    }
    
    

    
    
}
