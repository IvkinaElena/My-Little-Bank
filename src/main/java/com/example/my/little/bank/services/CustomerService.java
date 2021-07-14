package com.example.my.little.bank.services;

import com.example.my.little.bank.models.Customer;
import com.example.my.little.bank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Transactional
    public Customer findById(Long id) {
        return customerRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unknown user id: " + id));
    }

    @Transactional
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }



}
