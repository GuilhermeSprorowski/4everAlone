
package com.ufpr.tads.dac.beans;

public class EnderecoBean {
    
    private int enderecoId;
    private String cidade;
    private String uF;
    private String rua;

    public EnderecoBean() {
    }

    public void setEnderecoId(int enderecoId) {
        this.enderecoId = enderecoId;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setuF(String uF) {
        this.uF = uF;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getEnderecoId() {
        return enderecoId;
    }

    public String getCidade() {
        return cidade;
    }

    public String getuF() {
        return uF;
    }

    public String getRua() {
        return rua;
    }
}
