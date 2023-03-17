package com.eBankingPortal.mapper;

import com.eBankingPortal.dto.UserDTO;
import com.eBankingPortal.models.Customer;

import org.springframework.beans.BeanUtils;

public class UserMapper extends BaseMapper<Customer, UserDTO> {
    private AccountMapper AccountMapper = new AccountMapper();

    @Override
    public Customer convertToEntity(UserDTO dto, Object... args) {
        Customer entity = new Customer();
        if (dto != null) {
            BeanUtils.copyProperties(dto, entity);
            entity.setAccounts(AccountMapper.convertToEntityList(dto.getAccounts()));
        }
        return entity;
    }

    @Override
    public UserDTO convertToDto(Customer entity, Object... args) {
        UserDTO dto = new UserDTO();
        if (entity != null) {
            BeanUtils.copyProperties(entity, dto);
            dto.setAccounts(AccountMapper.convertToDtoList(entity.getAccounts()));
        }
        return dto;
    }
}
