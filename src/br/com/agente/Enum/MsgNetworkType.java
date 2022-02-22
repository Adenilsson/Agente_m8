/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agente.Enum;

/**
 *
 * @author nosli
 */
public enum MsgNetworkType {
     /**
     * Valor 0 - Requisita a descarga da pilha de registro de eventos do dosador.<br>
     */
    AGENTE_UPDATE_REQUEST(0),
    /**
     * Valor 1 - Usado no debug, para forçar a troca de dia.<br>
     */
    AGENTE_DEBUG_COMMAND(1),
    /**
     * Valor 1 - Usado para debug, para forçar a troca de dia.<br>
     */
    DOSADOR_STACK_DATA(2),
    DOSADOR_STACK_STATUS(3),
    AGENTE_ANIMAL_UPDATE(4),
    AGENTE_RECEIPE_UPDATE_A(5),
    AGENTE_RECEIPE_UPDATE_B(6),
    AGENTE_RECEIPE_UPDATE_C(7),
    AGENTE_RECEIPE_UPDATE_D(8),
    AGENTE_RECEIPE_UPDATE_E(9),
    DOSADOR_DATA_UPDATE(10),
    AGENTE_NETWORK_FREEZE(11),
    DOSADOR_NEW_DEVICE(12),
    AGENTE_NEW_DEVICE_UP(13),
    DOSADOR_NEW_DEVICE_OK(14),
    AGENTE_NEW_DEVICE_END(15),
    AGENTE_DATE_HOUR_UP(16),
    AGENTE_PARAMS_UP(17),
    AGENTE_DOSADOR_RESET(18),
    DOSADOR_RFID_NOT_FOUND(19),
    AGENTE_NEW_DEVICE_REQ(20),
    AGENTE_RECEIPE_UPDATE(50);
    public int valor;
    MsgNetworkType(int val) {
        valor = val;
    }
    public int getValor(){
        return valor;
    }
    public static MsgNetworkType  findType(int id) {
        for(MsgNetworkType e : values()) {
            if (e.getValor() == id)
                return e;
        }
        return null;
    }
    
}
