package com.eBankingPortal.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.eBankingPortal.Request.StatementRequest;
import com.eBankingPortal.Request.TransactionRequest;
import com.eBankingPortal.Response.StatementResponse;
import com.eBankingPortal.Response.TransactionResponse;
import com.eBankingPortal.service.AccountServiceImpl;

@RestController
@RequestMapping(path = "api/account") // api prefix
@Api(tags = "Account api") // api documentation with swagger
public class AccountController {

    private final Logger log = LoggerFactory.getLogger(AccountController.class);

    // autowire to instantiate accountService (can only autowire Spring beans)
    @Autowired
    private AccountServiceImpl AccountService;

    // handling post request
    @GetMapping("/transactions")
    @ApiOperation(value = "acquire transactions", response = TransactionResponse.class, notes = "account must exist")
    @ApiResponses({
            @ApiResponse(code = 401, message = "unauthorized request"),
            @ApiResponse(code = 404, message = "the path doesn't exist")
    })
    public List<TransactionResponse> getTransactionsMesseage(@RequestBody TransactionRequest request) {
        log.info("acquiring transactions");
        try {
            List<TransactionResponse> responseList = AccountService.TransactionPage(request);
            return responseList;
        } catch (Exception e) {
            log.info("error to acquire transactions");
            return null;
        }
    }

    // handling post request
    @GetMapping("/statement")
    @ApiOperation(value = "acquire statement", response = StatementResponse.class, notes = "account must exist")
    @ApiResponses({
            @ApiResponse(code = 401, message = "unauthorized request"),
            @ApiResponse(code = 404, message = "the path doesn't exist")
    })
    public StatementResponse getStatementMesseage(@RequestBody StatementRequest request) {
        log.info("acquiring statement");
        try {
            StatementResponse statement = AccountService.getStatement(request);
            return statement;
        } catch (Exception e) {
            log.info("error to acquire statement");
            return null;
        }
    }
}