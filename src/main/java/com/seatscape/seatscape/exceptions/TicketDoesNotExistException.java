package com.seatscape.seatscape.exceptions;

public class TicketDoesNotExistException extends Exception{
    public TicketDoesNotExistException() {
        super();
    }

    public TicketDoesNotExistException(String message) {
        super(message);
    }

    public TicketDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public TicketDoesNotExistException(Throwable cause) {
        super(cause);
    }
}
