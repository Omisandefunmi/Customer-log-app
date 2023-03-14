package com.olufunmi.Customer.Log.data.models;

import lombok.*;

@Setter
@Getter
@Builder

public class BillingDetails {


    private String accountNumber;
    @NonNull
    private String tariff;
}
