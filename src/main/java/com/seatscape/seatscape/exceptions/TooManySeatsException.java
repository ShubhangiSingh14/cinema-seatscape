package com.seatscape.seatscape.exceptions;

public class TooManySeatsException extends Throwable {
    public TooManySeatsException() {
        super();
    }

    public TooManySeatsException(String message) {
        super(message);
    }

    public TooManySeatsException(String message, Throwable cause) {
        super(message, cause);
    }

    public TooManySeatsException(Throwable cause) {
        super(cause);
    }
}
