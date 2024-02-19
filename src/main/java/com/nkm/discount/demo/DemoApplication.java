package com.nkm.discount.demo;

import com.nkm.discount.demo.config.DiscountConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class})
public class DemoApplication  {

    @Autowired
    private DiscountConfig discountConfig;
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

