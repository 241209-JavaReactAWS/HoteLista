package com.revature.controllers;

import com.revature.models.Payment;
import com.revature.requests.PaymentDto;
import com.revature.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/add")
    public ResponseEntity<PaymentDto> createPaymentMethod(@RequestBody Payment cardToBeCreated, @RequestParam Integer userId) {
        try {
            PaymentDto newPayment = paymentService.addPayment(cardToBeCreated, userId);
            if (newPayment == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            return new ResponseEntity(newPayment, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}

