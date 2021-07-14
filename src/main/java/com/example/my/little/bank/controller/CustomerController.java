package com.example.my.little.bank.controller;

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

@Controller
public class CustomerController {
    private final CustomerService customerService;
    private final AccountService accountService;

    @Autowired
    public CustomerController(CustomerService customerService,AccountService accountService ){
        this.customerService = customerService;
        this.accountService = accountService;
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
        model.addAttribute("customers", this.customerService.findAll());
        return "index";
    }

    @GetMapping("/customer/{id}")
    public String getCustomer(@PathVariable("id") Long id, Model model) {
        model.addAttribute("customer", this.customerService.findById(id));
        model.addAttribute("accounts", this.accountService.findByIdOwner(id));
        return "customer";
    }
}
