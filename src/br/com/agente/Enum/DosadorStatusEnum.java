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
public enum DosadorStatusEnum {
     /**
     * Valor 0 - Dosador em Funcionamento na rede<br>
     * Dosador conectado na rede e funcionando, porem não especifica as falhas como
     * sem ração ou motor, apenas a sua presença na rede.
     */
    DOSADOR_OK(0),
    /**
     * Valor 1 - Dosador não encontrado na rede<br>
     * Dosador pode estar desligado ou não conectado a rede.
     */
    DOSADOR_NOT_FOUND(1),
    /**
     * Valor 2 - Falha de perda de mensagens durante as requisições<br>
     * Houve falha ao receber todas as mensagens enviadas pelo dosador durante umma requisisção
     */   
    DOSADOR_LOST_MSG(2);
    public int valor;
    DosadorStatusEnum(int val) {
        valor = val;
    }
    /**
     * Retorna o valor do ENUM
     * @return o valot
     */
    public int getValor(){
        return valor;
    }
}
