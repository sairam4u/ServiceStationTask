package com.epam.ServiceStation.exception;

public class InsuranceIssueException extends RuntimeException {
    public InsuranceIssueException(String regNo) {

        super(String.format("Vehicle Insurance with Id %s expired", regNo));
    }
}
