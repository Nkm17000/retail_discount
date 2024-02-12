package com.nkm.discount.demo.config.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationPreparedEvent> {

    private final AuthenticationService authenticationService;

    @Autowired
    public ApplicationStartup(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Autowired
    private Environment env;

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        // Read properties here
        String authTokenHeaderName = env.getProperty("auth.token.header.name");
        String authToken = env.getProperty("auth.token");

    }
}

