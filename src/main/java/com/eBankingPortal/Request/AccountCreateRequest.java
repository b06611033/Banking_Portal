package com.eBankingPortal.Request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AccountCreateRequest {
    private String userName;
    private String currency;
    private String iban;
    private BigDecimal amount;
}
