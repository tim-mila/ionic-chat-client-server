package com.alimmit.ionic.chatclientserver.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping(Path.SIGNUP)
    public String signup() {
        return "signup";
    }

    @PostMapping(Path.LOGIN)
    public String login() {
        return "login";
    }

}
