package com.ufpr.tads.dac.beans;

public class EnderecoBean {

    private int enderecoId;
    private String rua;
    private CidadeBean cidade;
    private EstadoBean estado;
    private String enderecoString;
    private String nomeLocal;

    public EnderecoBean(int enderecoId, String rua, CidadeBean cidade, EstadoBean estado, String nomeLocal) {
        this.enderecoId = enderecoId;
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
        this.nomeLocal = nomeLocal;
    }

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

    public EnderecoBean(String enderecoString) {
        this.enderecoString = enderecoString;
    }

    public EnderecoBean(int enderecoId) {
        this.enderecoId = enderecoId;
    }

    public EnderecoBean() {
    }

    public String getEnderecoString() {
        return enderecoString;
    }

    public String getNomeLocal() {
        return nomeLocal;
    }

    public void setNomeLocal(String nomeLocal) {
        this.nomeLocal = nomeLocal;
    }

    public void setEnderecoString(String enderecoString) {
        this.enderecoString = enderecoString;
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
