package com.ufpr.tads.dac.dao;

import com.ufpr.tads.dac.beans.FuncionarioBean;
import com.ufpr.tads.dac.exceptions.FuncionarioException;
import java.util.ArrayList;

public interface FuncionarioDAO {

    public void setFuncionario(FuncionarioBean f, String email) throws FuncionarioException;

    public void updateFuncionario(FuncionarioBean f) throws FuncionarioException;

    public void deleteFuncionario(int idFuncionario) throws FuncionarioException;

    public FuncionarioBean getFuncionarioById(int idFuncionario) throws FuncionarioException;

    public ArrayList<FuncionarioBean> getAllFuncionarios() throws FuncionarioException;
}
