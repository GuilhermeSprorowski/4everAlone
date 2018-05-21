package com.ufpr.tads.dac.dao.impl;

import com.ufpr.tads.dac.beans.ClienteBean;
import com.ufpr.tads.dac.dao.ClienteDAO;
import com.ufpr.tads.dac.dao.ConnectionFactory;
import com.ufpr.tads.dac.exceptions.ClienteException;
import com.ufpr.tads.dac.exceptions.EnderecoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAOimpl implements ClienteDAO {

    private Connection con;

    @Override
    public void setCliente(ClienteBean c) throws ClienteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateCliente(ClienteBean c) throws ClienteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteCliente(int clienteId) throws ClienteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClienteBean getClienteById(int clienteId) throws ClienteException, EnderecoException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("SELECT Cli.id as id, nome, cpf, dataNasc, sexo, Cli.descricao as descricao, E.descricao as escolaridade,  c.descricao as corCabelo, p.descricao as corPele, codEndereco FROM bd4everalone.cliente Cli\n"
                    + "INNER JOIN bd4everalone.escolaridade E ON codEscolaridade = E.id\n"
                    + "INNER JOIN bd4everalone.corcabelo c ON codCabelo = c.id\n"
                    + "INNER JOIN bd4everalone.corpele p ON codPele = p.id\n"
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
        } finally {if (pst != null) {try { pst.close();} catch (SQLException ex) {}}
        }
        return null;
    }

    @Override
    public ArrayList<ClienteBean> getAllClientes() throws ClienteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
