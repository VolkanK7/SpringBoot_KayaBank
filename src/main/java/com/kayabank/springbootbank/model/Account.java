package com.kayabank.springbootbank.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
@Builder
@EqualsAndHashCode
public class Account {

    @Id
    @NotBlank(message = "ID must be not blank")
    private String id;

    @NotBlank(message = "Customer ID must be not blank")
    @Column(name = "customer_id")
    private String customerId;
    @NotNull(message = "Balance must be not null")
    @Min(value = 200, message = "Min value must be greater than 200")
    private Double balance;
    private City city;
    private Currency currency;


}
