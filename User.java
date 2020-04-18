package com.example.myfoodapp;

public class User {
    String name;
    String email;
    String number;

    public String getName() {
        return name;
    }

    public User(String name, String email, String number) {
        this.name = name;
        this.email = email;
        this.number = number;
    }
}
