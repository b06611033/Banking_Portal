package com.eBankingPortal.Response;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

// reponse for client's query
@Data
public class TransactionResponse {
    private Long account_id;

    private Long transaction_id;

    private BigDecimal balance;

    private BigDecimal amount;

    private String currency;

    private String type;

    private LocalDate transactionDate;
}
