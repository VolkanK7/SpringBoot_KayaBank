package com.kayabank.springbootbank.repository;

import com.kayabank.springbootbank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,String> {
}
