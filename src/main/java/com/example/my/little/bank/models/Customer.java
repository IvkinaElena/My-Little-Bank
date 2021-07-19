package com.example.my.little.bank.models;

import javax.persistence.*;
import java.util.Set;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idCustomer;

    private String firstname;

    private String lastname;

    private String middlename;

    @OneToMany(mappedBy = "mCustomer")
    private Set<Account> accounts;

}
