package com.ufpr.tads.dac.facade;

import com.ufpr.tads.dac.beans.EstadoBean;
import com.ufpr.tads.dac.dao.impl.EstadoDAOimpl;
import com.ufpr.tads.dac.exceptions.EstadoException;
import java.util.ArrayList;


public class EstadoFacade {
    static EstadoDAOimpl EstadoDAO = new EstadoDAOimpl();
    
    public static ArrayList<EstadoBean> getAllEstados() throws EstadoException {
        return EstadoDAO.getAllEstados();
    }
}
