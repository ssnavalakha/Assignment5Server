package com.example.assignment5.repositories;
import com.example.assignment5.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository
        extends CrudRepository<User,Long> {
    @Query("SELECT user from User user WHERE user.username=:username " +
            "AND user.password=:password")
            public List<User> findUserByCredentials
            (@Param("username") String username,
            @Param("password") String password);
    @Query("select count(*)>0 from User user where user.username=:username")
    public Boolean existsByUsername
            (@Param("username") String username);
}
