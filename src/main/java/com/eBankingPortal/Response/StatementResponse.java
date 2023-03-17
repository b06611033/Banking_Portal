package com.eBankingPortal.Response;

import java.util.List;

import com.eBankingPortal.models.Transaction;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StatementResponse {
    BigDecimal balance;
    List<Transaction> transactionHistory;
}
