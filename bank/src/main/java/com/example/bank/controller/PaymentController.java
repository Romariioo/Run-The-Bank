package com.example.bank.controller;

import com.example.bank.model.Payment;
import com.example.bank.service.PaymentService;
import com.example.bank.dto.PaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody PaymentDTO paymentDTO) {
        Payment payment = paymentService.createPayment(paymentDTO);
        return new ResponseEntity<>(payment, HttpStatus.CREATED);
    }

    @PostMapping("/reverse")
    public ResponseEntity<Void> reversePayment(@RequestBody PaymentDTO paymentDTO) {
        paymentService.reversePayment(paymentDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}