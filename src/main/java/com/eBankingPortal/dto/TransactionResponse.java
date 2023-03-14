package com.eBankingPortal.dto;

import java.math.BigDecimal;
import lombok.Data;

// reponse for client's query
@Data
public class TransactionResponse {
    private Long account_id;

    private BigDecimal balance;

    private String currency;
}
