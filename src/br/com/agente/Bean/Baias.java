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

   private short ID;
    private long RFID;
    private int id_primary;
    private long num_rfid;
    private String tatuagem;
    private String baia_nome;
    private byte dia;
    private int id_baia;
    private int QA;
    private int QR;
    private int QT;
    private Calendar date;
    private byte receipe;
    private long ultimoDosador;
    private char score;
    private int t_feed;
    private byte setor;
    private byte tipo;
    private int ag_update;

    public int getAg_update() {
        return ag_update;
    }

    public void setAg_update(int ag_update) {
        this.ag_update = ag_update;
    }

  

    public int getQT() {
        return QT;
    }

    public void setQT(int QT) {
        this.QT = QT;
    }

    public String getBaiaNome() {
        return baia_nome;
    }

    public void setBaiaNome(String baia_nome) {
        this.baia_nome = baia_nome;
    }
    
    
    private String brinco;

    public String getBrinco() {
        return brinco;
    }

    public void setBrinco(String brinco) {
        this.brinco = brinco;
    }
    public long getNum_rfid() {
        return num_rfid;
    }

    public void setNum_rfid(long num_rfid) {
        this.num_rfid = num_rfid;
    }

    public byte getTipo() {
        return tipo;
    }

    public void setTipo(byte tipo) {
        this.tipo = tipo;
    }

    public byte getSetor() {
        return setor;
    }

    public void setSetor(byte setor) {
        this.setor = setor;
    }
    

    public int getId_baia() {
        return id_baia;
    }

    public void setId_baia(int id_baia) {
        this.id_baia = id_baia;
    }

    
    /**
     * Pega o valor referente ao ID de 16bits de trafego na rede de dosadores.<br>
     * Parametro máximo da rede por limitação da memória e firmware do dosador é
     * 2000 cadastros de animais
     * @return the ID
     */
    public short getID() {
        return this.ID;
    }

    /**
     * Atribui o valor referente ao ID de 16bits de trafego na rede de dosadores.<br>
     * Parametro máximo da rede por limitação da memória e firmware do dosador é
     * 2000 cadastros de animais
     * @param ID the ID to set
     */
    public void setID(short ID) {
        this.ID = ID;
    }

    /**
     * Pega o valor referente ao código do brinco RFID.<br>
     * Valor do RFID ocupa 38bits do valor long
     * @return the RFID
     */
    public long getRFID() {
        return this.RFID;
    }

    /**
     * Atribui o valor referente ao código do brinco RFID.<br>
     * Valor do RFID ocupa 38bits do valor long
     * @param RFID the RFID to set
     */
    public void setRFID(long RFID) {
        this.RFID = RFID;
    }

    /**
     * Pega o valor referente ao dia atual de alimentação do animal.<br>
     * O dia do animal varia de 0 a 120 dias.
     * @return the dia
     */
    public byte getDia() {
        return this.dia;
    }

    /**
     * Atribui o valor referente ao dia atual de alimentação do animal.<br>
     *  O dia do animal varia de 0 a 120 dias.
     * @param dia the dia to set
     */
    public void setDia(byte dia) {
        this.dia = dia;
    }

    /**
     * Pega o valor referente ao quantidade alimentada do animal em número de dosagem.<br>
     * O número de dosagem é o numero de vezes que é liberado ração para o animal, levando em
     * consideração a constante de g/dose que especifica o valor em gramas de ração.
     * @return the QA
     */
    public int getQA() {
        return this.QA;
    }

    /**
     * Atribui o valor referente ao quantidade alimentada do animal em número de dosagem.<br>
     * O número de dosagem é o numero de vezes que é liberado ração para o animal, levando em
     * consideração a constante de g/dose que especifica o valor em gramas de ração.
     * @param QA the QA to set
     */
    public void setQA(int QA) {
        this.QA = QA;
    }

    /**
     * Pega o valor referente ao quantidade restante de alimentação do animal em número de dosagem.<br>
     * O número de dosagem é o numero de vezes que é liberado ração para o animal, levando em
     * consideração a constante de g/dose que especifica o valor em gramas de ração.
     * @return the QR
     */
    public int getQR() {
        return this.QR;
    }

    /**
     * Atribui o valor referente ao quantidade restante de alimentação do animal em número de dosagem.<br>
     * O número de dosagem é o numero de vezes que é liberado ração para o animal, levando em
     * consideração a constante de g/dose que especifica o valor em gramas de ração.
     * 
     * @param QR the QR to set
     */
    public void setQR(int QR) {
        this.QR = QR;
    }

    /**
     * Pega a data-hora do registro do evento de alimentação do animal.<br>
     * Este evento é o evento registrado pelo dosador durante a recepção 
     * de um registro advindo do dosador, pois este armazena os registro 
     * em sua memória interna.
     * @return the data
     */
    public Calendar getDate() {
        return this.date;
    }

    /**
     * Atribui a data-hora do registro do evento de alimentação do animal.<br>
     * Este evento é o evento registrado pelo dosador durante a recepção 
     * de um registro advindo do dosador, pois este armazena os registro 
     * em sua memória interna.
     * @param date the data to set
     */
    public void setDate(Calendar date) {
        this.date = date;
    }

    /**
     * Pega a receita ou Curva de alimentação que o animal segue.<br>
     * Este valor deve ser no máximo 10 devido ao espaço reservado na memória local do dosador.
     * o qual pode armazenar no máximo 10 curvas de alimentação com no máximo 120 dias.
     * @return the receita
     */
    public byte getReceipe() {
        return this.receipe;
    }

    /**
     * Atribui a receita ou Curva de alimentação que o animal segue.<br>
     * Este valor deve ser no máximo 10 devido ao espaço reservado na memória local do dosador.
     * o qual pode armazenar no máximo 10 curvas de alimentação com no máximo 120 dias.
     * @param receipe the receita to set
     */
    public void setReceipe(byte receipe) {
        this.receipe = receipe;
    }

    /**
     * Pega o ultimo dosador ao qual o animal teve acesso, ou ao qual o registro se refere.<br>
     * Este valor é o MAC address do dosador.
     * @return the ultimoDosador
     */
    public long getUltimoDosador() {
        return this.ultimoDosador;
    }

    /**
     * Atribui o ultimo dosador ao qual o animal teve acesso, ou ao qual o registro se refere.<br>
     *      * Este valor é o MAC address do dosador.
     * @param ultimoDosador the ultimoDosador to set
     */
    public void setUltimoDosador(long ultimoDosador) {
        this.ultimoDosador = ultimoDosador;
    }

    /**
     * Pega o status do animal, se ativo ou não.<br>
     * @return the excluido
     */
    public byte getExcluido() {
        return (byte) this.excluido;
    }

    /**
     * Atribui o status do animal, se ativo ou não.<br>
     * @param excluido the status to set
     */
    public void setExcluido(byte excluido) {
        this.excluido = excluido;
    }

    /**
     * Pega o valor da chave primária.<br>
     * @return the id_primary
     */
    public int getId_primary() {
        return this.id_primary;
    }

    /**
     * Atribui o valor da chave primária.<br>
     * @param id_primary the id_primary to set
     */
    public void setId_primary(int id_primary) {
        this.id_primary = id_primary;
    }

    /**
     * @return the score
     */
    public char getScore() {
        return this.score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(char score) {
        this.score = score;
    }

    /**
     * @return the t_feed
     */
    public int getT_feed() {
        return this.t_feed;
    }

    /**
     * @param t_feed the t_feed to set
     */
    public void setT_feed(int t_feed) {
        this.t_feed = t_feed;
    }
    

    public String getTatuagem() {
        return tatuagem;
    }

    public void setTatuagem(String tatuagem) {
        this.tatuagem = tatuagem;
    }
    
    
}
