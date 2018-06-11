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

    public EncontroBean(Date dataEncontro, ClienteBean solicitado, EnderecoBean endereco) {
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

    public void setDataSolicitacao(String dataSolicitacao) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        try {
            data = format.parse(dataSolicitacao);
        } catch (ParseException e) {
            System.out.println("Data no formato errado");
            e.printStackTrace();
        }
        this.dataSolicitacao = data;
    }

    public void setDataResposta(Date dataResposta) {
        this.dataResposta = dataResposta;
    }

    public void setDataResposta(String dataResposta) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        try {
            data = format.parse(dataResposta);
        } catch (ParseException e) {
            System.out.println("Data no formato errado");
            e.printStackTrace();
        }
        this.dataResposta = data;
    }

    public void setAceito(boolean aceito) {
        this.aceito = aceito;
    }

    public Date getDataResposta() {
        return dataResposta;
    }

    public String getDataRespostaS() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        return fmt.format(dataResposta);
    }
    
    public boolean isAceito() {
        return aceito;
    }

    public boolean getAceito() {
        return aceito;
    }

    public void setDataEncontro(Date dataEncontro) {
        this.dataEncontro = dataEncontro;
    }

    public void setDataEncontro(String dataEncontro) {
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

    public String getDataSolicitacaoS() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        return fmt.format(dataSolicitacao);
    }

    public Date getDataEncontro() {
        return dataEncontro;
    }

    public String getDataEncontroS() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        return fmt.format(dataEncontro);
    }

    public ClienteBean getSolicitado() {
        return solicitado;
    }

    public EnderecoBean getEndereco() {
        return endereco;
    }

}
