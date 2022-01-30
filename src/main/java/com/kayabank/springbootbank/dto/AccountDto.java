package com.kayabank.springbootbank.dto;

import com.kayabank.springbootbank.model.City;
import com.kayabank.springbootbank.model.Currency;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto{
    private String id;
    private String customerId;
    private Double balance;
    private Currency currency;
}
