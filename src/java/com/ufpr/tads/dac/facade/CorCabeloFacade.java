package com.ufpr.tads.dac.facade;

import com.ufpr.tads.dac.beans.CorCabeloBean;
import com.ufpr.tads.dac.dao.impl.CorCabeloDAOimpl;
import com.ufpr.tads.dac.exceptions.CorCabeloException;
import java.util.ArrayList;

public class CorCabeloFacade {
    static CorCabeloDAOimpl CorCabeloDAO = new CorCabeloDAOimpl();
    
    public static ArrayList<CorCabeloBean> getAllCoresCabelo() throws CorCabeloException {
        return CorCabeloDAO.getAllCoresCabelo();
    }
}
