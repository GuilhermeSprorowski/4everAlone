package com.ufpr.tads.dac.dao;

import com.ufpr.tads.dac.beans.PedidoCasamentoBean;
import com.ufpr.tads.dac.exceptions.PedidoCasamentoException;
import java.util.ArrayList;

public interface PedidoCasamentoDAO {

    public int setPedidoCasamento(PedidoCasamentoBean pc) throws PedidoCasamentoException;

    public void updatePedidoCasamento(PedidoCasamentoBean pc) throws PedidoCasamentoException;

    public PedidoCasamentoBean getPedidoCasamentoById(int idPedido) throws PedidoCasamentoException;

    public ArrayList<PedidoCasamentoBean> getAllPedidosCasamento() throws PedidoCasamentoException;
}
