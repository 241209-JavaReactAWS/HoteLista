package com.revature.exceptions;

import com.revature.exceptions.booking.BookingException;
import com.revature.exceptions.booking.BookingNotCreated;
import com.revature.exceptions.booking.BookingNotFound;
import com.revature.exceptions.payment.PaymentNotCreated;
import com.revature.exceptions.payment.PaymentNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PaymentNotFound.class)
    public ResponseEntity<ErrorResponse> handlePaymentNotFound(PaymentNotFound ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PaymentNotCreated.class)
    public ResponseEntity<ErrorResponse> handlePaymentNotCreated(PaymentNotCreated ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookingNotFound.class)
    public ResponseEntity<ErrorResponse> handleBookingNotFound(BookingNotFound ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookingNotCreated.class)
    public ResponseEntity<ErrorResponse> handleBookingNotCreated(BookingNotCreated ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookingException.class)
    public ResponseEntity<ErrorResponse> handleBookingException(BookingException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);

    }
}

