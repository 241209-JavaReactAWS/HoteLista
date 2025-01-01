package com.revature.exceptions.payment;

public class PaymentNotCreated extends RuntimeException {

    public PaymentNotCreated(String message) {
        super(message);
    }
}
