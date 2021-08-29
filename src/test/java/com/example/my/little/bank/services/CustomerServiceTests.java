package com.example.my.little.bank.services;

import com.example.my.little.bank.exception.NotFoundException;
import com.example.my.little.bank.models.Customer;
import com.example.my.little.bank.repository.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static com.example.my.little.bank.models.ModelObjects.createCustomer;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CustomerServiceTests {
    @SpyBean
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("Создание пользователя - успех")
    public void createCustomer_success() {
        Customer customer = createCustomer();

        customerService.create(customer);

        verify(customerRepository).save(customer);
    }

    @Test
    @DisplayName("Поиск по ID - успех")
    public void findById_success() {
        Customer customer = createCustomer();
        when(customerRepository.findById(any()))
                .thenReturn(Optional.of(customer));

        var expectedCustomer = customerService.findById(1L);

        assertNotNull(expectedCustomer);
    }

    @Test
    @DisplayName("Поиск по ID - пользователь не найден")
    public void findById_customerNotFound_exception() {
        when(customerRepository.findById(any()))
                .thenReturn(Optional.empty());

        var exception = assertThrows(
                NotFoundException.class,
                () -> customerService.findById(1L)
        );
        assertEquals("Unknown id 1", exception.getMessage());
    }

    @Test
    @DisplayName("Поиск всех пользователей - успех")
    public void findAll_success() {
        List<Customer> customers = new ArrayList<>();

        when(customerRepository.findAll())
                .thenReturn(customers);

        var expectedCustomers = customerService.findAll();
        assertNotNull(expectedCustomers);
    }
}
