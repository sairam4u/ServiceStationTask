package com.epam.ServiceStation.entity;

import io.swagger.models.auth.In;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@Table(name = "servicing_details")
public class ServicingDetails {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Integer service_number;

        @Column(name = "vehicle_number")
        private String vehicleNumber;

        private String vehicle_servicenumber;

        private Date service_in_date;

        private Date service_delivery_date;


        private String chassisNumber;

}
