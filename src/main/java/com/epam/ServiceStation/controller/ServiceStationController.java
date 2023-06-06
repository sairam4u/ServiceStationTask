package com.epam.ServiceStation.controller;

import com.epam.ServiceStation.entity.VehicleDTO;
import com.epam.ServiceStation.exception.RegistrationExpiredException;
import com.epam.ServiceStation.entity.ServicingDetails;
import com.epam.ServiceStation.service.ServiceStationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.epam.ServiceStation.service.ServiceStationServiceImpl;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController(value = "/rest")
public class ServiceStationController {


    @Autowired
    ServiceStationService service;

    @ApiOperation(value = "Takes Vehicle and gives delivery date",
            response = ServicingDetails.class,
            notes = "Reg Number must exist")
    @PostMapping(value = "/vehicleService", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServicingDetails> doServicing(@RequestBody @Valid VehicleDTO vehicle){
        if(vehicle.getExpiredate().before(new Date())){
            throw  new RegistrationExpiredException(vehicle.getRegistrationNumber());
        }
        return new ResponseEntity<>(service.checkServicing(vehicle), HttpStatus.CREATED);
    }

    @ApiOperation(value = "To Check Service History of Particular Vehicle",
            response = ServicingDetails.class,
            notes = "Reg Number must provide")
    @GetMapping(value = "/serviceHistory/{regNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ServicingDetails>>serviceHistory(@PathVariable(value = "regNumber")  String registratioNo){

        return new ResponseEntity<>(service.serviceHistory(registratioNo), HttpStatus.OK);
    }
}
