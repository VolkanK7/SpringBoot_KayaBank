package com.kayabank.springbootbank.repository;

import com.kayabank.springbootbank.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,String> {
}
