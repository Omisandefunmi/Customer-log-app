package com.olufunmi.Customer.Log;

import com.olufunmi.Customer.Log.data.models.BillingDetails;
import com.olufunmi.Customer.Log.data.models.Customer;
import com.olufunmi.Customer.Log.data.repositories.BillingDetailsRepository;
import com.olufunmi.Customer.Log.data.repositories.CustomerRepository;
import com.olufunmi.Customer.Log.dtos.requests.AddCustomerRequest;
import com.olufunmi.Customer.Log.dtos.responses.AddCustomerResponse;
import com.olufunmi.Customer.Log.dtos.responses.RetrieveCustomerResponse;
import com.olufunmi.Customer.Log.services.CustomerService;
import com.olufunmi.Customer.Log.services.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CustomerServiceTest {

    private CustomerService customerService;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private BillingDetailsRepository billingDetailsRepository;
    private AddCustomerRequest request;
    private BillingDetails mockBillingDetails;
    private Customer mockCustomer;



    @BeforeEach
    public void setUp(){
        customerService = new CustomerServiceImpl(customerRepository,billingDetailsRepository);
        mockBillingDetails = BillingDetails.builder()
                .tariff("flex")
                .accountNumber("1234567890-10")
                .id("1")
                .build();
         mockCustomer = Customer.builder()
                .id("1")
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@mail.com")
                .billingDetails(mockBillingDetails)
                .build();

        request = AddCustomerRequest.builder()
                .email("johndoe@mail.com")
                .firstName("John")
                .lastName("Doe")
                .tariff("flex")
                .build();
    }

    @Test
    public void addCustomerTest(){
        AddCustomerResponse response = AddCustomerResponse.builder()
                .message("add success")
                .email(request.getEmail())
                .build();

        when(customerRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(billingDetailsRepository.save(any(BillingDetails.class))).thenReturn(mockBillingDetails);
        when(customerRepository.save(any(Customer.class))).thenReturn(mockCustomer);

        AddCustomerResponse response1 = customerService.addCustomer(request);
        assertThat(response1.getEmail()).isEqualTo(response.getEmail());
    }

    @Test
    public void retrieveCustomerTest(){
        RetrieveCustomerResponse response = RetrieveCustomerResponse.builder()
                .billingDetails(mockBillingDetails)
                .email(mockCustomer.getEmail())
                .lastName(mockCustomer.getLastName())
                .firstName(mockCustomer.getFirstName())
                .build();

        when(customerRepository.findByEmail(mockCustomer.getEmail())).thenReturn(Optional.of(mockCustomer));
        RetrieveCustomerResponse response1 = customerService.retrieveCustomer(mockCustomer.getEmail());
        assertThat(response1.getEmail()).isEqualTo(response.getEmail());

    }

    @Test
    public void retrieveAllCustomerTest(){
        when(customerRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(billingDetailsRepository.save(any(BillingDetails.class))).thenReturn(mockBillingDetails);
        when(customerRepository.save(any(Customer.class))).thenReturn(mockCustomer);
        AddCustomerResponse response = customerService.addCustomer(request);
        assertThat(response.getEmail()).isEqualTo(response.getEmail());

        when(customerRepository.findByEmail(mockCustomer.getEmail())).thenReturn(Optional.of(mockCustomer));
        List<RetrieveCustomerResponse> response1 = customerService.retrieveAll();
        assertThat(response1.size()).isEqualTo(1);

    }
}
