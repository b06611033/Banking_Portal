package com.eBankingPortal.Response;

import java.util.List;

import com.eBankingPortal.models.Account;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GetAccountResponse {
    private List<Account> accountList;
    private String message;
}
