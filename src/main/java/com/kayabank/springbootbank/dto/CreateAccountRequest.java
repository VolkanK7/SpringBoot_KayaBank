package com.kayabank.springbootbank.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAccountRequest extends BaseAccountRequest{
    @NotBlank(message = "ID must be not blank")
    private String id;
}
