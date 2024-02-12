package com.nkm.discount.demo.config.security.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

public class AuthenticationService {

    private static  String authTokenHeaderName;
    private static  String authToken;

    public AuthenticationService(String authTokenHeaderName, String authToken) {
        this.authTokenHeaderName = authTokenHeaderName;
        this.authToken = authToken;
    }
    public static Authentication getAuthentication(HttpServletRequest request) {
        String apiKey = request.getHeader(authTokenHeaderName);
        StringBuffer path = request.getRequestURL();
        if (apiKey == null || !apiKey.equals(authToken)) {
            throw new BadCredentialsException("Invalid API Key");
        }

        return new ApiKeyAuthentication(apiKey, AuthorityUtils.NO_AUTHORITIES);
       // return null;

    }

}
