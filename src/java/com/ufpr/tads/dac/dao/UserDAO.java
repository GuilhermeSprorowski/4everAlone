
package com.ufpr.tads.dac.dao;
import com.ufpr.tads.dac.beans.UserBean;
import java.sql.SQLException;

public interface UserDAO {
    
    public UserBean getUserLogin(String login, String senha) throws SQLException;    
    
}
