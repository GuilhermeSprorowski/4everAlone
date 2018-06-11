package com.ufpr.tads.dac.dao.impl;

import com.ufpr.tads.dac.beans.ClienteBean;
import com.ufpr.tads.dac.exceptions.EncontroException;
import com.ufpr.tads.dac.beans.EncontroBean;
import com.ufpr.tads.dac.beans.EnderecoBean;
import com.ufpr.tads.dac.dao.ConnectionFactory;
import com.ufpr.tads.dac.dao.EncontroDAO;
import com.ufpr.tads.dac.exceptions.ClienteException;
import com.ufpr.tads.dac.exceptions.EnderecoException;
import com.ufpr.tads.dac.facade.ClienteFacade;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EncontroDAOimpl implements EncontroDAO {

    private Connection con;

    @Override
    public void setNovoEncontro(EncontroBean encontro, int clienteId) throws EncontroException {
        PreparedStatement pst = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("INSERT INTO  bd4everalone.encontro(dataEncontro, codCSolicitante, codCSolicitado, codEndereco) VALUES(?,?,?,?);");
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dataEncontro = fmt.format(encontro.getDataEncontro());
            pst.setString(1, dataEncontro);
            pst.setInt(2, clienteId);
            pst.setInt(3, encontro.getSolicitado().getClienteId());
            pst.setInt(4, encontro.getEndereco().getEnderecoId());

            int resp = pst.executeUpdate();
            if (resp == 0) {
                throw new EncontroException("Erro encontro: não foi possivel fazer essa Solicitacao de encotro");
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new EncontroException("Erro encontro: comando sql invalido");
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    throw new EncontroException("Erro encontro: erro ao fechar conexão");
                }
            }
        }
    }

    @Override
    public EncontroBean getEncontro(int encontroId) throws EncontroException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateEncontro(EncontroBean encontro) throws EncontroException {
        PreparedStatement pst = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("UPDATE bd4everalone.encontro SET dataResposta = ?, aceito = ? WHERE id = ?");
            pst.setDate(1, new Date(System.currentTimeMillis()));
            pst.setBoolean(2, encontro.getAceito());
            pst.setInt(3, encontro.getEncontroId());
            int resp = pst.executeUpdate();
            if (resp == 0) {
                throw new EncontroException("Erro encontro: não foi possivel respoder ao encotro");
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new EncontroException("Erro encontro: comando sql invalido");
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    throw new EncontroException("Erro encontro: erro ao fechar conexão");
                }
            }
        }
    }

    @Override
    public ArrayList<EncontroBean> getAllEncontrosByIdCliente(int clienteId) throws EncontroException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("SELECT encontro.id, dataSolicitacao, dataEncontro,dataResposta,aceito,codCSolicitado idSolicitado, solicitado.nome nomeSolicitado, solicitado.descricao descricaoSolicitado, encontro.codEndereco as idEndereco, en.local as local "
                    + "FROM bd4everalone.encontro "
                    + "INNER JOIN bd4everalone.cliente solicitado ON codCSolicitado = solicitado.id "
                    + "INNER JOIN bd4everalone.endereco en ON en.id = encontro.codEndereco "
                    + "WHERE codCSolicitante = ? "
                    + "ORDER BY dataSolicitacao");
            pst.setInt(1, clienteId);
            rs = pst.executeQuery();
            final ArrayList<EncontroBean> al = new ArrayList<EncontroBean>();
            while (rs.next()) {
                
                ClienteBean c = new ClienteBean(rs.getInt("idSolicitado"), rs.getString("nomeSolicitado"), rs.getString("descricaoSolicitado"));
                EnderecoBean end = new EnderecoBean(rs.getInt("idEndereco"));
                end.setNomeLocal(rs.getString("local"));
                al.add(new EncontroBean(rs.getInt("id"), rs.getDate("dataSolicitacao"), rs.getTimestamp("dataEncontro"),
                        rs.getDate("dataResposta"), c, end, rs.getBoolean("aceito")));
            }
            return al;
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new EncontroException("Erro encontro: comando sql invalido");
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    throw new EncontroException("Erro encontro: erro ao fechar conexão");
                }
            }
        }
    }

    @Override
    public ArrayList<EncontroBean> getEncontrosAceitosPendentesByIdCliente(int clienteId) throws EncontroException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("SELECT encontro.id, dataSolicitacao, dataEncontro,dataResposta,aceito,solicitado.id as idSolicitado, solicitado.nome nomeSolicitado, solicitado.descricao descricaoSolicitado, encontro.codEndereco as idEndereco, en.local as local "
                    + "FROM bd4everalone.encontro "
                    + "INNER JOIN bd4everalone.cliente solicitado "
                    + "ON CASE "
                    + "WHEN codCSolicitado = ? THEN codCSolicitante = solicitado.id "
                    + "WHEN codCSolicitante = ? THEN codCSolicitado = solicitado.id "
                    + "END "
                    + "INNER JOIN bd4everalone.endereco en ON en.id = encontro.codEndereco "
                    + "WHERE aceito AND dataEncontro > CURDATE() AND (codCSolicitante = ? OR codCSolicitado = ?)");
            pst.setInt(1, clienteId);
            pst.setInt(2, clienteId);
            pst.setInt(3, clienteId);
            pst.setInt(4, clienteId);
            rs = pst.executeQuery();
            final ArrayList<EncontroBean> al = new ArrayList<EncontroBean>();
            while (rs.next()) {
                ClienteBean c = new ClienteBean(rs.getInt("idSolicitado"), rs.getString("nomeSolicitado"), rs.getString("descricaoSolicitado"));
                EnderecoBean end = new EnderecoBean(rs.getInt("idEndereco"));
                end.setNomeLocal(rs.getString("local"));
                al.add(new EncontroBean(rs.getInt("id"), rs.getDate("dataSolicitacao"), rs.getTimestamp("dataEncontro"),
                        rs.getDate("dataResposta"), c, end, rs.getBoolean("aceito")));
            }
            return al;
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new EncontroException("Erro encontro: comando sql invalido");
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    throw new EncontroException("Erro encontro: erro ao fechar conexão");
                }
            }
        }
    }

    @Override
    public ArrayList<EncontroBean> getEncontrosRecebidos(int clienteId) throws EncontroException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("SELECT encontro.id, dataSolicitacao, dataEncontro,dataResposta,aceito,codCSolicitado idSolicitado, codCSolicitante idSolicitante, "
                    + "encontro.codEndereco as idEndereco, en.local as local "
                    + "FROM bd4everalone.encontro "
                    + "INNER JOIN bd4everalone.endereco en ON en.id = encontro.codEndereco "
                    + "WHERE dataResposta IS NULL AND dataEncontro > CURDATE() AND codCSolicitado = ? ");
            pst.setInt(1, clienteId);
            rs = pst.executeQuery();
            final ArrayList<EncontroBean> al = new ArrayList<EncontroBean>();
            while (rs.next()) {
                ClienteBean c = null;
                try {
                    c = ClienteFacade.getClienteById(rs.getInt("idSolicitante"));
                } catch (ClienteException | EnderecoException ex) {
                    Logger.getLogger(EncontroDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                EnderecoBean end = new EnderecoBean(rs.getInt("idEndereco"));
                end.setNomeLocal(rs.getString("local"));
                
                al.add(new EncontroBean(rs.getInt("id"), rs.getDate("dataSolicitacao"), rs.getTimestamp("dataEncontro"),
                        rs.getDate("dataResposta"), c, end, rs.getBoolean("aceito")));
            }
            return al;
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new EncontroException("Erro encontro: comando sql invalido");
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    throw new EncontroException("Erro encontro: erro ao fechar conexão");
                }
            }
        }
    }
}
