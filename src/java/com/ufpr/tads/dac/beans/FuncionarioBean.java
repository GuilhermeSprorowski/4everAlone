package com.ufpr.tads.dac.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FuncionarioBean {

    private int idFuncionario;
    private String nome;
    private String cpf;
    private Double salario;
    private Date dataNasc;
    private Date dataCadastro;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public FuncionarioBean() {
    }

    public FuncionarioBean(int idFuncionario, String nome, Double salario, Date dataNasc, Date dataCadastro) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.salario = salario;
        this.dataNasc = dataNasc;
        this.dataCadastro = dataCadastro;
    }
    
    public FuncionarioBean(int idFuncionario, String nome, Double salario, Date dataNasc, Date dataCadastro, String cpf) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.salario = salario;
        this.dataNasc = dataNasc;
        this.dataCadastro = dataCadastro;
        this.cpf = cpf;
    }

    public FuncionarioBean(int idFuncionario, String nome) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public String getDataNascS() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        return fmt.format(dataNasc);
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        try {
            data = format.parse(dataNasc);
        } catch (ParseException e) {
            System.out.println("Data no formato errado");
            e.printStackTrace();
        }
        this.dataNasc = data;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public String getDataCadastroS() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        return fmt.format(dataCadastro);
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        try {
            data = format.parse(dataCadastro);
        } catch (ParseException e) {
            System.out.println("Data no formato errado");
            e.printStackTrace();
        }
        this.dataCadastro = data;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public Double getSalario() {
        return salario;
    }
}
