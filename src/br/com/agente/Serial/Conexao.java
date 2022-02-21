/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agente.Serial;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.Observable;
import java.util.TooManyListenersException;

/**
 *
 * @author nosli
 */
public class Conexao extends Observable implements SerialPortEventListener {

    public InputStream  input;
    public OutputStream output;
    public SerialPort serialPort;
    public boolean sincronismo=false;
    /**
     * Fáz a conexão com a serial com os parametros passados pelo objeto  
     * {@link br.com.agente.Serial.ParamesSerialBean} , tais dados incluem porta, baud_rate,
     * dentre outros
     * @param parametros Parametros da conexão serial
     * @return .
     * @throws TooManyListenersException .
     * @throws InterruptedException  .
     */
    public String Conectar(ParamesSerialBean parametros) throws TooManyListenersException, InterruptedException {
        //Velocidade de comunicação da porta
        CommPortIdentifier portId = null;
        Enumeration pList = CommPortIdentifier.getPortIdentifiers();
        while (pList.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) pList.nextElement();
            if (currPortId.getName().equals(parametros.getPort())) {
                portId = currPortId;
            }
        }
        if (portId == null) {
            return "Selecione uma porta.";
        }
        try {
            // Abre a porta selecionada
            this.serialPort = (SerialPort) portId.open(parametros.getPort(), parametros.getTime_out());
            // set port parameters
            this.serialPort.setSerialPortParams(parametros.getBaud(),
                    parametros.getData_bits(),
                    parametros.getStop_bits(),
                    parametros.getParity());
            // abre os streams streams
            this.input = this.serialPort.getInputStream();
            this.output = this.serialPort.getOutputStream();
            // add os eventos do buffer
            this.serialPort.addEventListener(this);
            this.serialPort.notifyOnDataAvailable(true);
        } catch (PortInUseException | UnsupportedCommOperationException | IOException e) {
            System.err.println(e.toString());
            if (e.toString().equals("gnu.io.PortInUseException: Unknown Application")) {
                return "Porta em uso";
            }
            System.err.println(e.toString());
            return "Erro";
        }
        
        return "Conectado";
    }
    
    /**
     * Envia uma String pela serial
     * @param msg Mensagem no formato ASCII
     * @return Retorna o status do envio
     * @throws InterruptedException . 
     */
    public synchronized String Enviar(String msg) throws InterruptedException {
        try {
            this.output.write(msg.getBytes());
            
                //System.out.print(msg);
            
        } catch (IOException e) {
            System.out.println(new Date()+"  "+"STATUS: " + e);
            System.exit(1);
            return "Houve um erro durante o envio. "+e;
        }
        return "";
    }
    
    /**
     * Envia um vetor de bytes, enviando eatamente seuvalor diferente do metodo de envio de String 
     * @param msg Vetor de bytes
     * @return Retorna o status do envio
     * @throws InterruptedException .
     */
    public synchronized String Enviar(byte msg[]) throws InterruptedException {
        System.out.println("Enviando mensagem");
       
        try {
            this.output.write(msg);
        } catch (IOException e) {
            System.out.println(new Date()+"  "+"STATUS: " + e);
            System.exit(1);
            return "Houve um erro durante o envio. "+e;
        }
        return "";
    }
    
    /**
     * Fecha a conexão Serial
     */
    public void Close() {
        try {
            this.serialPort.removeEventListener();
            this.serialPort.close();
            this.output.close();
            this.input.close();
        } catch (IOException e) {
            System.out.println(new Date()+"  "+"Erro fechando porta: " + e);
            System.exit(0);
        }
    }
    int cont = 0;
    /**
     * Método que trata a chamada dos eventos de listener da Serial
     * @param ev Evento que chamou o método
     */
    @Override
    public void serialEvent(SerialPortEvent ev) {
        if (ev.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
            int data;
            if(this.input.available()>0){
                    data=this.input.read();
                    if(data==0x7E){		//Byte de Inicio do pacote do xbee
                        data=(char) this.input.read();
                        int length=data;
                        data= this.input.read();
                        length=(length<<8)+data+1;
                        byte check_sum,check_sum_local=0;
                        char mensagem[]= new char[length+2];
                        mensagem[0]=(char) length;
                        for(int i=1;i<=length;i++){
                            data=(char) this.input.read();
                            if(data!=(-1)){
                                if(i==length){
                                        check_sum=(byte) data;
                                        check_sum_local=(byte) (0xff-check_sum_local);
                                        if(check_sum_local==check_sum){
                                            setChanged();
                                            notifyObservers(mensagem);
                                        }
                                }
                                mensagem[i]=(char) data;
                                check_sum_local+=data;
                            }
                        }     
                    }
                }
            } catch (IOException | NumberFormatException e) {
                cont = 0;
                System.err.println(e.toString());
            }
        }
    }
}

