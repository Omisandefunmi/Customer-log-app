package com.olufunmi.Customer.Log.data.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;

@Setter
@Getter
@Builder
public class Customer {
    @Id
    private String id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String email;
    @DBRef
    private BillingDetails billingDetails;

}
