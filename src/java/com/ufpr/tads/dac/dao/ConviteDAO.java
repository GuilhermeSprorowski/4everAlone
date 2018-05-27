
package com.ufpr.tads.dac.dao;

import com.ufpr.tads.dac.beans.ConviteBean;
import com.ufpr.tads.dac.exceptions.ConviteException;
import java.util.ArrayList;

public interface ConviteDAO {
    public void setNovoConvite(ConviteBean convite) throws ConviteException;
    public void updateConvite(ConviteBean convite)throws ConviteException;
    public void setResposta(boolean resposta)throws ConviteException;
    public ConviteBean getConviteById(int idConvite)throws ConviteException;
    public ArrayList<ConviteBean> getAllConvites(int clienteId)throws ConviteException;
}
