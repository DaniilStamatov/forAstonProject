package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.User;
import org.example.service.UserService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user){
        User savedUser = userService.addUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatusCode.valueOf(200));
    }

    @PatchMapping
    public ResponseEntity<User> updateUser(@RequestParam String login, @RequestHeader String password){
        User user = userService.updateUser(login,password);
        return new ResponseEntity<>(user, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/login")
    public ResponseEntity<User> enterAccount(@RequestHeader String login, @RequestHeader String password){
        User user = userService.enterToAccount(login, password);
        return new ResponseEntity<>(user, HttpStatusCode.valueOf(200));
    }


}
