package com.kayabank.springbootbank.service;

import com.kayabank.springbootbank.dto.AccountDto;
import com.kayabank.springbootbank.dto.AccountDtoConverter;
import com.kayabank.springbootbank.dto.CreateAccountRequest;
import com.kayabank.springbootbank.exception.CustomerNotFoundException;
import com.kayabank.springbootbank.model.Account;
import com.kayabank.springbootbank.model.Customer;
import com.kayabank.springbootbank.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final AccountDtoConverter accountDtoConverter;

    public AccountService(AccountRepository accountRepository, CustomerService customerService, AccountDtoConverter accountDtoConverter) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.accountDtoConverter = accountDtoConverter;
    }

    public AccountDto createAccount(CreateAccountRequest createAccountRequest){
        Customer customer = customerService.getCustomerById(createAccountRequest.getCustomerId());
        if(customer.getId() == null || customer.getId().trim().equals("")){
            throw new CustomerNotFoundException("Customer Not Found!");
        }
        Account account = Account.builder()
                .id(createAccountRequest.getId())
                .balance(createAccountRequest.getBalance())
                .currency(createAccountRequest.getCurrency())
                .customerId(createAccountRequest.getCustomerId())
                .city(createAccountRequest.getCity())
                .build();
        return accountDtoConverter.convert(accountRepository.save(account));
    }
}
