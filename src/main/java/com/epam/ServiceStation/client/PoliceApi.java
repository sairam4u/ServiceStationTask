
package com.epam.ServiceStation.client;

import com.epam.ServiceStation.entity.TheftVehicleEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "policeapi", url = "http://localhost:8082/")
public interface PoliceApi {

    @GetMapping(value = "/verifyVehicle/{regNumber}/{chassisNumber}")
    public ResponseEntity<TheftVehicleEntity> verifyVehicle(@PathVariable(value = "regNumber") String regNumber, @PathVariable(value = "chassisNumber") String chassisNumber);

}

