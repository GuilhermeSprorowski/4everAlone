
package com.ufpr.tads.dac.dao.impl;


import com.ufpr.tads.dac.beans.CorPeleBean;
import com.ufpr.tads.dac.dao.ConnectionFactory;
import com.ufpr.tads.dac.dao.CorPeleDAO;
import com.ufpr.tads.dac.exceptions.CorPeleException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CorPeleDAOimpl implements CorPeleDAO{
    
    private Connection con;

    @Override
    public ArrayList<CorPeleBean> getAllCoresPele() throws CorPeleException {
        PreparedStatement pst = null;
        ResultSet rs = null;        
        final ArrayList<CorPeleBean> al = new ArrayList<CorPeleBean>();
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("SELECT id, descricao FROM bd4everalone.corpele");
            rs = pst.executeQuery();
            while (rs.next()) {
                al.add(new CorPeleBean(rs.getInt("id"), rs.getString("descricao")));
            }
            if (al.isEmpty()) {
               throw new CorPeleException("Erro Cor Pele: cores não encontradas");
            }
           return al;
        } catch (SQLException e) {
            throw new CorPeleException("Erro Cor Pele: Comando SQL invalido");
        }finally {
            if (pst!= null) {try {pst.close(); } catch (SQLException ex) {throw new CorPeleException("Erro Cor Pele: Falha ao tentar fechar conexão!");}}
        }
    }
    
}
