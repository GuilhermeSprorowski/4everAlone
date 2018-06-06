package com.ufpr.tads.dac.dao.impl;

import com.ufpr.tads.dac.beans.CidadeBean;
import com.ufpr.tads.dac.beans.ClienteBean;
import com.ufpr.tads.dac.beans.CorCabeloBean;
import com.ufpr.tads.dac.beans.CorPeleBean;
import com.ufpr.tads.dac.beans.EnderecoBean;
import com.ufpr.tads.dac.beans.EscolaridadeBean;
import com.ufpr.tads.dac.beans.EstadoBean;
import com.ufpr.tads.dac.beans.PreferenciaBean;
import com.ufpr.tads.dac.dao.ClienteDAO;
import com.ufpr.tads.dac.dao.ConnectionFactory;
import com.ufpr.tads.dac.exceptions.ClienteException;
import com.ufpr.tads.dac.exceptions.EnderecoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClienteDAOimpl implements ClienteDAO {

    private Connection con;

    @Override
    public void setCliente(ClienteBean c) throws ClienteException {
        PreparedStatement pst = null;
        ResultSet rs= null;
        int idGerado =0;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("", Statement.RETURN_GENERATED_KEYS);
            int resp = pst.executeUpdate();
            rs = pst.getGeneratedKeys();
            while(rs.next()){
               idGerado = rs.getInt(1);
            }
            resp = 0;
            if(idGerado == 0){
                throw new ClienteException("Erro cliente: não foi possivel salvar as informações do cliente.");
            }else{
                pst = con.prepareStatement("");
                resp = pst.executeUpdate();
                if (resp == 0) {
                    throw new ClienteException("Erro cliente: não foi possivel salvar as informações do endereço do cliente.");
                }
            }   
        } catch (SQLException e) {
            throw new ClienteException("Erro cliente: comando sql invalido");
        }finally {if (pst != null) {try { pst.close();} catch (SQLException ex) {throw new ClienteException("Erro cliente: erro ao fechar conecxão");}}
        }
    }

    @Override
    public void updateCliente(ClienteBean c) throws ClienteException {
        PreparedStatement pst = null;
        ResultSet rs= null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("UPDATE cliente");
            int resp = pst.executeUpdate();
            pst.setInt(1, c.getClienteId());
            if (resp == 0) {
              throw new ClienteException("Erro cliente: não foi possivel salvar as informações do cliente.");
            }
            resp = 0;
            pst = con.prepareStatement("UPDATE endereco");
            resp = pst.executeUpdate();
            pst.setInt(1, c.getEndereco().getEnderecoId());
            if (resp == 0) {
              throw new ClienteException("Erro cliente: não foi possivel salvar as informações do endereço do cliente.");
            }              
        } catch (SQLException e) {
            throw new ClienteException("Erro cliente: comando sql invalido");
        }finally {if (pst != null) {try { pst.close();} catch (SQLException ex) {throw new ClienteException("Erro cliente: erro ao fechar conecxão");}}
        }
    }

    @Override
    public ClienteBean getClienteById(int clienteId) throws ClienteException, EnderecoException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("SELECT Cli.id as id, Cli.nome, cpf, dataNasc, Cli.sexo as sexoc, Cli.descricao as descricao, codEscolaridade,  E.descricao as escolaridade,  \n" +
                    "c.descricao as corCabelo, c.id as idCabelo, p.descricao as corPele,p.id as idPele,pref.id as codPreferencia, pref.*,codEndereco,ende.*,cidade.nome as cidade, estado.sigla as uf,\n" +
                    "pc.descricao as prefCorCabelo, pp.descricao as prefCorPele\n" +
                    "FROM bd4everalone.cliente Cli\n" +
                    "LEFT JOIN bd4everalone.escolaridade E ON codEscolaridade = E.id\n" +
                    "LEFT JOIN bd4everalone.corcabelo c ON Cli.codCabelo = c.id\n" +
                    "LEFT JOIN bd4everalone.corpele p ON Cli.codPele = p.id\n" +
                    "LEFT JOIN bd4everalone.preferencia pref ON Cli.id = codCliente\n" +
                    "LEFT JOIN bd4everalone.endereco ende ON ende.id = codEndereco\n" +
                    "LEFT JOIN bd4everalone.corcabelo pc ON pref.codCabelo = c.id\n" +
                    "LEFT JOIN bd4everalone.corpele pp ON pref.codPele = p.id\n" +
                    "LEFT JOIN bd4everalone.cidade on codCidade = cidade.id\n" +
                    "LEFT JOIN bd4everalone.estado on codEstado = estado.id\n" +
                    "WHERE Cli.id = ?;");
            pst.setInt(1, clienteId);
            rs = pst.executeQuery();
            while (rs.next()) {
                CorPeleBean cp = new CorPeleBean(rs.getInt("idPele"), rs.getString("corPele"));
                CorCabeloBean cc = new CorCabeloBean(rs.getInt("idCabelo"), rs.getString("corCabelo"));
                EnderecoBean eb = new EnderecoBean(rs.getInt("codEndereco"), rs.getString("rua"), new CidadeBean(rs.getInt("codCidade"), rs.getString("cidade")), new EstadoBean(rs.getInt("codEstado"),rs.getString("uf")));
                EscolaridadeBean esco = new EscolaridadeBean(rs.getInt("codEscolaridade"), rs.getString("escolaridade"));
                
                int[] pa = {rs.getInt("alturaMin"), rs.getInt("alturaMax")};
                int[] pi = {rs.getInt("idadeMin"), rs.getInt("idadeMax")};
                PreferenciaBean pb = new PreferenciaBean(rs.getInt("codPreferencia"),rs.getString("sexo"),pi, pa, new CorCabeloBean(rs.getInt("codCabelo"),rs.getString("prefCorCabelo")), new CorPeleBean(rs.getInt("codPele"), rs.getString("prefCorPele")));
                return new ClienteBean(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getDate("dataNasc"), rs.getString("sexoc"),
                rs.getString("descricao"),cp, cc,eb,esco, pb);
            }
        } catch (SQLException ex) {
            throw new ClienteException("Erro cliente: comando sql invalido");
        }  finally {if (pst != null) {try { pst.close();} catch (SQLException ex) {throw new ClienteException("Erro cliente: erro ao fechar conecxão");}}
        }
        return null;
    }

    @Override
    public ArrayList<ClienteBean> getAllClientes() throws ClienteException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("");
            rs = pst.executeQuery();
            final ArrayList<ClienteBean> al = new ArrayList<ClienteBean>();
            while(rs.next()){
                al.add(new ClienteBean());
            }
            return al;
        } catch (SQLException e) {
            throw new ClienteException("Erro cliente: comando sql invalido");
        } finally {if (pst != null) {try { pst.close();} catch (SQLException ex) {throw new ClienteException("Erro cliente: erro ao fechar conecxão");}}
        }      
    }

    @Override
    public boolean isCpfDisponivel(String cpf) throws ClienteException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("SELECT false as cpfValido FROM bd4everalone.cliente WHERE cpf = ?");
            pst.setString(1, cpf);
            rs = pst.executeQuery();
            while (rs.next()) {
               return rs.getBoolean("cpfValido");
            }
            return true;
        } catch (SQLException e) {
            throw new ClienteException("Erro cliente: comando sql invalido");
        }finally {if (pst != null) {try { pst.close();} catch (SQLException ex) {throw new ClienteException("Erro cliente: erro ao fechar conecxão");}}
        }   
    }

}
