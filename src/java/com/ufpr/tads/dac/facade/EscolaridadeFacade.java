package com.ufpr.tads.dac.facade;

import com.ufpr.tads.dac.beans.EscolaridadeBean;
import com.ufpr.tads.dac.dao.impl.EscolaridadeDAOimpl;
import com.ufpr.tads.dac.exceptions.EscolaridadeException;
import java.util.ArrayList;

public class EscolaridadeFacade {
    static EscolaridadeDAOimpl EscolaridadeDAO = new EscolaridadeDAOimpl();
    
    public static ArrayList<EscolaridadeBean> getAllEscolaridade() throws EscolaridadeException {
        return EscolaridadeDAO.getAllEscolaridade();
    }
}
