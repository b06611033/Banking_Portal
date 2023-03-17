package com.eBankingPortal.models;

import lombok.Getter;
import lombok.Setter;

//Use JPA and hibernate to handle data between Java and MySQL database
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

// Use lombok to create getter and setter easily
@Getter
@Setter
@Entity
@Table(name = "transactions")

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = " transaction_id")
    private Long transaction_id;

    @Column(name = "IBAN")
    private String IBAN;

    @Column(name = "currency")
    private String currency;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "type")
    private String type;

    @Column(name = "transactionDate")
    private LocalDate transactionDate;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
