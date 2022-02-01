package com.kayabank.springbootbank.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "customer")
@EqualsAndHashCode
public class Customer {

    @Pattern(regexp = "^[1-9]{1}[0-9]{9}[02468]{1}$")
    @Id
    private String id;
    @NotBlank(message = "Name must be not null")
    @Size(min = 3, message = "Name must be min 3 of characters")
    private String name;
    @NotNull(message = "Date of birth must be not null")
    @Column(name = "date_of_birth")
    private Integer dateOfBirth;
    private City city;
    @NotBlank(message = "Address must be not blank")
    private String address;
}
