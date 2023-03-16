package com.olufunmi.Customer.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olufunmi.Customer.Log.data.repositories.CustomerRepository;
import com.olufunmi.Customer.Log.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private MockMvc mockMvc;
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("add a customer api")
    void createCustomerTest() throws Exception {
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.post("/api/customer_log/add_customer")
                        .param("firstName", "Jessica")
                        .param("lastName", "Seth")
                        .param("email", "seth@mail")
                        .param("tariff", "flex");

        mockMvc.perform(request
                        .contentType("application/json"))
                .andExpect(status().is(201))
                .andDo(print());
    }
}
