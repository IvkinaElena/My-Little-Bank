package com.example.my.little.bank.services;

import com.example.my.little.bank.exception.NotFoundException;
import com.example.my.little.bank.models.Account;
import com.example.my.little.bank.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static com.example.my.little.bank.models.ModelObjects.createAccount;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
public class AccountServiceTests {

    @SpyBean
    private AccountService accountService;

    @MockBean
    private AccountRepository accountRepository;

    @Test
    @DisplayName("Создание аккаунта - успех")
    public void createAccount_success() {
        Account account = createAccount();

        accountService.create(account);

        verify(accountRepository).save(account);
    }

    @Test
    @DisplayName("Поиск по ID - успех")
    public void findById_success() {
        Account account = createAccount();
        when(accountRepository.findById(any()))
                .thenReturn(Optional.of(account));

        var expectedAccount = accountService.findById(1L);

        assertNotNull(expectedAccount);
    }

    @Test
    @DisplayName("Поиск по ID - аккаунт не найден")
    public void findById_accountNotFound_exception() {
        when(accountRepository.findById(any()))
                .thenReturn(Optional.empty());

        var exception = assertThrows(
                NotFoundException.class,
                () -> accountService.findById(1L)
        );
        assertEquals("Unknown id 1", exception.getMessage());
    }

    @Test
    @DisplayName("Поиск по id владельца - успех")
    public void findByIdOwner_success() {
        List<Account> accounts = new ArrayList<>();

        when(accountRepository.findByCustomerIdCustomer(any()))
                .thenReturn(accounts);

        var expectedAccounts = accountService.findByIdOwner(1L);
        assertNotNull(expectedAccounts);
    }

    @Test
    @DisplayName("Поиск всех аккаунтов - успех")
    public void findAll_success() {
        List<Account> accounts = new ArrayList<>();

        when(accountRepository.findAll())
                .thenReturn(accounts);

        var expectedAccounts = accountService.findAll();
        assertNotNull(expectedAccounts);
    }
}
