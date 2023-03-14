package com.olufunmi.Customer.Log.dtos.responses;

import com.olufunmi.Customer.Log.data.models.BillingDetails;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class RetrieveCustomerResponse {
    private String firstName;
    private String lastName;
    private String email;
    private BillingDetails billingDetails;
}
