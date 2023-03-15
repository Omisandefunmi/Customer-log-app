package com.olufunmi.Customer.Log.web;

import com.olufunmi.Customer.Log.dtos.requests.AddCustomerRequest;

import com.olufunmi.Customer.Log.services.CustomerService;
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

    @PostMapping("/add_customer")
    public ResponseEntity <?> addCustomer(@RequestBody AddCustomerRequest request)  {
        return new ResponseEntity<>(customerService.addCustomer(request), HttpStatus.CREATED);
    }

    @GetMapping("/retrieve_customer/{email}")
    public ResponseEntity <?> retrieveCustomer(@PathVariable String email) {
        return new ResponseEntity<>(customerService.retrieveCustomer(email), HttpStatus.FOUND);
    }

    @GetMapping("/retrieve_all")
    public ResponseEntity <?> retrieveAll(){
        return new ResponseEntity<>(customerService.retrieveAll(), HttpStatus.FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        }));
        return errors;
    }

}
