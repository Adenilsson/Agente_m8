package br.com.agente.Serial;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nosli
 */
public class ParamesSerialBean {
    private int baud;
    private String port;
    private int time_out;
    private int data_bits;
    private int stop_bits;
    private int parity;
    

    /**
     * @return the baud
     */
    public int getBaud() {
        return baud;
    }

    /**
     * @param baud the baud to set
     */
    public void setBaud(int baud) {
        this.baud = baud;
    }

    /**
     * @return the port
     */
    public String getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * @return the time_out
     */
    public int getTime_out() {
        return time_out;
    }

    /**
     * @param time_out the time_out to set
     */
    public void setTime_out(int time_out) {
        this.time_out = time_out;
    }

    /**
     * @return the data_bits
     */
    public int getData_bits() {
        return data_bits;
    }

    /**
     * @param data_bits the data_bits to set
     */
    public void setData_bits(int data_bits) {
        this.data_bits = data_bits;
    }

    /**
     * @return the stop_bits
     */
    public int getStop_bits() {
        return stop_bits;
    }

    /**
     * @param stop_bits the stop_bits to set
     */
    public void setStop_bits(int stop_bits) {
        this.stop_bits = stop_bits;
    }

    /**
     * @return the parity
     */
    public int getParity() {
        return parity;
    }

    /**
     * @param parity the parity to set
     */
    public void setParity(int parity) {
        this.parity = parity;
    }
}
