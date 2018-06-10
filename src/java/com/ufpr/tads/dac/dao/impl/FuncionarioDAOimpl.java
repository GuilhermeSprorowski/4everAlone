package com.ufpr.tads.dac.dao.impl;

import com.ufpr.tads.dac.beans.CidadeBean;
import com.ufpr.tads.dac.beans.EnderecoBean;
import com.ufpr.tads.dac.beans.FuncionarioBean;
import com.ufpr.tads.dac.dao.ConnectionFactory;
import com.ufpr.tads.dac.dao.FuncionarioDAO;
import com.ufpr.tads.dac.exceptions.CidadeException;
import com.ufpr.tads.dac.exceptions.FuncionarioException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FuncionarioDAOimpl implements FuncionarioDAO {

    private Connection con;

    @Override
    public void setFuncionario(FuncionarioBean f) throws FuncionarioException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateFuncionario(FuncionarioBean f) throws FuncionarioException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteFuncionario(int idFuncionario) throws FuncionarioException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FuncionarioBean getFuncionarioById(int idFuncionario) throws FuncionarioException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<FuncionarioBean> getAllFuncionarios() throws FuncionarioException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        final ArrayList<FuncionarioBean> al = new ArrayList<FuncionarioBean>();
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("SELECT funcionario.id, salario, funcionario.nome, cpf, dataNasc, codEndereco, rua, codCidade, cidade.nome as cidade, dataCadastro FROM bd4everalone.funcionario\n"
                    + "INNER JOIN bd4everalone.endereco ON codEndereco = endereco.id\n"
                    + "INNER JOIN bd4everalone.cidade ON codCidade = cidade.id\n"
                    + "INNER JOIN bd4everalone.usuario ON codUser = usuario.id\n"
                    + "WHERE NOT adm AND dataDemissao IS NULL;");
            rs = pst.executeQuery();
            while (rs.next()) {
                al.add(new FuncionarioBean(rs.getInt("id"), rs.getString("nome"), rs.getDouble("salario"), new EnderecoBean(), rs.getDate("dataNasc"), rs.getDate("dataCadastro")));
            }
            if (al.isEmpty()) {
                throw new FuncionarioException("Erro Funcionario: Falha ao procurar os funcionarios");
            }
            return al;
        } catch (SQLException e) {
            System.out.println(e);
            throw new FuncionarioException("Erro Funcionario: Comando SQL invalido");
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    throw new FuncionarioException("Erro Funcionario: Falha ao tentar fechar conexão!");
                }
            }
        }
    }

}
