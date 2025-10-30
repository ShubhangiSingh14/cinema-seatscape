package com.seatscape.seatscape.exceptions;

public class CountOfSeatsZero extends Exception {

    public CountOfSeatsZero() {
        super();
    }

    public CountOfSeatsZero(String message) {
        super(message);
    }

    public CountOfSeatsZero(String message, Throwable cause) {
        super(message, cause);
    }

    public CountOfSeatsZero(Throwable cause) {
        super(cause);
    }
}
