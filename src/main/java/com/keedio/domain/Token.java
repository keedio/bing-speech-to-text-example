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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Token{");
        sb.append("HIGHCONF='").append(HIGHCONF).append('\'');
        sb.append(", MIDCONF='").append(MIDCONF).append('\'');
        sb.append(", LOWCONF='").append(LOWCONF).append('\'');
        sb.append('}');
        return sb.toString();
    }

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
