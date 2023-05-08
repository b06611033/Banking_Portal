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
@Table(name = "accounts")

public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = " accountId")
    private Long accountId;

    @Column(name = " IBAN")
    private String IBAN;

    @Column(name = "currency")
    private String currency;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "created_date")
    private LocalDateTime createDate;

    // account belongs to user
    @Column(name = "userName")
    private String userName;

}
