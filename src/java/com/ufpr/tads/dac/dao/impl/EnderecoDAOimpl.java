package com.ufpr.tads.dac.dao.impl;

import com.ufpr.tads.dac.beans.EnderecoBean;
import com.ufpr.tads.dac.dao.ConnectionFactory;
import com.ufpr.tads.dac.dao.EnderecoDAO;
import com.ufpr.tads.dac.exceptions.EnderecoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnderecoDAOimpl implements EnderecoDAO {

    private Connection con;

    @Override
    public EnderecoBean getEnderecoById(int enderecoId) throws EnderecoException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("SELECT endereco.id as id, rua, cidade.nome as cidade, estado.sigla as uf FROM bd4everalone.endereco\n"
                    + "INNER JOIN bd4everalone.cidade ON codCidade = cidade.id\n"
                    + "INNER JOIN bd4everalone.estado ON codEstado = estado.id\n"
                    + "WHERE endereco.id = ?;");
            pst.setInt(1, enderecoId);
            rs = pst.executeQuery();
            while (rs.next()) {
                return new EnderecoBean();
            }           
        } catch(SQLException e){
            throw new EnderecoException("Erro: comando sql invalido");
        }finally{ if (pst!= null) {try {pst.close(); } catch (SQLException ex) {}}}
        return null;
    }

    @Override
    public void setEndereco(EnderecoBean eb) throws EnderecoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateEndereco(EnderecoBean eb) throws EnderecoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
