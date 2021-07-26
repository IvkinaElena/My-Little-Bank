package com.example.my.little.bank.dto;

import com.example.my.little.bank.models.Account;
import com.example.my.little.bank.models.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import javax.persistence.EntityNotFoundException;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper( TransactionMapper.class );

    @Mapping(source = "account", target = "idAccount", qualifiedByName = "fromAccountToLong")
    TransactionDto transactionToTransactionDto(Transaction transaction);

    @Named("fromAccountToLong")
    static Long fromAccountToLong(Account account) throws EntityNotFoundException {
        return account.getIdAccount();
    }
}
