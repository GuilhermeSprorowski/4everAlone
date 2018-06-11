package com.ufpr.tads.dac.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConviteBean {

    private int idConvite;
    private Date dataResposta;
    private boolean confirmado;
    private Date dataEnvio;
    private FestaBean festa;

    public ConviteBean() {
    }

    public ConviteBean(int idConvite, Date dataResposta, boolean confirmado, Date dataEnvio, FestaBean festa) {
        this.idConvite = idConvite;
        this.dataResposta = dataResposta;
        this.confirmado = confirmado;
        this.dataEnvio = dataEnvio;
        this.festa = festa;
    }

    public int getIdConvite() {
        return idConvite;
    }

    public Date getDataResposta() {
        return dataResposta;
    }

    public String getDataRespostaS() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return fmt.format(dataResposta);
    }

    public boolean isConfirmado() {
        return confirmado;
    }
    
    public boolean getConfirmado() {
        return confirmado;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public String getDataEnvioS() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return fmt.format(dataEnvio);
    }

    public FestaBean getFesta() {
        return festa;
    }

    public void setIdConvite(int idConvite) {
        this.idConvite = idConvite;
    }

    public void setDataResposta(Date dataResposta) {
        this.dataResposta = dataResposta;
    }

    public void setDataResposta(String dataResposta) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date data = null;
        try {
            data = format.parse(dataResposta);
        } catch (ParseException e) {
            System.out.println("Data no formato errado");
            e.printStackTrace();
        }
        this.dataResposta = data;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public void setDataEnvio(String dataEnvio) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date data = null;
        try {
            data = format.parse(dataEnvio);
        } catch (ParseException e) {
            System.out.println("Data no formato errado");
            e.printStackTrace();
        }
        this.dataEnvio = data;
    }

    public void setFesta(FestaBean festa) {
        this.festa = festa;
    }

}
