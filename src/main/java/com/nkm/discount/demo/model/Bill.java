package com.nkm.discount.demo.model;

public class Bill {
    private int billId;

    private double totalAmount;
    private boolean isGrocery;

    public int getBillId() {
        return billId;
    }


    public Bill(int billId, double totalAmount, boolean isGrocery) {
        this.billId = billId;
        this.totalAmount = totalAmount;
        this.isGrocery = isGrocery;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public boolean isGrocery() {
        return isGrocery;
    }

    public void setGrocery(boolean grocery) {
        isGrocery = grocery;
    }


}
