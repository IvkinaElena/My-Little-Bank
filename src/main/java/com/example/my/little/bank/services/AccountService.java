package com.example.my.little.bank.services;

import com.example.my.little.bank.models.Account;
import com.example.my.little.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Account create(Account account) {
        return accountRepository.save(account);
    }

    @Transactional
    public Account findById(Long id) {
        return accountRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unknown account id: " + id));
    }

    @Transactional
    public List<Account> findByIdOwner(Long idOwner) {
        return accountRepository
                .findByCustomerIdCustomer(idOwner);
    }

    @Transactional
    public List<Account> findAll() {
        return accountRepository.findAll();
    }
}
