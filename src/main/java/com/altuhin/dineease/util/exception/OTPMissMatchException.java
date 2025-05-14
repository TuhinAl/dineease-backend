package com.altuhin.dineease.util.exception;

public class OTPMissMatchException extends RuntimeException {
    public OTPMissMatchException(String message) {
        super(message);
    }

}
