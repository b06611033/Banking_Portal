package com.eBankingPortal.mapper;

import com.eBankingPortal.dto.TransactionDTO;
import com.eBankingPortal.models.Transaction;

import org.springframework.beans.BeanUtils;

public class TransactionMapper extends BaseMapper<Transaction, TransactionDTO> {
    @Override
    public Transaction convertToEntity(TransactionDTO dto, Object... args) {
        Transaction entity = new Transaction();
        if (dto != null) {
            BeanUtils.copyProperties(dto, entity);
        }
        return entity;
    }

    @Override
    public TransactionDTO convertToDto(Transaction entity, Object... args) {
        TransactionDTO dto = new TransactionDTO();
        if (entity != null) {
            BeanUtils.copyProperties(entity, dto);
        }
        return dto;
    }
}
