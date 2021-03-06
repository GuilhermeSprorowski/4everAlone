package com.ufpr.tads.dac.beans;

public class EscolaridadeBean {

    private int idEscolaridade;
    private String descricao;

    public EscolaridadeBean() {

    }

    public EscolaridadeBean(int idEscolaridade) {
        this.idEscolaridade = idEscolaridade;
    }

    public EscolaridadeBean(int idEscolaridade, String descricao) {
        this.idEscolaridade = idEscolaridade;
        this.descricao = descricao;
    }    

    public int getIdEscolaridade() {
        return idEscolaridade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setIdEscolaridade(int idEscolaridade) {
        this.idEscolaridade = idEscolaridade;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
