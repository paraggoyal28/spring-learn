package com.example.spring.librarymanagementsystem.models;

public class Person {
    private String name;
    private String email;
    private String phone;
    private String address;

    public Person(String name, String email, String phone, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getAddress() {
        return this.address;
    }
}
