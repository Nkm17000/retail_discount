package com.nkm.discount.demo.config.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthenticationConfig {

    @Value("${auth.token.header.name}")
    private String authTokenHeaderName;

    @Value("${auth.token}")
    private String authToken;

    @Bean
    public AuthenticationService authenticationService() {
        return new AuthenticationService(authTokenHeaderName, authToken);
    }
}
