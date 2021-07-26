package com.example.my.little.bank.models;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idAccount;

    @NotNull
    private Long number;

    private Integer balance = 0;

    @OneToMany(mappedBy = "account")
    private Set<Transaction> transactions;

    /** many accounts have a single customer **/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_owner", referencedColumnName = "id")
    private Customer customer;
}