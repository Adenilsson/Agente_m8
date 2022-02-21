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
public class TransmitStatusMsg {
     private byte frameID;
    private byte transmitRetryCount;
    private byte deliveryStatus;
    private byte discoveryStatus;

    /**
     * Retorna o ID de controle de envio de mensagens
     * @return the frameID
     */
    public byte getFrameID() {
        return this.frameID;
    }

    /**
     * Atribui o ID de controle de envio de mensagens
     * @param frameID the frameID to set
     */
    public void setFrameID(byte frameID) {
        this.frameID = frameID;
    }

    /**
     * Retorna o numero de tentativas de envio da mensagem
     * @return the transmitRetryCount
     */
    public byte getTransmitRetryCount() {
        return this.transmitRetryCount;
    }

    /**
     * Atribui o numero de tentativas de envio da mensagem
     * @param transmitRetryCount the transmitRetryCount to set
     */
    public void setTransmitRetryCount(byte transmitRetryCount) {
        this.transmitRetryCount = transmitRetryCount;
    }

    /**
     * Retorna o status da entrega da mensagem.
     * @return the deliveryStatus
     */
    public byte getDeliveryStatus() {
        return this.deliveryStatus;
    }

    /**
     * Atribui o status da entrega da mensagem.
     * @param deliveryStatus the deliveryStatus to set
     */
    public void setDeliveryStatus(byte deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    /**
     * Retorna o status da descoberta de rede.
     * @return the discoveryStatus
     */
    public byte getDiscoveryStatus() {
        return this.discoveryStatus;
    }

    /**
     * Atribui o status da descoberta de rede.
     * @param discoveryStatus the discoveryStatus to set
     */
    public void setDiscoveryStatus(byte discoveryStatus) {
        this.discoveryStatus = discoveryStatus;
    }
}
