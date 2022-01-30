package com.kayabank.springbootbank.controller;

import com.kayabank.springbootbank.dto.AccountDto;
import com.kayabank.springbootbank.dto.CreateAccountRequest;
import com.kayabank.springbootbank.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createAccount(@RequestBody CreateAccountRequest createAccountRequest){
        return ResponseEntity.ok(accountService.createAccount(createAccountRequest));
    }
}
