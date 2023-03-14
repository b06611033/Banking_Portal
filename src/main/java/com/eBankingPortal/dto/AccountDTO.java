package com.eBankingPortal.dto;

import lombok.Data;
import java.util.List;

@Data
public class AccountDTO {
    private Long account_id;
    private String IBAN_id;
    private List<TransactionDTO> transactions;
    private UserDTO user;
}
