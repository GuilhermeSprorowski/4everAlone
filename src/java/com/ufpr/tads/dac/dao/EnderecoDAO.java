package com.ufpr.tads.dac.dao;

import com.ufpr.tads.dac.beans.EnderecoBean;
import com.ufpr.tads.dac.exceptions.EnderecoException;
import java.util.ArrayList;

public interface EnderecoDAO {

    public EnderecoBean getEnderecoById(int enderecoId) throws EnderecoException;

    public EnderecoBean getRandomLocalPorCidadeId(int cidadeId) throws EnderecoException;
    
    public void setEndereco(EnderecoBean eb) throws EnderecoException;

    public void updateEndereco(EnderecoBean eb) throws EnderecoException;
    
    public ArrayList<EnderecoBean> getAllEndereco() throws EnderecoException;

}
