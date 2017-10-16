package com.alimmit.ionic.chatclientserver.controller;

import com.alimmit.ionic.chatclientserver.bean.User;
import com.alimmit.ionic.chatclientserver.service.OAuthLoginService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private static final Log LOG = LogFactory.getLog(UserController.class);

    private final OAuthLoginService oAuthLoginService;

    public UserController(final OAuthLoginService oAuthLoginService) {
        this.oAuthLoginService = oAuthLoginService;
    }

    @PostMapping(Path.SIGN_UP)
    public ResponseEntity<OAuth2AccessToken> signup(@RequestBody final User user) {
        LOG.debug("signup::" + user.getUsername());
        return oAuthLoginService.signup(user);
    }

    @PostMapping(Path.LOGIN)
    public ResponseEntity<OAuth2AccessToken> login(@RequestBody final User user) {
        LOG.debug("login::" + user.getUsername());
        return oAuthLoginService.login(user);
    }
}
