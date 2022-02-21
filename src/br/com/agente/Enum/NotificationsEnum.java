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
public enum NotificationsEnum {
    /**
     * Valor 1 - NETWORK OPEN - REQUISIÇÃO PARA ABRIR REDE<BR>
     * 
     */
    NOPEN(1),
    /**
     * Valor 2 - NOVO DOSADOR NOTIFICAÇÃO - NOTIFICAÇÃO DE NOVO DOSADOR<BR>
     * 
     */
    NDNOT(2),
    /**
     * Valor 3 - NOVO DOSADOR CONFIGURAR - REQUISIÇÃO DE CONFIGURAÇÃO DE NOVO DOSADOR<BR>
     * 
     */
    NDCONF(3),
    /**
     * Valor 4 - NOVO DOSADOR FIM - NOTIFICAÇÃO DE FIM DE CONFIGURAÇÃO DE DOSADOR<BR>
     * 
     */
    NDFIM(4),
    /**
     * Valor 5 - TAD DESCONHECIDA - NOTIFICAÇÃO DE BRINCO DESCONHECIDO<BR>
     * 
     */
    TAGD(5),
    /**
     * Valor 6 - DOSADOR RESET REQUISIÇÃO - REQUISIÇÃO DE RESET DE UM DOSADOR<BR>
     * 
     */
    DRSTR(6),
    /**
     * Valor 7 - DOSADOR RESET NOTIFICAÇÃO - NOTIFICAÇÃO DE RESET DE UM DOSADOR<BR>
     * 
     */
    DRSTN(7),
    /**
     * Valor 8 - RECEITA UPDATE REQUISIÇÃO - REQUISIÇÃO DE ATUALIZAÇÃO DE RECEITAS NOS DOSADORES<BR>
     * 
     */
    RUPREQ(8),
    /**
     * Valor 9 - RECEITA UPDATE NOTIFICAÇÃO - NOTIFICAÇÃO DE ATUALIZAÇÃO DE RECEITAS NOS DOSADORES<BR>
     * 
     */
    RUPNOT(9),
    /**
     * Valor 10 - ANIMAL UPDATE REQUISISÇÃO - REQUISIÇÃO A ATUALIZAÇÃO DOS DADOS DOS ANIMAIS NOS DOSADORES<BR>
     * 
     */    
    AUPREQ(10),
    /**
     * Valor 11 - ANIMAL UPDATE NOTIFICAÇÃO - NOTIFICAÇÃO DE ATUALIZAÇÃO DOS DADOS DOS ANIMAIS NOS DOSADORES<BR>
     * 
     */
    AUPNOT(11),
    /**
     * Valor 12 - TIME UPDATE REQUISIÇÃO - REQUISITA A PARAMETRIZAÇÃO DOS TEMPOS DOS DOSADORES<BR>
     * 
     */
    TUPREQ(12),
    /**
     * Valor 13 - TIME UPDATE NOTIFICAÇÃO - NOTIFICA A PARAMETRIZAÇÃO DOS TEMPOS DOS DOSADORES<BR>
     * 
     */
    TUPNOT(13),
    /**
     * Valor 14 - NETWORK STOP - REQUISITA A PARALIZAÇÃO DOS DOSADORES<BR>
     * 
     */    
    NSTOP(14),
    /**
     * Valor 15  - Prioridade de busca 
     */
    DPB(15);
    
    public int valor;
    NotificationsEnum(int val) {
        valor = val;
    }
    /**
     * Retorna o valor do ENUM
     * @return Retorna o valor do ENUM
     */
    public int getValor(){
        return valor;
    }
    /**
     * Busca o ENUM correspondente ao valor inteiro
     * @param id Valor que se deseja encontrar o enum
     * @return Retorna ENUM correspondente
    */
    public static NotificationsEnum  findType(int id) {
        for(NotificationsEnum e : values()) {
            if (e.getValor() == id)
                return e;
        }
        return null;
    }
    
}
