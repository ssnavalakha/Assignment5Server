package com.example.assignment5.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.assignment5.model.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserService {

    User alice = new Faculty("alice","sanket", "Alice", "Wonderland",0,
            999);
    User bob   = new Faculty("bob","sanket", "Bob", "Marley",
            1,311);

    ArrayList<User> users = new ArrayList<User>() {
        {
            add(alice);
            add(bob);
        }
    };
    @PostMapping(path = "/api/register")
    public User register(@RequestBody User user,HttpSession session) {
        if(!users.stream().allMatch(x->x.getUsername().equals(user.getUsername())))
        {
            users.add(user);
            session.setAttribute("currentUser",user);
            return user;
        }
        else
            return null;
    }
    @PostMapping(path = "/api/profile"
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public User profile(HttpSession session) {
        return (User)session.getAttribute("currentUser");
    }

    @PostMapping(path = "/api/login")
    public User login(@RequestBody User user,HttpSession session) {
        Optional<User> p=users.stream().filter(x->x.getUsername().equals(user.getUsername()) &&
                x.getPassword().equals(user.getPassword())).findFirst();
        if(p.isPresent())
        {
            p.ifPresent(y->{
                user.setId(y.getId());
                session.setAttribute("currentUser",user);
            });
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
        return users;
    }

    @GetMapping("/api/users/{id}")
    public User findUserById(
            @PathVariable("id") Integer id) {
        for(User user: users) {
            if(id == user.getId())
                return user;
        }
        return null;
    }

    @PutMapping(path = "/api/updateUser/{userId}")
    public User updateUser(@PathVariable("userId")Integer id,
                           @RequestBody User user) {
        Optional<User> x= users.stream().filter(t->t.getId()==id).findFirst();
        if(x.isPresent())
            x.ifPresent(y->{
                y.setFirstName(user.getFirstName());
                y.setUsername(user.getUsername());
                y.setId(user.getId());
                y.setLastName(user.getLastName());
                y.setPassword(user.getPassword());
                y.setRole(user.getRole());
                y.setPhoneNumber(user.getPhoneNumber());
            });
        return user;
    }
}