/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agente.Serial;

import gnu.io.CommPortIdentifier;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;;

/**
 *
 * @author nosli
 */
public class ListaPortas {
    /**
     * Retorna o ArrayList com as portas Serial disponíveis no formato 
     * CON
     * @return Retorna a lista de portas
     */
    public ArrayList ListaPortas(){
        ArrayList<String> portas = new ArrayList<>();
        // captura a lista de portas disponíveis, 
        // pelo método estético em CommPortIdentifier.
        Enumeration pList = CommPortIdentifier.getPortIdentifiers(); // Um mapping de nomes para CommPortIdentifiers.
        HashMap map = new HashMap(); 
        // Procura pela porta desejada
        while (pList.hasMoreElements()) {
            CommPortIdentifier cpi = (CommPortIdentifier)pList.nextElement();      
            map.put(cpi.getName(), cpi);  
            if (cpi.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                portas.add(cpi.getName());
            }
        }
        return portas;
    }
}

    

