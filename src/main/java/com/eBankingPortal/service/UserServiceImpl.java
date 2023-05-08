package com.eBankingPortal.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

import com.eBankingPortal.Request.UserCreateRequest;
import com.eBankingPortal.models.User;
import com.eBankingPortal.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    private final UserRepository userRepository;

    public void createUser(UserCreateRequest userCreateRequest) {
        User user = new User();
        User checkUser = userRepository.findByuserNameEquals(userCreateRequest.getUserName());
        if (checkUser != null) {
            throw new RuntimeException("User already registered. Please use different username.");
        }
        LocalDateTime now = LocalDateTime.now();
        user.setUserName(userCreateRequest.getUserName());
        user.setPassword(userCreateRequest.getPassword());
        user.setCreateDate(now);
        userRepository.save(user);
    }
}
