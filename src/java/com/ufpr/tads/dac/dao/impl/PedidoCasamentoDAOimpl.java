package com.ufpr.tads.dac.dao.impl;

import com.ufpr.tads.dac.beans.PedidoCasamentoBean;
import com.ufpr.tads.dac.dao.ConnectionFactory;
import com.ufpr.tads.dac.dao.PedidoCasamentoDAO;
import com.ufpr.tads.dac.exceptions.ClienteException;
import com.ufpr.tads.dac.exceptions.PedidoCasamentoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PedidoCasamentoDAOimpl implements PedidoCasamentoDAO {

    private Connection con;

    @Override
    public int setPedidoCasamento(PedidoCasamentoBean pc) throws PedidoCasamentoException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        int idGerado = 0;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("INSERT INTO bd4everalone.pedidocasamento(padrinho1, padrinho2, madrinha1, madrinha2, dataCasamento, codSolicitante, codConjuge, igreja, padre, luademel, nConvidados)\n"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, pc.getPadrinho1());
            pst.setString(2, pc.getPadrinho2());
            pst.setString(3, pc.getMadrinha1());
            pst.setString(4, pc.getMadrinha2());
            pst.setDate(5, new java.sql.Date(pc.getDataCasamento().getTime()));
            pst.setInt(6, pc.getSolicitante());
            pst.setInt(7, pc.getConjuge());
            pst.setString(8, pc.getIgreja());
            pst.setString(9, pc.getPadre());
            pst.setString(10, pc.getLocalLua());     
            pst.setInt(11, pc.getnConvidados());
            pst.executeUpdate();
            rs = pst.getGeneratedKeys();
            while (rs.next()) {
                idGerado = rs.getInt(1);
            }
            if (idGerado == 0) {
                throw new PedidoCasamentoException("Erro Pedido Casamento: não foi possivel gerar esse login");
            } else {
                pc.setIdPedido(idGerado);
                return idGerado;
            }
        } catch (SQLException e) {
            throw new PedidoCasamentoException("Erro Pedido Casamento: comando sql invalido");
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    throw new PedidoCasamentoException("Erro Pedido Casamento: erro ao fechar conecxão");
                }
            }
        }
    }

    @Override
    public void updatePedidoCasamento(PedidoCasamentoBean pc) throws PedidoCasamentoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PedidoCasamentoBean getPedidoCasamentoById(int idPedido) throws PedidoCasamentoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<PedidoCasamentoBean> getAllPedidosCasamento() throws PedidoCasamentoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
