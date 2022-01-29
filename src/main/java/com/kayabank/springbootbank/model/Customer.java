package com.kayabank.springbootbank.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "customer")
public class Customer {

    @Id
    private String id;

    private String name;
    @Column(name = "date_of_birth")
    private Integer dateOfBirth;
    private City city;
    private String address;
}
