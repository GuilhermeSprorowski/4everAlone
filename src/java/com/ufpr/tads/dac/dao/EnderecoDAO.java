package com.ufpr.tads.dac.dao;

import com.ufpr.tads.dac.beans.EnderecoBean;
import com.ufpr.tads.dac.exceptions.EnderecoException;

public interface EnderecoDAO {

    public EnderecoBean getEnderecoById(int enderecoId) throws EnderecoException;

    public void setEndereco(EnderecoBean eb) throws EnderecoException;

    public void updateEndereco(EnderecoBean eb) throws EnderecoException;

}
