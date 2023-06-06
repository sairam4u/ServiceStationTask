package com.epam.ServiceStation.exception;

public class PoliceVerificatioFailException extends RuntimeException {
    public PoliceVerificatioFailException(String regNo) {

        super(String.format("Police Verification failed for  %s ", regNo));
    }
}
