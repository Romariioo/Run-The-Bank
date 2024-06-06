package com.example.bank;

import com.example.bank.service.PaymentService;
import com.example.bank.dto.PaymentDTO;
import com.example.bank.model.Payment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @Test
    public void testCreatePayment() {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setSourceAccountId(1L);
        paymentDTO.setTargetAccountId(2L);
        paymentDTO.setAmount(BigDecimal.valueOf(100));

        Payment payment = paymentService.createPayment(paymentDTO);
        assertNotNull(payment);
        assertEquals(BigDecimal.valueOf(100), payment.getAmount());
    }
}