package com.ufpr.tads.dac.beans;

public class UserBean {

    private String email;
    private String nome;
    private int userId;

    public UserBean() {
    }

    public UserBean(String email, String nome,  int userId) {
        this.email = email;
        this.nome = nome;
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getUserId() {
        return userId;
    }
}
