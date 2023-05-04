package com.eBankingPortal.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.eBankingPortal.Request.UserCreateRequest;
import com.eBankingPortal.models.Customer;
import com.eBankingPortal.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    private final UserRepository userRepository;

    public Customer readUserByUsername(String username) {
        // todo: Handle Exception
        return userRepository.findByuserNameEquals(username);
    }

    public void createUser(UserCreateRequest userCreateRequest) {
        Customer user = new Customer();
        Customer checkUser = userRepository.findByuserNameEquals(userCreateRequest.getUserName());
        if (checkUser != null) {
            throw new RuntimeException("User already registered. Please use different username.");
        }
        user.setUserName(userCreateRequest.getUserName());
        user.setPassword(userCreateRequest.getPassword());
        userRepository.save(user);
    }
}
