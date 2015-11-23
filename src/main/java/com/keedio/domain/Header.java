package com.keedio.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Luca Rosellini <lrosellini@keedio.com> on 23/11/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Header {
    private String status;
    private String scenario;
    private String name;
    private  String lexical;
    private HeaderToken properties;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Header{");
        sb.append("status='").append(status).append('\'');
        sb.append(", scenario='").append(scenario).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", lexical='").append(lexical).append('\'');
        sb.append(", properties=").append(properties);
        sb.append('}');
        return sb.toString();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLexical() {
        return lexical;
    }

    public void setLexical(String lexical) {
        this.lexical = lexical;
    }

    public HeaderToken getProperties() {
        return properties;
    }

    public void setProperties(HeaderToken properties) {
        this.properties = properties;
    }
}

