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
public enum MsgXbeeType {
    /**
     * Valor 10h - TRANSMIT_REQUEST - Requisição de transmissao <br>
     * É enviada ao xbee para que este envie informação na rede. O destinatário recebe
     * uma mensagem do tipo {@link MsgXbeeType#RECEIVED_PACKAGE}.<br>
     * seus campos mais importantes são:<br>
     * -Tamanho da Mensagem<br>
     * -Endereço de Destino<br>
     * -Mensagem<br>
     */
    TRANSMIT_REQUEST(0x10),
    /**
     * Valor 90h - RECEIVED_PACKAGE - Pacote de mensagem recebido <br>
     * É a mensagem recebida pelo xbee que corresponde a resposta de uma mensagem do tipo
     * {@link MsgXbeeType#TRANSMIT_REQUEST}.<br>
     * seus campos mais importantes são:<br>
     * -Tamanho da Mensagem<br>
     * -Endereço de Remetente<br>
     * -Mensagem<br>
     */
    RECEIVED_PACKAGE(0x90),
    /**
     * Valor 8Bh - TRANSMIT_STATUS - Sstatus de requisição de transmissao <br>
     * Retorna o status da transmissão solicitada, a resposta pode ser:<br>
     * Destinatario não encontrado<br>
     * Falha de reconhecimento da rede<br>
     * dentre outros.
     */
    TRANSMIT_STATUS(0x8B),
    /**
     * Valor 60h - MODEM_STATUS - Requisição de transmissao <br>
     * <br>
     */
    MODEM_STATUS(0x60),
    /**
     * Valor 30h - MODEM_STATUS - Requisição de transmissao<br>
     */
    REMOTE_AT_COMMAND(0x30),
    /**
     * Valor 08h - AT_COMMAND - Comando AT para parametrização do xbee<br>
     * Mensagem para configuração do xbee, para parametros como.<br>
     * -Parametos de Rede<br>
     * -Configurações Serial<br>
     * -Configuração de pinos<br>
     * para maiores detalhes ver <a href="https://www.digi.com/resources/documentation/digidocs/pdfs/90002002.pdf">Datasheet</a>
     */
    AT_COMMAND(0x08),
    /**
     * Valor A5h - JOIN_NOTIFICATION_STATUS - Notificação de novo xbee conectado a rede<br>
     * <br>
     */
    JOIN_NOTIFICATION_STATUS(0XA5),
    
    ROUTE_RECORD_INDICATOR(0XA1);
    public int valor;
    MsgXbeeType(int val) {
        valor = val;
    }
    /**
     * <br>
     * @return Retorna valor do ENUM
     */
    public int getValor(){
        return valor;
    }
    
    /**
     * Busca o ENUM correspondente ao valor inteiro<br>
     * @param id Valor que se deseja encontrar o enum
     * @return Retorna ENUM correspondente
     */
    public static MsgXbeeType  findType(int id) {
        for(MsgXbeeType e : values()) {
            if (e.getValor() == id)
                return e;
        }
        return null;
    }
    
}
