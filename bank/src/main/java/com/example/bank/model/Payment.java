package com.example.bank.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Account sourceAccount;

    @ManyToOne
    private Account targetAccount;

    private BigDecimal amount;
    private LocalDateTime timestamp;

    // Getters and Setters
}