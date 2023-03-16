package com.eBankingPortal.entity;

import lombok.Getter;
import lombok.Setter;

//Use JPA and hibernate to handle data between Java and MySQL database
import jakarta.persistence.*;
import java.util.List;
import java.time.LocalDate;

// Use lombok to create getter and setter easily
@Getter
@Setter
@Entity
@Table(name = "users")
public class Customer {
    // primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "created_date")
    private LocalDate createDate;

    // User has 1 or more accounts
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Account> accounts;
}
