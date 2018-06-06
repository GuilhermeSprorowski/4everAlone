package com.ufpr.tads.dac.beans;

public class EnderecoBean {

    private int enderecoId;
    private String rua;
    private CidadeBean cidade;
    private EstadoBean estado;

    public EnderecoBean(int enderecoId, String rua, CidadeBean cidade, EstadoBean estado) {
        this.enderecoId = enderecoId;
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
    }

    public EnderecoBean(int enderecoId, String rua, CidadeBean cidade) {
        this.enderecoId = enderecoId;
        this.rua = rua;
        this.cidade = cidade;
    }

    public EnderecoBean() {
    }

    public CidadeBean getCidade() {
        return cidade;
    }

    public void setCidade(CidadeBean cidade) {
        this.cidade = cidade;
    }

    public EstadoBean getEstado() {
        return estado;
    }

    public void setEstado(EstadoBean estado) {
        this.estado = estado;
    }

    public void setEnderecoId(int enderecoId) {
        this.enderecoId = enderecoId;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getEnderecoId() {
        return enderecoId;
    }

    public String getRua() {
        return rua;
    }
}
