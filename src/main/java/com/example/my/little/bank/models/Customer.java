package com.example.my.little.bank.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @NotBlank(message = "Firstname is mandatory")
    @Size(max=256)
    private String firstname;

    @Size(max=256)
    @NotBlank(message = "Lastname is mandatory")
    private String lastname;

    @Size(max=256)
    @NotBlank(message = "Middlename is mandatory")
    private String middlename;

    @OneToMany(mappedBy = "customer")
    private Set<Account> accounts;

}
