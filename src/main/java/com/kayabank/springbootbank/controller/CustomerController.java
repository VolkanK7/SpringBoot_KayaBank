package com.kayabank.springbootbank.controller;

import com.kayabank.springbootbank.dto.CreateCustomerRequest;
import com.kayabank.springbootbank.dto.CustomerDto;
import com.kayabank.springbootbank.dto.UpdateCustomerRequest;
import com.kayabank.springbootbank.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest){
        return ResponseEntity.ok(customerService.createCustomer(createCustomerRequest));
    }

    @GetMapping("/getall")
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/getcustomer/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") String id){
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

   @DeleteMapping("/deletecustomer/{id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable String id){
        customerService.deleteCustomerById(id);
        return ResponseEntity.ok("Customer Deleted: " + id);
    }

    @PutMapping("/updatecustomer/{id}")
    public ResponseEntity<CustomerDto> updateCustomerById(@PathVariable String id,
                                                          @RequestBody UpdateCustomerRequest updateCustomerRequest){
        return ResponseEntity.ok(customerService.updateCustomerById(id, updateCustomerRequest));
    }
}
