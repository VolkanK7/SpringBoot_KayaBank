package com.kayabank.springbootbank.service;

import com.kayabank.springbootbank.dto.CreateCustomerRequest;
import com.kayabank.springbootbank.dto.CustomerDto;
import com.kayabank.springbootbank.dto.CustomerDtoConverter;
import com.kayabank.springbootbank.dto.UpdateCustomerRequest;
import com.kayabank.springbootbank.exception.CustomerNotFoundException;
import com.kayabank.springbootbank.model.City;
import com.kayabank.springbootbank.model.Customer;
import com.kayabank.springbootbank.repository.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter customerDtoConverter;

    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter customerDtoConverter) {
        this.customerRepository = customerRepository;
        this.customerDtoConverter = customerDtoConverter;
    }

    public CustomerDto createCustomer(CreateCustomerRequest createCustomerRequest){
        Customer customer = new Customer();
        customer.setId(createCustomerRequest.getId());
        customer.setAddress(createCustomerRequest.getAddress());
        customer.setName(createCustomerRequest.getName());
        customer.setDateOfBirth(createCustomerRequest.getDateOfBirth());
        customer.setCity(City.valueOf(createCustomerRequest.getCity().name()));

        customerRepository.save(customer);

        return customerDtoConverter.convert(customer);
    }

    public List<CustomerDto> getAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerDto> customerDtoList = new ArrayList<>();
        for(Customer customer : customerList){
            customerDtoList.add(customerDtoConverter.convert(customer));
        }
        return customerDtoList;
    }

    public CustomerDto getCustomerDtoById(String id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

       return customerOptional.map(customerDtoConverter::convert)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Id Not Found!"));
    }

    public void deleteCustomerById(String id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Id Not Found!"));
        customerRepository.delete(customer);
    }


    public CustomerDto updateCustomerById(String id, UpdateCustomerRequest updateCustomerRequest) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        customerOptional.ifPresent(customer -> {
            customer.setName(updateCustomerRequest.getName());
            customer.setAddress(updateCustomerRequest.getAddress());
            customer.setDateOfBirth(updateCustomerRequest.getDateOfBirth());
            customer.setCity(City.valueOf(updateCustomerRequest.getCity().name()));
            customerRepository.save(customer);
        });
        return customerOptional.map(customerDtoConverter::convert)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Id Not Found!"));
    }

    protected Customer getCustomerById(String id){
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found!"));
    }
}
