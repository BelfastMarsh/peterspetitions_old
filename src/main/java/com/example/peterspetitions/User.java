package com.example.peterspetitions;

import java.util.ArrayList;

public class User {

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    private String name;
    private String email;


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

}