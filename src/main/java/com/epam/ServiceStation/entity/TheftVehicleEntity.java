package com.epam.ServiceStation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
public class TheftVehicleEntity {

    private String vehicleNmbr;

    private String chassisNmbr;

    private String isTheft;

}

