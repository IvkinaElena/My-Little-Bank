package com.example.my.little.bank.dto;


import lombok.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDto {
    private Instant createdAt;
    private Integer operation;
    private Long idAccount;
}
