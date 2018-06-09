/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.dao.impl;

import com.ufpr.tads.dac.beans.ClienteBean;
import com.ufpr.tads.dac.exceptions.EncontroException;
import com.ufpr.tads.dac.beans.EncontroBean;
import com.ufpr.tads.dac.beans.PreferenciaBean;
import com.ufpr.tads.dac.dao.ConnectionFactory;
import com.ufpr.tads.dac.dao.EncontroDAO;
import com.ufpr.tads.dac.exceptions.ClienteException;
import com.ufpr.tads.dac.exceptions.EnderecoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    public ArrayList<EncontroBean> getEncontrosPendentesByIdCliente(int clienteId) throws EncontroException{
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("SELECT encontro.id, dataSolicitacao, dataEncontro,dataResposta,aceito,codCSolicitado idSolicitado, solicitado.nome nomeSolicitado, solicitado.descricao descricaoSolicitado, encontro.codEndereco as idEndereco, getEndereco.Endereco as local "
                    + "FROM bd4everalone.encontro "
                    + "INNER JOIN bd4everalone.cliente solicitado ON codCSolicitado = solicitado.id "
                    + "INNER JOIN bd4everalone.getEndereco ON getEndereco.id = encontro.codEndereco "
                    + "WHERE aceito AND dataEncontro > CURDATE() AND codCSolicitante = ?;");
            pst.setInt(1, clienteId);
            rs = pst.executeQuery();
            final ArrayList<EncontroBean> al = new ArrayList<EncontroBean>();
            while (rs.next()) {
                ClienteBean c = new ClienteBean(rs.getInt("idSolicitado"), rs.getString("nomeSolicitado"), rs.getString("descricaoSolicitado"));
                al.add(new EncontroBean(rs.getInt("id"), rs.getDate("dataSolicitacao"), rs.getDate("dataEncontro"),
                        rs.getDate("dataResposta"),c, rs.getString("local"), rs.getBoolean("aceito")));
            }
            return al;
        } catch (SQLException ex) {
            throw new EncontroException("Erro encontro: comando sql invalido");
        } finally {
            if (pst != null) {try { pst.close();} catch (SQLException ex) {
                throw new EncontroException("Erro encontro: erro ao fechar conexão");
            }}
        }
    }
}
