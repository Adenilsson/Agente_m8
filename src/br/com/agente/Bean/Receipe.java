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
public class Receipe {
     private byte ID;
    private short[] QRdia;

    /**
     * Retorna o id da Receita
     * @return the ID
     */
    public byte getID() {
        return this.ID;
    }

    /**
     * Atribui o id da Receita
     * @param ID the ID to set
     */
    public void setID(byte ID) {
        this.ID = ID;
    }

    /**
     * Retorna o Array que possui o QT de cada dia da Receita
     * @return the QRsia
     */
    public short[] getQRdia() {
        return this.QRdia;
    }

    /**
     * Atribui o Array que possui o QT de cada dia da Receita
     * @param QRdia the QRsia to set
     */
    public void setQRdia(short[] QRdia) {
        this.QRdia = QRdia;
    }
    
}
