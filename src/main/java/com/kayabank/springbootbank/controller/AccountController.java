package com.kayabank.springbootbank.controller;

import com.kayabank.springbootbank.dto.AccountDto;
import com.kayabank.springbootbank.dto.CreateAccountRequest;
import com.kayabank.springbootbank.dto.CustomerDto;
import com.kayabank.springbootbank.dto.UpdateAccountRequest;
import com.kayabank.springbootbank.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getall")
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/getaccount/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable String id){
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable String id,
                                                    @RequestBody UpdateAccountRequest updateAccountRequest){
        return ResponseEntity.ok(accountService.updateAccount(id, updateAccountRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable String id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account Deleted!");
    }
}
