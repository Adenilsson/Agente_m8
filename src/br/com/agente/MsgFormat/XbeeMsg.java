/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agente.MsgFormat;

import br.com.agente.Bean.Baias;
import br.com.agente.Bean.Grupo;
import br.com.agente.Bean.NetworkMsgBean;
import br.com.agente.Bean.TransmitStatusMsg;
import br.com.agente.Bean.XbeeMsgBean;
import br.com.agente.Enum.MsgNetworkType;
import br.com.agente.Enum.MsgXbeeType;
import br.com.agente.Main.Agente;
import br.com.agente.Serial.Conexao;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nosli
 */
public class XbeeMsg {
    public Conexao conexao;
    public char data[];
    private byte control=0;
    private int tx_status;
    private int id_msg=0;
    /**
     * Formata a mensagem do xbee e dependendo do pacote recebido ({@link br.com.agente.Enum.MsgXbeeType#RECEIVED_PACKAGE}), formata a mensagem da rede
     * chamando o método {@link br.com.agente.MsgFormat.NetworkMsg#format(char[])};
     * @return retorna o objeto com os dados da mensagem do xbee e da rede.
     * @see br.com.agente.MsgFormat.NetworkMsg#format(char[]) 
     * @param data array recebido pela Serial do xbee
     */
    public XbeeMsgBean format(char data[]){
        XbeeMsgBean msg=new XbeeMsgBean();
        msg.setXbee_msg_type(MsgXbeeType.findType(data[1])); 
        int add16;
      
        switch(msg.getXbee_msg_type()){
            case ROUTE_RECORD_INDICATOR:
                int n_node = data[13], n=0;
                long addres=0;
                long r_addres=0;
                String mensagem ="";
                for(int i=2;i<10;i++){
                    addres=addres<<8;
                    addres=addres|(long)data[i];
                }
                msg.setAddress64(addres);
                add16=(data[10]*256);
                add16+=data[11];
                msg.setAddress16(add16);
                msg.setNum_node(data[13]);
                for(int i=14;i<(data.length -2);i+=2){
                    if(r_addres ==0){
                        r_addres=(data[i]*256);
                        r_addres+=data[i+1];
                    }
                        mensagem += Long.toHexString(r_addres);
                        if(mensagem.length()<4){
                            mensagem = "0"+mensagem;
                        }
                        r_addres =0;
                        
                    
//                    if((int)data[i]== 0){
//                        mensagem+="00";
//                        System.out.println(" XbeeMsg linha 67 "+ Long.toHexString(data[i]));
//                    }else{
//                        mensagem += Long.toHexString(data[i]);
//                        System.out.println(" XbeeMsg linha 67 "+ Long.toHexString(data[i]));
//                    }
                }
//                msg.setNum_node(3);
//                mensagem="14b1c3ed54dc";
                msg.setMsg(mensagem);
                break;
            case RECEIVED_PACKAGE:
                System.out.println("Recebendo pacote da rede xbee.");
                long address=0;
                for(int i=2;i<10;i++){
                    address=address<<8;
                    address=address|(long)data[i];
                }
                msg.setAddress64(address);
                add16=(data[10]*256);
                add16+=data[11];
                msg.setAddress16(add16);
                NetworkMsg network_msg=new NetworkMsg();
                System.out.println("class xbeemsg linha 98: Network_msg.format(data): "+ network_msg.format(data).getNetwork_msg_type());
                msg.setNetworMsg(network_msg.format(data));
                NetworkMsgBean nm=msg.getNetworMsg();
                Grupo a_temp= nm.getGrupo(); //msg.getNetworMsg().getAnimal();
                a_temp.setUltimoDosador(address);
                nm.setGrupo(a_temp);
                msg.setNetworMsg(nm);
                System.out.println("Mac_16: "+ Long.toHexString(msg.getAddress16()));
                System.out.println("Mac: "+ Long.toHexString(msg.getAddress64()));
                System.out.println("baia:  "+nm.getGrupo());
//                
                break;
            case TRANSMIT_STATUS:
                add16=(short) (data[3]*256);
                add16+=(short) data[4];
                msg.setAddress16(add16);
                TransmitStatusMsg transmitStatus = new TransmitStatusMsg();
                transmitStatus.setTransmitRetryCount((byte) data[5]);
                transmitStatus.setDeliveryStatus((byte) data[6]);
                transmitStatus.setDiscoveryStatus((byte) data[7]);
                transmitStatus.setFrameID((byte) data[2]);
                msg.setTransmitStatus(transmitStatus);
                break;
        }
        return msg;
    }
    /**
     * Formata a data-hora para o formato utilizado na rede
     * @param date Data a ser formatada
     * @return Retorna valor long com o valor da data em 32bits unsigned
     */
    public long dataFormat(Calendar date){
        long date_time=0;
        date_time|=((date.get(Calendar.DAY_OF_MONTH))&0b11111);
        date_time=date_time<<4;
        date_time|=((date.get(Calendar.MONTH)+1)&0b1111);
        date_time=date_time<<6;
        date_time|=(date.get(Calendar.YEAR)-2000)&0b111111;
        date_time=date_time<<5;
        date_time|=(date.get(Calendar.HOUR_OF_DAY))&0b11111;
        date_time=date_time<<6;
        date_time|=(date.get(Calendar.MINUTE) &0b111111);
        date_time=date_time<<6;
        date_time|=(date.get(Calendar.SECOND)&0b111111);
        return date_time;  
    }
    
    /**
     * Envia uma mensagem para o destinatario (MAC addres) na rede, sendo a mensagem especificada no Bean
     * {@link br.com.agente.Bean.NetworkMsgBean};
     * @param address Endereço do dosador de destino
     * @param msg Mensagem a ser enviada
     * @return Retorna o identificador da mensagem para contorle do transmit_status
     * @throws InterruptedException .
     */
    public byte sendNetworkMsg(long address, NetworkMsgBean msg) throws InterruptedException{
        byte char_msg[];
        //char_msg=new byte[19];//Obs provisorio  pra não geerar erro no retorno remover quando for imprlementado.
        byte checksum;
        int start_msg=17;
        switch(msg.getNetwork_msg_type()){
            
            case AGENTE_UPDATE_REQUEST:
                char_msg=new byte[19];
                char_msg[2]=15;//Tamanho
                break;
            case AGENTE_DEBUG_COMMAND:
                char_msg=new byte[19];
                char_msg[2]=15;//Tamanho
                break;
            case AGENTE_ANIMAL_UPDATE:
                char_msg=new byte[34];
                char_msg[2]=30;//Tamanho
                short id = (short) msg.getGrupo().getId();
                char_msg[start_msg+1]=(byte) msg.getGrupo().getTbBaiaId();
                for(int i=0;i<2;i++){
                    char_msg[start_msg+2+i]=(byte)id;
                    id=(short) (id>>8);
                }
//                long rfid=msg.getGrupo().getRFID();
//                for(int i=0;i<8;i++){
//                    char_msg[start_msg+4+i]=(byte)rfid;
//                    rfid=rfid>>8;
//                }
                char_msg[29]=(byte) msg.getGrupo().getTbCurvaId();
                char_msg[30] =(byte) msg.getGrupo().getQR();
                char_msg[31]=(byte) msg.getGrupo().getScore();
                char_msg[32]= (byte)msg.getGrupo().getT_feed();
                break;
            case AGENTE_RECEIPE_UPDATE_A:
                char_msg=new byte[50];
                char_msg[2]=46;//Tamanho
                char_msg[start_msg+1]=msg.getReceipe().getID();
                for(int i=0;i<30;i++){
                    char_msg[start_msg+2+i]=(byte) msg.getReceipe().getQRdia()[i];
                }
                break;
            case AGENTE_RECEIPE_UPDATE_B:
                char_msg=new byte[50];
                char_msg[2]=46;//Tamanho
                char_msg[start_msg+1]=msg.getReceipe().getID();
                for(int i=0;i<30;i++){
                    char_msg[start_msg+2+i]=(byte) msg.getReceipe().getQRdia()[i+30];
                }
                break;
            case AGENTE_RECEIPE_UPDATE_C:
                char_msg=new byte[50];
                char_msg[2]=46;//Tamanho
                char_msg[start_msg+1]=msg.getReceipe().getID();
                for(int i=0;i<30;i++){
                    char_msg[start_msg+2+i]=(byte) msg.getReceipe().getQRdia()[i+60];
                }
                break;            
            case AGENTE_RECEIPE_UPDATE_D:
                char_msg=new byte[50];
                char_msg[2]=46;//Tamanho
                char_msg[start_msg+1]=msg.getReceipe().getID();
                for(int i=0;i<30;i++){
                    char_msg[start_msg+2+i]=(byte) msg.getReceipe().getQRdia()[i+90];
                }
                break;
            case AGENTE_NEW_DEVICE_UP:
                char_msg=new byte[23];
                char_msg[2]=19;//Tamanho
                char_msg[start_msg+1]=(byte)msg.getNReceipe();
                short val=msg.getNRFID();
                //RFID
                for(int i=0;i<2;i++){
                    char_msg[start_msg+2+i]=(byte)val;
                    val=(short) (val>>8);
                }
                break;
            case AGENTE_PARAMS_UP:
                char_msg=new byte[36];
                char_msg[2]=32;//Tamanho
                for(int i=0;i<2;i++){
                    char_msg[start_msg+2+i]=(byte)msg.getDosadorTime().getMotorFeed();
                    msg.getDosadorTime().setMotorFeed((short) (msg.getDosadorTime().getMotorFeed()>>8));
                    char_msg[start_msg+4+i]=(byte)msg.getDosadorTime().getMotorReverse();
                    msg.getDosadorTime().setMotorReverse((short) (msg.getDosadorTime().getMotorReverse()>>8));
                    char_msg[start_msg+6+i]=(byte)msg.getDosadorTime().getMotorStop();
                    msg.getDosadorTime().setMotorStop((short) (msg.getDosadorTime().getMotorStop()>>8));
                    char_msg[start_msg+8+i]=(byte)msg.getDosadorTime().getFeedBegin();
                    msg.getDosadorTime().setFeedBegin((short) (msg.getDosadorTime().getFeedBegin()>>8));
                    char_msg[start_msg+10+i]=(byte)msg.getDosadorTime().getFeed();
                    msg.getDosadorTime().setFeed((short) (msg.getDosadorTime().getFeed()>>8));
                    char_msg[start_msg+12+i]=(byte)msg.getDosadorTime().getAnimalOut();
                    msg.getDosadorTime().setAnimalOut((short) (msg.getDosadorTime().getAnimalOut()>>8));                     
                }
                break;
            case AGENTE_DATE_HOUR_UP:
                ;
                char_msg=new byte[50];
                char_msg[2]=46;//Tamanho
                //Data-Hora
                Date dn = new Date();
                Calendar caln = new GregorianCalendar();
                caln.setTime(dn);
                long n_time=dataFormat(caln);
                for(int i=0;i<4;i++){
                    char_msg[start_msg+4+i]=(byte)n_time;
                    n_time=(n_time>>8);
                }
                for(int i=0;i<2;i++){
                    char_msg[start_msg+2+i]=(byte)msg.getNewDay();
                    msg.setNewDay((short) (msg.getNewDay()>>8));
                }
                break;
            case AGENTE_DOSADOR_RESET:
                char_msg=new byte[21];
                char_msg[2]=17;//Tamanho
                char_msg[start_msg+2]='R';//Caractere de Reset
                break;
            case AGENTE_NEW_DEVICE_END:
                char_msg=new byte[19];
                char_msg[2]=15;//Tamanho
                break;
            case AGENTE_NEW_DEVICE_REQ:
                 char_msg=new byte[19];
                char_msg[2]=15;//Tamanho
                break;
            default:
                return 0;
        }
        char_msg[0]=0x7e;
        char_msg[1]=0;
        char_msg[3]=(byte) MsgXbeeType.TRANSMIT_REQUEST.getValor();
        if(control==0)
            control=1;
        char_msg[4]=control++;
        
        for(int i=7;i>=0;i--){
            char_msg[5+i]=(byte)address;
            address=address>>8;
        }
        char_msg[13]=(byte) 0xFF;
        char_msg[14]=(byte) 0xFe;
        char_msg[15]=0x00;      //BROADCAST RADIUS  
        char_msg[16]=0x01;      //OPTION
        //Inicio dos dados
        char_msg[17]=(byte) msg.getNetwork_msg_type().getValor();
        checksum=0;
        for(int i=0; i<=char_msg[2];i++){
            checksum+=char_msg[i+3];
        }
        checksum=(byte) (0xFF-checksum);
        char_msg[char_msg.length-1]=checksum;
        conexao.Enviar(char_msg);
        return char_msg[4];
       
    }
    /**
     * Envia a mensagem e aguarda a resposta do Transmit status, confirmando a entrega ao destinatário.
     * @param dosador Endereço do dosador de destino
     * @param msg Mensagem a ser enviada
     * @return  Retorna o delivery_status da resposta da transmissão
     * @throws java.lang.InterruptedException .
     */
    public int sendNetworkMsgWait(long dosador, NetworkMsgBean msg) throws InterruptedException {
        //System.err.println("  " + Long.toHexString(dosador)+"  ");
        this.id_msg = sendNetworkMsg(dosador, msg);
        long t=System.currentTimeMillis();
        setTx_status(0xff);
        while((getTx_status()==0xFF)&&(System.currentTimeMillis()-t)<1500){
        }
        return getTx_status();
    }
    /**
     * Envia uma receita na rede, devido a receita ser uma quantidade maior de dados ela foi diidida em 4 pacotes,
     * visando a limitação de memória de buffer para as mensagens nos dosadores.
     * @param address Endereço do dosador de destino
     * @param msg Mensagem a ser enviada
     */
    public void sendNetworkReceipe(long address, NetworkMsgBean msg) {
        msg.setNetwork_msg_type(MsgNetworkType.AGENTE_RECEIPE_UPDATE_A);
        try {
            sendNetworkMsg(address, msg);
            msg.setNetwork_msg_type(MsgNetworkType.AGENTE_RECEIPE_UPDATE_B);
            Thread.sleep(150);
            sendNetworkMsg(address, msg);
            msg.setNetwork_msg_type(MsgNetworkType.AGENTE_RECEIPE_UPDATE_C);
            Thread.sleep(150);
            sendNetworkMsg(address, msg);
            msg.setNetwork_msg_type(MsgNetworkType.AGENTE_RECEIPE_UPDATE_D); 
            Thread.sleep(150);
            sendNetworkMsg(address, msg);
            Thread.sleep(150);
        } catch (InterruptedException ex) {
            Logger.getLogger(new Date()+"\n"+Agente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Observador da serial para verificação do transmit status
     * @param o Retorna o objeto observado
     * @param dados Objeto de dados do observado
     */
    //@Override
    public void update(Observable o, Object dados) {
        XbeeMsg xbee=new XbeeMsg();
        char mensagem[]=(char[]) dados;
        XbeeMsgBean msg;
        msg = xbee.format(mensagem);
        if(msg.getXbee_msg_type()==MsgXbeeType.TRANSMIT_STATUS){
            
            if(this.id_msg==msg.getTransmitStatus().getFrameID()){
                setTx_status(msg.getTransmitStatus().getDeliveryStatus());
                //System.err.println(msg.getTransmitStatus().getDeliveryStatus());
            }
        }
    }
    /**
     * Envia as partes da receita aguarda a resposta do Transmit status<br>
     * O teste é feito apenas na primeira parte da receita
     * @param address Endereço do dosador de destino
     * @param msg Mensagem a ser enviada
     * @return Retorna o delivery_status da resposta da transmissão
     */
    public int sendNetworkReceipeWait(long address, NetworkMsgBean msg) {

        try {
            msg.setNetwork_msg_type(MsgNetworkType.AGENTE_RECEIPE_UPDATE_A);
            if(sendNetworkMsgWait(address, msg)!=0)
                return getTx_status();
            msg.setNetwork_msg_type(MsgNetworkType.AGENTE_RECEIPE_UPDATE_B);
            Thread.sleep(150);
            if(sendNetworkMsgWait(address, msg)!=0)
                return getTx_status();
            msg.setNetwork_msg_type(MsgNetworkType.AGENTE_RECEIPE_UPDATE_C);
            Thread.sleep(150);
            if(sendNetworkMsgWait(address, msg)!=0)
                return getTx_status();
            msg.setNetwork_msg_type(MsgNetworkType.AGENTE_RECEIPE_UPDATE_D); 
            Thread.sleep(150);
            if(sendNetworkMsgWait(address, msg)!=0)
                return getTx_status();
        } catch (InterruptedException ex) {
            Logger.getLogger(new Date()+"\n"+Agente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    /**
      * Pega o valor de tx_status no contexto sincronizado
      * @return the tx_status
      */
    public synchronized int getTx_status() {
        return this.tx_status;
    }
    /**
     * Atribui valor a tx_status no contexto sincronizado
     * @param tx_status the val to set
     */
    public synchronized void setTx_status(int tx_status) {
        this.tx_status = tx_status;
}
}
