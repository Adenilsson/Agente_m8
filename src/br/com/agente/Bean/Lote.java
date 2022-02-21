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
public class Lote {
    private int id;
    private String nome;
    private int qtdAnimais;
    private Date created;
    private Date update;
    private float idadeMediaEntrada;
    private float pesoMediaEntrada;
    private float pesoMediaSaida;
    private float idadeMediaSaida;
    private Date dataInicial;
    private Date dataFinal;
    private int ativo;
    private int excluido;

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

    public int getQtdAnimais() {
        return qtdAnimais;
    }

    public void setQtdAnimais(int qtdAnimais) {
        this.qtdAnimais = qtdAnimais;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

    public float getIdadeMediaEntrada() {
        return idadeMediaEntrada;
    }

    public void setIdadeMediaEntrada(float idadeMediaEntrada) {
        this.idadeMediaEntrada = idadeMediaEntrada;
    }

    public float getPesoMediaEntrada() {
        return pesoMediaEntrada;
    }

    public void setPesoMediaEntrada(float pesoMediaEntrada) {
        this.pesoMediaEntrada = pesoMediaEntrada;
    }

    public float getPesoMediaSaida() {
        return pesoMediaSaida;
    }

    public void setPesoMediaSaida(float pesoMediaSaida) {
        this.pesoMediaSaida = pesoMediaSaida;
    }

    public float getIdadeMediaSaida() {
        return idadeMediaSaida;
    }

    public void setIdadeMediaSaida(float idadeMediaSaida) {
        this.idadeMediaSaida = idadeMediaSaida;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public int getExcluido() {
        return excluido;
    }

    public void setExcluido(int excluido) {
        this.excluido = excluido;
    }
    
    
    
    
    
}
