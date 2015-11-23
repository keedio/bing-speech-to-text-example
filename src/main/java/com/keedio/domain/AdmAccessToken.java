package com.keedio.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Data Structure mapping the json returned from Microsoft's Speech-to-text authentication API.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdmAccessToken{
    private String access_token;
    private String token_type;
    private String expires_in;
    private String scope;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AdmAccessToken{");
        sb.append("access_token='").append(access_token).append('\'');
        sb.append(", token_type='").append(token_type).append('\'');
        sb.append(", expires_in='").append(expires_in).append('\'');
        sb.append(", scope='").append(scope).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
