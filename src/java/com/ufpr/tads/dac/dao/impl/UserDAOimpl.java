package com.ufpr.tads.dac.dao.impl;

import com.ufpr.tads.dac.beans.UserBean;
import com.ufpr.tads.dac.dao.ConnectionFactory;
import com.ufpr.tads.dac.dao.UserDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOimpl implements UserDAO {

    private Connection con;

    @Override
    public UserBean getUserLogin(String email, String senha) throws SQLException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        con = new ConnectionFactory().getConnection();
        pst = con.prepareStatement("SELECT cliente.id as id, email, nome FROM bd4everalone.usuario\n"
                + "INNER JOIN bd4everalone.cliente ON cliente.codUser = usuario.id WHERE (email = ?) AND (senha = ?) ");
        pst.setString(1, email);
        pst.setString(2, senha);
        rs = pst.executeQuery();
        UserBean u = null;
        while (rs.next()) {
            u = new UserBean(rs.getString("email"), rs.getString("nome"), rs.getInt("id"));
        }
        return u;
    }

}
