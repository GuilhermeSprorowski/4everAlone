package com.ufpr.tads.dac.facade;

import com.ufpr.tads.dac.beans.CorPeleBean;
import com.ufpr.tads.dac.dao.impl.CorPeleDAOimpl;
import com.ufpr.tads.dac.exceptions.CorPeleException;
import java.util.ArrayList;

public class CorPeleFacade {
    static CorPeleDAOimpl CorPeleDAO = new CorPeleDAOimpl();
    
    public static ArrayList<CorPeleBean> getAllCoresPele() throws CorPeleException {
       return CorPeleDAO.getAllCoresPele();
    }
}
