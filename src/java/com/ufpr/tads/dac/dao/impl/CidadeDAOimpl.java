package com.ufpr.tads.dac.dao.impl;

import com.ufpr.tads.dac.beans.CidadeBean;
import com.ufpr.tads.dac.dao.CidadeDAO;
import com.ufpr.tads.dac.dao.ConnectionFactory;
import com.ufpr.tads.dac.exceptions.CidadeException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CidadeDAOimpl implements CidadeDAO {
    
    private Connection con;
    @Override
    public ArrayList<CidadeBean> getAllCidades(String idEstado) throws CidadeException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        final ArrayList<CidadeBean> al = new ArrayList<CidadeBean>();
        try {
            con = new ConnectionFactory().getConnection();
            
            pst = con.prepareStatement("SELECT id, nome FROM db4everalone.cidade WHERE codEstado = ?");
            pst.setString(1, idEstado);
            rs = pst.executeQuery();            
            while(rs.next()) {
                al.add(new CidadeBean(rs.getInt("id"),rs.getString("nome")));
            }            
            if (al.isEmpty()) {
                throw new CidadeException("Erro Cidade: Falha ao procurar estas cidades");
            }
            return al;
        } catch (SQLException e) {
           throw new CidadeException("Erro Cidade: Comando SQL invalido");
        } finally {
            if (pst!= null) {try {pst.close(); } catch (SQLException ex) {throw new CidadeException("Erro Cidade: Falha ao tentar fechar conexão!");}}
        }
    }
}
