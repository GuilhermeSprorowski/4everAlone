package com.ufpr.tads.dac.dao.impl;

import com.ufpr.tads.dac.beans.UserBean;
import com.ufpr.tads.dac.dao.ConnectionFactory;
import com.ufpr.tads.dac.dao.UserDAO;
import com.ufpr.tads.dac.exceptions.UserException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOimpl implements UserDAO {

    private Connection con;

    @Override
    public UserBean getUserLogin(String email, String senha) throws UserException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("SELECT cliente.id as id, email, ifnull(cliente.nome,funcionario.nome) as nome,\n"
                    + "(SELECT true FROM bd4everalone.cliente a WHERE a.codUser = bd4everalone.usuario.id) as isCliente,\n"
                    + "ifnull((SELECT true FROM bd4everalone.funcionario F WHERE F.codUser = bd4everalone.usuario.id), false) as isFuncionario,\n"
                    + "funcionario.id as idFuncionario,\n"
                    + "ifnull((SELECT true FROM bd4everalone.funcionario F WHERE F.codUser = bd4everalone.usuario.id AND adm), false) as isAdm\n"
                    + "FROM bd4everalone.usuario\n"
                    + "LEFT JOIN bd4everalone.cliente ON cliente.codUser = usuario.id\n"
                    + "LEFT JOIN bd4everalone.funcionario ON funcionario.codUser = usuario.id\n"
                    + "WHERE (email = ?) AND (senha = ?) AND dataExcluido IS NULL");
            pst.setString(1, email);
            pst.setString(2, senha);
            rs = pst.executeQuery();
            UserBean u = null;
            while (rs.next()) {
                u = new UserBean(rs.getString("email"), rs.getString("nome"), rs.getInt("id"));
                u.setAdm(rs.getBoolean("isAdm"));
                u.setFuncionario(rs.getBoolean("isFuncionario"));
                u.setCliente(rs.getBoolean("isCliente"));
                u.setFuncionarioId(rs.getInt("idFuncionario"));
            }
            return u;
        } catch (SQLException e) {
            System.out.println(e);
            throw new UserException("Erro Usuario: comando sql invalido");
        } finally {
            if (pst != null) {
                try {
                    rs.close();
                    pst.close();
                    con.close();
                } catch (SQLException ex) {
                    throw new UserException("Erro user: erro ao fechar conecxão");
                }
            }
        }
    }

    @Override
    public void setSenha(String login, String senhaAntiga, String novaSenha) throws UserException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("SELECT id FROM bd4everalone.usuario WHERE (email = ?) AND (senha = ?) ");
            pst.setString(1, login);
            pst.setString(2, senhaAntiga);
            rs = pst.executeQuery();
            int idUsuario = 0;
            System.out.println(pst);
            while (rs.next()) {
                idUsuario = rs.getInt("id");
            }
            if (idUsuario == 0) {
                System.out.println("erroooo");
                throw new UserException("Erro cliente: senha invalida");
            }else{
                pst = con.prepareStatement("UPDATE bd4everalone.usuario SET senha = ? WHERE id = ?");
                pst.setString(1, novaSenha);
                pst.setInt(2, idUsuario);
                int resp = 0;
                resp = pst.executeUpdate();
                if (resp == 0) {
                    throw new UserException("Erro cliente: não foi possivel trocar a senha");
                }
                
            }
        } catch (SQLException e) {
            throw new UserException("Erro cliente: comando sql invalido");
        } finally {
            if (pst != null) {
                try {
                    rs.close();
                    pst.close();
                    con.close();
                } catch (SQLException ex) {
                    throw new UserException("Erro user: erro ao fechar conecxão");
                }
            }
        }
    }

    @Override
    public void deleteUser(String login, String senha) throws UserException {

        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("SELECT id FROM bd4everalone.usuario WHERE (email = ?) AND (senha = ?) ");
            pst.setString(1, login);
            pst.setString(2, senha);
            rs = pst.executeQuery();
            int idUsuario = 0;
            while (rs.next()) {
                idUsuario = rs.getInt("id");
            }
            if (idUsuario != 0) {
                pst = con.prepareStatement("delete");
                pst.setInt(1, idUsuario);
                int resp = 0;
                resp = pst.executeUpdate();
                if (resp == 0) {
                    throw new UserException("Erro cliente: não foi possivel trocar a senha");
                }
            }
        } catch (SQLException e) {
            throw new UserException("Erro cliente: comando sql invalido");
        } finally {
            if (pst != null) {
                try {
                    rs.close();
                    pst.close();
                    con.close();
                } catch (SQLException ex) {
                    throw new UserException("Erro user: erro ao fechar conecxão");
                }
            }
        }
    }

    @Override
    public boolean isEmailDisponivel(String email) throws UserException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("SELECT false as emailvalido FROM bd4everalone.usuario WHERE email = ?");
            pst.setString(1, email);
            rs = pst.executeQuery();
            while (rs.next()) {
                return rs.getBoolean("emailValido");
            }
            return true;
        } catch (SQLException e) {
            throw new UserException("Erro user: comando sql invalido");
        } finally {
            if (pst != null) {
                try {
                    rs.close();
                    pst.close();
                    con.close();
                } catch (SQLException ex) {
                    throw new UserException("Erro user: erro ao fechar conecxão");
                }
            }
        }
    }

}
