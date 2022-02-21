/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agente.Main;

import br.com.agente.Bean.Baias;
import br.com.agente.Bean.Dosador;
import br.com.agente.Bean.DosadorStatus;
import br.com.agente.Bean.DosadorTime;
import br.com.agente.Bean.Grupo;
import br.com.agente.Bean.NetworkMsgBean;
import br.com.agente.Bean.Notificacoes;
import br.com.agente.Bean.Receipe;
import br.com.agente.Bean.XbeeMsgBean;
import br.com.agente.Dao.ServiceDao;
import br.com.agente.Enum.MsgNetworkType;
import br.com.agente.MsgFormat.XbeeMsg;
import br.com.agente.Serial.Conexao;
import br.com.agente.Serial.ParamesSerialBean;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.in;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TooManyListenersException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author nosli
 */
public class Main extends Thread implements Observer{
    public String verção ="Terminação 26-01-22";
    public int cont =0, ligar =0;
    private long BROADCAST_ADDRESS;
    public int id_cordenador;
    public Conexao conexao = new Conexao();
    public int tVarredura = 0;
    DosadorStatus dosadorStatus;
    public int NOTIFICATION_SCAN_TIME = 30000;    // Determina o intervalo de tempo entre a vareduara do banoc de dados 
    public String porta;
    NotifyCheck notifyTH = new NotifyCheck(porta,cont);
    final RequestUpdate requestTask = new RequestUpdate();
    final Timer requestTH = new Timer();
    JSONObject config_jsom;
    short receipe_checksum;
    int agenteID;
    long rfid_checksum, timeoutNUDB;
    byte marcador_request, marcador_update, contador_stack_ref;
    Sincronismo ctr_req = new Sincronismo();
    Sincronismo sinc = new Sincronismo();
    Sincronismo upd = new Sincronismo();
    Sincronismo contador_stack = new Sincronismo();
    
    
    Main(String porta, int id_cordenador) throws Exception {
        
        this.porta = porta;
        this.notifyTH.porta = porta;
        this.id_cordenador = id_cordenador;
        // this.dosador  = new ServiceDao(obj_cfg).buscaDosadorRede(porta);
        JSONParser parser = new JSONParser();
        this.config_jsom = (JSONObject) parser.parse(new FileReader("config.json"));

        this.agenteID = Integer.valueOf((String) config_jsom.get("id_agente"));
        this.NOTIFICATION_SCAN_TIME = Integer.valueOf((String)config_jsom.get("NOTIFICATION_SCAN_TIME"));
        this.BROADCAST_ADDRESS = 0xFFFF;     //Endereço para envio de msg broadcast
        sinc.setVal(3);
        String resposta = "";
        ParamesSerialBean parametros = new ParamesSerialBean();
        parametros.setPort(this.porta);
        parametros.setBaud(115200);
        parametros.setData_bits(8);
        parametros.setParity(0);
        parametros.setStop_bits(1);
        parametros.setTime_out(2000);
        dosadorStatus = new DosadorStatus();
        
        try {
            resposta = conexao.Conectar(parametros);
            System.out.println("Resposta da conexao: " + resposta);
        } catch (TooManyListenersException | InterruptedException ex) {
            Logger.getLogger("[" + new Date() + " ] " + "\n" + Agente.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (resposta.equals("Conectado")) {
            System.out.println("Conectado a Porta: "+this.porta);
            requestTH.schedule(requestTask, 1, 30);
            notifyTH.main(null);
        } else {
            System.out.println("[" + new Date() + " ]  A L: 127:  " + "  " + "Sem resposta da porta Serial.");
            System.exit(1);
        }
    }
   
    @Override
    public synchronized void update(Observable o, Object dados) {
        JSONParser jsonParser1 = new JSONParser();
        JSONObject obj2 = null;
        String tipo = Integer.toString(dosadorStatus.getMotorStatus());
        String resposta = null;
        
        XbeeMsg xbee = new XbeeMsg();
        char mensagem[] = (char[]) dados;
        mensagem[0] = 0x10;
        XbeeMsgBean msg;
        msg = xbee.format(mensagem);
        Baias baia;
        
        int i = 0;
        try{
            System.out.println("Mac "+msg.getAddress64()+" Mac16"+msg.getAddress16()+" TransmitStatus "+msg.getTransmitStatus().getDeliveryStatus()+" MSG "+msg.getMsg());
            System.out.println("msg.getNetworMsg().getNetwork_msg_type() "+msg.getNetworMsg().getNetwork_msg_type());
        }catch(Exception ex){
            Logger.getLogger("[" + new Date() + " ] " + ex);
        }
        switch (msg.getXbee_msg_type()) {
            case ROUTE_RECORD_INDICATOR:
                break;
            case RECEIVED_PACKAGE:
                switch (msg.getNetworMsg().getNetwork_msg_type()) {
                    case DOSADOR_STACK_STATUS:
                        System.out.println("Notificação DOSADOR_STACK_STAUS");
                        break;
                    case DOSADOR_DATA_UPDATE:  
                        System.out.println("Notificação DOSADOR_DATA_UPDATE");
                       break;
                    case DOSADOR_STACK_DATA:   
                        System.out.println("Notificação DOSADOR_STACK_DATA");
                        break;
                    case DOSADOR_NEW_DEVICE:
                        System.out.println("Notificação DOSADOR_NEW_DEVICE");
                        //Insere o novo dosador no Banco de dados ainda não configurado
                        try {
                            //System.out.println("[" + new Date() + " ]  A L: 252:   Novo Dosador: Mac "+ Long.toHexString(msg.getAddress64())+" Adderres16: "+ Integer.toHexString(msg.getAddress16()));
                            new ServiceDao(config_jsom).insereDosador(Long.toHexString(msg.getAddress64()), Long.toHexString(msg.getAddress16()));
                            new ServiceDao(config_jsom).insereNotificacaoNovoDosador(Long.toHexString(msg.getAddress64()));
                        } catch (Exception ex) {
                            Logger.getLogger("[" + new Date() + " ] " + "\n" + Agente.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case DOSADOR_NEW_DEVICE_OK:
                        System.out.println("Notificação DOSADOR_NEW_DEVICE_OK");
                        break;
                    case DOSADOR_RFID_NOT_FOUND:
                        System.out.println("Notificação DOSADOR_RFID_NOT_FOUD");
                        break;
                }
                break;
                case TRANSMIT_STATUS:
                    System.out.println("Notificação TRANSMIT_STATUS");
                timeoutNUDB = System.currentTimeMillis();
                if (ctr_req.getVal() == 0) {
                    if (msg.getTransmitStatus().getFrameID() == marcador_request) {
                        if (msg.getTransmitStatus().getDeliveryStatus() == 0) {
                            ctr_req.setVal(1);
                        } else {
                            ctr_req.setVal(2);
                        }
                    }
                }
                if (upd.getVal() == 1) {
                    if (msg.getTransmitStatus().getFrameID() == marcador_update) {
                        if (msg.getTransmitStatus().getDeliveryStatus() == 0) {      //Address not found
                            upd.setVal(0);
                        } else {
                            upd.setVal(3);
                        }
                    }
                }
                    break;
        }
    }
    
    private class RequestUpdate extends TimerTask {
       
        private int runFlag = 0;
        public int isRunnig() {
            return runFlag;
        }
         private int priorityScaner(){
             return 0;
         }
         
         private int Scaner() throws Exception{
             return 0;
         }
         
        @Override
        public void run() {
            if(ligar == 0){
                
                System.out.println("\n                      Fairtek Terminação V01-00.        \n");
                 ligar =1;
            }
           
            synchronized (this) {
                System.out.println("sinc.getVal = "+ sinc.getVal());
                if (sinc.getVal() == 4) {
                    sinc.setVal(1);
                }
                switch (sinc.getVal()) {
                    case 1:
                        sinc.setVal(2 );

                        try {
                            synchronized (notifyTH) {
                                notifyTH.wait(60000);
                                
                            }
                        } catch (InterruptedException ex) {
                            Logger.getLogger("[" + new Date() + " ] " + "\n" + Agente.class.getName()).log(Level.SEVERE, null, ex);
                            notify();
                            runFlag = 0;
                        }
                        sinc.setVal(3);
                        break;
                }
                try {
                    int res = scanear();
                    if (res == 5) {
                        long f = System.currentTimeMillis();
                        wait(50);

                        sinc.setVal(1);
                        scanear();
                        //System.out.println("[" + new Date() + " ]  A L: 681:  "+"Thread ReqUp Parou");
                        sinc.setVal(2);
                        notify();

                        synchronized (notifyTH) {
                            notifyTH.wait(60000);
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger("[" + new Date() + " ] " + "\n" + Agente.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    notify();
                }
                notify();
                runFlag = 0;
            }
            
            
            
        }

        private int scanear() throws InterruptedException {
            System.out.println("Aqui eu busco os dosadores.");
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            this.wait(5000);
            return 0;
        }
        
    }
    private byte novoDosador(long dosador_address) throws InterruptedException,Exception{
        int nun_temtativas= 0;
        XbeeMsg msg_send = new XbeeMsg();
        NetworkMsgBean msg = new NetworkMsgBean();
        conexao.addObserver((Observer) msg_send);
        msg_send.conexao = conexao;                       //Expecifica a conexao a ser usada
        int resposta = 0;
        //RuterSend rota = new RuterSend();
        Dosador dosador = new ServiceDao(config_jsom).buscaDosador(Long.toHexString(dosador_address));
        //Baias baia = new ServiceDao(config_jsom).buscaBaia(dosador.getTbBaiaId());
        Grupo grupo = new ServiceDao(config_jsom).buscaGrupo(dosador.getTbBaiaId());
        msg.setNetwork_msg_type(MsgNetworkType.AGENTE_ANIMAL_UPDATE);
        rfid_checksum = 0;
        Receipe re = new Receipe();
        msg.setReceipe(re);
        upd.setVal(1);
        
        if(grupo!=null){
            msg.setGrupo(grupo);
            resposta  = msg_send.sendNetworkMsgWait(dosador_address, msg);
            while(nun_temtativas <= 3){
                resposta  = msg_send.sendNetworkMsgWait(dosador_address, msg);
                nun_temtativas++;
                wait(50);
            }
        wait(500);
        }else{
            msg.setGrupo(new Grupo());
           
            //msg.getGurpo().setRFID(0);
            msg.getGrupo().setId((short) 1);
            if(msg_send.sendNetworkMsgWait(dosador_address, msg)!=0){
                conexao.deleteObserver((Observer) msg_send);
                return 2;
            }
        }
        if(resposta != 0){
            conexao.deleteObserver((Observer) msg_send);
        }
        ///////////////////////////////////INICIO DA ATUALIZAÇÃO DAS RECEITAS
        conexao.deleteObserver((Observer) msg_send);
        ArrayList<Receipe> receitas = new ServiceDao(config_jsom).buscaReceitasLista();
        msg.setNetwork_msg_type(MsgNetworkType.AGENTE_RECEIPE_UPDATE);
        receipe_checksum = 0;
        receitas.stream().map((r) -> {
            ///System.out.print("Dieta: "+ r.getID());
            for (int p = 0; p < 150; p++) {
                receipe_checksum += r.getQRdia()[p];
                //  System.out.println( "DIA: "+ i +" QR: "+ r.getQRdia()[i]);
            }
            return r;
        }).map((r) -> {
            msg.setReceipe(r);
            return r;
        }).forEachOrdered((_item) -> {
            msg_send.sendNetworkReceipe(dosador_address, msg);
        }); 
        ///////////////////////////////////ATUALIZA A HORA E MUDANÇA DE DIA.
        msg.setNetwork_msg_type(MsgNetworkType.AGENTE_DATE_HOUR_UP);
        short new_day;
        new_day = 0;
        new_day = (short) (new_day << 8);
        System.out.println("[" + new Date() + " ]  A L: 817:  Tempo apra novo dia:" + new_day);
        new_day += 0;
        msg.setNewDay(new_day);
        msg_send.sendNetworkMsg(dosador_address, msg);
        //////////////////////////////////ATUALIZA OS TEMPOS DO DOSADOR.
        DosadorTime t = new ServiceDao(config_jsom).buscaDosadorTempos(Long.toHexString(dosador_address));
        msg.setNetwork_msg_type(MsgNetworkType.AGENTE_PARAMS_UP);
        msg.setDosadorTime(t);
        try {
            msg_send.sendNetworkMsg(dosador_address, msg);
        } catch (InterruptedException ex) {
            Logger.getLogger("[" + new Date() + " ] " + "\n" + Agente.class.getName()).log(Level.SEVERE, null, ex);
            return 2;
        }
        /////////////////////////////////FINALIZA A CONFIGURAÇÃO DO DOSADOR.
        msg.setNetwork_msg_type(MsgNetworkType.AGENTE_NEW_DEVICE_UP);
            msg.setNRFID((short) 1);
        if (receitas.size() > 13) {
            msg.setNReceipe((short) 14);
        } else if (grupo!=null) {
            msg.setNReceipe(receitas.get(receitas.size() - 1).getID());
        } else {
            msg.setNReceipe((short) 0);
        }
        try {
            msg_send.sendNetworkMsg(dosador_address, msg);
        } catch (InterruptedException ex) {
            Logger.getLogger("[" + new Date() + " ] " + "\n" + Agente.class.getName()).log(Level.SEVERE, null, ex);
            return 2;
        }
        return 0;
    }
    private class NotifyCheck {
        String porta;
        NotifyCheck(String porta, int contador) {
            this.porta = porta;
        }
        public void main(String[] args) throws FileNotFoundException, IOException {
           final ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
            ses.scheduleWithFixedDelay(() -> {
               try {
                    Notificacoes notfi = new ServiceDao(config_jsom).busacaNotificacoesAgente(this.porta);
                    if(notfi.getCodigo()!=null){
                        System.out.println("Notificaçõa: "+notfi.getCodigo());
                        switch(notfi.getCodigo()){
                            case "NOPEN":
                                System.out.println("Abrindo rede na porata:"+ notfi.getPorta());
                                new ServiceDao(config_jsom).notificacaoLida(notfi.getId());
                                 try {
                                    //Abre a rede
                                    System.out.println("Abrindo Rede na Porta: " + notfi.getPorta());
                                    conexao.Enviar(new byte[]{0x7E, 0x00, 0x05, 0x08, 0x01, 0x43, 0x42, 0x02, 0x6F});
                                    conexao.Enviar(new byte[]{0x7E, 0x00, 0x04, 0x08, 0x01, 0x4E, 0x44, 0x64});
                                    XbeeMsg msg_xbee = new XbeeMsg();
                                    NetworkMsgBean msg_send = new NetworkMsgBean();
                                    msg_send.setNetwork_msg_type(MsgNetworkType.AGENTE_NEW_DEVICE_REQ);
                                    msg_xbee.conexao = conexao;
                                    msg_xbee.sendNetworkMsg(BROADCAST_ADDRESS, msg_send);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger("[" + new Date() + " ] " + "\n" + Agente.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            break;
                            case "NDCFG":
                                byte ans = 2;
                                ans = novoDosador(Long.parseLong((String) config_jsom.get("mac"), 16));
                                wait(150);
                                System.out.println("[" + new Date() + " ]  A L: 919:   Valor de retorno da atualizção do dosador :" + ans);
                                
                            break;
                            case "DRSTR":
                                
                            break;
                            case "AUPREQ":
                                
                            break;
                            case "TUPREQ":
                                
                            break;
                            case "NSTOP":
                                
                            break;
                            case "TABD":
                                
                            break;
                        
                    }
                    }
                      
               } catch (Exception ex) {
                   Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
               }
              
            }, 0, NOTIFICATION_SCAN_TIME, TimeUnit.MILLISECONDS);
        }
        
    }
   
    

   

     private char freezeNetwork() throws InterruptedException {
        
        return 0;
     }
    
  
   /**
     * Classe que implementa syncronized nos métodos de acesso a variável valor
     * para que esta seja acessada pelas diversas threads sem que haja o
     * conflito de aceeso, pois caso uma Thread mude o valor de val a outra
     * tambem acessa a alteração.
     */
     private static class Sincronismo {
        int val = 1;
        
        /**
         * Pega o valor de val no contexto sincronizado
         *
         * @return the val
         */
        public synchronized int getVal() {
            return val;
        }
        /**
         * Atribui valor a val no contexto sincronizado
         *
         * @param val the val to set
         */
        public synchronized void setVal(int val) {
            this.val = val;
        }
    }
    
}
