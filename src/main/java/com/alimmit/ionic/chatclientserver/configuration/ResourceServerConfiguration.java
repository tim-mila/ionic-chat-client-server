package com.alimmit.ionic.chatclientserver.configuration;

import com.alimmit.ionic.chatclientserver.controller.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableResourceServer
@EnableOAuth2Client
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Autowired
    private OAuth2ClientContext oauth2ClientContext;

    @Autowired
    private AuthorizationServerProperties authorizationServerProperties;

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(Path.SIGNUP, Path.LOGIN).permitAll()
                .anyRequest().authenticated()
//                .and().addFilterBefore(oauthFilter(), BasicAuthenticationFilter.class).sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable();
    }

//    private Filter oauthFilter() {
//        final OAuth2ClientAuthenticationProcessingFilter oauthProcessingFilter = new OAuth2ClientAuthenticationProcessingFilter("/foo");
//        oauthProcessingFilter.setRestTemplate(oAuth2RestTemplate());
//        oauthProcessingFilter.setTokenServices(remoteTokenServices(authorizationServerProperties));
//        return oauthProcessingFilter;
//    }

    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate() {
        return new OAuth2RestTemplate(authorizationDetails(), oauth2ClientContext);
    }

    @Bean
    @ConfigurationProperties("auth.client")
    public ResourceOwnerPasswordResourceDetails authorizationDetails() {
        return new ResourceOwnerPasswordResourceDetails();
    }

    @Bean
    public RemoteTokenServices remoteTokenServices(final AuthorizationServerProperties authorizationServerProperties) {
        final RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setCheckTokenEndpointUrl(authorizationServerProperties.getCheckTokenUri());
        remoteTokenServices.setClientId(authorizationServerProperties.getClientId());
        remoteTokenServices.setClientSecret(authorizationServerProperties.getClientSecret());
        remoteTokenServices.setAccessTokenConverter(accessTokenConverter(authorizationServerProperties));
        return remoteTokenServices;
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter(final AuthorizationServerProperties authorizationServerProperties) {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(authorizationServerProperties.getTokenSigningKey());
        return converter;
    }
}
