
package com.ufpr.tads.dac.dao.impl;

import com.ufpr.tads.dac.beans.EstadoBean;
import com.ufpr.tads.dac.dao.ConnectionFactory;
import com.ufpr.tads.dac.dao.EstadoDAO;
import com.ufpr.tads.dac.exceptions.EstadoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EstadoDAOimpl implements EstadoDAO{
    
    private Connection con;
    
    @Override
    public ArrayList<EstadoBean> getAllEstados() throws EstadoException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        final ArrayList<EstadoBean> al = new ArrayList<EstadoBean>();
        try {
            con = new ConnectionFactory().getConnection();            
            pst = con.prepareStatement("SELECT id, nome, sigla FROM bd4everalone.estado");
            rs = pst.executeQuery();            
            while(rs.next()) {
                al.add(new EstadoBean(rs.getString("nome"),rs.getString("sigla"),rs.getInt("id")));
            }            
            if (al.isEmpty()) {
                throw new EstadoException("Erro Estado: Falha ao carregar os estados");
            }
            return al;
        } catch (SQLException e) {
           throw new EstadoException("Erro Estado: Comando SQL invalido");
        } finally {
            if (pst!= null) {try {pst.close(); } catch (SQLException ex) {throw new EstadoException("Erro Estado: Falha ao tentar fechar conexão!");}}
        }
    }
    
}
