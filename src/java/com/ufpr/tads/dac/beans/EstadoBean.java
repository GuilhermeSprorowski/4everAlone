
package com.ufpr.tads.dac.beans;

public class EstadoBean {
    private String nome;
    private String sigla;
    private int idEstado;

    public EstadoBean() {
    }

    public EstadoBean(String nome, String sigla, int idEstado) {
        this.nome = nome;
        this.sigla = sigla;
        this.idEstado = idEstado;
    }

    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }
    
    
}
