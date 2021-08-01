package com.example.my.little.bank.services;

import com.example.my.little.bank.MyException.NotFoundException;
import com.example.my.little.bank.models.Transaction;
import com.example.my.little.bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public Transaction create(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Transactional
    public Transaction findById(Long id) throws NotFoundException {
        return transactionRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @Transactional
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Transactional
    public List<Transaction> findByIdScore(Long idScore) {
        return transactionRepository
                .findByAccountIdAccount(idScore);
    }
}
