package com.example.my.little.bank.dto;

import com.example.my.little.bank.models.Transaction;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {
    private Long idAccount;
    private Long number;
    private Integer balance;
    private Set<TransactionDto> transactions;
    private Long idCustomer;

}
