package com.nkm.discount.demo.model;

public enum UserType {
    EMPLOYEE(0.30),
    AFFILIATE(0.10),
    CUSTOMER(0.05);
    private double discountPercentage;

    UserType(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }
}
