
package com.ufpr.tads.dac.dao.impl;

import com.ufpr.tads.dac.beans.PreferenciaBean;
import com.ufpr.tads.dac.dao.ConnectionFactory;
import com.ufpr.tads.dac.dao.PreferenciaDAO;
import com.ufpr.tads.dac.exceptions.PreferenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PrefereciaDAOimpl implements PreferenciaDAO{
    private Connection con;
    @Override
    public void setPreferencia(PreferenciaBean p) throws PreferenciaException {
    }

    @Override
    public void updatePreferencia(PreferenciaBean p) throws PreferenciaException {
    }

    @Override
    public PreferenciaBean getPreferenciasByClienteId(int idCliente) throws PreferenciaException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = new ConnectionFactory().getConnection();            
            pst = con.prepareStatement("SELECT * FROM bd4everalone.preferencia WHERE codCliente = ?");
            pst.setInt(1, idCliente);
            rs = pst.executeQuery();   
            while(rs.next()) {
                int[] altura = { rs.getInt("alturaMin"), rs.getInt("alturaMax") };
                int[] idade = { rs.getInt("idadeMin"), rs.getInt("idadeMax") };
                return new PreferenciaBean(rs.getInt("id"), rs.getString("sexo"), idade, altura, 
                        rs.getInt("codCabelo"), rs.getInt("codPele"));
            }
            return null;
        } catch (SQLException e) {
            System.out.println(e);
           throw new PreferenciaException("Erro Preferencia: Comando SQL invalido");
        } finally {
            if (pst!= null) {try {pst.close(); } catch (SQLException ex) {throw new PreferenciaException("Erro Preferencia: Falha ao tentar fechar conexão!");}}
        }
    }
    
}
