package com.eBankingPortal.service;

import com.eBankingPortal.Request.AccountCreateRequest;
import com.eBankingPortal.Request.GetAccountRequest;
import com.eBankingPortal.Request.StatementRequest;
import com.eBankingPortal.Request.TransactionRequest;
import com.eBankingPortal.Response.GetAccountResponse;
import com.eBankingPortal.Response.StatementResponse;
import com.eBankingPortal.Response.TransactionResponse;
import com.eBankingPortal.models.Account;
import com.eBankingPortal.models.Transaction;
import com.eBankingPortal.models.User;
import com.eBankingPortal.repository.AccountRepository;
import com.eBankingPortal.repository.TransactionRepository;
import com.eBankingPortal.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
import java.math.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    private final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    public void createAccount(AccountCreateRequest request) {
        log.info("AccountService creating account");
        Account account = new Account();
        User checkUser = userRepository.findByuserNameEquals(request.getUserName());
        if (checkUser == null) {
            throw new RuntimeException("User doesn't exist. Can't create account.");
        }
        Account checkAccount = accountRepository.findByIBANEquals(request.getIban());
        if (checkAccount != null) {
            throw new RuntimeException("Account with this IBAN already exists, IBAN should be unique");
        }
        LocalDateTime now = LocalDateTime.now();
        account.setUserName(request.getUserName());
        account.setIBAN(request.getIban());
        account.setCurrency(request.getCurrency());
        account.setBalance(request.getAmount());
        account.setCreateDate(now);
        accountRepository.save(account);
    }

    public GetAccountResponse getAccounts(GetAccountRequest request) {
        log.info("AccountService getting account");
        log.info("UserName is: " + request.getUserName());
        User checkUser = userRepository.findByuserNameEquals(request.getUserName());
        if (checkUser == null) {
            throw new RuntimeException("User does not exist");
        }
        List<Account> accounts = accountRepository.findByuserNameEquals(request.getUserName());
        String message = "List of accounts that belongs to user: " + request.getUserName();
        return new GetAccountResponse(accounts, message);
    }

    public TransactionResponse handleTransaction(TransactionRequest request) {
        log.info("AccountService handling transcation");
        Account fromAccount = accountRepository.findByIBANEquals(request.getFromIBAN());
        Account toAccount = accountRepository.findByIBANEquals(request.getToIBAN());
        if (fromAccount == null) {
            throw new RuntimeException("Account doesn't exist");
        }
        if (toAccount == null) {
            throw new RuntimeException("Destination account doesn't exist");
        }
        BigDecimal amount = request.getAmount();
        if (fromAccount.getBalance().compareTo(amount) == -1) {
            throw new RuntimeException("Balance is not enough to make transaction");
        }
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        accountRepository.save(fromAccount);
        toAccount.setBalance(toAccount.getBalance().add(amount));
        accountRepository.save(toAccount);
        LocalDateTime now = LocalDateTime.now();
        Transaction fromTransaction = new Transaction();
        String fromDescription = "Transfered to: " + toAccount.getIBAN();
        fromTransaction.setAmount(amount.negate());
        fromTransaction.setCurrency(fromAccount.getCurrency());
        fromTransaction.setIBAN(fromAccount.getIBAN());
        fromTransaction.setDescription(fromDescription);
        fromTransaction.setTransactionDate(now);
        transactionRepository.save(fromTransaction);
        Transaction toTransaction = new Transaction();
        String toDescription = "Deposited from: " + fromAccount.getIBAN();
        toTransaction.setAmount(amount);
        toTransaction.setCurrency(toAccount.getCurrency());
        toTransaction.setIBAN(toAccount.getIBAN());
        toTransaction.setDescription(toDescription);
        toTransaction.setTransactionDate(now);
        transactionRepository.save(toTransaction);

        String message = "sent money successfully from " + fromAccount.getUserName() + " to " + toAccount.getUserName()
                + ", your balance is as above";
        return new TransactionResponse(fromAccount.getBalance(), message);
    }

    public StatementResponse getStatement(StatementRequest request) {
        log.info("AccountService getting statement");
        log.info(request.getIban());
        Account account = accountRepository.findByIBANEquals(request.getIban());
        if (account == null) {
            throw new RuntimeException("Query failed, account doesn't exist");
        }
        String IBAN = account.getIBAN();
        return new StatementResponse(account.getBalance(),
                transactionRepository.findByIBANEquals(IBAN), "this is your statement for account: " + IBAN);
    }
}
