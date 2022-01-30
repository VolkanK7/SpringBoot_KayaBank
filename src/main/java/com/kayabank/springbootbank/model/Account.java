package com.kayabank.springbootbank.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Builder
public class Account {

    @Id
    private String id;

    private String customerId;
    private Double balance;
    private City city;
    private Currency currency;


}
