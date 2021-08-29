package com.example.my.little.bank.models;

public class ModelObjects {
    public static Account createAccount() {
        return new Account();
    }
    public static Customer createCustomer() {
        return new Customer();
    }
    public static Transaction createTransaction() {
        return new Transaction();
    }
}
