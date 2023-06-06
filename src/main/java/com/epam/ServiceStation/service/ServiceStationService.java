package com.epam.ServiceStation.service;

import com.epam.ServiceStation.entity.ServicingDetails;
import com.epam.ServiceStation.entity.VehicleDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServiceStationService {

    public ServicingDetails checkServicing(VehicleDTO vehicle);

    public List<ServicingDetails> serviceHistory(String registrationNo);
}
