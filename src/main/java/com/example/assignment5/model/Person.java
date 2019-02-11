package com.example.assignment5.model;

public class Person extends User{
    public Person() {}
    public Person(String username,String password, String firstName, String lastName,String role,
                  long id,int phoneNumber) {
        super(username,password,firstName,lastName,role,id,phoneNumber);
    }
}
