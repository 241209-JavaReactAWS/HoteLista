package com.revature.exceptions.booking;

public class BookingNotFound extends RuntimeException {

    public BookingNotFound(String message) {
        super(message);
    }
}
