package com.ufpr.tads.dac.dao.impl;

import com.ufpr.tads.dac.beans.CidadeBean;
import com.ufpr.tads.dac.beans.EnderecoBean;
import com.ufpr.tads.dac.beans.EstadoBean;
import com.ufpr.tads.dac.beans.FuncionarioBean;
import com.ufpr.tads.dac.dao.ConnectionFactory;
import com.ufpr.tads.dac.dao.FuncionarioDAO;
import com.ufpr.tads.dac.exceptions.CidadeException;
import com.ufpr.tads.dac.exceptions.FuncionarioException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

public class FuncionarioDAOimpl implements FuncionarioDAO {

    private Connection con;

    @Override
    public void setFuncionario(FuncionarioBean f, String email) throws FuncionarioException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        int idGerado = 0;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("INSERT INTO bd4everalone.usuario (email) VALUES(?)");
            pst.setString(1, email);
            int resp = pst.executeUpdate();
            rs = pst.getGeneratedKeys();
            while (rs.next()) {
                idGerado = rs.getInt(1);            
            }
            resp = 0;
            if (idGerado == 0) {
                throw new FuncionarioException("Erro funcionario: não foi possivel gerar esse login");
            } else {
                pst = con.prepareStatement("INSERT INTO bd4everalone.funcionario (nome, cpf, salario, codUser, dataNasc) VALUES(?,?,?,?,?);");
                pst.setString(1, f.getNome());
                pst.setString(2, f.getCpf());
                pst.setDouble(3, f.getSalario());
                pst.setInt(4, idGerado);               
                pst.setTimestamp(5, new Timestamp(f.getDataNasc().getTime()));   
                resp = pst.executeUpdate();
                if (resp == 0) {
                    throw new FuncionarioException("Erro funcionario: não foi possivel criar esse funcionario");
                }
            }
        } catch (SQLException e) {
            throw new FuncionarioException("Erro funcionario: comando sql invalido");
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    throw new FuncionarioException("Erro funcionario: erro ao fechar conecxão");
                }
            }
        }
    }

    @Override
    public void updateFuncionario(FuncionarioBean f) throws FuncionarioException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("UPDATE bd4everalone.funcionario SET nome = ?, cpf = ?, salario = ?, "
                        + "dataNasc = ? WHERE id = ?");
            pst.setString(1, f.getNome());
            pst.setString(2, f.getCpf());
            pst.setDouble(3, f.getSalario());       
            pst.setTimestamp(4, new Timestamp(f.getDataNasc().getTime()));   
            pst.setInt(5, f.getIdFuncionario());
            int resp = pst.executeUpdate();
            if (resp == 0) {
                throw new FuncionarioException("Erro funcionario: não foi possivel criar esse funcionario");
            }
        } catch (SQLException e) {
            throw new FuncionarioException("Erro funcionario: comando sql invalido");
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    throw new FuncionarioException("Erro funcionario: erro ao fechar conecxão");
                }
            }
        }
    }

    @Override
    public void deleteFuncionario(int idFuncionario) throws FuncionarioException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FuncionarioBean getFuncionarioById(int idFuncionario) throws FuncionarioException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("SELECT funcionario.id, salario, funcionario.nome, cpf, dataNasc, dataCadastro FROM bd4everalone.funcionario\n"
                    + "INNER JOIN bd4everalone.usuario ON codUser = usuario.id\n"
                    + "WHERE dataDemissao IS NULL AND usuario.dataExcluido IS NULL AND funcionario.id = ?");
            pst.setInt(1, idFuncionario);
            rs = pst.executeQuery();
            while (rs.next()) {
                return new FuncionarioBean(rs.getInt("id"), rs.getString("nome"), rs.getDouble("salario"),
                        rs.getDate("dataNasc"), rs.getDate("dataCadastro"), rs.getString("cpf"));
            }
            return null;
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

    @Override
    public ArrayList<FuncionarioBean> getAllFuncionarios() throws FuncionarioException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        final ArrayList<FuncionarioBean> al = new ArrayList<FuncionarioBean>();
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("SELECT funcionario.id, salario, funcionario.nome, cpf, dataNasc, dataCadastro FROM bd4everalone.funcionario\n"
                    + "INNER JOIN bd4everalone.usuario ON codUser = usuario.id\n"
                    + "WHERE dataDemissao IS NULL AND usuario.dataExcluido IS NULL");
            rs = pst.executeQuery();
            while (rs.next()) {
                al.add(new FuncionarioBean(rs.getInt("id"), rs.getString("nome"), rs.getDouble("salario"), rs.getDate("dataNasc"), rs.getDate("dataCadastro")));
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
