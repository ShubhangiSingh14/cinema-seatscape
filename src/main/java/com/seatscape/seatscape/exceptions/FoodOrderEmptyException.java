package com.seatscape.seatscape.exceptions;

public class FoodOrderEmptyException extends Exception {
    public FoodOrderEmptyException() {
        super();
    }

    public FoodOrderEmptyException(String message) {
        super(message);
    }

    public FoodOrderEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public FoodOrderEmptyException(Throwable cause) {
        super(cause);
    }

}
