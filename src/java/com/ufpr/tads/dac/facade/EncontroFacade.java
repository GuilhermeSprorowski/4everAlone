
package com.ufpr.tads.dac.facade;

import com.ufpr.tads.dac.beans.EncontroBean;
import com.ufpr.tads.dac.dao.impl.EncontroDAOimpl;
import com.ufpr.tads.dac.exceptions.ClienteException;
import com.ufpr.tads.dac.exceptions.EncontroException;
import com.ufpr.tads.dac.exceptions.EnderecoException;
import java.util.ArrayList;

public class EncontroFacade {
    
    static EncontroDAOimpl EncontroDAO = new EncontroDAOimpl();
    
    public static ArrayList<EncontroBean> getEncontrosPendentes(int clienteId) throws EncontroException, ClienteException, EnderecoException{
        
        return EncontroDAO.getAllEncontrosByIdCliente(clienteId);
    }    
}
