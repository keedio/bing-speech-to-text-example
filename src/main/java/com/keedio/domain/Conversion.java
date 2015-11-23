package com.keedio.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Data structure mapping the JSON returned from Microsoft Speech-to-Text Conversion API.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Conversion {
    private String version;
    private Header header;
    private List<Result> results;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Conversion{");
        sb.append("version='").append(version).append('\'');
        sb.append(", header=").append(header);
        sb.append(", results=").append(results);
        sb.append('}');
        return sb.toString();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
