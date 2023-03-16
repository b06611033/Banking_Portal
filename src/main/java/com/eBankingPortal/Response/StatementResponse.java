package com.eBankingPortal.Response;

import java.util.List;
import java.math.BigDecimal;
import com.eBankingPortal.entity.Transaction;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StatementResponse {
    BigDecimal balance;
    List<Transaction> transactionHistory;
}
