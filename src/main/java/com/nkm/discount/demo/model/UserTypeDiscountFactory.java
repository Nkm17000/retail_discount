package com.nkm.discount.demo.model;

import com.nkm.discount.demo.retailstoreinterface.UserTypeDiscount;

import java.util.HashMap;
import java.util.Map;

public class UserTypeDiscountFactory {
    private static final Map<UserType, UserTypeDiscount> userTypeDiscountMap = new HashMap<>();

    static {
        userTypeDiscountMap.put(UserType.EMPLOYEE, new Employee());
        userTypeDiscountMap.put(UserType.AFFILIATE, new Affiliate());
        userTypeDiscountMap.put(UserType.CUSTOMER, new Customer());
    }

    public UserTypeDiscount getUserTypeDiscount(UserType userType) {
        return userTypeDiscountMap.getOrDefault(userType, new Customer());
    }
}

