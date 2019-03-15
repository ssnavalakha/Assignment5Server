package com.example.assignment5.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.assignment5.model.*;
import com.example.assignment5.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
public class UserService {

    @Autowired
    UserRepository repo;


    @PostMapping(path = "/api/register")
    public User register(@RequestBody User user,HttpSession session) {
        if(!repo.existsByUsername(user.getUsername()))
        {
            repo.save(user);
            session.setAttribute("currentUser",user);
            return user;
        }
        else
            return null;
    }
    @GetMapping(path = "/api/profile"
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public User profile(HttpSession session) {
        return (User)session.getAttribute("currentUser");
    }

    @PostMapping(path = "/api/login")
    public User login(@RequestBody User user,HttpSession session) {
        List<User> p=repo.findUserByCredentials(user.getUsername(),user.getPassword());
        if(p.size()>0)
        {
            User y= p.get(0);
            user.setId(y.getId());
            user.setPhoneNumber(y.getPhoneNumber());
            user.setRole(y.getRole());
            user.setFirstName(y.getFirstName());
            user.setLastName(y.getLastName());
            user.setPassword(y.getPassword());
            session.setAttribute("currentUser",user);
            return user;
        }
        else
            return null;
    }

    @PostMapping("/api/logout")
    public void logout
            (HttpSession session) {
        session.invalidate();
    }


    @GetMapping("/api/users")
    public List<User> findAllUser() {
        List all=new ArrayList<User>();
        repo.findAll().forEach(all::add);
        return all;
    }

    @GetMapping("/api/users/{id}")
    public User findUserById(
            @PathVariable("id") Long id) {
        Optional<User> u=repo.findById(id);
        if(u.isPresent())
            return u.get();
        return null;
    }

    @PutMapping(path = "/api/updateUser/{userId}")
    public User updateUser(@PathVariable("userId")Integer id,
                           @RequestBody User user) {
        Optional<User> x= repo.findById(user.getId());
        if(x.isPresent())
            x.ifPresent(y->{
                y.setFirstName(user.getFirstName());
                y.setUsername(user.getUsername());
                y.setId(user.getId());
                y.setLastName(user.getLastName());
                y.setPassword(user.getPassword());
                y.setRole(user.getRole());
                y.setPhoneNumber(user.getPhoneNumber());
                repo.save(y);
            });
        return user;
    }
}