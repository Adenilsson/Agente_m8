/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agente.Bean;

import br.com.agente.Enum.NotificationsEnum;
import java.util.Date;

/**
 *
 * @author nosli
 */
public class Notificacoes {
    private int id;
    private String descricao;
    private int tbdDescricaoId;
    private String valor;
    private String mac;
    private int lida;
    private Date create;
    private int excluido;
    private int visualizada;
    private String porta;
    private String codigo;
    private NotificationsEnum code;
    private int tbCordenadorId;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }



    public int getTbdDescricaoId() {
        return tbdDescricaoId;
    }

    public void setTbdDescricaoId(int tbdDescricaoId) {
        this.tbdDescricaoId = tbdDescricaoId;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public int getLida() {
        return lida;
    }

    public void setLida(int lida) {
        this.lida = lida;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public int getExcluido() {
        return excluido;
    }

    public void setExcluido(int excluido) {
        this.excluido = excluido;
    }

    public int getVisualizada() {
        return visualizada;
    }

    public void setVisualizada(int visualizada) {
        this.visualizada = visualizada;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public NotificationsEnum getCode() {
        return code;
    }

    public void setCode(NotificationsEnum code) {
        this.code = code;
    } 

    public int getTbCordenadorId() {
        return tbCordenadorId;
    }

    public void setTbCordenadorId(int tbCordenadorId) {
        this.tbCordenadorId = tbCordenadorId;
    }
    
}
