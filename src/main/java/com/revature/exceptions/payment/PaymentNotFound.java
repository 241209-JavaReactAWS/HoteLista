package com.revature.exceptions.payment;

public class PaymentNotFound extends RuntimeException {

    public PaymentNotFound(String message) {
        super(message);
    }
}
