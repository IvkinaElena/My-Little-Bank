package com.example.my.little.bank.controller;

import com.example.my.little.bank.dto.AccountDto;
import com.example.my.little.bank.dto.AccountMapper;
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


@Controller
public class AccountController {
    private final CustomerService customerService;
    private final AccountService accountService;
    private AccountMapper accountMapper;

    @Autowired
        public AccountController(AccountService accountService, CustomerService customerService, AccountMapper accountMapper){
        this.accountService = accountService;
        this.customerService = customerService;
        this.accountMapper = accountMapper;
    }

    @GetMapping("/customer/{id_customer}/accounts")
    public String showAddAccountForm(@PathVariable("id_customer") Long id_customer, Model model, AccountDto accountDto) {
        model.addAttribute("customer", this.customerService.findById(id_customer));
        return "add-account";
    }

    @PostMapping("/customer/{id_customer}/new_account")
    public String addAccount(@PathVariable("id_customer") Long id_customer,  Model model, AccountDto accountDto, BindingResult result) {
        if (result.hasErrors()) {
            return "add-account";
        }

        Account account = AccountMapper.INSTANCE.accountDtoToAccount(accountDto);
        this.accountService.create(account);
        return "redirect:/customer/{id_customer}";
    }
}
