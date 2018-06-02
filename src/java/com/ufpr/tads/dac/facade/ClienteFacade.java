
package com.ufpr.tads.dac.facade;

import com.ufpr.tads.dac.beans.ClienteBean;
import com.ufpr.tads.dac.dao.impl.ClienteDAOimpl;
import com.ufpr.tads.dac.exceptions.ClienteException;
import com.ufpr.tads.dac.exceptions.EnderecoException;

public class ClienteFacade {
    ClienteDAOimpl ClienteDAO = new ClienteDAOimpl();
    
    public ClienteBean getClienteById(int clienteId) throws ClienteException, EnderecoException{
        return ClienteDAO.getClienteById(clienteId);
    }
    public void updateCliente(ClienteBean cliente) throws ClienteException{
        ClienteDAO.updateCliente(cliente);
    }
//    public void deleteCliente(int clienteId) throws ClienteException{
//        ClienteDAO.deleteCliente(clienteId);
//    }

    public Boolean isCpfDisponivel(String cpf)throws ClienteException {
       return ClienteDAO.isCpfDisponivel(cpf);
    }
}
