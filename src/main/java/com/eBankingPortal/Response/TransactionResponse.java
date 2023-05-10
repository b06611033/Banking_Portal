package com.eBankingPortal.Response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TransactionResponse {
    private BigDecimal newBalance;
    private String message;
}
