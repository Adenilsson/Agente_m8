/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agente.Bean;

import br.com.agente.Enum.DosadorStatusEnum;

/**
 *
 * @author nosli
 */
public class DosadorStatus {
     private DosadorStatusEnum dosadorStatus;
    private int rationSensorStatus;
    private int antenaStatus;
    private int networkStatus;
    private int memmoryStatus;
    private int motorStatus;
    private int temperatura;
    private int temperatura_b;
    private String tmp;
   
    
    public void setTmp(int temperatura, int temperatura_b, int status_rede){
        if(temperatura > 50 ){
            this.tmp = "null";
            //System.out.println("Neste momento a TEMPERATURA DEVE SER NULL");
        }else{
            if(status_rede != 0 || (temperatura == 0 && temperatura_b == 6 )){
                System.out.println("temp: "+ temperatura + "."+ temperatura_b);
                this.tmp = "null";
            }else{
                 this.tmp = temperatura+"."+temperatura_b;
            }
        }  
    }
    public String getTmp(){
        return this.tmp;
    }
    public int getTemperatura_b() {
        return temperatura_b;
    }

    public void setTemperatura_b(int temperatura_b) {
        this.temperatura_b = temperatura_b;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    /**
     * Pega o valor referente ao status de ligado ou desligado do dosador quando adicionado a uma rede.<br>
     * O agente faz uma requisição e aguarda uma resposta doo dosador, caso haja a resposta ele esta ligado.
     * -Ligado 0
     * -Desligado 1
     * @return the dosadorStatus
     */
    public DosadorStatusEnum getDosadorStatus() {
        return this.dosadorStatus;
    }
    
    /**
     * Atribui o valor referente ao status de ligado ou desligado quando adicionado a uma rede.<br>
     * O agente faz uma requisição e aguarda uma resposta doo dosador, caso haja a resposta ele esta ligado.
     * -Ligado = 0
     * -Desligado = 1
     * @param dosadorStatus the dosadorStatus to set
     */
    public void setDosadorStatus(DosadorStatusEnum dosadorStatus) {
        this.dosadorStatus = dosadorStatus;
    }

    /**
     * Pega o valor referente ao status da ração.<br>
     * É a resposta do sensor indutivo que detecta a presença ou não de ração no dosador.
     * - há ração = 0
     * - sem ração = 1
     * @return the rationSentor
     */
    public int getRationSensorStatus() {
        return this.rationSensorStatus;
    }

    /**
     * Atribui o valor referente ao status da ração.<br>
     * É a resposta do sensor indutivo que detecta a presença ou não de ração no dosador.
     * - há ração = 0
     * - sem ração = 1
     * @param rationSensorStatus the rationSentor to set
     */
    public void setRationSensorStatus(int rationSensorStatus) {
        this.rationSensorStatus = rationSensorStatus;
    }

    /**
     * Pega o valor referente ao status da antena do RFID, conectada ou desconectada.<br>
     * Notifica uma falha da antena, ajudando o diagnóstico com uma simples troca para a
     * correção do problema.
     * - Conectada = 0
     * - Desconectada = 1
     * @return the antenaStatus
     */
    public int getAntenaStatus() {
        return this.antenaStatus;
    }

    /**
     * Atribui o valor referente ao status da antena do RFID, conectada ou desconectada.<br>
     * Notifica uma falha da antena, ajudando o diagnóstico com uma simples troca para a
     * correção do problema.
     * - Conectada = 0
     * - Desconectada = 1
     * @param antenaStatus the antenaStatus to set
     */
    public void setAntenaStatus(int antenaStatus) {
        this.antenaStatus = antenaStatus;
    }

    /**
     * Pega o valor referente a falhas na troca de mensagens da rede usando o auto diagnóstico do dosador.<br>
     * -Sem falhas = 0
     * - Com falhas = 1
     * @return the networkSensor
     */
    public int getNetworkStatus() {
        return this.networkStatus;
    }

    /**
     * Atribui o valor referente a falhas na troca de mensagens da rede usando o auto diagnóstico do dosador.<br>
     * -Sem falhas = 0
     * -Com falhas = 1
     * @param networkStatus the networkSentor to set
     */
    public void setNetworkStatus(int networkStatus) {
        this.networkStatus = networkStatus;
    }

    /**
     * Pega o valor referente a falhas na memória interna do dosador referente a leitura e escrita.<br>
     * -Sem falhas = 0
     * -Com falhas = 1
     * @return the memmorySentor
     */
    public int getMemmoryStatus() {
        return this.memmoryStatus;
    }

    /**
     * Atribui o valor referente a falhas na memória interna do dosador referente a leitura e escrita.<br>
     * -Sem falhas = 0
     * -Com falhas = 1
     * @param memmoryStatus the memmorySentor to set
     */
    public void setMemmoryStatus(int memmoryStatus) {
        this.memmoryStatus = memmoryStatus;
    }

    /**
     * Pega o valor referente a falhas na motor do dosador.<br>
     * -Motor OK = 0
     * -Motor desconectado = 1
     * -Sobrecarga = 2
     * @return the motorStatus
     */
    public int getMotorStatus() {
        return this.motorStatus;
    }

    /**
     * Atribui o valor referente a falhas na motor do dosador.<br>
     * -Motor OK = 0
     * -Motor desconectado = 1
     * -Sobrecarga = 2
     * @param motorStatus the motorStatus to set
     */
    public void setMotorStatus(int motorStatus) {
        this.motorStatus = motorStatus;
    }
    
}
