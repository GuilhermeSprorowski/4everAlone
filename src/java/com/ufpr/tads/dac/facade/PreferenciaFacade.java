package com.ufpr.tads.dac.facade;


import com.ufpr.tads.dac.beans.PreferenciaBean;
import com.ufpr.tads.dac.dao.impl.PrefereciaDAOimpl;
import com.ufpr.tads.dac.exceptions.PreferenciaException;

public class PreferenciaFacade {
    static PrefereciaDAOimpl PreferenciaDAO = new PrefereciaDAOimpl();
}
