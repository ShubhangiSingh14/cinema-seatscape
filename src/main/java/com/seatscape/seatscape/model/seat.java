package com.seatscape.seatscape.model;

import org.hibernate.validator.constraints.CreditCardNumber;

public class seat {
    @CreditCardNumber
    private Integer cardNo;
}
