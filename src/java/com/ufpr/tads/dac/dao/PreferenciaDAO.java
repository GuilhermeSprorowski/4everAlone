package com.ufpr.tads.dac.dao;

import com.ufpr.tads.dac.beans.PreferenciaBean;
import com.ufpr.tads.dac.exceptions.PreferenciaException;

public interface PreferenciaDAO {

    public void setPreferencia(PreferenciaBean p) throws PreferenciaException;

    public void updatePreferencia(PreferenciaBean p) throws PreferenciaException;

    public PreferenciaBean getPreferenciasByClienteId(int idCliente) throws PreferenciaException;
}
