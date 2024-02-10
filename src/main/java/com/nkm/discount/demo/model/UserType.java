package com.nkm.discount.demo.model;

public enum UserType {
    EMPLOYEE(0.0),
    AFFILIATE(0.0),
    CUSTOMER(0.0);
    private double discountPercentage;

    UserType(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
