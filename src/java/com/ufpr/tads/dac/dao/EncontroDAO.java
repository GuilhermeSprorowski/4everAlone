
package com.ufpr.tads.dac.dao;

import com.tads.web2.dac.exceptions.EncontroException;
import com.ufpr.tads.dac.beans.EncontroBean;
import java.util.ArrayList;


public interface EncontroDAO {
    
    public void setNovoEncontro(EncontroBean encontro) throws EncontroException;   
    public void updateEncontro(EncontroBean encontro) throws EncontroException;    
    public EncontroBean getEncontro(int encontroId) throws EncontroException; 
    public ArrayList<EncontroBean> getAllEncontrosByIdCliente(int clienteId) throws EncontroException;
    public ArrayList<EncontroBean> getEncontrosPendentesByIdCliente(int clienteId) throws EncontroException;
}
