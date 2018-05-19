
package com.ufpr.tads.dac.beans;

import java.util.Date;

public class EncontroBean {
    
    private int encontroId;
    private Date dataSolicitacao;
    private String local;
    private Date dataEncontro;
    private Date dataRecusado;
    private ClienteBean solicitado;
    private EnderecoBean endereco;

    public int getEncontroId() {
        return encontroId;
    }

    public void setEncontroId(int encontroId) {
        this.encontroId = encontroId;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setDataEncontro(Date dataEncontro) {
        this.dataEncontro = dataEncontro;
    }

    public void setDataRecusado(Date dataRecusado) {
        this.dataRecusado = dataRecusado;
    }

    public void setSolicitado(ClienteBean solicitado) {
        this.solicitado = solicitado;
    }

    public void setEndereco(EnderecoBean endereco) {
        this.endereco = endereco;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public String getLocal() {
        return local;
    }

    public Date getDataEncontro() {
        return dataEncontro;
    }

    public Date getDataRecusado() {
        return dataRecusado;
    }

    public ClienteBean getSolicitado() {
        return solicitado;
    }

    public EnderecoBean getEndereco() {
        return endereco;
    }
    
    
    
    
    
}
