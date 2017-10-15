package com.alimmit.ionic.chatclientserver.service;

import com.alimmit.ionic.chatclientserver.bean.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

public interface OAuthLoginService {

    ResponseEntity<OAuth2AccessToken> login(User user);

    ResponseEntity<OAuth2AccessToken> signup(User user);
}
