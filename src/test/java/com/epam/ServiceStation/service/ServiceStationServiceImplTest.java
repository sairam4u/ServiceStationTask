package com.epam.ServiceStation.service;

import com.epam.ServiceStation.client.PoliceApi;
import com.epam.ServiceStation.entity.ServicingDetails;
import com.epam.ServiceStation.entity.TheftVehicleEntity;
import com.epam.ServiceStation.entity.VehicleDTO;
import com.epam.ServiceStation.entity.VehicleType;
import com.epam.ServiceStation.exception.PoliceVerificatioFailException;
import com.epam.ServiceStation.repository.ServicingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServiceStationServiceImplTest {

    @Mock
    private PoliceApi policeApi;

    @InjectMocks
    ServiceStationServiceImpl service;

    @Mock
    ServicingRepository repository;


    @Test
    void testCheckServicing() {

        TheftVehicleEntity entity =  new TheftVehicleEntity("ap23", "44342", "no");;


        when(policeApi.verifyVehicle("ap23","5432")).thenReturn(new ResponseEntity<>(entity, HttpStatus.OK));

        VehicleDTO vehicle = new VehicleDTO();
        LocalDate today = LocalDate.now();
        vehicle.setExpiredate(Date.from(today.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        vehicle.setOwnerName("Dummy  Name");
        vehicle.setRegistrationNumber("ap23");
        vehicle.setChassisNumber("5432");
        vehicle.setVehicleName("Dummy Vehicle Name");
        vehicle.setVehicleType(VehicleType.CAR);

        when(repository.save(any())).thenReturn(new ServicingDetails());
        service.checkServicing(vehicle);
    }

    @Test
    void testCheckServicing_withPoliceIssue() {

        TheftVehicleEntity entity =  new TheftVehicleEntity("ts36", "44342", "yes");;


        when(policeApi.verifyVehicle("ts36","44342")).thenReturn(new ResponseEntity<>(entity, HttpStatus.OK));

        VehicleDTO vehicle = new VehicleDTO();
        LocalDate today = LocalDate.now();
        vehicle.setExpiredate(Date.from(today.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        vehicle.setOwnerName("Dummy  Name");
        vehicle.setRegistrationNumber("ts36");
        vehicle.setChassisNumber("44342");
        vehicle.setVehicleName("Dummy Vehicle Name");
        vehicle.setVehicleType(VehicleType.BIKE);

       try {

           service.checkServicing(vehicle);
       }catch (Exception e){
           assertEquals("Police Verification failed for  ts36 ",e.getMessage());
       }
    }

}