package com.example.bank.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String agency;

    private BigDecimal balance;
    private Boolean active;

    @ManyToOne
    private Customer customer;

    // Getters and Setters
}