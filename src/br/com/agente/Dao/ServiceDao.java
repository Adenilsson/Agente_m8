/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agente.Dao;



import br.com.agente.Bean.Baias;
import br.com.agente.Bean.Cordenador;
import br.com.agente.Bean.Dosador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import br.com.agente.Bean.Cordenador;
import br.com.agente.Bean.DosadorTime;
import br.com.agente.Bean.Grupo;
import br.com.agente.Bean.Notificacoes;
import br.com.agente.Bean.Receipe;
import br.com.agente.Enum.NotificationsEnum;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nosli
 */
public class ServiceDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    
    /**
     * Metodo constutor
     *
     * @param obj .
     * @throws Exception .
     */
    public ServiceDao(JSONObject obj) throws Exception {
        try {
            this.conn = MySqlConfig.getConnection(obj);
        } catch (Exception e) {
            throw new Exception("Erro: " + e.getMessage());
        }
    }
    public void notificacaoLida(int id) throws Exception{
        String SQL = "call sp_altera_notificacao_lida(?)";
        try {
            this.ps = this.conn.prepareStatement(SQL);
            this.ps.setInt(1, id);
            this.ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            MySqlConfig.closeConnection(this.conn, this.ps, this.rs);
        }
    }
    public ArrayList<Cordenador> buscaCordenadores() throws Exception {
        String SQL = "call sp_lista_coordenadores()";
        ArrayList<Cordenador> cordenador= new ArrayList();
        try {
           
            this.ps = conn.prepareStatement(SQL);
            this.rs = this.ps.executeQuery();
            while (rs.next()) {
                    Cordenador c = new Cordenador();
                    c.setId(this.rs.getInt("id"));
                    c.setNome(this.rs.getString("nome"));
                    c.setExcluido(this.rs.getInt("excluido"));
                    c.setAtivo(this.rs.getInt("ativo"));
                    cordenador.add(c);
               
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            MySqlConfig.closeConnection(this.conn, this.ps, this.rs);
        }
        return cordenador;
    }
    public boolean insereDosador(String MAC, String MAC_16) throws SQLException, Exception {
        String SQL = " call sp_insert_dosador(?,?) ";
        try {
            this.ps = conn.prepareStatement(SQL);
            this.ps.setString(1, MAC);
            this.ps.setString(2, MAC_16);
            this.rs = ps.executeQuery();
        } catch (SQLException sqle) {
            throw new SQLException(sqle);
        } finally {
            MySqlConfig.closeConnection(this.conn, this.ps, this.rs);
        }
        return true;
    }
    public boolean insereNotificacaoNovoDosador(String MAC) throws Exception {
        String SQL = " call sp_insert_notificacao_novo_dosador(?,?) ";
        try {
            this.ps = conn.prepareStatement(SQL);
            this.ps.setString(1, String.format("{\"mac\":\"%s\"}", MAC));
            this.ps.setString(2, MAC);
            this.rs = ps.executeQuery();

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            MySqlConfig.closeConnection(this.conn, this.ps, this.rs);
        }
        return true;
    }
    public Dosador buscaDosador(String mac) throws Exception {
        String SQL = "call sp_lista_dosadores()";
        Dosador dosador = new Dosador();
        try {
            this.ps = conn.prepareStatement(SQL);
            this.rs = this.ps.executeQuery();
            if(mac.equals(this.rs.getLong("mac"))){
                dosador.setMac16(this.rs.getString("mac16"));
                dosador.setTbBaiaId(this.rs.getInt("tb_baias_id"));
                dosador.setMac(this.rs.getLong("mac"));
                dosador.setSetor(this.rs.getInt("setor")); 
            }
            
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            MySqlConfig.closeConnection(this.conn, this.ps, this.rs);
        }
        return dosador;
    }
    public Baias buscaBaia(int id_baia) throws Exception {
        Baias baia = new Baias();
        String SQL = "call sp_busca_baia(?)";
        try {
            this.ps = conn.prepareStatement(SQL);
            this.ps.setInt(1, id_baia);
            baia.setId(this.rs.getInt("id_baia"));
            baia.setSetor(this.rs.getInt("setor"));
            baia.setNome(this.rs.getString("nome"));
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            MySqlConfig.closeConnection(this.conn, this.ps, this.rs);
        }
        return baia;
    }

    public Grupo buscaGrupo(int id_baia) throws Exception {
        Grupo grupo = new Grupo();
        String SQL ="call sp_buaca_gruppo()";
        try{
            this.ps = conn.prepareStatement(SQL);
            this.ps.setInt(1, id_baia);
            this.rs = ps.executeQuery();
            grupo.setId(this.rs.getInt("id"));
            grupo.setQuantidade_animais(this.rs.getInt("qantidade"));
            grupo.setTbCurvaId(this.rs.getInt("tb_baia_id"));
            grupo.setTbRacaoId(this.rs.getInt("tb_racao_id"));
            
        }catch(SQLException sqle){
            throw new Exception(sqle);
        }finally{
            MySqlConfig.closeConnection(this.conn, this.ps, this.rs);
        }
        return grupo;
    }
    
    
    /*Retorna um ArrayList com as receitas ativas */
    public ArrayList<Receipe> buscaReceitasLista() throws Exception {
        ArrayList<Receipe> receitas = new ArrayList<>();
        String SQL = " call sp_lista_dietas_dias() ";
        try {
            ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            int i;
            while (rs.next()) {
                Receipe r = new Receipe();
                short[] data = new short[151];
                i = 0;
                r.setID((byte) rs.getInt("id_dieta"));
                data[rs.getInt("dia")] = (short) rs.getInt("qr");
                i++;
                while (((r.getID() == rs.getInt("id_dieta")) && i < 151) && rs.next()) {
                data[rs.getInt("dia")] = (short) rs.getInt("qr");
                    i++; 
                }
                r.setQRdia(data);
                receitas.add(r);
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            MySqlConfig.closeConnection(conn, ps, rs);
        }
        return receitas;
    }
    /**
     * Busca os tempos de operação dos dosadores
     *
     * @param mac Endereço do dosador
     * @return Retorna o objeto com os dados
     * @throws Exception .
     */
    public DosadorTime buscaDosadorTempos(String mac) throws Exception {
        String SQL = " call sp_select_parametros_dosador(?) ";
        try {
            this.ps = conn.prepareStatement(SQL);
            this.ps.setString(1, mac);   //Dosador
            this.rs = ps.executeQuery();
            DosadorTime t = new DosadorTime();
            if (rs.next()) {
                t.setFeedBegin(this.rs.getInt("pTi"));
                //t.setFeed(this.rs.getShort("pTd"));
                t.setMotorFeed(this.rs.getInt("pTa"));
                t.setMotorStop(this.rs.getInt("pTp"));
                t.setMotorReverse(this.rs.getInt("pTr"));
                t.setAnimalOut(this.rs.getInt("pTs"));
            }
            MySqlConfig.closeConnection(this.conn, this.ps, this.rs);
            return t;
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            MySqlConfig.closeConnection(this.conn, this.ps, this.rs);
        }
    }
                       
    public Notificacoes busacaNotificacoesAgente(String porta) throws Exception {
        String SQL = " call sp_select_notificacao_agente(?) ";
        Notificacoes not = new Notificacoes();
        try {
            this.ps = conn.prepareStatement(SQL);
            this.ps.setString(1, porta);
            this.rs = this.ps.executeQuery();
            if (this.rs.next()) {
                not.setId(this.rs.getInt("id"));
                not.setDescricao(this.rs.getString("codigo"));
                not.setTbdDescricaoId(this.rs.getInt("id_descricao"));
                not.setTbCordenadorId(this.rs.getInt("id_cordenador"));
                not.setMac(this.rs.getString("mac"));
                not.setLida(this.rs.getInt("lida"));
                not.setValor(this.rs.getString("valor"));
                not.setPorta(rs.getString("porta"));
                not.setCode(NotificationsEnum.findType(this.rs.getInt("tipo_id")));
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            MySqlConfig.closeConnection(this.conn, this.ps, this.rs);
        }
        return not;
    }
    
}
