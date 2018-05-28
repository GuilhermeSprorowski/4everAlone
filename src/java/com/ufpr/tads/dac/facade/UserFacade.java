
package com.ufpr.tads.dac.facade;

import com.ufpr.tads.dac.beans.UserBean;
import com.ufpr.tads.dac.dao.impl.UserDAOimpl;
import com.ufpr.tads.dac.exceptions.UserException;

public class UserFacade {
    
    static UserDAOimpl UserDAO = new UserDAOimpl();
    
    public static UserBean getUserByLogin(String email, String senha) throws UserException{
        
        return UserDAO.getUserLogin(email, senha);
    }    
}
