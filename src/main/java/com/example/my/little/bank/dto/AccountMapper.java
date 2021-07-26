package com.example.my.little.bank.dto;

import com.example.my.little.bank.models.Account;
import com.example.my.little.bank.models.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import javax.persistence.EntityNotFoundException;

@Mapper(componentModel = "spring",  uses = {TransactionMapper.class})
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper( AccountMapper.class );

    @Mapping(source = "account.customer", target = "idCustomer", qualifiedByName = "fromCustomerToLong")
    AccountDto accountToAccountDto(Account account);

    @Mapping(source = "idCustomer", target = "customer.idCustomer")
    Account accountDtoToAccount(AccountDto accountDto);

    @Named("fromCustomerToLong")
    static Long fromCustomerToLong(Customer customer) throws EntityNotFoundException {
        return customer.getIdCustomer();
    }
}
