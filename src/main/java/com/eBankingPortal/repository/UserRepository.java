package com.eBankingPortal.repository;

import com.eBankingPortal.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Customer, Long> {
    Customer findByuserIdEquals(Long userId);

    Customer findByuserNameEquals(String userName);
}
