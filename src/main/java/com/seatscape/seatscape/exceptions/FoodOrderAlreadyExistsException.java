package com.seatscape.seatscape.exceptions;

public class FoodOrderAlreadyExistsException extends Exception{
    public FoodOrderAlreadyExistsException() {
        super();
    }

    public FoodOrderAlreadyExistsException(String message) {
        super(message);
    }

    public FoodOrderAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public FoodOrderAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
