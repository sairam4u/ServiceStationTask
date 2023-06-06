/*
package com.epam.ServiceStation.client;

import com.epam.ServiceStation.entity.InsuranceDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import javax.websocket.server.PathParam;

@FeignClient(value = "insuranceApi", url="http://localhost:8083/v1")
public interface InsuranceApi {

    @GetMapping("/getInsuranceDetails/{vehicleNumber}")
    public ResponseEntity<InsuranceDetails> getInsureDetails(@PathParam("vehicleNumber") String regNumber);
}
*/
