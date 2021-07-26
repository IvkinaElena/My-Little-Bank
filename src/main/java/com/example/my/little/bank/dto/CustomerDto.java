package com.example.my.little.bank.dto;


import lombok.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
    private Long idCustomer;
    private String firstname;
    private String lastname;
    private String middlename;
    private Set<AccountDto> idAccount;
}
