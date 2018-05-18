
package com.ufpr.tads.dac.beans;

public class UserBean {
    private String email;
    private String nome;
    private String senha;

    public UserBean(){}
    
    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
