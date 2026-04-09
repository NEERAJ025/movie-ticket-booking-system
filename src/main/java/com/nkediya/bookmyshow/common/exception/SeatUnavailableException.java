package com.nkediya.bookmyshow.common.exception;

public class SeatUnavailableException extends  RuntimeException {
    public SeatUnavailableException(String message) {
        super(message);
    }
}
