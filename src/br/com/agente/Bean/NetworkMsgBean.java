/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agente.Bean;

import br.com.agente.Enum.MsgNetworkType;

/**
 *
 * @author nosli
 */
public class NetworkMsgBean {
    private MsgNetworkType network_msg_type;
    private Grupo grupo;
    private DosadorTime dosadorTime;
    private DosadorStatus dosadorStatus;
    private Dosador dosador;
    private Receipe receipe;
    private short stack_size;
    private short NReceipe;
    private short NRFID;    
    private short newDay;
    private short freezeNetworkTime;
    private short receipeChecksum;
    private long  rfidChecksum;
    public  NetworkMsgBean(){
        this.grupo=new Grupo();
        this.dosadorTime=new DosadorTime();
        this.dosadorStatus=new DosadorStatus();
        this.dosador=new Dosador();
        this.receipe=new Receipe();
    }
    /**
     * Retorna o tipo de mensagem da rede dos dosadores
     * @return the network_msg_type
     */
    public MsgNetworkType getNetwork_msg_type() {
        return this.network_msg_type;
    }

    /**
     * Atribui o tipo de mensagem da rede dos dosadores
     * @param network_msg_type the network_msg_type to set
     */
    public void setNetwork_msg_type(MsgNetworkType network_msg_type) {
        this.network_msg_type = network_msg_type;
    }

    public Grupo getGrupo() {
        return this.grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    /**
     * Retorna o tamanho da pilha de eventos armazenada no dosador na mensagem do tipo
     * {@link br.com.agente.Enum.MsgNetworkType#DOSADOR_STACK_STATUS}
     * @return the stack_size
     */
    public short getStack_size() {
        return this.stack_size;
    }

    /**
     * Atribui o tamanho da pilha de eventos armazenada no dosador na mensagem do tipo
     * {@link br.com.agente.Enum.MsgNetworkType#DOSADOR_STACK_STATUS}
     * @param stack_size the stack_size to set
     */
    public void setStack_size(short stack_size) {
        this.stack_size = stack_size;
    }

    /**
     * Retorna o numero de de receitas cadastradas no dosador, refere-se a mensagem do tipo
     * {@link br.com.agente.Enum.MsgNetworkType#AGENTE_NEW_DEVICE_UP}
     * @return the NReceipe
     */
    public short getNReceipe() {
        return this.NReceipe;
    }

    /**
     * Atribui o numero de de receitas cadastradas no dosador, refere-se a mensagem do tipo
     * {@link br.com.agente.Enum.MsgNetworkType#AGENTE_NEW_DEVICE_UP}
     * @param NReceipe the NReceipe to set
     */
    public void setNReceipe(short NReceipe) {
        this.NReceipe = NReceipe;
    }

    /**
     * Retorna o numero de de animais ou RFID cadastradas no dosador, refere-se a mensagem do tipo
     * {@link br.com.agente.Enum.MsgNetworkType#AGENTE_NEW_DEVICE_UP}
     * @return the NRFID
     */
    public short getNRFID() {
        return this.NRFID;
    }

    /**   
     * Atribui o numero de de animais ou RFID cadastradas no dosador, refere-se a mensagem do tipo
     * {@link br.com.agente.Enum.MsgNetworkType#AGENTE_NEW_DEVICE_UP}
     * @param NRFID the NRFID to set
     */
    public void setNRFID(short NRFID) {
        this.NRFID = NRFID;
    }

    /**
     * Retorna a hora de mudança de dia, referente a mensagem do tipo
     * {@link br.com.agente.Enum.MsgNetworkType#AGENTE_DATE_HOUR_UP}
     * @return the newDay
     */
    public short getNewDay() {
        return this.newDay;
    }

    /**
     * Atribui a hora de mudança de dia, referente a mensagem do tipo
     * {@link br.com.agente.Enum.MsgNetworkType#AGENTE_DATE_HOUR_UP}
     * @param newDay the newDay to set
     */
    public void setNewDay(short newDay) {
        this.newDay = newDay;
    }

    /**
     * Retorna o tempo de congelamento da rede, referente a mensagem do tipo
     * {@link br.com.agente.Enum.MsgNetworkType#AGENTE_NETWORK_FREEZE}
     * @return the freezeNetwork
     */
    public short getFreezeNetworkTime() {
        return this.freezeNetworkTime;
    }

    /**
     * Atribui o tempo de congelamento da rede, referente a mensagem do tipo
     * {@link br.com.agente.Enum.MsgNetworkType#AGENTE_NETWORK_FREEZE}
     * @param freezeNetwork the freezeNetwork to set
     */
    public void setFreezeNetworkTime(short freezeNetwork) {
        this.freezeNetworkTime = freezeNetwork;
    }

    /**
     * Retorna o Beam do tipo {@link Receipe} que contem as informações de uma receita ou curva de alimentação
     * @see Receipe
     * @return the receipe
     */
    public Receipe getReceipe() {
        return this.receipe;
    }

    /**
     * Atribui o Beam do tipo {@link Receipe} que contem as informações de uma receita ou curva de alimentação
     * @see Receipe
     * @param receipe the receipe to set
     */
    public void setReceipe(Receipe receipe) {
        this.receipe = receipe;
    }

    /**
     * Retorna o valor do checksum das receitas, usado para validação da configuração de um novo dosador,
     * se refere a mensagem do tipo {@link br.com.agente.Enum.MsgNetworkType#AGENTE_NEW_DEVICE_UP}
     * @return the receipeChecksum
     */
    public short getReceipeChecksum() {
        return this.receipeChecksum;
    }

    /**
     * Atribui o valor do checksum das receitas, usado para validação da configuração de um novo dosador,
     * se refere a mensagem do tipo {@link br.com.agente.Enum.MsgNetworkType#AGENTE_NEW_DEVICE_UP}
     * @param receipeChecksum the receipeChecksum to set
     */
    public void setReceipeChecksum(short receipeChecksum) {
        this.receipeChecksum = receipeChecksum;
    }

    /**
     * Retorna o valor do checksum dos RFID, usado para validação da configuração de um novo dosador,
     * se refere a mensagem do tipo {@link br.com.agente.Enum.MsgNetworkType#AGENTE_NEW_DEVICE_UP}
     * @return the rfidChecksum
     */
    public long getRfidChecksum() {
        return this.rfidChecksum;
    }

    /**
     * Atribui o valor do checksum dos RFID, usado para validação da configuração de um novo dosador,
     * se refere a mensagem do tipo {@link br.com.agente.Enum.MsgNetworkType#AGENTE_NEW_DEVICE_UP} 
     * @param rfidChecksum the rfidChecksum to set
     */
    public void setRfidChecksum(long rfidChecksum) {
        this.rfidChecksum = rfidChecksum;
    }

    /**
     * Retorna o Beam do tipo {@link DosadorTime} que contem as informações de tempo de acionamento do dosador
     * @see DosadorTime
     * @return the dosadorTime
     */
    public DosadorTime getDosadorTime() {
        return this.dosadorTime;
    }

    /*
     * Atribui o Beam do tipo {@link DosadorTime} que contem as informações de tempo de acionamento do dosador
     * @see DosadorTime
     * @param dosadorTime the dosadorTime to set
     */
    public void setDosadorTime(DosadorTime dosadorTime) {
        this.dosadorTime = dosadorTime;
    }

    /**     
     * Retorna o Beam do tipo {@link DosadorStatus} que contem as informações de funcionamento do dosador
     * @see DosadorStatus
     * @return the dosadorStatus
     */
    public DosadorStatus getDosadorStatus() {
        return this.dosadorStatus;
    }

    /**
     * Atribui o Beam do tipo {@link DosadorStatus} que contem as informações de funcionamento do dosador
     * @see DosadorStatus
     * @param dosadorStatus the dosadorStatus to set
     */
    public void setDosadorStatus(DosadorStatus dosadorStatus) {
        this.dosadorStatus = dosadorStatus;
    }

    /**
     * Retorana o Beam do tipo {@link Dosador} que contem as informações de id e MAC do dosador
     * @see Dosador
     * @return the dosador
     */
    public Dosador getDosador() {
        return this.dosador;
    }

    /**
     * Atribui o Beam do tipo {@link Dosador} que contem as informações de id e MAC do dosador
     * @see Dosador
     * @param dosador the dosador to set
     */
    public void setDosador(Dosador dosador) {
        this.dosador = dosador;
    }

 
    
}
