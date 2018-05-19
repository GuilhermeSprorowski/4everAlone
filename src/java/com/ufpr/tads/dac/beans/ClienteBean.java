
package com.ufpr.tads.dac.beans;

public class ClienteBean {
    private String cpf;
    private String endereco;
    private String dataNasc;
    private char sexo;
    private String descricao;
    private String corPele;    
    private String corCabelo;

    public ClienteBean(){
    }
    
            
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public void setSexo(char sexo) {
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

    public String getEndereco() {
        return endereco;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public char getSexo() {
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
