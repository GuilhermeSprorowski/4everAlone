
package com.ufpr.tads.dac.dao;

import com.ufpr.tads.dac.beans.CorPeleBean;
import com.ufpr.tads.dac.exceptions.CorPeleException;
import java.util.ArrayList;


public interface CorPeleDAO {
    public ArrayList<CorPeleBean> getAllCoresPele() throws CorPeleException;
}
