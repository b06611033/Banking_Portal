package com.eBankingPortal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eBankingPortal.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByaccountIdEquals(Long accountId);

    Account findByIBANEquals(String IBAN);

    List<Account> findByuserNameEquals(String userName);
}
