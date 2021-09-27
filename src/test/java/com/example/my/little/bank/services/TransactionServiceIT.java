package com.example.my.little.bank.services;

import com.example.my.little.bank.models.Account;
import com.example.my.little.bank.models.Customer;
import com.example.my.little.bank.models.ModelObjects;
import com.example.my.little.bank.models.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TransactionServiceIT {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AccountService accountService;

    @Test
    @Transactional
    public void createTransaction() {
        Transaction transaction = createNewTransaction();

        assertNotNull(transaction.getIdTransacrion());
    }

    @Test
    @Transactional
    public void findTransactionById() {
        Transaction createdTransaction = createNewTransaction();
        Long idTransaction = createdTransaction.getIdTransacrion();

        Transaction transactionFromDb = transactionService.findById(idTransaction);
        assertEquals(idTransaction, transactionFromDb.getIdTransacrion());
        assertEquals(createdTransaction.getOperation(), transactionFromDb.getOperation());
    }

    @Test
    @Transactional
    public void findAllTransaction() {
        createNewTransaction();
        createNewTransaction();

        assertEquals(2, transactionService.findAll().size());
    }

    @Test
    @Transactional
    public void findTransactionByIdScore() {
        Transaction createdTransaction = createNewTransaction();
        Long idAccount = createdTransaction.getAccount().getIdAccount();

        List<Transaction> transactionsFromDb = transactionService.findByIdScore(idAccount);
        for(Transaction transaction : transactionsFromDb) {
            assertEquals(idAccount, transaction.getAccount().getIdAccount());
        }
    }

    private Transaction createNewTransaction() {
        Customer customer = customerService.create(ModelObjects.createGoodCustomer());
        Account account = accountService.create(ModelObjects.createGoodAccount(customer));
        return transactionService.create(ModelObjects.createGoodTransaction(account));
    }
}
