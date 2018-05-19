
package com.ufpr.tads.dac.facade;

import com.ufpr.tads.dac.beans.UserBean;
import com.ufpr.tads.dac.dao.UserDAOimpl;
import java.sql.SQLException;

public class UserFacade {
    
    static UserDAOimpl UserDAO = new UserDAOimpl();
    
    public static UserBean getUserByLogin(String email, String senha) throws SQLException{
        
        return UserDAO.getUserLogin(email, senha);
    }    
}
