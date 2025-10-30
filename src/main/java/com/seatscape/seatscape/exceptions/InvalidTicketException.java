package com.seatscape.seatscape.exceptions;

public class InvalidTicketException extends Exception{
    public InvalidTicketException() {
        super();
    }

    public InvalidTicketException(String message) {
        super(message);
    }

    public InvalidTicketException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidTicketException(Throwable cause) {
        super(cause);
    }
}
