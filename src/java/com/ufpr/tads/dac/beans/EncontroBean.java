
package com.ufpr.tads.dac.beans;

import java.util.Date;

public class EncontroBean {
    
    private int encontroId;
    private Date dataSolicitacao;
    private Date dataEncontro;
    private Date dataResposta;
    private ClienteBean solicitado;
    private String endereco;
    private Boolean aceito;

    public EncontroBean() {        
    }

    public EncontroBean(int encontroId, Date dataSolicitacao, Date dataEncontro, Date dataResposta, ClienteBean solicitado, String endereco, Boolean aceito) {
        this.encontroId = encontroId;
        this.dataSolicitacao = dataSolicitacao;
        this.dataEncontro = dataEncontro;
        this.dataResposta = dataResposta;
        this.solicitado = solicitado;
        this.endereco = endereco;
        this.aceito = aceito;
    }
    
        
    
    public int getEncontroId() {
        return encontroId;
    }

    public void setEncontroId(int encontroId) {
        this.encontroId = encontroId;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public void setDataResposta(Date dataResposta) {
        this.dataResposta = dataResposta;
    }

    public void setAceito(Boolean aceito) {
        this.aceito = aceito;
    }

    public Date getDataResposta() {
        return dataResposta;
    }

    public Boolean isAceito() {
        return aceito;
    }

    public void setDataEncontro(Date dataEncontro) {
        this.dataEncontro = dataEncontro;
    }
    
    public void setSolicitado(ClienteBean solicitado) {
        this.solicitado = solicitado;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }
    public Date getDataEncontro() {
        return dataEncontro;
    }

    public ClienteBean getSolicitado() {
        return solicitado;
    }

    public String getEndereco() {
        return endereco;
    }
    
    
    
    
    
}
