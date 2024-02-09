package com.nkm.discount.demo.service;

import com.nkm.discount.demo.model.Bill;
import com.nkm.discount.demo.model.User;
import com.nkm.discount.demo.model.UserType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class RetailStoreDiscountsService {

    @Value("${discount.employee}")
    private double percentageDiscountEmployee;

    @Value("${discount.affiliate}")
    private double percentageDiscountAffiliate;

    @Value("${discount.customer}")
    private double percentageDiscountCustomer;

    @Value("${discount.for100}")
    private double discountFor100;

    public double calculateNetPayableAmount(User user, List<Bill> bills) {
        log.info("Calculating net payable amount for user: {}, bill: {}", user, bills);
        double payableAmount = bills
                .stream()
                .map(bill -> getPayableAmount(user, bill))
                .collect(Collectors.summarizingDouble(Double::doubleValue))
                .getSum();
        double netPayableAmount = payableAmount - (int) (payableAmount / 100) * discountFor100;
        log.info("Net payable amount calculated: {}", netPayableAmount);
        return netPayableAmount;
    }

    private double getPayableAmount(User user, Bill bill) {
        double discount = 0;
        if (!bill.isGrocery()) {
            if (user.getUserType() == UserType.EMPLOYEE) {
                discount = percentageDiscountEmployee;
            } else if (user.getUserType() == UserType.AFFILIATE) {
                discount = percentageDiscountAffiliate;
            } else if (user.getUserType() == UserType.CUSTOMER
                    && user.getRegistrationDate().isBefore(LocalDate.now().minusYears(2))) {
                discount = percentageDiscountCustomer;
            }

        }

        double totalDiscount = calculateTotalDiscount(bill.getTotalAmount(), discount);
        return bill.getTotalAmount() - totalDiscount;
    }

    private double calculateTotalDiscount(double totalAmount, double discountPercentage) {
        return totalAmount * discountPercentage;
    }
}
