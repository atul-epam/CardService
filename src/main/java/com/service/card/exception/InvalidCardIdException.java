package com.service.card.exception;

public class InvalidCardIdException extends RuntimeException {

    public InvalidCardIdException(String msg)
    {
        super(msg);
    }
}
