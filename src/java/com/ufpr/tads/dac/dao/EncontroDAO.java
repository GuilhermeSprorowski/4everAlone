
package com.ufpr.tads.dac.dao;

import com.ufpr.tads.dac.exceptions.EncontroException;
import com.ufpr.tads.dac.beans.EncontroBean;
import java.util.ArrayList;


public interface EncontroDAO {
    
    public void setNovoEncontro(EncontroBean encontro, int clienteId) throws EncontroException;   
    public void updateEncontro(EncontroBean encontro) throws EncontroException;    
    public EncontroBean getEncontro(int encontroId) throws EncontroException; 
    public ArrayList<EncontroBean> getAllEncontrosByIdCliente(int clienteId) throws EncontroException;
    public ArrayList<EncontroBean> getEncontrosRecebidos(int clienteId) throws EncontroException;
    public ArrayList<EncontroBean> getEncontrosAceitosPendentesByIdCliente(int clienteId) throws EncontroException;
}
