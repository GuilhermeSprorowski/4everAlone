package com.ufpr.tads.dac.dao.impl;

import com.ufpr.tads.dac.beans.ClienteBean;
import com.ufpr.tads.dac.dao.ClienteDAO;
import com.ufpr.tads.dac.dao.ConnectionFactory;
import com.ufpr.tads.dac.exceptions.ClienteException;
import com.ufpr.tads.dac.exceptions.EnderecoException;
import com.ufpr.tads.dac.exceptions.UserException;
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
            pst = con.prepareStatement("SELECT Cli.id as id, nome, cpf, dataNasc, sexo, Cli.descricao as descricao, E.descricao as escolaridade,  c.descricao as corCabelo, p.descricao as corPele, codEndereco, pref.* FROM bd4everalone.cliente Cli\n"
                    + "INNER JOIN bd4everalone.escolaridade E ON codEscolaridade = E.id\n"
                    + "INNER JOIN bd4everalone.corcabelo c ON codCabelo = c.id\n"
                    + "INNER JOIN bd4everalone.corpele p ON codPele = p.id\n"
                    + "INNER JOIN bd4everalone.preferencia pref ON Cli.id = codCliente\n"
                    + "WHERE Cli.id = ?;");
            pst.setInt(1, clienteId);
            rs = pst.executeQuery();
            while (rs.next()) {
                return new ClienteBean(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getDate("dataNasc"), rs.getString("sexo"),
                rs.getString("descricao"), rs.getString("corPele"), rs.getString("corCabelo"), new EnderecoDAOimpl().getEnderecoById(rs.getInt("codEndececo")));
            }
        } catch (SQLException ex) {
            throw new ClienteException("Erro cliente: comando sql invalido");
        } catch (EnderecoException ex) {
            throw new EnderecoException("Erro ao carregar endereço do cliente");
        } finally {if (pst != null) {try { pst.close();} catch (SQLException ex) {throw new ClienteException("Erro cliente: erro ao fechar conecxão");}}
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
