package com.eBankingPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eBankingPortal.models.Customer;

public interface UserRepository extends JpaRepository<Customer, Long> {
    Customer findByuserIdEquals(Long userId);

    Customer findByuserNameEquals(String userName);
}
