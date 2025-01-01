package com.revature.controllers;

import com.revature.exceptions.payment.PaymentNotFound;
import com.revature.models.Payment;
import com.revature.requests.PaymentDTO;
import com.revature.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@CrossOrigin
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
            PaymentDTO payment = paymentService.addPayment(cardToBeCreated, accountId);
            return ResponseEntity.status(HttpStatus.CREATED).body(payment);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/delete/{accountId}/{paymentId}")
    public ResponseEntity<String> deletePaymentMethod(@PathVariable Integer accountId, @PathVariable Integer paymentId) {
        try {
            String deleteMessage = paymentService.deletePayment(accountId, paymentId);
            return ResponseEntity.status(HttpStatus.OK).body(deleteMessage);
        } catch (Exception e) {
            throw new PaymentNotFound("PAYMENT ID NOT FOUND");

        }
    }





}

