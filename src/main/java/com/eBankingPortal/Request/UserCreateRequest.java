package com.eBankingPortal.Request;

import lombok.Data;

@Data
public class UserCreateRequest {
    private String userName;
    private String password;
}
