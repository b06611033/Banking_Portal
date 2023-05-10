package com.eBankingPortal.models;

import lombok.Getter;
import lombok.Setter;

//Use JPA and hibernate to handle data between Java and MySQL database
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

// Use lombok to create getter and setter easily
@Getter
@Setter
@Entity
@Table(name = "transactions")

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = " transactionId")
    private Long transactionId;

    @Column(name = "currency")
    private String currency;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "description")
    private String description;

    @Column(name = "transactionDate")
    private LocalDateTime transactionDate;

    // transaction belongs to account
    @Column(name = "IBAN")
    private String IBAN;

}
