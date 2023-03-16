package com.olufunmi.Customer.Log.dtos.responses;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private Object data;
    private boolean isSuccessful;
    private int statusCode;
}
