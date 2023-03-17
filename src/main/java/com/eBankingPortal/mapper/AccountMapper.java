package com.eBankingPortal.mapper;

import com.eBankingPortal.dto.AccountDTO;
import com.eBankingPortal.models.Account;

import org.springframework.beans.BeanUtils;

public class AccountMapper extends BaseMapper<Account, AccountDTO> {
    @Override
    public Account convertToEntity(AccountDTO dto, Object... args) {
        Account entity = new Account();
        if (dto != null) {
            BeanUtils.copyProperties(dto, entity);
        }
        return entity;
    }

    @Override
    public AccountDTO convertToDto(Account entity, Object... args) {
        AccountDTO dto = new AccountDTO();
        if (entity != null) {
            BeanUtils.copyProperties(entity, dto);
        }
        return dto;
    }
}
