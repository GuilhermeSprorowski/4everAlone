
package com.ufpr.tads.dac.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EncontroBean {
    
    private int encontroId;
    private Date dataSolicitacao;
    private Date dataEncontro;
    private Date dataResposta;
    private ClienteBean solicitado;
    private EnderecoBean endereco;
    private boolean aceito;

    public EncontroBean() {        
    }

    public EncontroBean(int encontroId, boolean aceito) {
        this.encontroId = encontroId;
        this.aceito = aceito;
    }

    public EncontroBean(String dataEncontro, ClienteBean solicitado, EnderecoBean endereco) {
        this.setDataEncontro(dataEncontro);
        this.solicitado = solicitado;
        this.endereco = endereco;
    }

    public EncontroBean(int encontroId, Date dataSolicitacao, Date dataEncontro, Date dataResposta, ClienteBean solicitado, EnderecoBean endereco, boolean aceito) {
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

    public void setAceito(boolean aceito) {
        this.aceito = aceito;
    }

    public Date getDataResposta() {
        return dataResposta;
    }

    public boolean isAceito() {
        return aceito;
    }

    public void setDataEncontro(Date dataEncontro) {
        this.dataEncontro = dataEncontro;
    }
    public void setDataEncontro(String dataEncontro){
         SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        try {
            data = format.parse(dataEncontro);
        } catch (ParseException e) {
            System.out.println("Data no formato errado");
            e.printStackTrace();
        }
         this.dataEncontro = data;
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
    public Date getDataEncontro() {
        return dataEncontro;
    }

    public ClienteBean getSolicitado() {
        return solicitado;
    }

    public EnderecoBean getEndereco() {
        return endereco;
    }
    
    
    
    
    
}
