/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agente.MsgFormat;

import br.com.agente.Bean.Baias;
import br.com.agente.Bean.DosadorStatus;
import br.com.agente.Bean.NetworkMsgBean;
import br.com.agente.Enum.MsgNetworkType;
import java.util.Calendar;

/**
 *
 * @author nosli
 */
public class NetworkMsg {
    
    int start=13;
    /**
     * Formatação de mensagens da rede de dosadores<br>
     * Formata o vetor de char byte a byte de acordo com o struct formatado nos dosadores
     * O primeiro byte determina o tipo de mensagem da rede dos dosadores.<br>
     * (Maiores detalhes dos vetor de char recebidos <a href="../../../../docs/msg_array.pdf">aqui</a>).
     * @param data Mensagem no formato de bytes recebido pelo xbee.
     * @return Retorna a mensagem formatada no Bean
     */
    public NetworkMsgBean  format(char data[]){
        NetworkMsgBean msg=new NetworkMsgBean();
        msg.setNetwork_msg_type(MsgNetworkType.findType(data[start])); 
//        short id;
//        int date_time=0;
//        Calendar cal;
//        Baias baia=new Baias();
//        switch(msg.getNetwork_msg_type()){
//            case DOSADOR_DATA_UPDATE:
//                baia.setQR((byte) (data[start+1]));
//                id=(short) data[start+3];
//                id=(short) (id*256);
//                id+=data[start+2];
//                 baia.setID(id);
//                 baia.setQA((data[start+4]));
//                break;
//            case DOSADOR_STACK_DATA:
//                 baia.setQA((byte) (data[start+1]));
//                id=(short) data[start+3];
//                id=(short) (id*256);
//                id+=data[start+2];
//                 baia.setID(id);
//                date_time=0;
//                for(int i=0;i<4;i++){
//                    date_time=date_time<<8;
//                    date_time|=(data[start+7-i]);
//                }
//                //Formata a hora-data
//                cal=Calendar.getInstance();
//                int sec=date_time&0b111111;
//                date_time=date_time>>6;
//                int min=date_time&0b111111;
//                date_time=date_time>>6;
//                int hour=date_time&0b11111;
//                date_time=date_time>>5;
//                int year=(date_time&0b111111)+2000; 
//                date_time=date_time>>6;
//                int month=(date_time&0b1111)-1;
//                date_time=date_time>>4;
//                int date=date_time&0b11111;              
//                cal.set(year, month,date, hour,min,sec);
//                 baia.setDate(cal);
//                break;
//            case AGENTE_UPDATE_REQUEST:
//                break;
//            case DOSADOR_STACK_STATUS:
//                DosadorStatus d_status=new DosadorStatus();
//                if(data[start+5]>10)
//                    d_status.setRationSensorStatus((byte)1);
//                else
//                    d_status.setRationSensorStatus((byte)0);
//                d_status.setNetworkStatus((short) data[start+2]);//Falhas no xbee
//                d_status.setAntenaStatus((short) data[start+4]);
//                d_status.setMemmoryStatus((short) data[start+3]);
//                d_status.setMotorStatus(data[start+6]);
//                d_status.setTemperatura(data[start+7]);
//                d_status.setTemperatura_b(data[start+8]);
//                msg.setStack_size((short) data[start+1]);
//                msg.setDosadorStatus(d_status);
//                break;
//            case DOSADOR_RFID_NOT_FOUND:
//                long rfid=0;
//                for(int i=0;i<8;i++){
//                    rfid=rfid<<8;
//                    rfid|=(data[start+15-i]);
//                }
//                 baia.setRFID(rfid);
//                for(int i=0;i<4;i++){
//                    date_time=date_time<<8;
//                    date_time|=(data[start+19-i]);
//                }
//                cal=Calendar.getInstance();
//                int sec2=date_time&0b111111;
//                date_time=date_time>>6;
//                int min2=date_time&0b111111;
//                date_time=date_time>>6;
//                int hour2=date_time&0b11111;
//                date_time=date_time>>5;
//                int year2=(date_time&0b111111)+2000; 
//                date_time=date_time>>6;
//                int month2=(date_time&0b1111)-1;
//                date_time=date_time>>4;
//                int date2=date_time&0b11111;              
//                cal.set(year2, month2,date2, hour2,min2,sec2);
//                 baia.setDate(cal);
//                break;
//            case DOSADOR_NEW_DEVICE_OK:
//                short rch=0;long rfch=0;
//                for(int i=0;i<2;i++){
//                    rch=(short) (rch<<8);
//                    rch|=(data[start+3-i]);
//                }
//                for(int i=0;i<8;i++){
//                    rfch=rfch<<8;
//                    rfch|=(data[start+15-i]);
//                }
//                msg.setReceipeChecksum(rch);
//                msg.setRfidChecksum(rfch);
//                break;
//        }
//        msg.setBaia(baia);
        return msg;
    }
    
}
    

