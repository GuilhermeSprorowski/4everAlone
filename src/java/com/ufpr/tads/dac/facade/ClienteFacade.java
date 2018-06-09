
package com.ufpr.tads.dac.facade;

import com.ufpr.tads.dac.beans.ClienteBean;
import com.ufpr.tads.dac.beans.PreferenciaBean;
import com.ufpr.tads.dac.dao.impl.ClienteDAOimpl;
import com.ufpr.tads.dac.exceptions.ClienteException;
import com.ufpr.tads.dac.exceptions.EnderecoException;
import java.util.ArrayList;

public class ClienteFacade {
    static ClienteDAOimpl ClienteDAO = new ClienteDAOimpl();
    
    public static ClienteBean getClienteById(int clienteId) throws ClienteException, EnderecoException{
        return ClienteDAO.getClienteById(clienteId);
    }

    public static ArrayList<ClienteBean> getClientesCompativeis(PreferenciaBean p, int cidade)throws ClienteException {
        return ClienteDAO.getClientesCompativeis(p, cidade);
    }
    public void updateCliente(ClienteBean cliente) throws ClienteException{
        ClienteDAO.updateCliente(cliente);
    }
    public Boolean isCpfDisponivel(String cpf)throws ClienteException {
       return ClienteDAO.isCpfDisponivel(cpf);
    }
}
