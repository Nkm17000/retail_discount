package com.nkm.discount.demo.service;

import com.nkm.discount.demo.model.Bill;
import com.nkm.discount.demo.model.User;
import com.nkm.discount.demo.model.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RetailStoreDiscountsServiceTest {

    @Mock
    private Environment environment;

    @InjectMocks
    private RetailStoreDiscountsService retailStoreDiscountsService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(environment.getProperty("discount.for100")).thenReturn("5");
        ReflectionTestUtils.setField(retailStoreDiscountsService, "discountFor100", 5.0);
        UserType.EMPLOYEE.setDiscountPercentage(.3);
        UserType.AFFILIATE.setDiscountPercentage(.1);
        UserType.CUSTOMER.setDiscountPercentage(.05);
    }

    @Test
    void testCalculateNetPayableAmount_Employee() {
        User user = new User(1, UserType.EMPLOYEE, LocalDate.now());
        Bill bill = new Bill(1, 1000, false);
        List list = new ArrayList<Bill>();
        list.add(bill);
        double netPayableAmount = retailStoreDiscountsService.calculateNetPayableAmount(user, list);
        assertEquals(665, netPayableAmount);
    }

    @Test
    void testCalculateNetPayableAmount_Affiliate() {
        User user = new User(1, UserType.AFFILIATE, LocalDate.now());
        Bill bill = new Bill(1, 1000, false);
        List list = new ArrayList<Bill>();
        list.add(bill);
        double netPayableAmount = retailStoreDiscountsService.calculateNetPayableAmount(user, list);
        assertEquals(855, netPayableAmount);
    }

    @Test
    void testCalculateNetPayableAmount_CustomerOver2Years() {
        User user = new User(1, UserType.CUSTOMER, LocalDate.now().minusYears(3));
        Bill bill = new Bill(1, 1000, false);
        List list = new ArrayList<Bill>();
        list.add(bill);
        double netPayableAmount = retailStoreDiscountsService.calculateNetPayableAmount(user, list);
        assertEquals(905, netPayableAmount);
    }

    @Test
    void testCalculateNetPayableAmount_Customer() {
        User user = new User(1, UserType.CUSTOMER, LocalDate.now());
        Bill bill = new Bill(1, 1000, false);
        List list = new ArrayList<Bill>();
        list.add(bill);
        double netPayableAmount = retailStoreDiscountsService.calculateNetPayableAmount(user, list);
        assertEquals(950, netPayableAmount);
    }

    @Test
    void testCalculateNetPayableAmount_Groceries() {
        User user = new User(1, UserType.CUSTOMER, LocalDate.now().minusYears(1));
        Bill bill = new Bill(1, 100, true); // Groceries
        List list = new ArrayList<Bill>();
        list.add(bill);
        double netPayableAmount = retailStoreDiscountsService.calculateNetPayableAmount(user, list);
        assertEquals(95, netPayableAmount);
    }

    @Test
    void testCalculateNetPayableAmount_NoDiscount() {
        User user = new User(1, UserType.CUSTOMER, LocalDate.now().minusYears(1));
        Bill bill = new Bill(1, 500, false);
        List list = new ArrayList<Bill>();
        list.add(bill);
        double netPayableAmount = retailStoreDiscountsService.calculateNetPayableAmount(user, list);
        assertEquals(475, netPayableAmount);
    }
}
