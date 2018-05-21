package com.ufpr.tads.dac.dao.impl;

import com.ufpr.tads.dac.beans.ClienteBean;
import com.ufpr.tads.dac.dao.ClienteDAO;
import com.ufpr.tads.dac.dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ClientErrorException;

public class ClienteDAOimpl implements ClienteDAO {

    private Connection con;

    @Override
    public void setCliente(ClienteBean c) throws ClientErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateCliente(ClienteBean c) throws ClientErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteCliente(int clienteId) throws ClientErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClienteBean getClienteById(int clienteId) throws ClientErrorException {
         PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("SELECT id, dataSolicitacao, dataEncontro, dataResposta, aceito, codCSolicitado, codEndereco FROM bd4everalone.encontro WHERE aceito AND dataEncontro > CURDATE() AND codCSolicitante = ?;");
            pst.setInt(1, clienteId);
            rs = pst.executeQuery();
            while (rs.next()) {
                return new ClienteBean();
            }
        } catch (SQLException ex) {
            Logger.getLogger(EncontroDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<ClienteBean> getAllClientes() throws ClientErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
