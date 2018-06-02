
package com.ufpr.tads.dac.beans;


public class PreferenciaBean {
    private int idPreferencia;
    private String sexo;
    private int[] idade;
    private int[] altura;
    private int corCabeloId;
    private int corPeleId;
    
    public PreferenciaBean(int id, String sexo, int idade[], int altura[], int corCabeloId, int corPeleId) {
        this.idPreferencia = id;
        this.idade = idade;
        this.altura = altura;
        this.sexo = sexo;
        this.corCabeloId = corCabeloId;
        this.corPeleId = corPeleId;
    }

    public int getIdPreferencia() {
        return idPreferencia;
    }

    public void setIdPreferencia(int idPreferencia) {
        this.idPreferencia = idPreferencia;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int[] getIdade() {
        return idade;
    }

    public void setIdade(int[] idade) {
        this.idade = idade;
    }

    public int[] getAltura() {
        return altura;
    }

    public void setAltura(int[] altura) {
        this.altura = altura;
    }

    public int getCorCabeloId() {
        return corCabeloId;
    }

    public void setCorCabeloId(int corCabeloId) {
        this.corCabeloId = corCabeloId;
    }

    public int getCorPeleId() {
        return corPeleId;
    }

    public void setCorPeleId(int corPeleId) {
        this.corPeleId = corPeleId;
    }
}
