package com.eBankingPortal.models;

import lombok.Getter;
import lombok.Setter;

//Use JPA and hibernate to handle data between Java and MySQL database
import jakarta.persistence.*;
import java.time.LocalDateTime;

// Use lombok to create getter and setter easily
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
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
    private LocalDateTime createDate;

}
