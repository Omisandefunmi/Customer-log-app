package com.olufunmi.Customer.Log.services;

import com.olufunmi.Customer.Log.dtos.requests.AddCustomerRequest;
import com.olufunmi.Customer.Log.dtos.requests.RetrieveAllRequest;
import com.olufunmi.Customer.Log.dtos.requests.RetrieveCustomerRequest;
import com.olufunmi.Customer.Log.dtos.responses.AddCustomerResponse;
import com.olufunmi.Customer.Log.dtos.responses.RetrieveAllResponse;
import com.olufunmi.Customer.Log.dtos.responses.RetrieveCustomerResponse;
import com.olufunmi.Customer.Log.exceptions.EmailAlreadyExistException;
import com.olufunmi.Customer.Log.exceptions.EmailNotFoundException;

import java.util.List;

public interface CustomerService {
    AddCustomerResponse addCustomer(AddCustomerRequest request) throws EmailAlreadyExistException;

    RetrieveCustomerResponse retrieveCustomer(String email) throws EmailNotFoundException;

    List<RetrieveCustomerResponse> retrieveAll ();
}
