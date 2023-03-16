package com.olufunmi.Customer.Log.services;

import com.olufunmi.Customer.Log.data.models.BillingDetails;
import com.olufunmi.Customer.Log.data.models.Customer;
import com.olufunmi.Customer.Log.data.repositories.BillingDetailsRepository;
import com.olufunmi.Customer.Log.data.repositories.CustomerRepository;
import com.olufunmi.Customer.Log.dtos.requests.AddCustomerRequest;

import com.olufunmi.Customer.Log.dtos.responses.AddCustomerResponse;

import com.olufunmi.Customer.Log.dtos.responses.RetrieveCustomerResponse;
import com.olufunmi.Customer.Log.exceptions.EmailAlreadyExistException;
import com.olufunmi.Customer.Log.exceptions.EmailNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;
    private final BillingDetailsRepository billingDetailsRepository;

    @Override
    public AddCustomerResponse addCustomer(AddCustomerRequest request) throws EmailAlreadyExistException {
      Optional<Customer> customerExists = customerRepository.findByEmail(request.getEmail());

      if(customerExists.isPresent()){
          throw new EmailAlreadyExistException("Email already exists");
      }
        String accountNumber = generateAccountNumber();
      log.info("Account number is ===>>> "+accountNumber);
        BillingDetails billingDetails = createBillingDetails(accountNumber, request.getTariff());

        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .billingDetails(billingDetails)
                .build();

        billingDetailsRepository.save(billingDetails);

        customerRepository.save(customer);



        return AddCustomerResponse.builder()
                .email(customer.getEmail())
                .message("Add successful")
                .build();

    }

    @Override
    public RetrieveCustomerResponse retrieveCustomer(String email) throws EmailNotFoundException {
        Optional <Customer> found = customerRepository.findByEmail(email);
        if(found.isEmpty()){
            throw new EmailNotFoundException("Email does not exist");
        }
        Customer retrieved = found.get();
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
        return  BillingDetails.builder()
                .accountNumber(accountNumber)
                .tariff(tariff)
                .build();
    }

    private String generateAccountNumber(){
        SecureRandom numbers = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            stringBuilder.append(numbers.nextInt(10));
        }
        return stringBuilder.append("-01").toString();
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
