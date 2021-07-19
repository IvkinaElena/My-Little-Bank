package com.example.my.little.bank.models;

import lombok.*;
import javax.persistence.*;
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

    private String number;

    private Long idOwner;

    private Integer balance;

    @OneToMany(mappedBy = "mAccount")
    private Set<Transaction> transactions;

    /** many accounts have a single customer **/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idOwner", referencedColumnName = "id", insertable = false, updatable = false)
    private Customer mCustomer;
}