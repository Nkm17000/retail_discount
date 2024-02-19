package com.nkm.discount.demo.model;

import com.nkm.discount.demo.config.DiscountConfig;
import com.nkm.discount.demo.retailstoreinterface.UserTypeDiscount;

public class Affiliate implements UserTypeDiscount {

    @Override
    public double getDiscount(User user) {
        return DiscountConfig.getAffiliateDiscountRate();
    }
}
