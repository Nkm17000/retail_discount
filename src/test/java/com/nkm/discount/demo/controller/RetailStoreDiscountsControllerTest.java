package com.nkm.discount.demo.controller;

import com.nkm.discount.demo.model.User;
import com.nkm.discount.demo.service.RetailStoreDiscountsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RetailStoreDiscountsController.class)
class RetailStoreDiscountsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RetailStoreDiscountsService retailStoreDiscountsService;

    @Test
    void testCalculateNetPayableAmount() throws Exception {
        when(retailStoreDiscountsService.calculateNetPayableAmount(any(User.class), any())).thenReturn(1615.0);
        String body = "{\"user\":{\"userId\":1,\"userType\":\"EMPLOYEE\",\"registrationDate\":\"2024-02-09\"}," +
                "\"bill\":[{\"billId\":1,\"totalAmount\":1000,\"grocery\":true}]}";
        mockMvc.perform(MockMvcRequestBuilders.post("/calculateNetPayableAmount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("1615.0"));
    }

    @Test
    void testCalculateNetPayableAmount_check_userNotExist() throws Exception {
        when(retailStoreDiscountsService.calculateNetPayableAmount(any(User.class), any())).thenReturn(1615.0);
        String body = "{\"user\":{\"userId\":0,\"userType\":\"EMPLOYEE\",\"registrationDate\":\"2024-02-09\"},\"bill\":[{\"billId\":1,\"totalAmount\":1000,\"grocery\":true}]}";
        mockMvc.perform(MockMvcRequestBuilders.post("/calculateNetPayableAmount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("UserId cann't be zero or null"));
    }

    @Test
    void testCalculateNetPayableAmount_checkException_amountZero() throws Exception {
        when(retailStoreDiscountsService.calculateNetPayableAmount(any(User.class), any())).thenReturn(1615.0);
        String body = "{\"user\":{\"userId\":1,\"userType\":\"EMPLOYEE\",\"registrationDate\":\"2024-02-09\"},\"bill\":[{\"billId\":1,\"totalAmount\":0,\"grocery\":true}]}";
        mockMvc.perform(MockMvcRequestBuilders.post("/calculateNetPayableAmount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Bill Amount can't be zero "));

    }
}
