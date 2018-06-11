package com.ufpr.tads.dac.facade;

import com.ufpr.tads.dac.beans.EnderecoBean;
import com.ufpr.tads.dac.dao.impl.EnderecoDAOimpl;
import com.ufpr.tads.dac.exceptions.EnderecoException;
import java.util.ArrayList;

public class EnderecoFacade {

    static EnderecoDAOimpl EnderecoDAO = new EnderecoDAOimpl();

    public static ArrayList<EnderecoBean> getAllEndereco() throws EnderecoException {
        return EnderecoDAO.getAllEndereco();
    }
    
    public static EnderecoBean getRandomLocal(int cidadeId) throws EnderecoException {
        return EnderecoDAO.getRandomLocalPorCidadeId(cidadeId);
    }
}
