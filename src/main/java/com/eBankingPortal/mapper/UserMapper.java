package com.eBankingPortal.mapper;

import com.eBankingPortal.dto.UserDTO;
import com.eBankingPortal.entity.User;
import org.springframework.beans.BeanUtils;

public class UserMapper extends BaseMapper<User, UserDTO> {
    private AccountMapper AccountMapper = new AccountMapper();

    @Override
    public User convertToEntity(UserDTO dto, Object... args) {
        User entity = new User();
        if (dto != null) {
            BeanUtils.copyProperties(dto, entity);
            entity.setAccounts(AccountMapper.convertToEntityList(dto.getAccounts()));
        }
        return entity;
    }

    @Override
    public UserDTO convertToDto(User entity, Object... args) {
        UserDTO dto = new UserDTO();
        if (entity != null) {
            BeanUtils.copyProperties(entity, dto);
            dto.setAccounts(AccountMapper.convertToDtoList(entity.getAccounts()));
        }
        return dto;
    }
}
