package com.ufpr.tads.dac.dao;

import com.ufpr.tads.dac.beans.FestaBean;
import com.ufpr.tads.dac.exceptions.FestaException;
import java.util.ArrayList;

public interface FestaDAO {

    public void setFesta(FestaBean f) throws FestaException;

    public void updateFesta(FestaBean f) throws FestaException;

    public void deleteFetas(int idFesta) throws FestaException;

    public FestaBean getFestaById(int idFesta) throws FestaException;

    public ArrayList<FestaBean> getAllFestasPorRegiao(int cidadeId) throws FestaException;
    
    public ArrayList<FestaBean> getAllFestas() throws FestaException;
}
