package com.eBankingPortal.dto;

import lombok.Data;

// request from client to see transactions of a certain calendar month
@Data
public class TransactionRequest {
    private Long accountId;
    private int year;
    private int month;
}
