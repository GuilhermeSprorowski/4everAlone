
package com.ufpr.tads.dac.facade;

import com.ufpr.tads.dac.beans.ConviteBean;
import com.ufpr.tads.dac.dao.impl.ConviteDAOimpl;
import com.ufpr.tads.dac.exceptions.ConviteException;
import java.util.ArrayList;


public class ConviteFacade {
    static ConviteDAOimpl ConviteDAO = new ConviteDAOimpl();
    public static ArrayList<ConviteBean> getAllConvites(int clienteId) throws ConviteException{
        return ConviteDAO.getAllConvites(clienteId);
    }
}
