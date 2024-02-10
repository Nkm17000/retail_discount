package com.nkm.discount.demo.config;

import com.nkm.discount.demo.model.UserType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DiscountConfig {

    @Value("${discount.employee}")
    private double employeeDiscount;

    @Value("${discount.affiliate}")
    private double affiliateDiscount;

    @Value("${discount.customer}")
    private double customerDiscount;

    public void discountConfig() {
        UserType.EMPLOYEE.setDiscountPercentage(employeeDiscount);
        UserType.AFFILIATE.setDiscountPercentage(affiliateDiscount);
        UserType.CUSTOMER.setDiscountPercentage(customerDiscount);
    }
}

