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
public class DosadorTime {
    private long  dosador;
    private int ID;
    private int feed;
    private int feedBegin;
    private int motorFeed;    
    private int motorStop;
    private int motorReverse;       
    private int animalOut;   

    /**
     * Ta - Tempo entre alimentação<br>
     * Pega o tempo emtre cada dosagem de alimentação, deve ser maior que a soma dos tempos de acionamento do motor.<br>
     * @return the feed
     */
    public int getFeed() {
        return this.feed;
    }

    /**
     * Ta - Tempo entre alimentação<br>
     * Atribui o tempo emtre cada dosagem de alimentação, deve ser maior que a soma dos tempos de acionamento do motor.<br>
     * @param feed the feed to set
     */
    public void setFeed(int feed) {
        this.feed = feed;
    }

    /**
     * Ti - Tempo de inicio<br>
     * Pega o tempo entre a detecção do animal e o início da primeira dose, o acionamento do motor.<br>
     * @return the feedBegin
     */
    public int getFeedBegin() {
        return this.feedBegin;
    }

    /**
     * Ti - Tempo de inicio<br>
     * Atribui o tempo entre a detecção do animal e o início da primeira dose, o acionamento do motor.<br>
     * @param feedBegin the feedBegin to set
     */
    public void setFeedBegin(int feedBegin) {
        this.feedBegin = feedBegin;
    }

    /**
     * Td - Tempo de dosagem<br>
     * Pega o tempo que o motor deve ficar acionado, representando uma dose de ração.<br>
     * @return the motorFeed
     */
    public int getMotorFeed() {
        return this.motorFeed;
    }

    /**
     * Td - Tempo de dosagem<br>
     * Atribui o tempo que o motor deve ficar acionado, representando uma dose de ração.<br>
     * @param motorFeed the motorFeed to set
     */
    public void setMotorFeed(int motorFeed) {
        this.motorFeed = motorFeed;
    }

    /**
     * Tp - Tempo de pausa do motor<br>
     * Pega o Tempo de pausa do motro entre o sentido de dosagem e reversão do dosador.<br>
     * Como o ciclo de alimentação exige que o dosador alimente e volte o motor um pouco para tirar ração residual do dosador,
     * o tempo Tp serve para dar uma pausa entre a troca de sentido do motor.<br>
     * @return the motorStop
     */
    public int getMotorStop() {
        return this.motorStop;
    }

    /**     
     * Tp - Tempo de pausa do motor<br>
     * Atribui Tempo de pausa do motro entre o sentido de dosagem e reversão do dosador.<br>
     * Como o ciclo de alimentação exige que o dosador alimente e volte o motor um pouco para tirar ração residual do dosador,
     * o tempo Tp serve para dar uma pausa entre a troca de sentido do motor.<br>
     * @param motorStop the motorStop to set
     */
    public void setMotorStop(int motorStop) {
        this.motorStop = motorStop;
    }

    /**
     * Tr - Tempo de reversão<br>
     * Pega o tempo que o motor fica acionado no sentido reverso para retirar a ração residual
     * do dosador.
     * @return the motorReverse
     */
    public int getMotorReverse() {
        return this.motorReverse;
    }

    /**
     * Tr - Tempo de reversão<br>
     * Atribui o tempo que o motor fica acionado no sentido reverso para retirar a ração residual
     * do dosador.
     * @param motorReverse the motorReverse to set
     */
    public void setMotorReverse(int motorReverse) {
        this.motorReverse = motorReverse;
    }

    /**
     * Ts - Tempo de saída<br>
     * Pega o tempo que o animal deve se ausentar da antena de detecção para considera que houve o fim
     * da alimentação e evitar gerar muitos registros de eventos de alimentação.
     * @return the animalOut
     */
    public int getAnimalOut() {
        return this.animalOut;
    }

    /**
     * Ts - Tempo de saída<br>
     * Atribui o tempo que o animal deve se ausentar da antena de detecção para considera que houve o fim
     * da alimentação e evitar gerar muitos registros de eventos de alimentação.
     * @param animalOut the animalOut to set
     */
    public void setAnimalOut(int animalOut) {
        this.animalOut = animalOut;
    }

    /**
     * Retorna o Dosador ao qual os parametros se referem
     * @return the dosador
     */
    public long getDosador() {
        return this.dosador;
    }

    /**
     * Atribui p Dosador ao qual os parametros se referem
     * @param dosador the dosador to set
     */
    public void setDosador(long dosador) {
        this.dosador = dosador;
    }

    /**
     * @return the ID
     */
    public int getID() {
        return this.ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }
    
}
