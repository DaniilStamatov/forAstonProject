package org.example.service;

import org.example.dto.User;

import java.util.List;

public interface UserService {
    User addUser(User user);

    List<User> getAllUsers();

    User updateUser(String login, String newPassword);

    User enterToAccount(String login, String password);
}
