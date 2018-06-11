package com.ufpr.tads.dac.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ClienteBean {

    private int clienteId;
    private String nome;
    private String cpf;
    private Date dataNasc;
    private String sexo;
    private int altura;
    private int idade;
    private String descricao;
    private CorPeleBean corPele;
    private CorCabeloBean corCabelo;
    private EnderecoBean endereco;
    private PreferenciaBean preferencias;
    private EscolaridadeBean escolaridade;

    public ClienteBean() {
    }

    public ClienteBean(int clienteId) {
        this.clienteId = clienteId;
    }

    public ClienteBean(String nome, String cpf, String sexo) {
        this.nome = nome;
        this.cpf = cpf;
        this.sexo = sexo;
    }

    public ClienteBean(int clienteId, String nome, String descricao) {
        this.clienteId = clienteId;
        this.nome = nome;
        this.descricao = descricao;
    }

    public ClienteBean(int clienteId, String nome, String sexo,Date dataNasc, int altura, CorPeleBean corPele, CorCabeloBean corCabelo) {
        this.clienteId = clienteId;
        this.nome = nome;
        this.sexo = sexo;
        this.altura = altura;
        this.corPele = corPele;
        this.corCabelo = corCabelo;
        this.dataNasc = dataNasc;
    }
    
    public ClienteBean(int clienteId, String nome, String sexo,Date dataNasc, int altura, CorPeleBean corPele, CorCabeloBean corCabelo, EscolaridadeBean es) {
        this.clienteId = clienteId;
        this.nome = nome;
        this.sexo = sexo;
        this.altura = altura;
        this.corPele = corPele;
        this.corCabelo = corCabelo;
        this.dataNasc = dataNasc;
        this.escolaridade = es;
    }

    public ClienteBean(int clienteId, String nome, String cpf, Date dataNasc, String sexo, String descricao, CorPeleBean corPele, CorCabeloBean corCabelo, EnderecoBean endereco, EscolaridadeBean escolaridade, PreferenciaBean preferencias, int alutra) {
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
        this.altura = alutra;
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

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
    
    public int getIdade() {
        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();
        birthDate.setTime(this.dataNasc);
        if (birthDate.after(today)) {
            throw new IllegalArgumentException("You don't exist yet");
        }
        int todayYear = today.get(Calendar.YEAR);
        int birthDateYear = birthDate.get(Calendar.YEAR);
        int todayDayOfYear = today.get(Calendar.DAY_OF_YEAR);
        int birthDateDayOfYear = birthDate.get(Calendar.DAY_OF_YEAR);
        int todayMonth = today.get(Calendar.MONTH);
        int birthDateMonth = birthDate.get(Calendar.MONTH);
        int todayDayOfMonth = today.get(Calendar.DAY_OF_MONTH);
        int birthDateDayOfMonth = birthDate.get(Calendar.DAY_OF_MONTH);
        int age = todayYear - birthDateYear;

        // If birth date is greater than todays date (after 2 days adjustment of leap year) then decrement age one year
        if ((birthDateDayOfYear - todayDayOfYear > 3) || (birthDateMonth > todayMonth)){
            age--;

        // If birth date and todays date are of same month and birth day of month is greater than todays day of month then decrement age
        } else if ((birthDateMonth == todayMonth) && (birthDateDayOfMonth > todayDayOfMonth)){
            age--;
        }
        return age;

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
    public String getDataNascS() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy"); 
        return  fmt.format(dataNasc);
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
