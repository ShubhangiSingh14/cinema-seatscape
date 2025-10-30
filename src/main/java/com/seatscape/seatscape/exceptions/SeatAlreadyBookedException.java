package com.seatscape.seatscape.exceptions;

public class SeatAlreadyBookedException extends Exception{
    public SeatAlreadyBookedException() {
        super();
    }

    public SeatAlreadyBookedException(String message) {
        super(message);
    }

    public SeatAlreadyBookedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeatAlreadyBookedException(Throwable cause) {
        super(cause);
    }
}
