package com.ufpr.tads.dac.beans;

public class UserBean {

    private String email;
    private String nome;
    private int clienteId;
    private int funcionarioId;
    private Boolean cliente;
    private Boolean adm;
    private Boolean funcionario;
    

    public UserBean() {
    }

    public UserBean(String email, String nome,  int clienteId) {
        this.email = email;
        this.nome = nome;
        this.clienteId = clienteId;
    }

    public Boolean isCliente() {
        return cliente;
    }
    
    public Boolean getCliente() {
        return cliente;
    }

    public void setCliente(Boolean cliente) {
        this.cliente = cliente;
    }

    public Boolean isAdm() {
        return adm;
    }
    
    public Boolean getAdm() {
        return adm;
    }

    public void setAdm(Boolean adm) {
        this.adm = adm;
    }
    
    public Boolean getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Boolean funcionario) {
        this.funcionario = funcionario;
    }
    
    
    public void setEmail(String email) {
        this.email = email;
    }
    public void setFuncionarioId(int id) {
        this.funcionarioId = id;
    }

    public int getFuncionarioId() {
        return funcionarioId;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public void setclienteId(int clienteId) {
        this.clienteId = clienteId;
    }
    public int getClienteId() {
        return clienteId;
    }
}
