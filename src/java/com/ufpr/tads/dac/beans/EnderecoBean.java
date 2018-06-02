
package com.ufpr.tads.dac.beans;

public class EnderecoBean {
    
    private int enderecoId;
    private String rua;
    private int cidadeId;
    private String cidade;
    private String uF;

    public EnderecoBean(int enderecoId, String rua, String cidade, String uF) {
        this.enderecoId = enderecoId;
        this.rua = rua;
        this.cidade = cidade;
        this.uF = uF;
    }
    
    public EnderecoBean() {
    }

    public void setCidadeId(int cidadeId) {
        this.cidadeId = cidadeId;
    }

    public int getCidadeId() {
        return cidadeId;
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
