
package com.ufpr.tads.dac.dao;
import com.ufpr.tads.dac.beans.UserBean;
import com.ufpr.tads.dac.exceptions.UserException;
import java.sql.SQLException;

public interface UserDAO {
    
    public UserBean getUserLogin(String login, String senha) throws UserException;    
    public void setSenha(String login, String senhaAntiga, String novaSenha) throws UserException;
    public void deleteUser(String login, String senha) throws UserException;
}
