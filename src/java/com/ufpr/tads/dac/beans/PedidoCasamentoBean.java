
package com.ufpr.tads.dac.beans;

import java.util.Date;


public class PedidoCasamentoBean {
    private int idPedido;
    private Date dataConfirmação;
    private Date dataSolicitação;
    private Date dataCasamento;
    private ClienteBean conjuge;
    private int nConvidados;
    private String padre;
    private String igreja;
    private String localLua;
    private String padrinho1;
    private String padrinho2;    
    private String madrinha1;    
    private String madrinha2;

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public void setDataConfirmação(Date dataConfirmação) {
        this.dataConfirmação = dataConfirmação;
    }

    public void setDataSolicitação(Date dataSolicitação) {
        this.dataSolicitação = dataSolicitação;
    }

    public void setDataCasamento(Date dataCasamento) {
        this.dataCasamento = dataCasamento;
    }

    public void setConjuge(ClienteBean conjuge) {
        this.conjuge = conjuge;
    }

    public void setnConvidados(int nConvidados) {
        this.nConvidados = nConvidados;
    }

    public void setPadre(String padre) {
        this.padre = padre;
    }

    public void setIgreja(String igreja) {
        this.igreja = igreja;
    }

    public void setLocalLua(String localLua) {
        this.localLua = localLua;
    }

    public void setPadrinho1(String padrinho1) {
        this.padrinho1 = padrinho1;
    }

    public void setPadrinho2(String padrinho2) {
        this.padrinho2 = padrinho2;
    }

    public void setMadrinha1(String madrinha1) {
        this.madrinha1 = madrinha1;
    }

    public void setMadrinha2(String madrinha2) {
        this.madrinha2 = madrinha2;
    }

    public Date getDataConfirmação() {
        return dataConfirmação;
    }

    public Date getDataSolicitação() {
        return dataSolicitação;
    }

    public Date getDataCasamento() {
        return dataCasamento;
    }

    public ClienteBean getConjuge() {
        return conjuge;
    }

    public int getnConvidados() {
        return nConvidados;
    }

    public String getPadre() {
        return padre;
    }

    public String getIgreja() {
        return igreja;
    }

    public String getLocalLua() {
        return localLua;
    }

    public String getPadrinho1() {
        return padrinho1;
    }

    public String getPadrinho2() {
        return padrinho2;
    }

    public String getMadrinha1() {
        return madrinha1;
    }

    public String getMadrinha2() {
        return madrinha2;
    }
    
}