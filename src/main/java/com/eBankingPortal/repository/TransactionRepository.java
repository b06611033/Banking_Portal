package com.eBankingPortal.repository;

import com.eBankingPortal.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // optional class is used because user might be null(not found)
    Optional<Transaction> findById(Long transaction_id);
}
