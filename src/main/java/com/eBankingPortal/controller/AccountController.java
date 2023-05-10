package com.eBankingPortal.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import com.eBankingPortal.Request.AccountCreateRequest;
import com.eBankingPortal.Request.GetAccountRequest;
import com.eBankingPortal.Request.StatementRequest;
import com.eBankingPortal.Request.TransactionRequest;
import com.eBankingPortal.Response.StatementResponse;
import com.eBankingPortal.Response.TransactionResponse;
import com.eBankingPortal.models.Account;
import com.eBankingPortal.service.AccountServiceImpl;

@RestController
@RequestMapping(path = "api/account") // api prefix
@Api(tags = "Account api") // api documentation with swagger
@RequiredArgsConstructor
public class AccountController {

    private final Logger log = LoggerFactory.getLogger(AccountController.class);

    private final AccountServiceImpl accountService;

    // handling post request
    @PostMapping("/create")
    @ApiOperation(value = "create account", response = String.class, notes = "user must exist")
    @ApiResponses({
            @ApiResponse(code = 401, message = "unauthorized request"),
            @ApiResponse(code = 404, message = "the path doesn't exist")
    })
    public String createAccount(@RequestBody AccountCreateRequest request) {
        log.info("creating account");
        try {
            accountService.createAccount(request);
            return "account created successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // handling get request
    @GetMapping("/accounts")
    public List<Account> getAccounts(@RequestBody GetAccountRequest request) {
        log.info("getting all accounts of the user");
        try {
            List<Account> accounts = accountService.getAccounts(request);
            return accounts;
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }

    // handling post request
    @PostMapping("/transfer")
    @ApiOperation(value = "transfer money", response = TransactionResponse.class, notes = "both accounts must exist")
    @ApiResponses({
            @ApiResponse(code = 401, message = "unauthorized request"),
            @ApiResponse(code = 404, message = "the path doesn't exist")
    })
    public TransactionResponse createTransaction(@RequestBody TransactionRequest request) {
        log.info("transfering money");
        try {
            TransactionResponse response = accountService.handleTransaction(request);
            return response;
        } catch (Exception e) {
            log.info(e.getMessage());
            return new TransactionResponse(null, e.getMessage());
        }
    }

    // handling get request
    @GetMapping("/statement")
    @ApiOperation(value = "acquire statement", response = StatementResponse.class, notes = "account must exist")
    @ApiResponses({
            @ApiResponse(code = 401, message = "unauthorized request"),
            @ApiResponse(code = 404, message = "the path doesn't exist")
    })
    public StatementResponse getStatementMesseage(@RequestBody StatementRequest request) {
        log.info("acquiring statement");
        try {
            StatementResponse statement = accountService.getStatement(request);
            return statement;
        } catch (Exception e) {
            log.info(e.getMessage());
            return new StatementResponse(null, null, e.getMessage());
        }
    }
}