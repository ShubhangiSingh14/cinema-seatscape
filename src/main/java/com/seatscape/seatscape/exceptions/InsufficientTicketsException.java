package com.seatscape.seatscape.exceptions;

public class InsufficientTicketsException extends Exception {

    public InsufficientTicketsException() {
        super();
    }

    public InsufficientTicketsException(String message) {
        super(message);
    }

    public InsufficientTicketsException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsufficientTicketsException(Throwable cause) {
        super(cause);
    }
}
