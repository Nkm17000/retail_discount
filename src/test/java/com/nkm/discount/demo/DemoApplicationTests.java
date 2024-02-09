package com.nkm.discount.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class DemoApplicationTests {
    @Value("${spring.application.name}")
    private String applicationName;

    @Test
    void contextLoads() {
        assertEquals("retail_discount", applicationName, "The application name should match");
    }
}

