package com.kayabank.springbootbank.service;

import com.kayabank.springbootbank.dto.AccountDto;
import com.kayabank.springbootbank.dto.AccountDtoConverter;
import com.kayabank.springbootbank.dto.CreateAccountRequest;
import com.kayabank.springbootbank.dto.UpdateAccountRequest;
import com.kayabank.springbootbank.exception.AccountNotFoundException;
import com.kayabank.springbootbank.exception.CustomerNotFoundException;
import com.kayabank.springbootbank.exception.InsufficientBalanceException;
import com.kayabank.springbootbank.model.Account;
import com.kayabank.springbootbank.model.Customer;
import com.kayabank.springbootbank.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public AccountDto updateAccount(String id, UpdateAccountRequest request){
        Customer customer = customerService.getCustomerById(request.getCustomerId());
        if(customer.getId() == null || customer.getId().trim().equals("")){
            throw new CustomerNotFoundException("Customer Not Found!");
        }

        Optional<Account> accountOptional = accountRepository.findById(id);
        accountOptional.ifPresent(account -> {
            account.setBalance(request.getBalance());
            account.setCity(request.getCity());
            account.setCurrency(request.getCurrency());
            account.setCustomerId(request.getCustomerId());
            accountRepository.save(account);
        });
        return accountOptional.map(accountDtoConverter::convert)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found!"));
    }

    public List<AccountDto> getAllAccounts(){
       List<Account> accountList = accountRepository.findAll();

       return accountList.stream()
               .map(accountDtoConverter :: convert)
               .collect(Collectors.toList());
    }

    public AccountDto getAccountById(String id){
        return accountRepository.findById(id)
                .map(accountDtoConverter::convert)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found!"));
    }

    public void deleteAccount(String id) {
        Account account = accountRepository.findById(id)
                        .orElseThrow(() -> new AccountNotFoundException("Account Not Found!"));
        accountRepository.deleteById(id);
    }
    public AccountDto withdrawMoney(String id, Double amount){
        Optional<Account> accountOptional = accountRepository.findById(id);
        accountOptional.ifPresent(account -> {
            if(account.getBalance() > amount){
                account.setBalance(account.getBalance() - amount);
                accountRepository.save(account);
            }else{
                throw new InsufficientBalanceException("Insufficient Balance! ID: "+id
                        +" Balance: "+account.getBalance() + " Amount: "+amount);
            }
        });
        return accountOptional.map(accountDtoConverter::convert)
                .orElseThrow(() -> new AccountNotFoundException("Account Not Found!"));
    }

    public AccountDto addMoney(String id, Double amount){
        Optional<Account> accountOptional = accountRepository.findById(id);
        accountOptional.ifPresent(account -> {
            if(amount >= 50){
                account.setBalance(account.getBalance() + amount);
                accountRepository.save(account);
            }else{
                throw new InsufficientBalanceException("Amount must be min 50");
            }

        });
        return accountOptional.map(accountDtoConverter::convert)
                .orElseThrow(() -> new AccountNotFoundException("Account Not Found!"));
    }
}
