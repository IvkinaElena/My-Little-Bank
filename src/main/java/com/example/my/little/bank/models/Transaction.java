package com.example.my.little.bank.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idTransacrion;

    private Long idScore;

    @CreationTimestamp
    private Instant createdAt;

    private Integer operation;

    /** many transaction have a single account **/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idScore", referencedColumnName = "id", insertable = false, updatable = false)
    private Account mAccount;

}
