package com.eBankingPortal.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransactionDTO {
    private Long transaction_id;
    private String IBAN;
    private String currency;
    private BigDecimal amount;
    private String type;
    private LocalDate transactionDate;
    private AccountDTO account;
}
