package com.seatscape.seatscape.exceptions;

public class HouseFullException extends Exception {

    public HouseFullException() {
        super();
    }

    public HouseFullException(String message) {
        super(message);
    }

    public HouseFullException(String message, Throwable cause) {
        super(message, cause);
    }

    public HouseFullException(Throwable cause) {
        super(cause);
    }
}
