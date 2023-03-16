package com.olufunmi.Customer.Log.dtos.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AddCustomerRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String tariff;
}
