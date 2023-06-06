package com.epam.ServiceStation.service;

public class InsuranceIssueException extends RuntimeException {
    public InsuranceIssueException(String regNo) {

        super(String.format("Vehicle Insurance with Id %d expired", regNo));
    }
}
