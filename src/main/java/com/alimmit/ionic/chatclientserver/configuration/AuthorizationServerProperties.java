package com.alimmit.ionic.chatclientserver.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.net.URL;

@EnableConfigurationProperties
@ConfigurationProperties(prefix = "auth.server")
@Configuration
public class AuthorizationServerProperties {

    private URL url;
    private String clientId;
    private String clientSecret;
    private String tokenSigningKey;

    URL getUrl() {
        return url;
    }

    public void setUrl(final URL url) {
        if (this.url != null) {
            return;
        }
        this.url = url;
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
