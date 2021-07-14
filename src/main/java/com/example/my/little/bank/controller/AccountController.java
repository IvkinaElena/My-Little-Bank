package com.example.my.little.bank.controller;

import com.example.my.little.bank.models.Account;
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
public class AccountController {
    private final CustomerService customerService;
    private final AccountService accountService;


    @Autowired
    public AccountController(AccountService accountService, CustomerService customerService){
        this.accountService = accountService;
        this.customerService = customerService;
    }

    @GetMapping("/newaccount/{id}")
    public String showAddAccountForm(@PathVariable("id") Long id, Model model, Account account) {
        model.addAttribute("customer", this.customerService.findById(id));
        return "add-account";
    }

    @PostMapping("/addaccount/{id}")
    public String addAccount(@Valid Account account, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-account";
        }

        this.accountService.create(account);
        return "redirect:/index";
    }
}
