
package com.ufpr.tads.dac.beans;


public class CorCabeloBean {
    private int idCorCabelo;
    private String cor;

    public CorCabeloBean() {
    }

    public CorCabeloBean(int idCorCabelo) {
        this.idCorCabelo = idCorCabelo;
    }

    public CorCabeloBean(int idCorCabelo, String cor) {
        this.idCorCabelo = idCorCabelo;
        this.cor = cor;
    }

    public int getIdCorCabelo() {
        return idCorCabelo;
    }

    public String getCor() {
        return cor;
    }

    public void setIdCorCabelo(int idCorCabelo) {
        this.idCorCabelo = idCorCabelo;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
    
    
}
