package com.olufunmi.Customer.Log.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AddCustomerResponse {
    private String email;
    private String message;
}
