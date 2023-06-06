package com.epam.ServiceStation.service;

public class RegistrationExpiredException extends RuntimeException {
    public RegistrationExpiredException(String regNo) {

        super(String.format("Vehicle with Id %d expired", regNo));
    }
}
