package com.nkm.discount.demo.retailstoreinterface;

import com.nkm.discount.demo.model.Bill;
import com.nkm.discount.demo.model.User;

import java.util.List;

public interface RetailStoreDiscountInterface {
    double calculateNetPayableAmount(User user, List<Bill> bills);
}
