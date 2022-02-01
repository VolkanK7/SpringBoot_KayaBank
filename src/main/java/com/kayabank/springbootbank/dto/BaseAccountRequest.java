package com.kayabank.springbootbank.dto;

import com.kayabank.springbootbank.model.City;
import com.kayabank.springbootbank.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseAccountRequest {

    @NotBlank(message = "Customer ID must be not blank")
    private String customerId;
    @NotNull(message = "Balance must be not null")
    @Min(value = 0, message = "Min value must be greater than 0")
    private Double balance;
    @NotNull(message = "Currency must be not null")
    private Currency currency;
    @NotNull(message = "City must be not null")
    private City city;
}
