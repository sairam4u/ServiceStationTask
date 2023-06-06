package com.epam.ServiceStation.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String registrationNumber;

    private String chassisNumber;

    private String vehicleName;

    private String ownerName;

    private Date expiredate;

    private VehicleType vehicleType;

}
