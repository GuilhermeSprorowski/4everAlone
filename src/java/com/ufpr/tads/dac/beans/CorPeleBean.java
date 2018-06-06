
package com.ufpr.tads.dac.beans;

public class CorPeleBean {
    private int idCorPele;
    private String cor;

    public CorPeleBean() {
    }

    public CorPeleBean(int idCorPele) {
        this.idCorPele = idCorPele;
    }

    public CorPeleBean(int idCorPele, String cor) {
        this.idCorPele = idCorPele;
        this.cor = cor;
    }

    public int getIdCorPele() {
        return idCorPele;
    }

    public String getCor() {
        return cor;
    }

    public void setIdCorPele(int idCorPele) {
        this.idCorPele = idCorPele;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
    
    
}
