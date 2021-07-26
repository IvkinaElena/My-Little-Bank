package com.example.my.little.bank.dto;

import com.example.my.little.bank.models.Account;
import com.example.my.little.bank.models.Customer;
import com.example.my.little.bank.services.AccountService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {AccountMapper.class})
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper( CustomerMapper.class );

    @Mapping(source = "customer.accounts", target = "idAccount")
    CustomerDto customerToCustomerDto(Customer customer);

    @Mapping(target ="idCustomer", ignore = true)
    @Mapping(source = "idAccount", target = "accounts")
    Customer customerDtoToCustomer(CustomerDto customerDto);

    /*@Named("fromAccountToAccountDTO")
    public static Set<AccountDto> fromAccountToAccountDTO(Set<Account> accounts) throws EntityNotFoundException {
        Set<AccountDto> accountDtos = new HashSet<>();
        for(Account account : accounts) {
            AccountDto accountDto = AccountMapper.INSTANCE.accountToAccountDto(account);
            accountDtos.add(accountDto);
        }
        return accountDtos;
    }*/
}
