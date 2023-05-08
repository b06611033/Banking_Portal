package com.eBankingPortal.Request;

import lombok.Data;

@Data
public class AccountCreateRequest {
    private String userName;
    private String currency;
    private String IBAN;
}
