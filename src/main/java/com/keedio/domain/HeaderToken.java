package com.keedio.domain;

/**
 * Created by Luca Rosellini <lrosellini@keedio.com> on 23/11/15.
 */
public class HeaderToken extends Token {
    private String requestid;

    private String ERROR;
    private String NOSPEECH;
    private String FALSERECO;

    public String getRequestid() {
        return requestid;
    }

    public void setRequestid(String requestid) {
        this.requestid = requestid;
    }

    public String getERROR() {
        return ERROR;
    }

    public void setERROR(String ERROR) {
        this.ERROR = ERROR;
    }

    public String getNOSPEECH() {
        return NOSPEECH;
    }

    public void setNOSPEECH(String NOSPEECH) {
        this.NOSPEECH = NOSPEECH;
    }

    public String getFALSERECO() {
        return FALSERECO;
    }

    public void setFALSERECO(String FALSERECO) {
        this.FALSERECO = FALSERECO;
    }
}
