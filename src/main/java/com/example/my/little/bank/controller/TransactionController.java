package com.example.my.little.bank.controller;


import com.example.my.little.bank.models.Account;
import com.example.my.little.bank.models.Transaction;
import com.example.my.little.bank.services.AccountService;
import com.example.my.little.bank.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TransactionController {
    private final TransactionService transactionService;
    private final AccountService accountService;

    @Autowired
    public TransactionController(TransactionService transactionService, AccountService accountService){
        this.transactionService = transactionService;
        this.accountService = accountService;
    }

    @GetMapping("/transaction/{id}")
    public String showTransaction(@PathVariable("id") Long id, Model model) {
        model.addAttribute("transactions", this.transactionService.findByIdScore(id));
        model.addAttribute("account", this.accountService.findById(id));
        return "transaction";
    }
}
