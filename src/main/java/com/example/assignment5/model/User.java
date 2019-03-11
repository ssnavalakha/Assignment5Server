package com.example.assignment5.model;
import javax.persistence.*;
import java.io.Serializable;

@Entity
public class User implements Serializable {
    @Id
    private long id;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String role;
    private int phoneNumber;

    public User() {}
    protected User(String username,String password, String firstName, String lastName,String role,long id
            ,int phoneNumber) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password=password;
        this.setRole(role);
        this.phoneNumber=phoneNumber;
    }
    public long getId() {
        return id;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
