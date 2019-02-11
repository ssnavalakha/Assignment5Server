package com.example.assignment5.model;

public class Student extends Person {
    public Student() {}
    public Student(String username,String password, String firstName, String lastName,
                   long id,int phoneNumber) {
        super(username,password,firstName,lastName,"Student",id,phoneNumber);
    }
}
