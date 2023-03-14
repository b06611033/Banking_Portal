package com.eBankingPortal.repository;

import com.eBankingPortal.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    // optional class is used because user might be null(not found)
    Optional<Account> findById(Long account_id);
}
