/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agente.Update;

import br.com.agente.Bean.Baias;
import br.com.agente.Bean.Dosador;
import br.com.agente.Bean.DosadorTime;
import br.com.agente.Bean.NetworkMsgBean;
import br.com.agente.Bean.Receipe;
import br.com.agente.Dao.ServiceDao;
import br.com.agente.Enum.MsgNetworkType;
import br.com.agente.MsgFormat.XbeeMsg;
import br.com.agente.Serial.Conexao;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author nosli
 */
public class UpdateDosador {

    Conexao conexao;
    ArrayList<Dosador> dosadores;
    JSONObject con_str;

    /**
     * Método construtor da classe
     *
     * @param dosadores Dosadores a serem atualizados
     * @param conexao Conecção serial a do xbee
     * @param obj Conexão Sql
     */
    public UpdateDosador(ArrayList<Dosador> dosadores, Conexao conexao, JSONObject obj) {
        this.dosadores = dosadores;
        this.conexao = conexao;
        con_str = obj;
    }

    /**
     * Atualiza as receitas modificadas de acordo com o retorno do BD que
     * verifica a flag ag_update.
     *
     * @param receitas Receitas a serem atualizadas
     * @return Reotorna o status da atualização
     * @throws java.lang.InterruptedException .
     */
    public String uReceipe(ArrayList<Receipe> receitas, String porta) throws InterruptedException {
        String status;
        String d_sinc = "", d_n_sinc = "";
        int falhas = 0;
        XbeeMsg msg_send = new XbeeMsg();
        NetworkMsgBean msg = new NetworkMsgBean();
        msg_send.conexao = this.conexao;   //Expecifica a conexao a ser usada
        this.conexao.addObserver((Observer) msg_send);
        msg.setNetwork_msg_type(MsgNetworkType.AGENTE_RECEIPE_UPDATE);
        if (!this.dosadores.isEmpty()) {
            if (!receitas.isEmpty()) {
               // OUT:
                for (Dosador d : dosadores) {
                    System.out.println("Numero de dosadores"+ dosadores.size()+" na porta: " +porta);
                    for (Receipe r : receitas) {
                        msg.setReceipe(r); 
                        System.out.println("Atualizando receita " + r.getID()+" no Dosador: "+Long.toHexString(d.getMac())+" na Porta: "+porta);
                        //Envia a mensagem na rede e aguarda a susa resposta de entrega
                        int status_t = msg_send.sendNetworkReceipeWait(d.getMac(), msg);
                        if (status_t != 0) {
                            if (!d_n_sinc.equals("")) {
                                d_n_sinc += ",";
                            }
                            d_n_sinc += d.getId();
                            falhas++;
                            long t = System.currentTimeMillis();
                            while ((status_t != 0) && (System.currentTimeMillis() - t) < 2000) {
                                Thread.sleep(50);
                                status_t = msg_send.sendNetworkMsgWait(d.getMac(), msg);
                            }
                            //break OUT;
                        } else {
                            System.out.println("Receita enviada com sucesso na Prota: "+porta);
                        }
                        if (!d_sinc.equals("")) {
                            d_sinc += ",";
                        }
                        d_sinc += d.getId();
                    }
                }
            } else {
                return "Nenhuma receita modificada";
            }
        } else {
            return "Nenhum Dosador";
        }
        if (falhas == 0) {
            status = "Sucesso";
        } else if (falhas == this.dosadores.size()) {
            status = "Falha - Nenhum Dosador Atualizado.";
        } else {
            status = "Alerta - Nem Todos dosadores Atualizados.";
        }
        try {
            new ServiceDao(this.con_str).updateDosadoresSinc(d_sinc, d_n_sinc, "");
        } catch (Exception ex) {
            Logger.getLogger(new Date() + "\n" + UpdateDosador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    /**
     * Atualiza as receitas modificadas de acordo com o retorno do BD que
     * verifica a flag ag_update.
     *
     * @param animais Animais a serem atualizados
     * @param max numero do maior ID 16 dos animais cadastrados
     * @return Retorna o status da atualização
     * @throws InterruptedException .
     */
   
    public String uBaiasAutomatico(ArrayList<Baias> baia, int max, String porta) throws InterruptedException, ParseException {
        JSONParser parser = new JSONParser();
        
        String status;
        String retorno ="[";
        String d_sinc = "", d_n_sinc = "", animais_ag_up = "", ids = "";
        XbeeMsg msg_send = new XbeeMsg();
        NetworkMsgBean msg = new NetworkMsgBean();
        msg_send.conexao = this.conexao;                                       //Expecifica a conexao a ser usada
        this.conexao.addObserver((Observer) msg_send);
        int falhas = 0;

        msg.setNetwork_msg_type(MsgNetworkType.AGENTE_ANIMAL_UPDATE);
       // if (!this.dosadores.isEmpty()) {
           // if (!animais.isEmpty()) {
                int i = 0;
                //System.out.println(this.dosadores.iterator());
                for (Dosador d : this.dosadores) {
                    
                    OUT:
                   // if (animais.size() > 0) {
                        for (Baias a : baia) {
//                            if (a.getSetor() == 3) {
//                                System.out.println("Animal da Maternidade:");
//                            }

                            if (d.getTbBaiaId() == a.getId_baia()) {
                               /* if ((a.getExcluido() == 1)) {
                                    a.setRFID(0);
                                    a.setExcluido((byte) 0);
                                }*/
//                            System.out.println("UP L: 157 QR: "+a.getQR());
//                            if(a.getQR()< 0){
//                                System.out.println("Correção QR atual: "+a.getQR());
 //                               a.setQR(a.getQR()+256);
//                                System.out.println(" QR corrigido: "+a.getQR());
 //                           }
                                msg.setBaia(a);
                                //Envia a mensagem na rede e aguarda a susa resposta de entrega
                               
                                int status_t = msg_send.sendNetworkMsgWait(d.getMac(), msg);
                                Thread.sleep(500);
                                if (status_t != 0) {
                                    falhas++;
                                    if((baia.size()-1)>i){
                                        retorno  +="{\"id_animal\":"+a.getID()+",\"brinco\":\""+a.getBrinco()+"\",\"Status\":"+"\"Falha\"},";
                                        
                                    }else{
                                       
                                         retorno  +="{\"id_animal\":"+a.getID()+",\"brinco\":\""+a.getBrinco()+"\",\"Status\":"+"\"Falha\"}]";
                                        
                                    }
                                    //System.err.println("[" + new Date() + " ]  U D: 129: Número de falhas: " + falhas);
                                    long t = System.currentTimeMillis();
                                    while ((status_t != 0) && (System.currentTimeMillis() - t) < 4000) {
                                        status_t = msg_send.sendNetworkMsgWait(d.getMac(), msg);
                                        Thread.sleep(200);
                                    }
                                    if (status_t == 0) {
                                        System.out.println("Id_sistema: "+a.getId_primary() +" Id_animal: "+a.getID() +" Rfid: "+a.getRFID() +" dia_animal: "+a.getDia()+ " Qr: "+a.getQR()+" Id_dieta: "+ a.getReceipe()+" Score_animal: "+ ((byte)a.getScore())+ " t_feed_animal: "+a.getT_feed());
                                        falhas--;
                                        i++;
                                    } else {
                                        if (!d_n_sinc.equals("")) {
                                            d_n_sinc += ",";
                                        }
                                        d_n_sinc += d.getTbBaiaId();
                                    }
                                } else {
                                    if((baia.size()-1)>i){
                                        retorno  +="{\"id_animal\":"+a.getID()+",\"brinco\":\""+a.getBrinco()+"\",\"Status\":"+"\"Sucesso\"},";
                                      
                                    }else{
                                         retorno  +="{\"id_animal\":"+a.getID()+",\"brinco\":\""+a.getBrinco()+"\",\"Status\":"+"\"Sucesso\"}]";
                                       
                                    }
                                    System.out.println("Id_sistema: "+a.getId_primary() +" Id_animal: "+a.getID() +" Rfid: "+a.getRFID() +" dia_animal: "+a.getDia()+ " Qr: "+a.getQR()+" Id_dieta: "+ a.getReceipe()+" Score_animal: "+ ((byte)a.getScore())+ " t_feed_animal: "+a.getT_feed());
                                     i++;
                                }
                            } 
                        }//fim do for animais
                        msg.setNetwork_msg_type(MsgNetworkType.AGENTE_NEW_DEVICE_UP);
                        msg.setNRFID((short) (max + 1));
                        msg.setNReceipe((short) 10);
                        msg_send.sendNetworkMsg(d.getMac(), msg);
                        msg.setNetwork_msg_type(MsgNetworkType.AGENTE_ANIMAL_UPDATE);
                        if (!d_sinc.equals("")) {
                            d_sinc += ",";
                        }
                        d_sinc += d.getTbBaiaId();
                   // }
                }
                System.out.println("[" + new Date() + " ]  UpD: 592: Dosadores Atualizados. "+ i+" Porta" +porta);
            /*} else {
                this.conexao.deleteObserver(msg_send);
                return "Nenhuma animal modificado.";
            }*/
       // } else {
           // this.conexao.deleteObserver(msg_send);
           // return "Nenhum Dosador.";
      //  }
        this.conexao.deleteObserver((Observer) msg_send);
        if (falhas == 0) {
            status = "Sucesso";
        } else if (falhas == this.dosadores.size()) {
            status = "Falha - Nenhum Dosador Atualizado na Porta" +porta;
        } else {
            status = "Alerta - Nem Todos dosadores Atualizados na  Porta" +porta;
        }
        try {
            //Limpa a flag ag_update dos animais
            if (baia.size() > 0) {
                for (Baias a : baia) {
                    if (a.getId_primary() != baia.get(0).getId_primary()) {
                        animais_ag_up += ",";
                    }
                    //Limpa os IDS utilizados
                    if (a.getRFID() == 0) {
                        if (!ids.isEmpty()) {
                            ids += a.getId_primary();
                        } else {
                            ids += "," + a.getId_primary();
                        }
                    }
                    animais_ag_up += a.getId_primary();
                }
                new ServiceDao(this.con_str).updateBaiasSinc(animais_ag_up, ids);
            }

            //Atualiza a flag sincronizado dos dosadores
            new ServiceDao(this.con_str).updateDosadoresSinc(d_sinc, d_n_sinc, "");
        } catch (Exception ex) {
            Logger.getLogger(new Date() + "\n" + UpdateDosador.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(status);
        return retorno;
    }
    
      public String uBaias(ArrayList<Baias> baia, int max, String porta) throws InterruptedException {
        String status;
        String d_sinc = "", d_n_sinc = "", animais_ag_up = "", ids = "";
        XbeeMsg msg_send = new XbeeMsg();
        NetworkMsgBean msg = new NetworkMsgBean();
        msg_send.conexao = this.conexao;                                       //Expecifica a conexao a ser usada
        this.conexao.addObserver((Observer) msg_send);
        int falhas = 0;

        msg.setNetwork_msg_type(MsgNetworkType.AGENTE_ANIMAL_UPDATE);
       // if (!this.dosadores.isEmpty()) {
           // if (!animais.isEmpty()) {
                int i = 0;
                //System.out.println(this.dosadores.iterator());
                for (Dosador d : this.dosadores) {
                    
                    OUT:
                   // if (animais.size() > 0) {
                        for (Baias a : baia) {
//                            if (a.getSetor() == 3) {
//                                System.out.println("Animal da Maternidade:");
//                            }
                            if (d.getTbBaiaId() == a.getId_baia()) {

                                msg.setBaia(a);
                                //Envia a mensagem na rede e aguarda a susa resposta de entrega
                               
                                int status_t = msg_send.sendNetworkMsgWait(d.getMac(), msg);
                                Thread.sleep(500);
                                if (status_t != 0) {
                                    falhas++;
                                    //System.err.println("[" + new Date() + " ]  U D: 129: Número de falhas: " + falhas);
                                    long t = System.currentTimeMillis();
                                    while ((status_t != 0) && (System.currentTimeMillis() - t) < 4000) {
                                        status_t = msg_send.sendNetworkMsgWait(d.getMac(), msg);
                                        Thread.sleep(200);
                                    }
                                    if (status_t == 0) {
                                        System.out.println("Id_sistema: "+a.getId_primary() +" Id_animal: "+a.getID() +" Rfid: "+a.getRFID() +" dia_animal: "+a.getDia()+ " Qr: "+a.getQR()+" Id_dieta: "+ a.getReceipe()+" Score_animal: "+ ((byte)a.getScore())+ " t_feed_animal: "+a.getT_feed());
                                        falhas--;
                                        i++;
                                    } else {
                                        if (!d_n_sinc.equals("")) {
                                            d_n_sinc += ",";
                                        }
                                        d_n_sinc += d.getTbBaiaId();
                                    }
                                } else {
                                    System.out.println("Id_sistema: "+a.getId_primary() +" Id_animal: "+a.getID() +" Rfid: "+a.getRFID() +" dia_animal: "+a.getDia()+ " Qr: "+a.getQR()+" Id_dieta: "+ a.getReceipe()+" Score_animal: "+ ((byte)a.getScore())+ " t_feed_animal: "+a.getT_feed());
                                     i++;
                                }
                            } 
                        }//fim do for animais
                        msg.setNetwork_msg_type(MsgNetworkType.AGENTE_NEW_DEVICE_UP);
                        msg.setNRFID((short) (max + 1));
                        msg.setNReceipe((short) 10);
                        msg_send.sendNetworkMsg(d.getMac(), msg);
                        msg.setNetwork_msg_type(MsgNetworkType.AGENTE_ANIMAL_UPDATE);
                        if (!d_sinc.equals("")) {
                            d_sinc += ",";
                        }
                        d_sinc += d.getTbBaiaId();
                   // }
                }
                System.out.println("[" + new Date() + " ]  UpD: 592: Dosadores Atualizados. "+ i+" Porta" +porta);
            /*} else {
                this.conexao.deleteObserver(msg_send);
                return "Nenhuma animal modificado.";
            }*/
       // } else {
           // this.conexao.deleteObserver(msg_send);
           // return "Nenhum Dosador.";
      //  }
        this.conexao.deleteObserver((Observer) msg_send);
        if (falhas == 0) {
            status = "Sucesso";
        } else if (falhas == this.dosadores.size()) {
            status = "Falha - Nenhum Dosador Atualizado na Porta" +porta;
        } else {
            status = "Alerta - Nem Todos dosadores Atualizados na  Porta" +porta;
        }
        try {
            //Limpa a flag ag_update dos animais
            if (baia.size() > 0) {
                for (Baias a : baia) {
                    if (a.getId_primary() != baia.get(0).getId_primary()) {
                        animais_ag_up += ",";
                    }
                    //Limpa os IDS utilizados
                    if (a.getRFID() == 0) {
                        if (!ids.isEmpty()) {
                            ids += a.getId_primary();
                        } else {
                            ids += "," + a.getId_primary();
                        }
                    }
                    animais_ag_up += a.getId_primary();
                }
                new ServiceDao(this.con_str).updateBaiasSinc(animais_ag_up, ids);
            }

            //Atualiza a flag sincronizado dos dosadores
            new ServiceDao(this.con_str).updateDosadoresSinc(d_sinc, d_n_sinc, "");
        } catch (Exception ex) {
            Logger.getLogger(new Date() + "\n" + UpdateDosador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }



  

    /**
     * Atualiza os parametros dos dosadores que necessitam de atualização de um
     * por um e retorna o resultado das atualizações.
     *
     * @param dTime ArrayList com os dados a atualizar
     * @return status da atualização
     * @throws InterruptedException .
     */
    public String uDosadorTime(ArrayList<Dosador> dosador, String porta) throws InterruptedException, Exception {
        String status = "";
        String d_sinc = "", d_n_sinc = "";
        XbeeMsg msg_send = new XbeeMsg();
        NetworkMsgBean msg = new NetworkMsgBean();
        conexao.addObserver((Observer) msg_send);
        msg_send.conexao = conexao;             //Expecifica a conexao a ser usada
        msg.setNetwork_msg_type(MsgNetworkType.AGENTE_PARAMS_UP);
        int falhas = 0;

        if (!dosador.isEmpty()) {
            for (Dosador dt : dosador) {

                Thread.sleep(150);
                //Envia a mensagem na rede e aguarda a susa resposta de entrega
                DosadorTime t = new ServiceDao(con_str).buscaDosadorTempos(Long.toHexString(dt.getMac()));
                msg.setDosadorTime(t);
                //System.out.println("Dosador :" + Long.toHexString(dt.getMAC()) + " Feed: " + msg.getDosadorTime().getMotorFeed() + " Motor Reverso: " + msg.getDosadorTime().getMotorReverse());

                int status_t = msg_send.sendNetworkMsgWait(dt.getMac(), msg);
                System.out.println();
                System.out.println(" Atualizando tempos: " + msg.getDosador().getMac() + " Porta: "+porta);
                if (status_t != 0) {
                    long y = System.currentTimeMillis();
                    while ((status_t != 0) && (System.currentTimeMillis() - y) < 1500) {
                        t = new ServiceDao(con_str).buscaDosadorTempos(Long.toHexString(dt.getMac()));
                        msg.setDosadorTime(t);
                        Thread.sleep(300);
                        //System.out.println("Dosador :" + Long.toHexString(dt.getMAC()) + " Feed: " + msg.getDosadorTime().getMotorFeed() + " Motor Reverso: " + msg.getDosadorTime().getMotorReverse());           
                        status_t = msg_send.sendNetworkMsgWait(dt.getMac(), msg);
                    }
                }
                System.out.println();
                if (status_t != 0) {
                    if (!d_n_sinc.equals("")) {
                        d_n_sinc += ",";
                    }
                    d_n_sinc += dt.getTbBaiaId();
                    falhas++;
                    new ServiceDao(con_str).insereNotificacaoTimeUpdate("Falha ao Atualizar tempos do dosador: " + Long.toHexString(dt.getMac()) + "faça a atualização individual.");
                    System.out.println("Falha ao atulizar o Dosador :" + Long.toHexString(dt.getMac())+" Porta: "+porta);
                }

                if (!d_sinc.equals("")) {
                    d_sinc += ",";
                }
                d_sinc += dt.getTbBaiaId();
            }
            System.out.println();
        } else {
            conexao.deleteObserver((Observer) msg_send);
            return "Nada a Atualizar";
        }
        conexao.deleteObserver((Observer) msg_send);
        if (falhas == 0) {
            status = "Sucesso";
        } else {
            status = "Nem todos os dosadores atualizados";
        }
        try {
            new ServiceDao(con_str).updateDosadoresSinc(d_sinc, d_n_sinc, "params");
        } catch (Exception ex) {
            Logger.getLogger(new Date() + "\n" + UpdateDosador.class.getName()).log(Level.SEVERE, null, ex);
        }
        //tentativas++;
        //}
        // System.out.println("Status: "+status);
        return status;
    }
public String gerRota(int baia_id){
    return "";
}
    
}

