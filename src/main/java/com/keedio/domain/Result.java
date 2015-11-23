package com.keedio.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Luca Rosellini <lrosellini@keedio.com> on 23/11/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    private String scenario;
    private String name;
    private String lexical;
    private Float confidence;
    private String pronunciation;
    private Token properties;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Result{");
        sb.append("scenario='").append(scenario).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", lexical='").append(lexical).append('\'');
        sb.append(", confidence=").append(confidence);
        sb.append(", pronunciation='").append(pronunciation).append('\'');
        sb.append(", properties=").append(properties);
        sb.append('}');
        return sb.toString();
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

    public Float getConfidence() {
        return confidence;
    }

    public void setConfidence(Float confidence) {
        this.confidence = confidence;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public Token getProperties() {
        return properties;
    }

    public void setProperties(Token properties) {
        this.properties = properties;
    }
}
