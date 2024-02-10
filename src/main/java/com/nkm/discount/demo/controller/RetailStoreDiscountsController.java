package com.nkm.discount.demo.controller;

import com.nkm.discount.demo.error.InvalidRequestException;
import com.nkm.discount.demo.model.CalculateNetPayableAmountRequest;
import com.nkm.discount.demo.service.RetailStoreDiscountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@Slf4j
public class RetailStoreDiscountsController {

    @Autowired
    RetailStoreDiscountsService retailStoreDiscountsService;

    /*RetailStoreDiscountsController(RetailStoreDiscountsService retailStoreDiscountsService) {
        this.retailStoreDiscountsService = retailStoreDiscountsService;
    }*/

    @Operation(summary = "Calculate Net Payable Amount", description = "Calculate the net payable amount based on user and bill information.")
    @PostMapping(value = "/calculateNetPayableAmount", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> calculateNetPayableAmount(@Parameter(description = "User and bill information") @RequestBody CalculateNetPayableAmountRequest request) {
        log.info("Received request to calculate net payable amount");
        if (request.getUser().getUserId() == 0) {
            throw new InvalidRequestException("UserId cann't be zero or null");
        }
        if (request.getBill().stream().filter(it -> it.getTotalAmount() == 0).collect(Collectors.toList()).size() > 0) {
            throw new InvalidRequestException("Bill Amount can't be zero ");
        }
        double netPayableAmount = retailStoreDiscountsService.calculateNetPayableAmount(request.getUser(), request.getBill());
        log.info("Net payable amount calculated: {}", netPayableAmount);
        return new ResponseEntity(netPayableAmount, HttpStatus.OK);
    }
}

