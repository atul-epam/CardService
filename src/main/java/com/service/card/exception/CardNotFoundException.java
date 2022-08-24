package com.service.card.exception;

public class CardNotFoundException extends RuntimeException {

    public CardNotFoundException(String msg)
    {
        super(msg);
    }
}
