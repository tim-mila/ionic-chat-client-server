package com.alimmit.ionic.chatclientserver.service;

import com.alimmit.ionic.chatclientserver.bean.User;
import com.alimmit.ionic.chatclientserver.configuration.AuthorizationServerProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class OAuthLoginServiceImpl implements OAuthLoginService {

    private static final Log LOG = LogFactory.getLog(OAuthLoginServiceImpl.class);

    private final AuthorizationServerProperties authorizationServerProperties;

    public OAuthLoginServiceImpl(final AuthorizationServerProperties authorizationServerProperties) {
        this.authorizationServerProperties = authorizationServerProperties;
    }

    @Override
    public ResponseEntity<OAuth2AccessToken> signup(final User user) {
        final RestTemplate template = restTemplate();

        final String authorizationHeader = "Basic " + org.apache.commons.codec.binary.Base64.encodeBase64String("clientIdPassword:secret".getBytes());
        final MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Authorization", authorizationHeader);

        template.exchange(authorizationServerProperties.getSignUpUri(), HttpMethod.POST, new HttpEntity<User>(user), User.class).getBody();
        return login(user);
    }

    @Override
    public ResponseEntity<OAuth2AccessToken> login(final User user) {

        final RestTemplate template = restTemplate();

        final String authorizationHeader = "Basic " + org.apache.commons.codec.binary.Base64.encodeBase64String("clientIdPassword:secret".getBytes());
        final MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Authorization", authorizationHeader);

        final MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("clientId", "clientIdPassword");
        body.add("username", user.getUsername());
        body.add("password", user.getPassword());

        final HttpEntity entity = new HttpEntity<>(body, headers);
        return template.exchange(authorizationServerProperties.getAccessTokenUri(), HttpMethod.POST, entity, OAuth2AccessToken.class);
    }

    private RestTemplate restTemplate() {
        final RestTemplate template = new RestTemplate();
        final HttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        final HttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        final HttpMessageConverter jacksonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        final List<HttpMessageConverter<?>> converters = Arrays.asList(formHttpMessageConverter, stringHttpMessageConverter, jacksonHttpMessageConverter);
        template.setMessageConverters(converters);
        return template;
    }
}
