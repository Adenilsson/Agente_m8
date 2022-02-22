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
public class Grupo {
    private int id;
    private int tbBaiaId;
    private int quantidade_animais;
    private int tbTempoEntreDosagensId;
    private int tblLoteId;
    private int tbCurvaId;
    private Date created;
    private Date updated;
    private int ativo;
    private int excluido;
    private int tbRacaoId;
    private long ultimoDosador;
    private int QA;
    private int QR;
    private int QT;
    private int t_feed;
    private char score;
    private Calendar date;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTbBaiaId() {
        return tbBaiaId;
    }

    public void setTbBaiaId(int tbBaiaId) {
        this.tbBaiaId = tbBaiaId;
    }

    public int getQuantidade_animais() {
        return quantidade_animais;
    }

    public void setQuantidade_animais(int quantidade_animais) {
        this.quantidade_animais = quantidade_animais;
    }

    public int getTbTempoEntreDosagensId() {
        return tbTempoEntreDosagensId;
    }

    public void setTbTempoEntreDosagensId(int tbTempoEntreDosagensId) {
        this.tbTempoEntreDosagensId = tbTempoEntreDosagensId;
    }

    public int getTblLoteId() {
        return tblLoteId;
    }

    public void setTblLoteId(int tblLoteId) {
        this.tblLoteId = tblLoteId;
    }

    public int getTbCurvaId() {
        return tbCurvaId;
    }

    public void setTbCurvaId(int tbCurvaId) {
        this.tbCurvaId = tbCurvaId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
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

    public int getTbRacaoId() {
        return tbRacaoId;
    }

    public void setTbRacaoId(int tbRacaoId) {
        this.tbRacaoId = tbRacaoId;
    }

    public long getUltimoDosador() {
        return ultimoDosador;
    }

    public void setUltimoDosador(long ultimoDosador) {
        this.ultimoDosador = ultimoDosador;
    }

    public int getQA() {
        return QA;
    }

    public void setQA(int QA) {
        this.QA = QA;
    }

    public int getQR() {
        return QR;
    }

    public void setQR(int QR) {
        this.QR = QR;
    }

    public int getQT() {
        return QT;
    }

    public void setQT(int QT) {
        this.QT = QT;
    }

    public int getT_feed() {
        return t_feed;
    }

    public void setT_feed(int t_feed) {
        this.t_feed = t_feed;
    }

    public char getScore() {
        return score;
    }

    public void setScore(char score) {
        this.score = score;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    
    
    
    
}
