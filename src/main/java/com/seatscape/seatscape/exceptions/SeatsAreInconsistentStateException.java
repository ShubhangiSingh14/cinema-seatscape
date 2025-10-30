package com.seatscape.seatscape.exceptions;

public class SeatsAreInconsistentStateException extends Exception {
    public SeatsAreInconsistentStateException() {
        super();
    }

    public SeatsAreInconsistentStateException(String message) {
        super(message);
    }

    public SeatsAreInconsistentStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeatsAreInconsistentStateException(Throwable cause) {
        super(cause);
    }
}
