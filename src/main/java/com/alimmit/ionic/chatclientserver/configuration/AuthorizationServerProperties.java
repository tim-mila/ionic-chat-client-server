package com.alimmit.ionic.chatclientserver.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.net.URL;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "auth.client")
public class AuthorizationServerProperties {

    private String accessTokenUri;
    private String checkTokenUri;
    private String clientId;
    private String clientSecret;
    private String tokenSigningKey;

    public String getAccessTokenUri() {
        return accessTokenUri;
    }

    public void setAccessTokenUri(final String accessTokenUri) {
        this.accessTokenUri = accessTokenUri;
    }

    public String getCheckTokenUri() {
        return checkTokenUri;
    }

    public void setCheckTokenUri(final String checkTokenUri) {
        this.checkTokenUri = checkTokenUri;
    }

    String getClientId() {
        return clientId;
    }

    public void setClientId(final String clientId) {
        if (this.clientId != null) {
            return;
        }
        this.clientId = clientId;
    }

    String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(final String clientSecret) {
        if (this.clientSecret != null) {
            return;
        }
        this.clientSecret = clientSecret;
    }

    String getTokenSigningKey() {
        return tokenSigningKey;
    }

    public void setTokenSigningKey(final String tokenSigningKey) {
        if (this.tokenSigningKey != null) {
            return;
        }
        this.tokenSigningKey = tokenSigningKey;
    }
}
