package com.ufpr.tads.dac.beans;

import java.util.Date;

public class ClienteBean {

    private int clienteId;
    private String nome;
    private String cpf;
    private Date dataNasc;
    private String sexo;
    private String descricao;
    private CorPeleBean corPele;
    private CorCabeloBean corCabelo;
    private EnderecoBean endereco;
    private PreferenciaBean preferencias;
    private EscolaridadeBean escolaridade;

    public ClienteBean() {
    }

    public ClienteBean(int clienteId, String nome, String descricao) {
        this.clienteId = clienteId;
        this.nome = nome;
        this.descricao = descricao;
    }

    public ClienteBean(int clienteId, String nome, String cpf, Date dataNasc, String sexo, String descricao, CorPeleBean corPele, CorCabeloBean corCabelo, EnderecoBean endereco, EscolaridadeBean escolaridade, PreferenciaBean preferencias) {
        this.clienteId = clienteId;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.sexo = sexo;
        this.descricao = descricao;
        this.corPele = corPele;
        this.corCabelo = corCabelo;
        this.endereco = endereco;
        this.escolaridade = escolaridade;
        this.preferencias = preferencias;
    }

    public EscolaridadeBean getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(EscolaridadeBean escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setPreferencias(PreferenciaBean preferencias) {
        this.preferencias = preferencias;
    }

    public PreferenciaBean getPreferencias() {
        return preferencias;
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

    public void setCorPele(CorPeleBean corPele) {
        this.corPele = corPele;
    }

    public void setCorCabelo(CorCabeloBean corCabelo) {
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

    public CorPeleBean getCorPele() {
        return corPele;
    }

    public CorCabeloBean getCorCabelo() {
        return corCabelo;
    }

}
