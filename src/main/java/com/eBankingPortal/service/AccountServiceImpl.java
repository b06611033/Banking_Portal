package com.eBankingPortal.service;

import com.eBankingPortal.Request.StatementRequest;
import com.eBankingPortal.Response.StatementResponse;
import com.eBankingPortal.models.Account;
import com.eBankingPortal.repository.AccountRepository;
import com.eBankingPortal.repository.TransactionRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AccountServiceImpl {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    private final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    public StatementResponse getStatement(StatementRequest request) {
        log.info("AccountService Statement");
        Account account = accountRepository.findByaccountIdEquals(request.getAccount_id());
        String IBAN = account.getIBAN_id();
        return new StatementResponse(account.getBalance(),
                transactionRepository.findByIBANEquals(IBAN));
    }
}
