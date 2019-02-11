package com.example.assignment5.model;

public class Faculty extends Person {
    public Faculty() {}
    public Faculty(String username,String password, String firstName, String lastName,
                   long id,int phoneNumber) {
        super(username,password,firstName,lastName,"Faculty",id,phoneNumber);
    }
}
