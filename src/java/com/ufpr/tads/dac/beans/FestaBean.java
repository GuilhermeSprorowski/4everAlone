
package com.ufpr.tads.dac.beans;

import java.util.Date;


public class FestaBean {
    private int idFesta;
    private EnderecoBean endereco;
    private int vagas;
    private String descricao;
    private String tema;


    private Date datahora;
    private FuncionarioBean funcionarioResponsavel;    
    private String enderecoString;

    public FestaBean() {
    }

    public FestaBean(int idFesta, int vagas, String descricao, String tema, Date datahora, FuncionarioBean funcionarioResponsavel, String enderecoString) {
        this.idFesta = idFesta;
        this.vagas = vagas;
        this.tema = tema;
        this.descricao = descricao;
        this.datahora = datahora;
        this.funcionarioResponsavel = funcionarioResponsavel;
        this.enderecoString = enderecoString;
    }
    
    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public int getIdFesta() {
        return idFesta;
    }

    public EnderecoBean getEndereco() {
        return endereco;
    }

    public void setEnderecoString(String enderecoString) {
        this.enderecoString = enderecoString;
    }

    public String getEnderecoString() {
        return enderecoString;
    }

    public void setIdFesta(int idFesta) {
        this.idFesta = idFesta;
    }

    public void setEndereco(EnderecoBean endereco) {
        this.endereco = endereco;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDatahora(Date datahora) {
        this.datahora = datahora;
    }

    public void setFuncionarioResponsavel(FuncionarioBean funcionarioResponsavel) {
        this.funcionarioResponsavel = funcionarioResponsavel;
    }

    public int getVagas() {
        return vagas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Date getDatahora() {
        return datahora;
    }

    public FuncionarioBean getFuncionarioResponsavel() {
        return funcionarioResponsavel;
    }
    
    
}
