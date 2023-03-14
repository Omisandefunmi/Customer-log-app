package com.olufunmi.Customer.Log.dtos.responses;

import lombok.Builder;

@Builder
public class ApiError {
    private String message;
    private int statusCode;
}
