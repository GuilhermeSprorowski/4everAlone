package com.ufpr.tads.dac.dao.impl;

import com.ufpr.tads.dac.beans.ConviteBean;
import com.ufpr.tads.dac.beans.FestaBean;
import com.ufpr.tads.dac.dao.ConnectionFactory;
import com.ufpr.tads.dac.dao.ConviteDAO;
import com.ufpr.tads.dac.beans.FuncionarioBean;
import com.ufpr.tads.dac.exceptions.ConviteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConviteDAOimpl implements ConviteDAO {

    private Connection con;

    @Override
    public void setNovoConvite(ConviteBean convite) throws ConviteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateConvite(ConviteBean convite) throws ConviteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setResposta(boolean resposta) throws ConviteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ConviteBean getConviteById(int idConvite) throws ConviteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ConviteBean> getAllConvites(int clienteId) throws ConviteException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("SELECT convite.id, dataResposta, confirmado, dataEnviado, codFesta, Endereco, vagas, tema, descricao, dataHora, nome FROM bd4everalone.convite "
                    + "INNER JOIN bd4everalone.festa ON codFesta = festa.id "
                    + "INNER JOIN bd4everalone.getendereco ON getendereco.id = codEndereco "
                    + "INNER JOIN bd4everalone.funcionario ON codFuncionario = funcionario.id "
                    + "WHERE codCliente = ? AND confirmado is NULL;");
            pst.setInt(1, clienteId);
            rs = pst.executeQuery();
            final ArrayList<ConviteBean> al = new ArrayList<ConviteBean>();
            while (rs.next()) {
                al.add(new ConviteBean(rs.getInt("id"),rs.getDate("dataResposta"), rs.getBoolean("confirmado"),rs.getDate("dataEnvio"),
                       new FestaBean(rs.getInt("codFesta"), rs.getInt("vagas"), rs.getString("tema"), rs.getString("descricao"),
                                     rs.getDate("dataHora"), new FuncionarioBean(rs.getInt("codFuncionario"),rs.getString("nome")),
                                     rs.getString("Endereco")))); 
            }
            return al;
        } catch (SQLException ex) {
            throw new ConviteException("Erro convite: comando sql invalido");
        } finally {if (pst != null) { try {pst.close();} catch (SQLException ex) {throw new ConviteException("Erro convite: erro ao fechar conexão"); } }
        }
    }

}
