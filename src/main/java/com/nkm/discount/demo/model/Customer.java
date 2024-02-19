package com.nkm.discount.demo.model;

import com.nkm.discount.demo.config.DiscountConfig;
import com.nkm.discount.demo.retailstoreinterface.UserTypeDiscount;

import java.time.LocalDate;

public class Customer implements UserTypeDiscount {

    @Override
    public double getDiscount(User user) {
        if (isCustomerOverTwoYears(user)) {
            return DiscountConfig.getLongTermCustomerDiscountRate();
        }
        return 0.0;
    }

    private boolean isCustomerOverTwoYears(User user) {
        return user.getRegistrationDate().isBefore(LocalDate.now().minusYears(2));
    }
}
