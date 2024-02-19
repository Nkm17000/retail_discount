package com.nkm.discount.demo.model;

import com.nkm.discount.demo.config.DiscountConfig;
import com.nkm.discount.demo.retailstoreinterface.UserTypeDiscount;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Employee implements UserTypeDiscount {
    @Override
    public double getDiscount(User user) {
        return DiscountConfig.getEmployeeDiscountRate();
    }

}
