package com.example.my.little.bank.services;

import com.example.my.little.bank.models.Account;
import com.example.my.little.bank.models.Customer;
import com.example.my.little.bank.models.ModelObjects;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AccountServiceIT {

    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerService customerService;

    @Test
    @Transactional
    public void createAccount() {
        Account createdAccount = createNewAccount();

        assertNotNull(createdAccount.getIdAccount());
    }

    @Test
    @Transactional
    public void findAccountById() {
        Account createdAccount = createNewAccount();
        Long idAccount = createdAccount.getIdAccount();

        Account accountFromDb = accountService.findById(idAccount);
        assertEquals(idAccount, accountFromDb.getIdAccount());
        assertEquals(createdAccount.getNumber(), accountFromDb.getNumber());
    }

    @Test
    @Transactional
    public void findAllAccount() {
        createNewAccount();
        createNewAccount();

        assertEquals(2, accountService.findAll().size());
    }

    @Test
    @Transactional
    public void findAccountByIdOwner() {
        Account createdAccount = createNewAccount();
        Customer owner = createdAccount.getCustomer();
        Long idOwner = owner.getIdCustomer();

        List<Account> accountsFromDb = accountService.findByIdOwner(idOwner);
        for(Account account : accountsFromDb) {
            assertEquals(idOwner, account.getCustomer().getIdCustomer());
        }
    }

    private Account createNewAccount(){
        Customer customer = customerService.create(ModelObjects.createGoodCustomer());
        return accountService.create(ModelObjects.createGoodAccount(customer));
    }
}
