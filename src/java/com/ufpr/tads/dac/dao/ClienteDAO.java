
package com.ufpr.tads.dac.dao;

import com.ufpr.tads.dac.beans.ClienteBean;
import com.ufpr.tads.dac.beans.PreferenciaBean;
import java.util.ArrayList;
import com.ufpr.tads.dac.exceptions.ClienteException;
import com.ufpr.tads.dac.exceptions.EnderecoException;


public interface ClienteDAO {
    
    public void setCliente(ClienteBean c, String email) throws ClienteException, EnderecoException;
    public void updateCliente(ClienteBean c) throws ClienteException, EnderecoException;
    public ClienteBean getClienteById(int clienteId) throws ClienteException, EnderecoException;
    public ArrayList<ClienteBean> getAllClientes() throws ClienteException, EnderecoException;
    public boolean isCpfDisponivel(String cpf) throws ClienteException;
    public void deleteClienteById(int clienteId) throws ClienteException;
    public ArrayList<ClienteBean> getClientesCompativeis(PreferenciaBean p, int cidade, int idCliente)throws ClienteException;
}
