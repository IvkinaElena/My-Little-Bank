package com.example.my.little.bank.models;

public class ModelObjects {
    public static Account createAccount() {
        return new Account();
    }

    public static Account createGoodAccount(Customer customer) {
        return Account.builder()
                .customer(customer)
                .balance(100)
                .number(Long.valueOf(123456789))
                .build();
    }

    public static Customer createCustomer() {
        return new Customer();
    }

    public static Customer createGoodCustomer() {
        return Customer.builder()
                .firstname("Igor")
                .lastname("Ivkin")
                .middlename("Mikhailovitch")
                .build();
    }

    public static Transaction createTransaction() {
        return new Transaction();
    }

    public static Transaction createGoodTransaction(Account account) {
        return Transaction.builder()
                .account(account)
                .operation(100)
                .build();
    }
}
