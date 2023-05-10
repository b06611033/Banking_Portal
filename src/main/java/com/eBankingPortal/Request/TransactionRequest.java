package com.eBankingPortal.Request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TransactionRequest {
    private String fromIBAN;
    private String toIBAN;
    private BigDecimal amount;

}
