
package com.ufpr.tads.dac.dao;

import com.ufpr.tads.dac.beans.CidadeBean;
import com.ufpr.tads.dac.exceptions.CidadeException;
import java.util.ArrayList;



public interface CidadeDAO {
    public ArrayList<CidadeBean> getAllCidades(String idEstado)throws CidadeException;
}
