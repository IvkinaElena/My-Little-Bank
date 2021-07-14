package com.example.my.little.bank.repository;

import com.example.my.little.bank.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByIdScore(Long idScore);
}
