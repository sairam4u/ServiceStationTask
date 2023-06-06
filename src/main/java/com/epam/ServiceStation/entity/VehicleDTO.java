package com.epam.ServiceStation.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
public class VehicleDTO {


    @NotBlank(message = "vehicle number is mandatory")
    @NotNull(message = "vehicle number is mandatory")
    private String registrationNumber;

    @NotBlank(message = "chassisNumber  is mandatory")
    @NotNull(message = "chassisNumber  is mandatory")
    private String chassisNumber;

    private String vehicleName;

    private String ownerName;

    private Date expiredate;

    private VehicleType vehicleType;

}
