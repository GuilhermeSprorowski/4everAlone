
package com.ufpr.tads.dac.dao;

import com.ufpr.tads.dac.beans.EstadoBean;
import com.ufpr.tads.dac.exceptions.EstadoException;
import java.util.ArrayList;


public interface EstadoDAO {
    public ArrayList<EstadoBean> getAllEstados()throws EstadoException;
}
