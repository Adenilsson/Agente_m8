/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agente.Bean;

/**
 *
 * @author nosli
 */
public class Dosador {
    private int id;
    private int tbBaiaId;
    private long mac;
    private String mac16;
    private int ativo;
    private int ativoRede;
    private int statusRacao;
    private int statusAntena;
    private int statusMotor;
    private int statusDosador;
    private int StatusMemoria;
    private int StatusRede;
    private int setor;
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTbBaiaId() {
        return tbBaiaId;
    }

    public void setTbBaiaId(int tb_baia_id) {
        this.tbBaiaId = tbBaiaId;
    }

    public long getMac() {
        return mac;
    }

    public void setMac(long mac) {
        this.mac = mac;
    }

    public String getMac16() {
        return mac16;
    }

    public void setMac16(String mac16) {
        this.mac16 = mac16;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public int getAtivoRede() {
        return ativoRede;
    }

    public void setAtivoRede(int ativoRede) {
        this.ativoRede = ativoRede;
    }

    public int getStatusRacao() {
        return statusRacao;
    }

    public void setStatusRacao(int statusRacao) {
        this.statusRacao = statusRacao;
    }

    public int getStatusAntena() {
        return statusAntena;
    }

    public void setStatusAntena(int statusAntena) {
        this.statusAntena = statusAntena;
    }

    public int getStatusMotor() {
        return statusMotor;
    }

    public void setStatusMotor(int statusMotor) {
        this.statusMotor = statusMotor;
    }

    public int getStatusDosador() {
        return statusDosador;
    }

    public void setStatusDosador(int statusDosador) {
        this.statusDosador = statusDosador;
    }

    public int getStatusMemoria() {
        return StatusMemoria;
    }

    public void setStatusMemoria(int StatusMemoria) {
        this.StatusMemoria = StatusMemoria;
    }

    public int getStatusRede() {
        return StatusRede;
    }

    public void setStatusRede(int StatusRede) {
        this.StatusRede = StatusRede;
    }

    public int getSetor() {
        return setor;
    }

    public void setSetor(int setor) {
        this.setor = setor;
    }
    
    
}
