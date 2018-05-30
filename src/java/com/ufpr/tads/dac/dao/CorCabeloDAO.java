
package com.ufpr.tads.dac.dao;

import com.ufpr.tads.dac.beans.CorCabeloBean;
import com.ufpr.tads.dac.exceptions.CorCabeloException;
import java.util.ArrayList;

public interface CorCabeloDAO {
    
    public ArrayList<CorCabeloBean> getAllCoresCabelo()throws CorCabeloException;
}
