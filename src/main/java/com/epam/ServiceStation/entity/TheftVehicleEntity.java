package com.epam.ServiceStation.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
public class TheftVehicleEntity {

    private String vehicleNmbr;

    private String chassisNmbr;

    private String isTheft;

}

