
package com.ufpr.tads.dac.beans;

public class CidadeBean {
    private int idCidade;
    private String nome;

    public CidadeBean() {
    }

    public CidadeBean(int idCidade) {
        this.idCidade = idCidade;
    }

    public CidadeBean(int idCidade, String nome) {
        this.idCidade = idCidade;
        this.nome = nome;
    }

    public void setIdCidade(int idCidade) {
        this.idCidade = idCidade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdCidade() {
        return idCidade;
    }

    public String getNome() {
        return nome;
    }
}
