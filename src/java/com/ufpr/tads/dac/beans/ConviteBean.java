
package com.ufpr.tads.dac.beans;

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

    public boolean isConfirmado() {
        return confirmado;
    }

    public Date getDataEnvio() {
        return dataEnvio;
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

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public void setFesta(FestaBean festa) {
        this.festa = festa;
    }
    
    
    
}
