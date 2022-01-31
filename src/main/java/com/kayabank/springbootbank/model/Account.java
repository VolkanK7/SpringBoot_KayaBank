package com.kayabank.springbootbank.model;

import lombok.*;

import javax.persistence.*;

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
    private String id;

    @Column(name = "customer_id")
    private String customerId;
    private Double balance;
    private City city;
    private Currency currency;


}
