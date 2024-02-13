package com.nkm.discount.demo.interfaces;

import com.nkm.discount.demo.model.Bill;
import com.nkm.discount.demo.model.User;

import java.util.List;

public interface RetailStoreDiscountInterface {
    public double calculateNetPayableAmount(User user, List<Bill> bills);
}
