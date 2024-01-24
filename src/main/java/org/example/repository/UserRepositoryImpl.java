package org.example.repository;

import org.example.dto.User;
import org.example.exceptions.EntityDoesNotExistException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository{

    List<User> users = new ArrayList<>();
    @Override
    public User addUser(User user) {
        users.add(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }
}
