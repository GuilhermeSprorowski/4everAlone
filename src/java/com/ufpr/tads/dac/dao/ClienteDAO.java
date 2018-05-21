
package com.ufpr.tads.dac.dao;

import com.ufpr.tads.dac.beans.ClienteBean;
import java.util.ArrayList;
import javax.ws.rs.ClientErrorException;


public interface ClienteDAO {
    
    public void setCliente(ClienteBean c) throws ClientErrorException;
    public void updateCliente(ClienteBean c) throws ClientErrorException;
    public void deleteCliente(int clienteId) throws ClientErrorException;
    public ClienteBean getClienteById(int clienteId) throws ClientErrorException;
    public ArrayList<ClienteBean> getAllClientes() throws ClientErrorException;
}
