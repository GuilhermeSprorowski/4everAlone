package com.ufpr.tads.dac.dao.impl;

import com.ufpr.tads.dac.beans.CidadeBean;
import com.ufpr.tads.dac.beans.EnderecoBean;
import com.ufpr.tads.dac.beans.EstadoBean;
import com.ufpr.tads.dac.beans.FestaBean;
import com.ufpr.tads.dac.beans.FuncionarioBean;
import com.ufpr.tads.dac.dao.ConnectionFactory;
import com.ufpr.tads.dac.dao.FestaDAO;
import com.ufpr.tads.dac.exceptions.FestaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FestaDAOimpl implements FestaDAO {

    private Connection con;

    @Override
    public void setFesta(FestaBean f) throws FestaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateFesta(FestaBean f) throws FestaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteFetas(int idFesta) throws FestaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FestaBean getFestaById(int idFesta) throws FestaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<FestaBean> getAllFestas() throws FestaException {

        PreparedStatement pst = null;
        ResultSet rs = null;
        final ArrayList<FestaBean> al = new ArrayList<FestaBean>();
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("SELECT festa.id, vagas, tema, festa.descricao, dataHora, codFuncionario,funcionario.nome as funcionario, codEndereco, rua, codCidade, cidade.nome as cidade, codEstado, sigla FROM bd4everalone.festa\n"
                    + "INNER JOIN bd4everalone.endereco ON codEndereco = endereco.id\n"
                    + "INNER JOIN bd4everalone.cidade ON codCidade = cidade.id\n"
                    + "INNER JOIN bd4everalone.estado ON codEstado = estado.id\n"
                    + "INNER JOIN bd4everalone.funcionario ON codFuncionario =funcionario.id;");
            rs = pst.executeQuery();
            while (rs.next()) {
                FuncionarioBean fb = new FuncionarioBean(rs.getInt("codFuncionario"),rs.getString("funcionario"));
                EnderecoBean eb = new EnderecoBean(rs.getInt("codEndereco"),rs.getString("rua"),new CidadeBean(rs.getInt("codCidade"), rs.getString("cidade")), new EstadoBean(rs.getInt("codEstado"), rs.getString("sigla")));
                al.add(new FestaBean(rs.getInt("id"), rs.getInt("vagas"),rs.getString("descricao"),rs.getString("tema"), rs.getDate("dataHora"), fb, eb));
            }
            if (al.isEmpty()) {
                throw new FestaException("Erro Festa: Falha ao procurar estas festas");
            }
            return al;
        } catch (SQLException e) {
            System.out.println(e);
            throw new FestaException("Erro Festa: Comando SQL invalido");
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    throw new FestaException("Erro Festa: Falha ao tentar fechar conexão!");
                }
            }
        }
    }

}
