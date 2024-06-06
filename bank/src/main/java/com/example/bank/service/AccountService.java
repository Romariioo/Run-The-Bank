package com.example.bank.service;

import com.example.bank.model.Account;
import com.example.bank.repository.AccountRepository;
import com.example.bank.dto.AccountDTO;
import com.example.bank.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(AccountDTO accountDTO) {
        if (accountRepository.existsByAgency(accountDTO.getAgency())) {
            throw new RuntimeException("Agency already exists");
        }
        Account account = new Account();
        account.setAgency(accountDTO.getAgency());
        account.setBalance(accountDTO.getBalance());
        account.setActive(accountDTO.getActive());
        // Set customer
        // account.setCustomer(...);
        return accountRepository.save(account);
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account not found"));
    }
}