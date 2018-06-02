package com.ufpr.tads.dac.dao.impl;

import com.ufpr.tads.dac.beans.EscolaridadeBean;
import com.ufpr.tads.dac.dao.ConnectionFactory;
import com.ufpr.tads.dac.dao.EscolaridadeDAO;
import com.ufpr.tads.dac.exceptions.EscolaridadeException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EscolaridadeDAOimpl implements EscolaridadeDAO {

    private Connection con;

    @Override
    public ArrayList<EscolaridadeBean> getAllEscolaridade() throws EscolaridadeException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        final ArrayList<EscolaridadeBean> al = new ArrayList<EscolaridadeBean>();
        try {
            con = new ConnectionFactory().getConnection();            
            pst = con.prepareStatement("SELECT id, descricao FROM bd4everalone.escolaridade");
            rs = pst.executeQuery();            
            while(rs.next()) {
                al.add(new EscolaridadeBean(rs.getInt("id"), rs.getString("descricao")));
            }            
            if (al.isEmpty()) {
                throw new EscolaridadeException("Erro Escolaridade: Falha ao carregar as Escolaridade");
            }
            return al;
        } catch (SQLException e) {
           throw new EscolaridadeException("Erro Escolaridade: Comando SQL invalido");
        } finally {
            if (pst!= null) {try {pst.close(); } catch (SQLException ex) {throw new EscolaridadeException("Erro Escolaridade: Falha ao tentar fechar conexão!");}}
        }
    }

}
