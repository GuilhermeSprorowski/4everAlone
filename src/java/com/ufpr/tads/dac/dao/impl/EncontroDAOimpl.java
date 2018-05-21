/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.dao.impl;

import com.tads.web2.dac.exceptions.EncontroException;
import com.ufpr.tads.dac.beans.ClienteBean;
import com.ufpr.tads.dac.beans.EncontroBean;
import com.ufpr.tads.dac.beans.EnderecoBean;
import com.ufpr.tads.dac.dao.ConnectionFactory;
import com.ufpr.tads.dac.dao.EncontroDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EncontroDAOimpl implements EncontroDAO {

    private Connection con;

    @Override
    public void setNovoEncontro(EncontroBean encontro) throws EncontroException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EncontroBean getEncontro(int encontroId) throws EncontroException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateEncontro(EncontroBean encontro) throws EncontroException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<EncontroBean> getAllEncontrosByIdCliente(int clienteId) throws EncontroException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<EncontroBean> getEncontrosPendentesByIdCliente(int clienteId) throws EncontroException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("SELECT id, dataSolicitacao, dataEncontro, dataResposta, aceito, codCSolicitado, codEndereco FROM bd4everalone.encontro WHERE aceito AND dataEncontro > CURDATE() AND codCSolicitante = ?;");
            pst.setInt(1, clienteId);
            rs = pst.executeQuery();
            final ArrayList<EncontroBean> al = new ArrayList<EncontroBean>();
            while (rs.next()) {
                al.add(new EncontroBean(rs.getInt("id"), rs.getDate("data"), rs.getDate("data"), rs.getDate("data"), new ClienteBean(), new EnderecoBean(), rs.getBoolean("")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(EncontroDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
