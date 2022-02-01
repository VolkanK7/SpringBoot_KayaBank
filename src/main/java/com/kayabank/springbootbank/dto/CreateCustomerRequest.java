package com.kayabank.springbootbank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerRequest extends BaseCustomerRequest{
    @Pattern(regexp = "^[1-9]{1}[0-9]{9}[02468]{1}$", message = "Invalid TCKN!")
    private String id;
}
