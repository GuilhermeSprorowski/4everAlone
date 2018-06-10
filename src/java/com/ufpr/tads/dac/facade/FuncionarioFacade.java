package com.ufpr.tads.dac.facade;

import com.ufpr.tads.dac.beans.FuncionarioBean;
import com.ufpr.tads.dac.dao.impl.FuncionarioDAOimpl;
import com.ufpr.tads.dac.exceptions.FuncionarioException;
import java.util.ArrayList;


public class FuncionarioFacade {
    static FuncionarioDAOimpl FuncionarioDAO = new FuncionarioDAOimpl();
    
    public static ArrayList<FuncionarioBean> getAllFuncionario() throws FuncionarioException{
        return FuncionarioDAO.getAllFuncionarios();
    }
}
