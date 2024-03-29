package com.eBankingPortal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import com.eBankingPortal.Request.UserCreateRequest;
import com.eBankingPortal.service.UserServiceImpl;

@RestController
@RequestMapping(value = "/api/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @PostMapping("/create")
    public String createUser(@RequestBody UserCreateRequest userCreateRequest) {
        try {
            userService.createUser(userCreateRequest);
            return "user created successfully, your userName is: " + userCreateRequest.getUserName();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
