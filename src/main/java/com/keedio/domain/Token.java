package com.keedio.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Luca Rosellini <lrosellini@keedio.com> on 23/11/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Token {

    private String HIGHCONF;
    private String MIDCONF;
    private String LOWCONF;

    public String getHIGHCONF() {
        return HIGHCONF;
    }

    public void setHIGHCONF(String HIGHCONF) {
        this.HIGHCONF = HIGHCONF;
    }

    public String getMIDCONF() {
        return MIDCONF;
    }

    public void setMIDCONF(String MIDCONF) {
        this.MIDCONF = MIDCONF;
    }

    public String getLOWCONF() {
        return LOWCONF;
    }

    public void setLOWCONF(String LOWCONF) {
        this.LOWCONF = LOWCONF;
    }
}
