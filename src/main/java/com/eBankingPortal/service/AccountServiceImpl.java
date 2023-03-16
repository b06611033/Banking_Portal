package com.eBankingPortal.service;

import com.eBankingPortal.Request.StatementRequest;
import com.eBankingPortal.Request.TransactionRequest;
import com.eBankingPortal.Response.StatementResponse;
import com.eBankingPortal.Response.TransactionResponse;
import com.eBankingPortal.repository.AccountRepository;
import com.eBankingPortal.repository.TransactionRepository;
import com.eBankingPortal.entity.Account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AccountServiceImpl {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    private final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    public List<TransactionResponse> TransactionPage(TransactionRequest request) {
        log.info("AccountService Transaction Page");
        List<TransactionResponse> list = new ArrayList<TransactionResponse>();
        return list;
    }

    public StatementResponse getStatement(StatementRequest request) {
        log.info("AccountService Statement");
        Account account = accountRepository.findByaccountIdEquals(request.getAccount_id());
        return new StatementResponse(account.getBalance(),
                transactionRepository.findByaccountEquals(request.getAccount_id()));
    }
}
