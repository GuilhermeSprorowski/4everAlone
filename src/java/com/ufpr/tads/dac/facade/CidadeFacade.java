package com.ufpr.tads.dac.facade;

import com.ufpr.tads.dac.beans.CidadeBean;
import com.ufpr.tads.dac.dao.impl.CidadeDAOimpl;
import com.ufpr.tads.dac.exceptions.CidadeException;
import java.util.ArrayList;


public class CidadeFacade {
    static CidadeDAOimpl CidadeDAO = new CidadeDAOimpl();
    
    public ArrayList<CidadeBean> getCidadeByEstadoId(int estado) throws CidadeException{
        return CidadeDAO.getAllCidades(estado);
    }
}
