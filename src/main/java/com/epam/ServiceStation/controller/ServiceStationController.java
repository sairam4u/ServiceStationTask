package com.epam.ServiceStation.controller;

import com.epam.ServiceStation.service.RegistrationExpiredException;
import com.epam.ServiceStation.entity.ServicingDetails;
import com.epam.ServiceStation.entity.Vehicle;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.epam.ServiceStation.service.ServiceStationServiceImpl;

import java.util.Date;

@RestController(value = "/rest")
public class ServiceStationController {


    @Autowired
    ServiceStationServiceImpl service;

    @ApiOperation(value = "Takes Vehicle and gives delivery date",
            response = ServicingDetails.class,
            notes = "Reg Number must exist")
    @GetMapping(value = "/vehicleService", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServicingDetails> doServicing(@RequestBody Vehicle vehicle){
        if(vehicle.getExpiredate().before(new Date())){
            throw  new RegistrationExpiredException(vehicle.getRegistrationNumber());
        }
        return new ResponseEntity<>(service.checkServicing(vehicle), HttpStatus.OK);
    }
}
