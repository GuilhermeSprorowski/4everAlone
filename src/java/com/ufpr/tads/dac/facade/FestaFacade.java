package com.ufpr.tads.dac.facade;

import com.ufpr.tads.dac.beans.FestaBean;
import com.ufpr.tads.dac.dao.impl.FestaDAOimpl;
import com.ufpr.tads.dac.exceptions.FestaException;
import java.util.ArrayList;


public class FestaFacade {
    static FestaDAOimpl FestaDAO = new FestaDAOimpl();
    
    public static ArrayList<FestaBean> getAllFesta() throws FestaException{
        
        return FestaDAO.getAllFestas();
    }
}
