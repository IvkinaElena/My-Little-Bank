package com.example.my.little.bank.controller;

import com.example.my.little.bank.exception.NotFoundException;
import com.example.my.little.bank.dto.CustomerDto;
import com.example.my.little.bank.dto.CustomerMapper;
import com.example.my.little.bank.models.Customer;
import com.example.my.little.bank.services.AccountService;
import com.example.my.little.bank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {
    private final CustomerService customerService;
    private final AccountService accountService;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerController(CustomerService customerService,AccountService accountService, CustomerMapper customerMapper){
        this.customerService = customerService;
        this.accountService = accountService;
        this.customerMapper = customerMapper;
    }

    @GetMapping("/signup")
    public String showSignUpForm(Customer customer) {
        return "add-customer";
    }

    @PostMapping("/addcustomer")
    public String addCustomer(@Valid Customer customer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-customer";
        }

        this.customerService.create(customer);
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String showUserList(Model model) {
        List<CustomerDto> listCustomerDto = new ArrayList();
        for(Customer customer : this.customerService.findAll()) {
            CustomerDto customerDto = customerMapper.customerToCustomerDto(customer);
            listCustomerDto.add(customerDto);
        }
        model.addAttribute("customers", listCustomerDto);
        return "index";
    }

    @GetMapping("/customer/{id}")
    public String getCustomer(@PathVariable("id") Long id, Model model) throws NotFoundException {
        model.addAttribute("customer", this.customerService.findById(id));
        model.addAttribute("accounts", this.accountService.findByIdOwner(id));
        return "customer";
    }
}
