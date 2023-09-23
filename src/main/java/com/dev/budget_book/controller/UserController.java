package com.dev.budget_book.controller;

import com.dev.budget_book.model.FixedInOut;
import com.dev.budget_book.model.Users;
import com.dev.budget_book.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/allUsers")
    public List<Users> allUsers() {
        return userService.allUsers();
    }

    @PostMapping("/getFixedInOut")
    public FixedInOut getFixedInOut(@RequestBody Users user) {
        return userService.getFixedInOut(user.getEmail());
    }

    @PostMapping("/login")
    public HashMap<String, Object> login(@RequestBody Users user) {
        return userService.login(user);
    }

    @PostMapping("/pwChange")
    public HashMap<String, Object> pwChange(@RequestBody Users user) {
        return userService.pwChange(user);
    }
}
