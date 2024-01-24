package org.example.repository;

import org.example.dto.User;

import java.util.List;

public interface UserRepository {
    User addUser(User user);

    List<User> getAllUsers();
}
