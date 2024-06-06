package com.example.bank.service;

import com.example.bank.model.Payment;
import com.example.bank.model.Account;
import com.example.bank.repository.PaymentRepository;
import com.example.bank.repository.AccountRepository;
import com.example.bank.dto.PaymentDTO;
import com.example.bank.exception.ResourceNotFoundException;
import com.example.bank.exception.InsufficientFundsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private AccountService accountService;

    public Payment createPayment(PaymentDTO paymentDTO) {
        Account sourceAccount = accountService.getAccountById(paymentDTO.getSourceAccountId());
        Account targetAccount = accountService.getAccountById(paymentDTO.getTargetAccountId());

        if (!sourceAccount.getActive() || !targetAccount.getActive()) {
            throw new RuntimeException("Account is inactive");
        }

        if (sourceAccount.getBalance().compareTo(paymentDTO.getAmount()) < 0) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        sourceAccount.setBalance(sourceAccount.getBalance().subtract(paymentDTO.getAmount()));
        targetAccount.setBalance(targetAccount.getBalance().add(paymentDTO.getAmount()));

        Payment payment = new Payment();
        payment.setSourceAccount(sourceAccount);
        payment.setTargetAccount(targetAccount);
        payment.setAmount(paymentDTO.getAmount());
        payment.setTimestamp(LocalDateTime.now());

        paymentRepository.save(payment);

        notifyCustomers(sourceAccount, targetAccount);

        return payment;
    }

    public void reversePayment(PaymentDTO paymentDTO) {
        Payment payment = paymentRepository.findById(paymentDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Payment not found"));

        Account sourceAccount = payment.getSourceAccount();
        Account targetAccount = payment.getTargetAccount();

        sourceAccount.setBalance(sourceAccount.getBalance().add(payment.getAmount()));
        targetAccount.setBalance(targetAccount.getBalance().subtract(payment.getAmount()));

        paymentRepository.delete(payment);

        notifyCustomers(sourceAccount, targetAccount);
    }

    private void notifyCustomers(Account sourceAccount, Account targetAccount) {
        // Call external service for notification
        // If the service is down, log and proceed
    }
}