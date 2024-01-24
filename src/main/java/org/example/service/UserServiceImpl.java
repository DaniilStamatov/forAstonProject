package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.User;
import org.example.exceptions.EntityAlreadyExistsException;
import org.example.exceptions.EntityDoesNotExistException;
import org.example.exceptions.ValidationException;
import org.example.exceptions.WrongPasswordException;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public User addUser(User user) {
        validateUser(user);
        return userRepository.addUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public User updateUser(String login, String newPassword) {
        User savedUser =  userRepository.getAllUsers()
                .stream()
                .filter(user -> user.getLogin().equals(login))
                .findAny()
                .orElseThrow(()-> new EntityDoesNotExistException("Пользователя с таким login не существует"));
        validatePassword(newPassword, savedUser);
        savedUser.setPassword(newPassword);
        return savedUser;
    }

    @Override
    public User enterToAccount(String login, String password) {
        User enteredUser = userRepository.getAllUsers()
                .stream()
                .filter(user -> user.getLogin().equals(login))
                .findAny()
                .orElseThrow(()-> new EntityDoesNotExistException("Пользователя с таким login не существует"));

        if(!enteredUser.getPassword().equals(password)){
            throw new WrongPasswordException("НЕВЕРНЫЙ ПАРОЛЬ");
        }
        return enteredUser;
    }



    private void validateUser(User user){
        if(user.getLogin().isEmpty() || user.getPassword().isEmpty()){
            throw new ValidationException("Логин и пароль не могут быть пустыми");
        }
        if(userRepository.getAllUsers().contains(user)){
            throw new EntityAlreadyExistsException("Такой пользователь уже существует");
        }
    }

    private void validatePassword(String password, User user){
        if(password.isBlank()) {
            throw new ValidationException("пароль не может быть пустым");
        }
        if(user.getPassword().equals(password)){
            throw new ValidationException("Введите новый пароль, не повторяющийся со старым");
        }
    }



}
