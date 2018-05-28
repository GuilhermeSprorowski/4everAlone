
package com.ufpr.tads.dac.beans;


public class PreferenciaBean {
    private int idPreferencia;
    private String sexo;
    private int[] idade;
    private int[] altura;
    private CorCabeloBean corCabelo;
    private CorPeleBean corPele;

    public PreferenciaBean() {
    }

    public int getIdPreferencia() {
        return idPreferencia;
    }

    public String getSexo() {
        return sexo;
    }

    public int[] getIdade() {
        return idade;
    }

    public int[] getAltura() {
        return altura;
    }

    public CorCabeloBean getCorCabelo() {
        return corCabelo;
    }

    public CorPeleBean getCorPele() {
        return corPele;
    }

    public void setIdPreferencia(int idPreferencia) {
        this.idPreferencia = idPreferencia;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setIdade(int[] idade) {
        this.idade = idade;
    }

    public void setAltura(int[] altura) {
        this.altura = altura;
    }

    public void setCorCabelo(CorCabeloBean corCabelo) {
        this.corCabelo = corCabelo;
    }

    public void setCorPele(CorPeleBean corPele) {
        this.corPele = corPele;
    }
    
    
}
