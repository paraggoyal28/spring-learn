package com.example.spring.librarymanagementsystem.models;

import com.example.spring.librarymanagementsystem.enums.AccountStatus;

public abstract class Account {
    private String id;
    private String password;
    private AccountStatus status;
    private Person person;

    public Account(String id, String password, Person person) {
        this.id = id;
        this.password = password;
        this.person = person;
    }

    public boolean resetPassword(String newPassword) {
        this.password = newPassword;
        return true;
    }

    public String getId() {
        return this.id;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus accountStatus) {
        this.status = accountStatus;
    }

    public Person getPerson() {
        return person;
    }
}
