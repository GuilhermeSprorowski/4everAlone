package com.ufpr.tads.dac.beans;

import java.util.Date;

public class ClienteBean {

    private int clienteId;
    private String nome;
    private String cpf;
    private Date dataNasc;
    private String sexo;
    private String descricao;
    private String corPele;
    private String corCabelo;
    private EnderecoBean endereco;

    public ClienteBean() {
    }

    public ClienteBean(int clienteId, String nome, String descricao) {
        this.clienteId = clienteId;
        this.nome = nome;
        this.descricao = descricao;
    }
    public ClienteBean(int clienteId, String nome, String cpf, Date dataNasc, String sexo, String descricao, String corPele, String corCabelo, EnderecoBean endereco) {
        this.clienteId = clienteId;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.sexo = sexo;
        this.descricao = descricao;
        this.corPele = corPele;
        this.corCabelo = corCabelo;
        this.endereco = endereco;
    }
    
    
    public String getCpf() {
        return cpf;
    }

    public int getClienteId() {
        return clienteId;
    }

    public String getNome() {
        return nome;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEndereco(EnderecoBean endereco) {
        this.endereco = endereco;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCorPele(String corPele) {
        this.corPele = corPele;
    }

    public void setCorCabelo(String corCabelo) {
        this.corCabelo = corCabelo;
    }

    public EnderecoBean getEndereco() {
        return endereco;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public String getSexo() {
        return sexo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCorPele() {
        return corPele;
    }

    public String getCorCabelo() {
        return corCabelo;
    }

}
