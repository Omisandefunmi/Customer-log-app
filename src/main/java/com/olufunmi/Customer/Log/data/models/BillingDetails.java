package com.olufunmi.Customer.Log.data.models;

import lombok.*;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@Builder
//@RequiredArgsConstructor
public class BillingDetails {

    @Id
    private String id;
    private String accountNumber;

    private String tariff;
}
