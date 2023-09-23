package com.dev.budget_book.service;

import com.dev.budget_book.mapper.UserRepo;
import com.dev.budget_book.model.FixedInOut;
import com.dev.budget_book.model.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    public List<Users> allUsers() {
        return userRepo.allUsers();
    }

    public FixedInOut getFixedInOut(String userId) {

        return userRepo.getFixedInOut(userId);
    }

    public HashMap<String, Object> login(Users user) {

        HashMap<String, Object> result = new HashMap<>();
        result.put("rst", userRepo.login(user));

        return result;
    }
    public HashMap<String, Object> pwChange(Users user) {

        HashMap<String, Object> result = new HashMap<>();
        result.put("rst", userRepo.pwChange(user));

        return result;
    }
}
