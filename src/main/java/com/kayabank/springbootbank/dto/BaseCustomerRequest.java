package com.kayabank.springbootbank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseCustomerRequest {
    @NotBlank(message = "Name must be not null")
    @Size(min = 3, message = "Name must be min 3 of characters")
    private String name;
    @NotNull(message = "Date of birth must be not null")
    private Integer dateOfBirth;
    @NotNull(message = "City must be not null")
    private CityDto city;
    @NotBlank(message = "Address must be not blank")
    private String address;
}
