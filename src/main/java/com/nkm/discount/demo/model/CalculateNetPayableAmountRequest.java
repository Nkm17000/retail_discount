package com.nkm.discount.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CalculateNetPayableAmountRequest {
    private User user;
    private List<Bill> bill;
}
