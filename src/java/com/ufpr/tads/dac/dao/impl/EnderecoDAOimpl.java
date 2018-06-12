package com.ufpr.tads.dac.dao.impl;

import com.ufpr.tads.dac.beans.CidadeBean;
import com.ufpr.tads.dac.beans.EnderecoBean;
import com.ufpr.tads.dac.beans.EstadoBean;
import com.ufpr.tads.dac.dao.ConnectionFactory;
import com.ufpr.tads.dac.dao.EnderecoDAO;
import com.ufpr.tads.dac.exceptions.CidadeException;
import com.ufpr.tads.dac.exceptions.EnderecoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EnderecoDAOimpl implements EnderecoDAO {

    private Connection con;
    public ArrayList<EnderecoBean> getAllEnderco;

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
        } catch (SQLException e) {
            throw new EnderecoException("Erro: comando sql invalido");
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                }
            }
        }
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

    @Override
    public ArrayList<EnderecoBean> getAllEndereco() throws EnderecoException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        final ArrayList<EnderecoBean> al = new ArrayList<EnderecoBean>();
        try {
            con = new ConnectionFactory().getConnection();

            pst = con.prepareStatement("SELECT endereco.id, rua, local, codCidade ,cidade.nome as cidade, codEstado, sigla FROM bd4everalone.endereco\n"
                    + "INNER JOIN bd4everalone.cidade ON codCidade = cidade.id\n"
                    + "INNER JOIN bd4everalone.estado ON codEstado = estado.id");
            rs = pst.executeQuery();
            while (rs.next()) {
                al.add(new EnderecoBean(rs.getInt("id"), rs.getString("rua"), new CidadeBean(rs.getInt("codCidade"), rs.getString("cidade")),new EstadoBean(rs.getInt("codEstado"), rs.getString("sigla")),rs.getString("local")));
            }
            if (al.isEmpty()) {
                throw new EnderecoException("Erro endereco: Falha ao procurar estes enderecos");
            }
            return al;
        } catch (SQLException e) {
            System.out.println(e);
            throw new EnderecoException("Erro endereco: Comando SQL invalido");
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    throw new EnderecoException("Erro endereco: Falha ao tentar fechar conexão!");
                }
            }
        }
    }

    @Override
    public EnderecoBean getRandomLocalPorCidadeId(int cidadeId) throws EnderecoException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("SELECT endereco.id, rua, local, codCidade ,cidade.nome as cidade, codEstado, sigla FROM bd4everalone.endereco\n"
                    + "INNER JOIN bd4everalone.cidade ON codCidade = cidade.id\n"
                    + "INNER JOIN bd4everalone.estado ON codEstado = estado.id\n"
                    + "WHERE endereco.codCidade = ? AND local IS NOT NULL\n"
                    + "ORDER BY RAND()");
            pst.setInt(1, cidadeId);
            rs = pst.executeQuery();
            while (rs.next()) {
                return new EnderecoBean(rs.getInt("id"), rs.getString("rua"), new CidadeBean(rs.getInt("codCidade"), rs.getString("cidade")),new EstadoBean(rs.getInt("codEstado"), rs.getString("sigla")),rs.getString("local"));
            }
        } catch (SQLException e) {
            throw new EnderecoException("Erro: comando sql invalido");
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                }
            }
        }
        return null;
    }

    public ArrayList<EnderecoBean> getAllLocaisPorCidade(int cidade) throws EnderecoException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<EnderecoBean> en = new ArrayList<>();
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("SELECT endereco.id, rua, local, codCidade ,cidade.nome as cidade, codEstado, sigla FROM bd4everalone.endereco\n"
                    + "INNER JOIN bd4everalone.cidade ON codCidade = cidade.id\n"
                    + "INNER JOIN bd4everalone.estado ON codEstado = estado.id\n"
                    + "WHERE endereco.codCidade = ? AND local IS NOT NULL");
            pst.setInt(1, cidade);
            rs = pst.executeQuery();
            while (rs.next()) {
                en.add(new EnderecoBean(rs.getInt("id"), rs.getString("rua"), new CidadeBean(rs.getInt("codCidade"), rs.getString("cidade")),new EstadoBean(rs.getInt("codEstado"), rs.getString("sigla")),rs.getString("local")));
            }
            return en;
        } catch (SQLException e) {
            throw new EnderecoException("Erro: comando sql invalido");
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

}
