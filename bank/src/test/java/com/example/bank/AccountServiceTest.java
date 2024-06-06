package com.example.bank;

import com.example.bank.service.AccountService;
import com.example.bank.dto.AccountDTO;
import com.example.bank.model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void testCreateAccount() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAgency("0001");
        accountDTO.setBalance(BigDecimal.valueOf(1000));
        accountDTO.setActive(true);

        Account account = accountService.createAccount(accountDTO);
        assertNotNull(account);
        assertEquals("0001", account.getAgency());
    }
}