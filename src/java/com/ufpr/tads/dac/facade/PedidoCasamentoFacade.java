package com.ufpr.tads.dac.facade;

import com.ufpr.tads.dac.beans.PedidoCasamentoBean;
import com.ufpr.tads.dac.dao.impl.PedidoCasamentoDAOimpl;
import com.ufpr.tads.dac.exceptions.PedidoCasamentoException;

public class PedidoCasamentoFacade {
    static PedidoCasamentoDAOimpl PedidoCasamentoDAO = new PedidoCasamentoDAOimpl();
    public static int setPedidoCasamento(PedidoCasamentoBean pc)throws PedidoCasamentoException{
        return PedidoCasamentoDAO.setPedidoCasamento(pc);
    }
}
