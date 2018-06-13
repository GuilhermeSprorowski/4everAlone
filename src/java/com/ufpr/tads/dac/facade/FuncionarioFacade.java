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
    
    public static void novoFuncionario(FuncionarioBean f, String email) throws FuncionarioException {
        FuncionarioDAO.setFuncionario(f, email);
    }
    
    public static void updateFuncionario(FuncionarioBean f) throws FuncionarioException {
        FuncionarioDAO.updateFuncionario(f);
    }

    public static FuncionarioBean getFuncionarioById(int funcId) throws FuncionarioException {
        return FuncionarioDAO.getFuncionarioById(funcId);
    }
}
