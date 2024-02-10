package com.nkm.discount.demo.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    HttpStatus status;
    String message;
    LocalDateTime localDateTime;
}
