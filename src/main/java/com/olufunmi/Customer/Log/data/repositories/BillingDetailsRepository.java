package com.olufunmi.Customer.Log.data.repositories;

import com.olufunmi.Customer.Log.data.models.BillingDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BillingDetailsRepository extends MongoRepository<BillingDetails, String> {
}
