package com.example.my.little.bank.services;

import com.example.my.little.bank.models.Customer;
import com.example.my.little.bank.models.ModelObjects;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CustomerServiceIT {

    @Autowired
    private CustomerService customerService;

    @Test
    @Transactional
    public void createCustomer() {
        Customer createdCustomer = createNewCustomer();

        assertNotNull(createdCustomer.getIdCustomer());
    }

    @Test
    @Transactional
    public void findCustomerById() {
        Customer createdCustomer = createNewCustomer();
        Long idCustomer = createdCustomer.getIdCustomer();

        Customer customerFromDb = customerService.findById(idCustomer);
        assertEquals(idCustomer, customerFromDb.getIdCustomer());
        assertEquals(createdCustomer.getFirstname(), customerFromDb.getFirstname());
    }

    @Test
    @Transactional
    public void findAllCustomers() {
        createNewCustomer();
        createNewCustomer();

        assertEquals(2, customerService.findAll().size());
    }

    private Customer createNewCustomer() {
        return customerService.create(ModelObjects.createGoodCustomer());
    }
}
