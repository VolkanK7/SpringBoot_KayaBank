package com.kayabank.springbootbank.dto;

import com.kayabank.springbootbank.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoConverter {

    public CustomerDto convert(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setAddress(customer.getAddress());
        customerDto.setDateOfBirth(customer.getDateOfBirth());
        customerDto.setName(customer.getName());
        customerDto.setCity(CityDto.valueOf(customer.getCity().name()));
        return customerDto;
    }
}
