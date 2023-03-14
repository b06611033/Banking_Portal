package com.eBankingPortal.repository;

import com.eBankingPortal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // optional class is used because user might be null(not found)
    Optional<User> findById(Long user_id);
}
