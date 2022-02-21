/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agente.Main;

import br.com.agente.Bean.Cordenador;
import java.util.Observable;
import java.util.Observer;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import br.com.agente.Dao.ServiceDao;
import java.awt.EventQueue;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.in;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.runtime.ParserException;

/**
 *
 * @author nosli
 */
public class Agente {
    
    public static void main(String[] args)throws Exception{
        JSONObject config_json =null;
        int agenteID;
        JSONParser parser = new JSONParser();
        try{
            config_json = (JSONObject) parser.parse(new FileReader("config.json"));
        }catch(IOException ex){
            Logger.getLogger(Agente.class.getName()).log(Level.SEVERE, null, ex);
        }catch(ParserException ex){
            Logger.getLogger(Agente.class.getName()).log(Level.SEVERE, null, ex);
        }
        agenteID = Integer.valueOf((String) config_json.get("id_agente"));
        ArrayList<Cordenador> cordenador = new ArrayList();
        try{
            System.out.println("Iniciando a Conexao com o banco. ");
            cordenador = new ServiceDao(config_json).buscaCordenadores();
            
            for(Cordenador c : cordenador ){
                System.out.println("Conectado a porta: "+c.getNome());
               Main a = new Main(c.getNome(),c.getId());
               a.conexao.addObserver(a);
               EventQueue.invokeLater(() ->{
               });
               a.start();
            }
        }catch (Exception ex) {
            Logger.getLogger(Agente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
}
