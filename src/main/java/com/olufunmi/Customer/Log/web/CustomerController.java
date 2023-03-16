package com.olufunmi.Customer.Log.web;

import com.olufunmi.Customer.Log.dtos.requests.AddCustomerRequest;

import com.olufunmi.Customer.Log.dtos.responses.ApiResponse;
import com.olufunmi.Customer.Log.services.CustomerService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/customer_log")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping(value = "/add_customer")
    public ResponseEntity <?> addCustomer(@RequestBody AddCustomerRequest request)  {
        return new ResponseEntity<>(ApiResponse.builder()
                .isSuccessful(true)
                .data(customerService.addCustomer(request))
                .statusCode(201)
                .build(), HttpStatus.CREATED);
    }

    @GetMapping("/find")
    public ResponseEntity <?> retrieveCustomer(@RequestParam String email) {
        return new ResponseEntity<>(ApiResponse.builder()
                .isSuccessful(true)
                .data(customerService.retrieveCustomer(email))
                .statusCode(200)
                .build(), HttpStatus.OK);
    }

    @GetMapping("/retrieve_all")
    public ResponseEntity <?> retrieveAll(){
        return new ResponseEntity<>(ApiResponse.builder()
                .isSuccessful(true)
                .data(customerService.retrieveAll())
                .statusCode(200)
                .build(),  HttpStatus.OK);
    }


}
