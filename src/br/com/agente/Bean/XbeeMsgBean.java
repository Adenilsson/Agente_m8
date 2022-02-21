/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agente.Bean;

import br.com.agente.Enum.MsgXbeeType;

/**
 *
 * @author nosli
 */
public class XbeeMsgBean {
    
    private MsgXbeeType xbee_msg_type;
    private long  address64;
    private int address16;
    private NetworkMsgBean networMsg;
    private TransmitStatusMsg transmitStatus;
    private String msg;
    private int num_node;

    public int getNum_node() {
        return num_node;
    }

    public void setNum_node(int num_node) {
        this.num_node = num_node;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    /**
     * Retorna o Beam referente a mensagem do tipo {@link br.com.agente.Enum.MsgXbeeType#TRANSMIT_STATUS}
     * que retorna os status do envio de uma mensagem.
     * @return the transmitStatus
     */
    public TransmitStatusMsg getTransmitStatus() {
        return this.transmitStatus;
    }

    /**
     * Atribui o Beam referente a mensagem do tipo {@link br.com.agente.Enum.MsgXbeeType#TRANSMIT_STATUS}
     * que retorna os status do envio de uma mensagem.
     * @param transmitStatus the transmitStatus to set
     */
    public void setTransmitStatus(TransmitStatusMsg transmitStatus) {
        this.transmitStatus = transmitStatus;
    }
    
    /**     
     * Retorna o tipo de mensagem do do xbee {@link br.com.agente.Enum.MsgXbeeType}
     * para tratamento durante a recepção ou envio.
     * @see MsgXbeeType
     * @return the xbee_msg_type
     */
    public MsgXbeeType getXbee_msg_type() {
        return this.xbee_msg_type;
    }

    /**     
     * Retorna o tipo de mensagem do do xbee {@link br.com.agente.Enum.MsgXbeeType}
     * para tratamento durante a recepção ou envio.
     * @see MsgXbeeType
     * @param xbee_msg_type the xbee_msg_type to set
     */
    public void setXbee_msg_type(MsgXbeeType xbee_msg_type) {
        this.xbee_msg_type = xbee_msg_type;
    }


    /**   
     * Retorna o endereço de 16bits do xbee na rede, pode ser do transmissor ou receptor
     * dependendo do tipo de mensagem.
     * @see MsgXbeeType
     * @return the addres16
     */
    public int getAddress16() {
        return this.address16;
    }

    /**
     * Atribui o endereço de 16bits do xbee na rede, pode ser do transmissor ou receptor
     * dependendo do tipo de mensagem.
     * @param addres16 the addres16 to set
     */
    public void setAddress16(int addres16) {
        this.address16 = addres16;
    }

    /**
     * Retorna a mensagem do tipo {@link NetworkMsgBean} que corresponde a mensagem 
     * da rede dos dosadores.
     * @return the networMsg
     */
    public NetworkMsgBean getNetworMsg() {
        return this.networMsg;
    }

    /**
     * Atribui a mensagem do tipo {@link NetworkMsgBean} que corresponde a mensagem 
     * da rede dos dosadores.
     * @param networMsg the networMsg to set
     */
    public void setNetworMsg(NetworkMsgBean networMsg) {
        this.networMsg = networMsg;
    }

    /**
     * Retorna o endereço de 64bits do xbee (Endereço físico MAC), pode ser do transmissor ou receptor
     * dependendo do tipo de mensagem.
     * @return the address64
     */
    public long getAddress64() {
        return this.address64;
    }

    /**     
     * Atribui o endereço de 64bits do xbee (Endereço físico MAC), pode ser do transmissor ou receptor
     * dependendo do tipo de mensagem.
     * @param address64 the address64 to set
     */
    public void setAddress64(long address64) {
        this.address64 = address64;
    }
    
}
