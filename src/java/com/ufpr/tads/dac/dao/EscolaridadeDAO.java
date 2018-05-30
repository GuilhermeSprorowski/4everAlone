package com.ufpr.tads.dac.dao;

import com.ufpr.tads.dac.beans.EscolaridadeBean;
import com.ufpr.tads.dac.exceptions.EscolaridadeException;
import java.util.ArrayList;

public interface EscolaridadeDAO {

    public ArrayList<EscolaridadeBean> getAllEscolaridade() throws EscolaridadeException;
}
