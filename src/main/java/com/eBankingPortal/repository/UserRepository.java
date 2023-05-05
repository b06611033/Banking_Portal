package com.eBankingPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eBankingPortal.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByuserIdEquals(Long userId);

    User findByuserNameEquals(String userName);
}
