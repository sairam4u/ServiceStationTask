package com.epam.ServiceStation.entity;

import lombok.Data;

import java.util.Date;

@Data
public class InsuranceDetails {

    private String insuranceNumber;
    private Date insureExpireDetails;
}
