package com.alimmit.ionic.chatclientserver.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooController {

    private static final Log LOG = LogFactory.getLog(FooController.class);

    @GetMapping("/foo")
    public String helloFoo() {
        LOG.debug("helloFoo");
        return "foo";
    }
}
