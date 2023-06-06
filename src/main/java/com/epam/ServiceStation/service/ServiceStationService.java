package com.epam.ServiceStation.service;

import com.epam.ServiceStation.entity.ServicingDetails;
import com.epam.ServiceStation.entity.Vehicle;
import org.springframework.stereotype.Service;

@Service
public interface ServiceStationService {

    public ServicingDetails checkServicing(Vehicle vehicle);
}
