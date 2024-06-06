package com.example.bank.model;

import javax.persistence.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String document; // CPF or CNPJ

    private String address;
    private String password;

    // Getters and Setters
}