/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agente.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.simple.JSONObject;
/**
 *
 * @author nosli
 */
public class MySqlConfig {
    
    
    public static Connection getConnection(JSONObject obj) throws Exception {

            String mysqlClass = "com.mysql.jdbc.Driver";
            String mysqlUser = (String) obj.get("mysql_user");//"fairtek";
            System.out.println("Usuario: "+(String)obj.get("mysql_user"));
            String mysqlPass = (String) obj.get("mysql_pass");//"ZamtxR6DWe2T25BL";
            System.out.println("Senha: "+(String)obj.get("mysql_pass"));
            String mysqUrl = (String) obj.get("mysql_url");//"jdbc:mysql://fairtek.com.br:3306/fairtek?useTimezone=true&serverTimezone=UTC&useSSL=false";
            System.out.println("URL: "+(String)obj.get("mysql_url"));
            Class.forName(mysqlClass);
            System.out.println(DriverManager.getConnection(mysqUrl, mysqlUser, mysqlPass));
            return DriverManager.getConnection(mysqUrl, mysqlUser, mysqlPass);
    }

    /**
     * Fecha a conexão com  o banco de dados
     * @param conn Connection
     * @param stmt Statement
     * @param rs   ResultSet
     * @throws Exception Exceção da conexão com o BD
     */
    public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) throws Exception {
        close(conn, stmt, rs);
    }
    /**
     *Fecha a conexao com o banco de dados
     *@param conn Connection
     *@param stmt Statement
     *@throws java.lang.Exception Exceção da conexão com o BD
     */
    public static void closeConnection(Connection conn, Statement stmt) throws Exception {
        close(conn, stmt, null);
    }
    /**
     *Fecha a conexao com o banco de dados
     *@param conn Connection
     *@throws java.lang.Exception Exceção da conexão com o BD
     */
    public static void closeConnection(Connection conn) throws Exception {
        close(conn, null, null);
    }
    /**
     *Fecha a conexao com o banco de dados
     *@param conn Connection
     *@param stmt Statement
     *@param rs ResultSet
     *@throws java.lang.Exception Exceção da conexão com o BD
     */
    private static void close(Connection conn, Statement stmt, ResultSet rs) throws Exception {        
    	
    	if (rs != null) {
    		rs.close( );
    	}
    	
        if (stmt != null) {
        	stmt.close( );
        }
        
        if (conn != null) {
        	conn.close( );
        }        
    }

    
    
}
