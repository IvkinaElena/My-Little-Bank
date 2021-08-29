package com.example.my.little.bank.services;

import com.example.my.little.bank.exception.NotFoundException;
import com.example.my.little.bank.models.Transaction;
import com.example.my.little.bank.repository.TransactionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static com.example.my.little.bank.models.ModelObjects.createTransaction;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TransactionServiceTests {
    @SpyBean
    private TransactionService transactionService;

    @MockBean
    private TransactionRepository transactionRepository;

    @Test
    @DisplayName("Создание транзакции - успех")
    public void createTransaction_success() {
        Transaction transaction = createTransaction();

        transactionService.create(transaction);

        verify(transactionRepository).save(transaction);
    }

    @Test
    @DisplayName("Поиск по ID - успех")
    public void findById_success() {
        Transaction transaction = createTransaction();
        when(transactionRepository.findById(any()))
                .thenReturn(Optional.of(transaction));

        var expectedTransaction = transactionService.findById(1L);

        assertNotNull(expectedTransaction);
    }

    @Test
    @DisplayName("Поиск по ID - транзакция не найдена")
    public void findById_transactionNotFound_exception() {
        when(transactionRepository.findById(any()))
                .thenReturn(Optional.empty());

        var exception = assertThrows(
                NotFoundException.class,
                () -> transactionService.findById(1L)
        );
        assertEquals("Unknown id 1", exception.getMessage());
    }

    @Test
    @DisplayName("Поиск всех транзакций - успех")
    public void findAll_success() {
        List<Transaction> transactions = new ArrayList<>();

        when(transactionRepository.findAll())
                .thenReturn(transactions);

        var expectedTransactions = transactionService.findAll();
        assertNotNull(expectedTransactions);
    }

    @Test
    @DisplayName("Поиск по id счета - успех")
    public void findByIdScore_success() {
        List<Transaction> transactions = new ArrayList<>();

        when(transactionRepository.findByAccountIdAccount(any()))
                .thenReturn(transactions);

        var expectedAccounts = transactionService.findByIdScore(1L);
        assertNotNull(expectedAccounts);
    }

}
