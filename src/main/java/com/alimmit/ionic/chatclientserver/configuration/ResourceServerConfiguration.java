package com.alimmit.ionic.chatclientserver.configuration;

import com.alimmit.ionic.chatclientserver.controller.Path;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(Path.SIGNUP, Path.LOGIN, WebSocketSecurityConfiguration.PATH).permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().cors()
                .and().csrf().disable()
                .logout().logoutUrl("/api/v1/user/logout");
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
