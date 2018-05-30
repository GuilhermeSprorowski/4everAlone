
package com.ufpr.tads.dac.dao.impl;


import com.ufpr.tads.dac.beans.CorCabeloBean;
import com.ufpr.tads.dac.dao.ConnectionFactory;
import com.ufpr.tads.dac.dao.CorCabeloDAO;
import com.ufpr.tads.dac.exceptions.CorCabeloException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CorCabeloDAOimpl implements CorCabeloDAO{
    
    private Connection con;
    @Override
    public ArrayList<CorCabeloBean> getAllCoresCabelo() throws CorCabeloException {
        PreparedStatement pst = null;
        ResultSet rs = null;        
        final ArrayList<CorCabeloBean> al = new ArrayList<CorCabeloBean>();
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("SELECT id, descricao FROM db4everalone.corcabelo");
            rs = pst.executeQuery();
            while (rs.next()) {
                al.add(new CorCabeloBean(rs.getInt("id"), rs.getString("descricao")));
            }
           return al;
        } catch (SQLException e) {
            throw new CorCabeloException("Erro Cor Cabelos: Comando SQL invalido");
        }finally {
            if (pst!= null) {try {pst.close(); } catch (SQLException ex) {throw new CorCabeloException("Erro Cor Cabelo: Falha ao tentar fechar conexão!");}}
        }
        
    }
    
}
