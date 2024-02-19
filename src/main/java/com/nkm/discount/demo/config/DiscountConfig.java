package com.nkm.discount.demo.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DiscountConfig {
    private static Map<String, Double> discountRates = new HashMap<>();
    @Value("${discount.employee}")
    private double employeeDiscount;

    @Value("${discount.affiliate}")
    private double affiliateDiscount;

    @Value("${discount.customer}")
    private double customerDiscount;

    public double getEmployeeDiscount() {
        return employeeDiscount;
    }

    public double getAffiliateDiscount() {
        return affiliateDiscount;
    }

    public double getCustomerDiscount() {
        return customerDiscount;
    }


    @PostConstruct
    public void init() {
        discountRates.put("employee", employeeDiscount);
        discountRates.put("affiliate", affiliateDiscount);
        discountRates.put("customer", customerDiscount);
    }

    public static double getEmployeeDiscountRate() {
        return discountRates.get("employee");
    }

    public static double getAffiliateDiscountRate() {
        return discountRates.get("affiliate");
    }

    public static double getLongTermCustomerDiscountRate() {
        return discountRates.get("customer");
    }
}

