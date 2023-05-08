package com.eBankingPortal.service;

import com.eBankingPortal.Request.AccountCreateRequest;
import com.eBankingPortal.Request.GetAccountRequest;
import com.eBankingPortal.Request.StatementRequest;
import com.eBankingPortal.Response.StatementResponse;
import com.eBankingPortal.models.Account;
import com.eBankingPortal.models.User;
import com.eBankingPortal.repository.AccountRepository;
import com.eBankingPortal.repository.TransactionRepository;
import com.eBankingPortal.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl {

    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    private final UserRepository userRepository;

    private final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    public void createAccount(AccountCreateRequest request) {
        log.info("AccountService creating account");
        Account account = new Account();
        Account checkAccount = accountRepository.findByIBANEquals(request.getIBAN());
        if (checkAccount != null) {
            throw new RuntimeException("Use a different account IBAN");
        }
        User checkUser = userRepository.findByuserNameEquals(request.getUserName());
        if (checkUser == null) {
            throw new RuntimeException("User does not exist");
        }
        LocalDateTime now = LocalDateTime.now();
        account.setUserName(request.getUserName());
        account.setIBAN(request.getIBAN());
        account.setCurrency(request.getCurrency());
        BigDecimal balance = new BigDecimal(0);
        account.setBalance(balance);
        account.setCreateDate(now);
        accountRepository.save(account);
    }

    public List<Account> getAccounts(GetAccountRequest request) {
        log.info("AccountService getting account");
        log.info("UserName is: " + request.getUserName());
        User checkUser = userRepository.findByuserNameEquals(request.getUserName());
        if (checkUser == null) {
            throw new RuntimeException("User does not exist");
        }
        List<Account> accounts = accountRepository.findByuserNameEquals(request.getUserName());
        return accounts;
    }

    public StatementResponse getStatement(StatementRequest request) {
        log.info("AccountService getting statement");
        Account account = accountRepository.findByIBANEquals(request.getIBAN());
        String IBAN = account.getIBAN();
        return new StatementResponse(account.getBalance(),
                transactionRepository.findByIBANEquals(IBAN));
    }
}
