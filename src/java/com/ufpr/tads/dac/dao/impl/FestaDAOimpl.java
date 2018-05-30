package com.ufpr.tads.dac.dao.impl;

import com.ufpr.tads.dac.beans.FestaBean;
import com.ufpr.tads.dac.dao.FestaDAO;
import com.ufpr.tads.dac.exceptions.FestaException;
import java.sql.Connection;
import java.util.ArrayList;

public class FestaDAOimpl implements FestaDAO{
    private Connection con;

    @Override
    public void setFesta(FestaBean f) throws FestaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateFesta(FestaBean f) throws FestaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteFetas(int idFesta) throws FestaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FestaBean getFestaById(int idFesta) throws FestaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<FestaBean> getAllFestas() throws FestaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
