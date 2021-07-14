package com.example.my.little.bank.repository;

import com.example.my.little.bank.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByIdOwner(Long idOwner);
}
