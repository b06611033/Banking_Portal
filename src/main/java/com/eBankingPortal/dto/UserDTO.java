package com.eBankingPortal.dto;

import lombok.Data;

import java.util.List;

// only provides relevant information to the client
// don't need to provide password
@Data
public class UserDTO {
    private Long user_id;
    private List<AccountDTO> accounts;
}
