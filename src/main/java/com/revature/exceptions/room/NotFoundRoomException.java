package com.revature.exceptions.room;

public class NotFoundRoomException extends RuntimeException {
    public NotFoundRoomException(String message) {
        super(message);
    }
}
