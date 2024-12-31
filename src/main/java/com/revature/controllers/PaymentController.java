package com.revature.controllers;

import com.revature.models.Payment;
import com.revature.requests.PaymentDTO;
import com.revature.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
// TODO: Add CrossOrign with credetials and modify endpoints accordingly
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/add")
    public ResponseEntity<PaymentDTO> createPaymentMethod(@RequestBody Payment cardToBeCreated, @RequestParam Integer accountId) {

        //TODO: USE httpsession instead of userID

        try {
            PaymentDTO newPayment = new PaymentDTO(paymentService.addPayment(cardToBeCreated, accountId));
            return ResponseEntity.status(HttpStatus.CREATED).body(newPayment);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}

