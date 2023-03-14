package com.olufunmi.Customer.Log.services;

import com.olufunmi.Customer.Log.data.models.BillingDetails;
import com.olufunmi.Customer.Log.data.models.Customer;
import com.olufunmi.Customer.Log.data.repositories.CustomerRepository;
import com.olufunmi.Customer.Log.dtos.requests.AddCustomerRequest;

import com.olufunmi.Customer.Log.dtos.responses.AddCustomerResponse;

import com.olufunmi.Customer.Log.dtos.responses.RetrieveCustomerResponse;
import com.olufunmi.Customer.Log.exceptions.EmailAlreadyExistException;
import com.olufunmi.Customer.Log.exceptions.EmailNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;

    @Override
    public AddCustomerResponse addCustomer(AddCustomerRequest request) throws EmailAlreadyExistException {
      Optional<Customer> customerExists = customerRepository.findByEmail(request.getEmail());

      if(customerExists.isPresent()){
          throw new EmailAlreadyExistException("Email already exists");
      }
        String accountNumber = generateAccountNumber();
        BillingDetails billingDetails = createBillingDetails(accountNumber, request.getTariff());

        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .billingDetails(billingDetails)
                .build();

        customerRepository.save(customer);

        return AddCustomerResponse.builder()
                .email(customer.getEmail())
                .message("Add successful")
                .build();

    }

    @Override
    public RetrieveCustomerResponse retrieveCustomer(String email) throws EmailNotFoundException {
        Customer retrieved = customerRepository.findByEmail(email).orElseThrow(() -> new EmailNotFoundException("Email does not exist"));

        return RetrieveCustomerResponse.builder()
                .firstName(retrieved.getFirstName())
                .lastName(retrieved.getLastName())
                .email(retrieved.getEmail())
                .billingDetails(retrieved.getBillingDetails())
                .build();
    }

    @Override
    public List <RetrieveCustomerResponse> retrieveAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(this::mapCustomerToResponse).toList();

    }

    private BillingDetails createBillingDetails(String accountNumber, String tariff){
        return new BillingDetails(accountNumber, tariff);
    }

    private String generateAccountNumber(){
        SecureRandom numbers = new SecureRandom();
        return String.valueOf(numbers.nextInt(10)).concat("-01");
    }

    private RetrieveCustomerResponse mapCustomerToResponse(Customer customer){
        return RetrieveCustomerResponse.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .billingDetails(customer.getBillingDetails())
                .build();
    }
}
