package com.nkm.discount.demo.model;

import com.nkm.discount.demo.retailstoreinterface.UserTypeDiscount;

public class UserTypeDiscountFactory {

    public UserTypeDiscount getUserTypeDiscount(UserType userType) {
        if (userType.name().equalsIgnoreCase("employee")) {
            return new Employee();
        } else if (userType.name().equalsIgnoreCase("affiliate")) {
            return new Affiliate();
        } else {
            return new Customer();
        }
    }
}
